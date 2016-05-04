/* Here we create the ID for the URL that's come from user
 * and save all the information in database as original URL, new URL, ID.
 */

package command;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import model.Url;
import mongo.ConnectionProvider;


public class CreateURL {
	public String theNewUrl = new String();
	SearchUrl search = new SearchUrl();
	
	public boolean newUrl(String givenURL){
		System.out.println("Now in create URL::::"+ theNewUrl);
		ObjectMapper mapper = new ObjectMapper();		
		Url addURL= new Url();

		MongoClient client = (new ConnectionProvider()).getConnection();
		MongoDatabase mdb = client.getDatabase("shortdb");
		MongoCollection<Document> urlsCollection = mdb.getCollection("urls");
		//addURL.setOriginalUrl(givenURL);

		try {
			System.out.println("Now TRY create URL::::"+ theNewUrl);

			String id = createID();
			System.out.println("the New ID after recived to new url ::::"+ id);

			URL myURL = new URL("http://motaz2.herokuapp.com/rest/shortenerUrl/");
			URL urlID = new URL(myURL, id);
			System.out.println("Now urlID  ::::"+ urlID);

			addURL.setOriginalUrl(givenURL);
			addURL.setNewUrl(urlID.toString());
			addURL.setId(id);
			
			Document objectDB = new Document(Document.parse(mapper.writeValueAsString(addURL)));
			urlsCollection.insertOne(objectDB);
			System.out.println("the New Url before send  ::::"+ theNewUrl);
			theNewUrl= urlID.toString();
			System.out.println("the New Url after send  ::::"+ theNewUrl);


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

// generate ID that be add to the main URI to mapping to the original URL	that given from user
	public String createID(){
		ArrayList<String> newID= new ArrayList<String>();
		Random numR= new Random();
		String [] seedId ={"q","w","e","r","t","y","u","i","o","p",
				"a","s","d","f","g","h","j","k","l","z","x","c","v",
				"b","n","m","1","2","3","4","5","6","7","8","9","0",
				"Q","W","E","R","T","Y","U","I","O","P","A","S","D"
				,"F","G","H","J","K","L","Z","X","C","V","B","N","M"};
		Boolean loop = true;
		String id = null; 
		while(loop){
			for(int i=0; i<=6; i++){
				System.out.println("Inside the loop for"+newID);
				newID.add(seedId[numR.nextInt(seedId.length)]);
			}
			id = String.join("", newID);
			System.out.println("Inside the loop while"+id);
			loop = search.searchByID(id);
			System.out.println("Inside the loop while"+loop);

		}
		System.out.println("the New ID befor send to creat ::::"+ id);

			return id;
	}
	

}

