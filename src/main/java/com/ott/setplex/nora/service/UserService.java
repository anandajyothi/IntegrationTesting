package com.ott.setplex.nora.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ott.setplex.nora.model.User;
import com.ott.setplex.nora.repository.UserRepository;
import com.ott.setplex.nora.repository.UserRepositoryImpl;

@Transactional
@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserRepositoryImpl userRepoImpl;

	public Iterable<User> userListing() {
		return userRepo.findAll();
	}

	public <Optional> User userById(Integer id) {
		return userRepo.findById(id).isPresent() ? userRepo.findById(id).get() : null;
	}

	public User saveUser(User user) {
		return userRepo.save(user);
	}

	public void deleteUser(Integer id) {
		userRepo.deleteById(id);
	}

	public List<User> getUserData(String username, String devicetype) {
		return userRepoImpl.getUserByUserNameByDeviceType(username, devicetype);
	}

}