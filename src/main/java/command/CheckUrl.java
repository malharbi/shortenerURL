package command;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import mongo.ConnectionProvider;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Url;

public class CheckUrl {
	
	public String newUrl = new String();
	
	public boolean isExiste(String originalUrl) throws IOException{
		System.out.println("NOW IN THE FRIST THE METHOD OF isExiste");
		HttpURLConnection httpUrlConn = (HttpURLConnection) new URL(originalUrl)
                .openConnection();
        return (httpUrlConn.getResponseCode() == HttpURLConnection.HTTP_OK);
	}
	
	public boolean notInTheDatabase(String originalUrl) throws IOException{
		System.out.println("NOW IN THE FRIST THE METHOD OF isInOurDatabase");
		Map thedata = new HashMap();
		
		MongoClientURI uri = new MongoClientURI("mongodb://motazurl:urlmotaz@ds015869.mlab.com:15869/shortdb");
		MongoClient client = new MongoClient(uri); // MongoClient connected with the specified URI.
		DB database = client.getDB("shortdb");
		DBCollection collection = database.getCollection("urls");
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("originalUrl", originalUrl);
		DBCursor cursor = collection.find(searchQuery);
		
		System.out.println("NOW IN THE VALUES OF cursor.hasNext() ==" + cursor.hasNext());
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

}
