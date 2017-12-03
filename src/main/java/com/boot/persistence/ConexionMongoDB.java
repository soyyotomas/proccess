package com.boot.persistence;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;

public class ConexionMongoDB {

	public static MongoClient mongo;
	public static MongoCredential mongoCredential;
	public static final int PORT = 27017;

	public static MongoClient getConnection(String url) {
		if (mongo == null) {
			mongo = new MongoClient(url, PORT);
			mongoCredential = MongoCredential.createCredential("momo", "users", "federer1".toCharArray());
		}

		return mongo;
	}

}
