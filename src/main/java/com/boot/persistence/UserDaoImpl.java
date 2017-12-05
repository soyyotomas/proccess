package com.boot.persistence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

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
import com.mongodb.client.model.Updates;

@Repository
public class UserDaoImpl implements UserDao {
	private static final Logger objLog = Logger.getLogger(UserDaoImpl.class);
	private static final AtomicLong counter = new AtomicLong();
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
		Iterator itup = cursor3.iterator();

		while (itup.hasNext()) {
			Document userDoc = (Document) itup.next();
			user = new User();
			user.setAge(userDoc.getInteger("id", 0));
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
		Iterator itup = cursor3.iterator();

		while (itup.hasNext()) {
			Document userDoc = (Document) itup.next();
			user = new User();
			user.setAge(userDoc.getInteger("id", 0));
		}
		return user;
	}

	@Override
	public void saveUser(User user) {
		objLog.info("------ SAVE USER ----------------");
		Document document = new Document("id", counter.incrementAndGet()).append("name", user.getName())
				.append("age", user.getAge()).append("salary", user.getSalary());
		collection.insertOne(document);
	}

	@Override
	public void updateUser(User user) {
		objLog.info("------ UPDATE USER ----------------");
		collection.updateOne(Filters.eq("id", user.getId()), Updates.set("name", user.getName()));
		System.out.println("Document updated successfully");

	}

	@Override
	public void deleteUserById(long id) {
		objLog.info("------ DELETE USER BY ID ----------------");
		collection.deleteOne(Filters.eq("id", 5));
		System.out.println("Document deleted successfully");
	}

	@Override
	public List<User> findAllUsers() {
		objLog.info("------ LIST ALL USER ----------------");
		List<User> listUser = new ArrayList<User>();
		// Getting the iterate object
		FindIterable<Document> iterUsers = collection.find();
		Iterator it = iterUsers.iterator();

		while (it.hasNext()) {
			User user = (User) it.next();
			listUser.add(user);
		}
		return listUser;
	}

	@Override
	public void deleteAllUsers() {
		objLog.info("------ DELETING ALL USER ----------------");
		((Document) collection).remove(new BasicDBObject());

	}

	@Override
	public boolean isUserExist(User user) {
		objLog.info("------ IS USER EXIST ----------------");
		return (findById(user.getId()) == null) ? false : true;
	}

}
