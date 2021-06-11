package com.ott.setplex.nora.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import com.ott.setplex.nora.model.User;
import javax.persistence.Query;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<User> getUserByUserNameByDeviceType(String username, String devicetype) {

		Query q = em
				.createQuery(
						"SELECT user FROM User user WHERE user.username = :username AND user.devicetype = :devicetype")
				.setParameter("username", username).setParameter("devicetype", devicetype);

		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) q.getResultList();
		return users;
	}
}
