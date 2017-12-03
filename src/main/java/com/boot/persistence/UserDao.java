package com.boot.persistence;

import java.util.List;

import com.boot.model.User;

public interface UserDao {
	User findById(long id);

	User findByName(String name);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUserById(long id);

	List<User> findAllUsers();

	void deleteAllUsers();

	boolean isUserExist(User user);

}
