package model;

import java.net.URL;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Url {

	private String originalUrl, newUrl, id;
	
	 @JsonDeserialize(as=ArrayList.class, contentAs=URL.class)
	private ArrayList<String> genres;

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String givenURL) {
		this.originalUrl = givenURL;
	}
	
	public String getNewUrl() {
		return newUrl;
	}
	
	public void setNewUrl(String newUrl) {
		this.newUrl = newUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
