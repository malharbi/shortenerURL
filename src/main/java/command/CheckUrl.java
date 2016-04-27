package command;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class CheckUrl {
	
	public boolean isExiste(String originalUrl) throws IOException{
		System.out.println("NOW IN THE FRIST THE METHOD OF isExiste");
		HttpURLConnection httpUrlConn = (HttpURLConnection) new URL(originalUrl)
                .openConnection();
        return (httpUrlConn.getResponseCode() == HttpURLConnection.HTTP_OK);
	}
	
}
