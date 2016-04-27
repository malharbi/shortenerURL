package command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class SearchUrl {
	public String newUrl = new String();
	public String originalUrl = new String();
	Map thedata = new HashMap();
	
	MongoClientURI uri = new MongoClientURI("mongodb://motazurl:urlmotaz@ds015869.mlab.com:15869/shortdb");
	MongoClient client = new MongoClient(uri); // MongoClient connected with the specified URI.
	DB database = client.getDB("shortdb");
	DBCollection collection = database.getCollection("urls");
	BasicDBObject searchQuery = new BasicDBObject();
		
	public boolean searchByOriginalUrl(String originalUrl) throws IOException{
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("originalUrl", originalUrl);
		DBCursor cursor = collection.find(searchQuery);
		System.out.println("NOW IN searchByOriginalUrl THE VALUES OF cursor.hasNext() ==" + cursor.hasNext());
		if(cursor.hasNext()){
			while(cursor.hasNext()){
				DBObject result = cursor.next();
				thedata =result.toMap();
				newUrl=thedata.get("newUrl").toString();
				}
			return false;
		}
		System.out.println("FROM CHECKURL CLASS THE RESULT OF THE SERARCH QUERY IS :"+ newUrl);
		return true;
	}
	
	public boolean searchByID(String id){
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("id", id);
		DBCursor cursor = collection.find(searchQuery);
		System.out.println("NOW IN searchByID THE VALUES OF cursor.hasNext() ==" + cursor.hasNext());		
		while(cursor.hasNext()){
			DBObject result = cursor.next();
			thedata =result.toMap();
			originalUrl=thedata.get("originalUrl").toString();
			}
		System.out.println("FROM CHECKURL CLASS THE RESULT OF THE SERARCH QUERY IS :"+ newUrl);
		return cursor.hasNext();
	}
	
	public boolean searchByNewUrl(String newUrl){
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("id", newUrl);
		DBCursor cursor = collection.find(searchQuery);
		System.out.println("NOW IN searchByNewUrl THE VALUES OF cursor.hasNext() ==" + cursor.hasNext());		
		while(cursor.hasNext()){
			DBObject result = cursor.next();
			thedata =result.toMap();
			originalUrl=thedata.get("originalUrl").toString();
			}
		System.out.println("FROM CHECKURL CLASS THE RESULT OF THE SERARCH QUERY IS :"+ newUrl);
		return cursor.hasNext();
		
	}
}
