package com.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.model.User;
import com.boot.persistence.UserDao;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public User findById(long id) {
		User user = null;
		user = userDao.findById(id);
		return user;
	}

	@Override
	public User findByName(String name) {
		User user = null;
		user = userDao.findByName(name);
		return user;
	}

	@Override
	public void saveUser(User user) {
		userDao.saveUser(user);
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public void deleteUserById(long id) {
		userDao.deleteUserById(id);
	}

	@Override
	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}

	@Override
	public void deleteAllUsers() {
		userDao.deleteAllUsers();

	}

	@Override
	public boolean isUserExist(User user) {
		return userDao.isUserExist(user);
	}

}
