package blueprint;


import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import command.CheckUrl;
import command.CreateURL;
import org.glassfish.jersey.server.mvc.Viewable;

@Path("/shortenerUrl")

public class urlService extends Application {
	ObjectMapper mapper = new ObjectMapper();
	Map<String, String> output = new HashMap<String, String>();
	
	@GET
	public Response get() {
		return Response.ok(new Viewable("/create")).build();
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_PLAIN})
	public Response shortenUrl(@FormParam("givenURL") String givenURL) {
		try {
			String newUrl = new String();
			CreateURL createUrl= new CreateURL();
			CheckUrl checker = new CheckUrl();
			if(givenURL != null)		
				if (checker.isExiste(givenURL))
					if(checker.notInTheDatabase(givenURL))
						if(createUrl.newUrl(givenURL)){
							System.out.println("====================11=====================");
							newUrl= createUrl.theNewUrl; 
							System.out.println("the NEW URL IS \t :"+ newUrl);
							output.put("url", newUrl);
							return Response.ok(new Viewable("/output", output)).build();}
						else {
							System.out.println("====================22=====================");
							System.out.println("We cant create the new URL : this message form urlService");
							return Response.status(400).entity("We cant create the new URL : this message form urlService").build();
							}
						else {
						System.out.println("====================33=====================");
						newUrl= checker.newUrl;
						System.out.println("the newURL in our database");
						System.out.println("the NEW URL IS \t :"+ newUrl);
						output.put("url", newUrl);
						return Response.ok(new Viewable("/output", output)).build();}
				else{
					System.out.println("====================44=====================");
					System.out.println("the URL doesn't Existe");
					return Response.status(201).entity("the URL doesn't Existe").build();
					}
			else {
				System.out.println("====================55=====================");
				System.out.println("the filed is null");
				return Response.status(201).entity("the filed is null").build();
				}
		} catch (Exception e) {
			System.out.println("====================66=====================");
			System.out.println("the mistake is \t"+ e);
			return Response.status(400).entity(e.toString()).build();
		}
	}
}
