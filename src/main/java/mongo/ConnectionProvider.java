package mongo;

import java.util.Arrays;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;

public class ConnectionProvider {

	public MongoClient getConnection() {
		try {

			MongoCredential credential = MongoCredential.createCredential("motazurl", "shortdb", "urlmotaz".toCharArray());
			//createCredential("User Name", "Database", "Password")
			MongoClient client = new MongoClient(new ServerAddress("ds015869.mlab.com", Integer.valueOf("15869")),
					Arrays.asList(credential));
			// ServerAddress("HostName", Integer.valueOf(mongod port)
			System.out.println("client :"+ client
								+"\n credential :" + credential);
			return client;
		} catch (MongoException e) {
			e.printStackTrace();
			System.out.print("Catsh on connect");
		}
		return null;

	}
}
