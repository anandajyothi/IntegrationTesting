package com.ott.setplex.nora.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ott.setplex.nora.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>, UserRepositoryCustom {

}
