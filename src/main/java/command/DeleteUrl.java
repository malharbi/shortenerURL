package command;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import mongo.ConnectionProvider;

public class DeleteUrl {
	
	public boolean delete(String id) {
		try{
			MongoClientURI uri = new MongoClientURI("mongodb://motazurl:urlmotaz@ds021691.mlab.com:21691/shortdb");
			MongoClient client = new MongoClient(uri); // MongoClient connected with the specified URI.
			DB database = client.getDB("shortdb");
			DBCollection collection = database.getCollection("urls");
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("id", id);
			DBCursor cursor = collection.find(searchQuery);
			collection.remove(searchQuery);
			return true;
		} catch (Exception e){
			return false;			
		}
	}

}
