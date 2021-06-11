package com.ott.setplex.nora.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ott.setplex.nora.model.Event;
import com.ott.setplex.nora.repository.EventRepository;

@Service
@Transactional
public class EventService {

	@Autowired
	private EventRepository eventRepo;

	public Iterable<Event> eventListing() {
		return eventRepo.findAll();
	}

	public Event eventById(Integer id) {
		return eventRepo.findById(id).get();
	}

	public void saveEvent(Event event) {
		eventRepo.save(event);
	}

	public void deleteEvent(Integer id) {
		eventRepo.deleteById(id);
	}

}
