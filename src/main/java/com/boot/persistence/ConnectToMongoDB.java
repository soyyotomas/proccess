package com.boot.persistence;

import java.util.Iterator;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class ConnectToMongoDB {

	public static void main(String[] args) {

		// Creating a Mongo Client
		MongoClient mongo = new MongoClient("localhost", 27017);

		// Creating Credentials
		MongoCredential mongoCredential;
		mongoCredential = MongoCredential.createCredential("momo", "insurance", "federer1".toCharArray());
		System.out.println("Connected to the database succesfully");

		// Accessing the database
		MongoDatabase dataBase = mongo.getDatabase("insurance");
		System.out.println("Credentials " + mongoCredential);

		// Creating a collection
		dataBase.createCollection("insurances");
		System.out.println("Collection created succcessfully");

		// Retrieving a collection
		MongoCollection<Document> collection = dataBase.getCollection("insurance");
		collection.find(Filters.eq("id", 1));
		System.out.println("Collection Insurance selected successfully");

		// Inserting a document
		Document document = new Document("nome", "AC 50").append("id", 5).append("insuranceType", "Stand-Alone")
				.append("coverage", "National");
		collection.insertOne(document);
		System.out.println("Document inserted successfully");

		// Updating document
		collection.updateOne(Filters.eq("id", 1), Updates.set("coverage", "International"));
		System.out.println("Document updated successfully");

		// Delete Document
		collection.deleteOne(Filters.eq("id", 5));
		System.out.println("Document deleted successfully");

		// Delete Collection
		MongoCollection<Document> collection1 = dataBase.getCollection("insurances");
		collection1.drop();
		System.out.println("Collection dropped successfully");

		// Getting the iterate object
		FindIterable<Document> iterDoc = collection.find();
		int i = 1;

		// Getting the iterator
		Iterator it = iterDoc.iterator();

		while (it.hasNext()) {
			System.out.println(it.next());
			i++;
		}
	}

}
