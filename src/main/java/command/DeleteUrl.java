package command;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import mongo.ConnectionProvider;

public class DeleteUrl {
	
	public boolean delete(String newUrl) {
		try{
			MongoClient client = (new ConnectionProvider()).getConnection();
			MongoDatabase db = client.getDatabase("shortdb");
			MongoCollection<Document> collection = db.getCollection("urls");

			BasicDBObject searchUrl = new BasicDBObject();
			searchUrl.put("newUrl", newUrl);
			collection.deleteOne(searchUrl);
			client.close();
			return true;
		} catch (Exception e){
			return false;			
		}
	}

}
