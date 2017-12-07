package com.boot.persistence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import com.boot.model.User;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

@Repository
public class UserDaoImpl implements UserDao {
	private static final Logger objLog = Logger.getLogger(UserDaoImpl.class);
	public static final String URL = "localhost";

	public MongoClient connection = ConexionMongoDB.getConnection(URL);
	public MongoDatabase dataBase = connection.getDatabase("users");
	public MongoCollection<Document> collection = dataBase.getCollection("users");

	@Override
	public User findById(long id) {
		objLog.info("------ FIND BY ID ----------------");
		User user = null;
		// Retrieving a collection

		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("id", id);
		FindIterable<Document> cursor3 = collection.find(whereQuery);
		// Getting the iterator
		Iterator<Document> itup = cursor3.iterator();

		while (itup.hasNext()) {
			Document userDoc = itup.next();
			user = new User();
			user.setId(userDoc.getLong("id"));
			user.setAge(userDoc.getInteger("age"));
			user.setName(userDoc.getString("name"));
			user.setSalary(userDoc.getDouble("salary"));
		}
		return user;
	}

	@Override
	public User findByName(String name) {
		objLog.info("------ FIND BY NAME ----------------");
		User user = null;
		// Retrieving a collection
		MongoCollection<Document> collection = dataBase.getCollection("users");
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("name", name);
		FindIterable<Document> cursor3 = collection.find(whereQuery);
		// Getting the iterator
		Iterator<Document> itup = cursor3.iterator();

		while (itup.hasNext()) {
			Document userDoc = (Document) itup.next();
			user = new User();
			user.setId(userDoc.getLong("id"));
			user.setAge(userDoc.getInteger("age"));
			user.setName(userDoc.getString("name"));
			user.setSalary(userDoc.getDouble("salary"));
		}
		return user;
	}

	@Override
	public void saveUser(User user) {
		objLog.info("------ SAVE USER ----------------");
		Document document = new Document("id", user.getId()).append("name", user.getName()).append("age", user.getAge())
				.append("salary", user.getSalary());
		collection.insertOne(document);
	}

	@Override
	public void updateUser(User user) {
		objLog.info("------ UPDATE USER ----------------");
		Document documentUpdate = new Document("id", user.getId()).append("name", user.getName())
				.append("age", user.getAge()).append("salary", user.getSalary());
		collection.replaceOne(Filters.eq("id", user.getId()), documentUpdate);
		System.out.println("Document updated successfully");

	}

	@Override
	public void deleteUserById(long id) {
		objLog.info("------ DELETE USER BY ID ----------------");
		collection.deleteOne(Filters.eq("id", id));
		System.out.println("Document deleted successfully");
	}

	@Override
	public List<User> findAllUsers() {
		objLog.info("------ LIST ALL USER ----------------");
		List<User> listUser = new ArrayList<User>();
		// Getting the iterate object
		FindIterable<Document> iterUsers = collection.find();
		Iterator<Document> it = iterUsers.iterator();

		while (it.hasNext()) {
			Document documentUser = (Document) it.next();
			User user = new User();

			user.setId(documentUser.getLong("id"));
			user.setName(documentUser.getString("name"));
			user.setAge(documentUser.getInteger("age", 18));
			user.setSalary(documentUser.getDouble("salary"));

			listUser.add(user);
		}
		return listUser;
	}

	@Override
	public void deleteAllUsers() {
		objLog.info("------ DELETING ALL USER ----------------");
		FindIterable<Document> iterUsers = collection.find();
		Iterator<Document> it = iterUsers.iterator();

		while (it.hasNext()) {
			Document documentUser = (Document) it.next();
			User user = new User();

			user.setId(documentUser.getLong("id"));
			collection.deleteOne(Filters.eq("id", user.getId()));
		}

	}

	@Override
	public boolean isUserExist(User user) {
		objLog.info("------ IS USER EXIST ----------------");
		return (findById(user.getId()) == null) ? false : true;
	}

}
