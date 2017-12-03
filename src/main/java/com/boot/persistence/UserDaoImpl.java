package com.boot.persistence;

import java.util.List;

import com.boot.model.User;
import com.mongodb.MongoClient;

public class UserDaoImpl implements UserDao {

	public static final String URL = "localhost";
	
	public MongoClient connection = ConexionMongoDB.getConnection(URL);
	@Override
	public User findById(long id) {
		
		return null;
	}

	@Override
	public User findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUserById(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllUsers() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isUserExist(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
