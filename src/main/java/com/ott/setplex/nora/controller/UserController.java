package com.ott.setplex.nora.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ott.setplex.nora.config.ConfigProperties;
import com.ott.setplex.nora.exception.NoDataFoundException;
import com.ott.setplex.nora.model.Event;
import com.ott.setplex.nora.model.User;
import com.ott.setplex.nora.service.EventService;
import com.ott.setplex.nora.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	EventService eventService;

	@Autowired
	ConfigProperties config;

	public UserController() {
	}

	@GetMapping("/users")
	public Iterable<User> list() {
		Iterable<User> users = userService.userListing();
		return users;
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> get(@PathVariable Integer id) {

		User user = userService.userById(id);

		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			throw new NoDataFoundException();
		}
	}

	@PostMapping("/users")
	public User add(@RequestBody User user) {

		User newUser = userService.saveUser(user);

		Event event = new Event();

		event.setUserid(newUser.getUserid());
		event.setUsername(user.getUsername());
		event.setEventtype(config.getTxtLogin());
		event.setEventsubscription(config.getTxtLoginSuccess());

		eventService.saveEvent(event);
	
		return newUser;
		
	}

	@PostMapping("/login")
	public String login(@RequestBody User user) {

		String result = null;
		String eventtype = null;
		String eventdescription = null;

		List<User> users = userService.getUserData(user.getUsername(), user.getDevicetype());

		if (false == users.isEmpty()) {

			result = config.getTxtWelcome();
			eventtype = config.getTxtLogin();
			eventdescription = config.getTxtLoginSuccess();

			User objUser = new User();

			objUser.setUserid(users.get(0).getUserid());
			objUser.setCreatedAt(users.get(0).getCreatedAt());
			objUser.setDevicetype(users.get(0).getDevicetype());
			objUser.setSubscription(users.get(0).isSubscription());
			objUser.setUsername(users.get(0).getUsername());

			userService.saveUser(objUser);

		} else {
			result = config.getTxtFailWelcome();
			eventtype = config.getTxtFailLogin();
			eventdescription = config.getTxtFailLoginDesc();
		}

		Event event = new Event();
		if (false == users.isEmpty()) {
			event.setUserid(users.get(0).getUserid());
		}

		event.setUsername(user.getUsername());
		event.setEventtype(eventtype);
		event.setEventsubscription(eventdescription);
		eventService.saveEvent(event);

		return result;

	}

	@PutMapping("/users/{id}")
	public ResponseEntity<?> update(@RequestBody User user, @PathVariable Integer id) {

		User userData = userService.userById(id);

		if (userData != null) {
			user.setUserid(userData.getUserid());
		}

		userService.saveUser(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/users/{id}")
	public String delete(@PathVariable Integer id) {
		userService.deleteUser(id);
		
		return "Record deleted";
	}

}
