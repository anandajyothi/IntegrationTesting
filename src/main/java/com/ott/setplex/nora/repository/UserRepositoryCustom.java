package com.ott.setplex.nora.repository;

import java.util.List;
import com.ott.setplex.nora.model.User;

public interface UserRepositoryCustom {
	List<User> getUserByUserNameByDeviceType(String username, String devicetype);
}
