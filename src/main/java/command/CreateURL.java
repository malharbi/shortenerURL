package command;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import mongo.ConnectionProvider;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import model.Url;

public class CreateURL {
	public String theNewUrl = new String();
	public boolean newUrl(String givenURL){
		ObjectMapper mapper = new ObjectMapper();
		CheckUrl newUrl=new CheckUrl();
		
		Url addURL= new Url();

		MongoClient client = (new ConnectionProvider()).getConnection();
		MongoDatabase mdb = client.getDatabase("shortdb");
		MongoCollection<Document> urlsCollection = mdb.getCollection("urls");
		addURL.setOriginalUrl(givenURL);

		try {
			URL myURL = new URL("http://mot.az");
			URL urlID = new URL(myURL, createID());
			System.out.println("Your URL from Creat URL is @@@@@@@@@@@@@@@@@:"+ urlID);
			
			addURL.setOriginalUrl(givenURL);
			addURL.setNewUrl(urlID.toString());
			
			Document objectDB = new Document(Document.parse(mapper.writeValueAsString(addURL)));
			urlsCollection.insertOne(objectDB);
			System.out.println("the URL urlID ==" + urlID);

			theNewUrl= urlID.toString();
			System.out.println("newUrl.newUrl= urlID.toString(); ==" + theNewUrl);

		} catch (MalformedURLException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally{
			client.close();
		}
		return true;
	}

// create ID that be add to the main URI to mapping to the original URL	that given from user
	public String createID(){
		ArrayList<String> newID= new ArrayList<String>();
		Random numR= new Random();
		String [] seedId ={"q","w","e","r","t","y","u","i","o","p",
				"a","s","d","f","g","h","j","k","l","z","x","c","v",
				"b","n","m","1","2","3","4","5","6","7","8","9","0",
				"Q","W","E","R","T","Y","U","I","O","P","A","S","D"
				,"F","G","H","J","K","L","Z","X","C","V","B","N","M"};
		for(int i=0; i<=6; i++){
			newID.add(seedId[numR.nextInt(seedId.length)]);
		}
		String id = String.join("", newID);
		System.out.println("Your ID FOR THE NEW URL IS FROM CREATE ID IN CREATEURL CLASS @@@@@@@@@@@@@@@@@:"+ id);

		return id;
	}
	

}

