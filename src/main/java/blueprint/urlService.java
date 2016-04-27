package blueprint;


import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import command.CheckUrl;
import command.CreateURL;
import command.DeleteUrl;
import command.SearchUrl;
import org.glassfish.jersey.server.mvc.Viewable;

@Path("/shortenerUrl")

public class urlService extends Application {
	ObjectMapper mapper = new ObjectMapper();
	Map<String, String> output = new HashMap<String, String>();
	SearchUrl search = new SearchUrl();
	CreateURL createUrl= new CreateURL();

	
	@GET
	public Response get() {
		return Response.ok(new Viewable("/create")).build();
	}
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_PLAIN})
	public Response getSong(@PathParam("id") String id) {
		try {
			if(search.searchByID(id)){
				String oldUrl= search.originalUrl;
				output.put("oldUrl", oldUrl);
				return Response.ok(new Viewable("/output", output)).build();}
		else
			return Response.status(400).entity("the URL doesn't correct").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(400).entity(e.toString()).build();
		}
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_PLAIN})
	public Response shortenUrl(@FormParam("givenURL") String givenURL) {
		try {
			String newUrl = new String();
			CheckUrl checker = new CheckUrl();
			SearchUrl search = new SearchUrl();
			
			if(givenURL != null)		
				if (checker.isExiste(givenURL))
					if(search.searchByOriginalUrl(givenURL))
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
						newUrl= search.newUrl;
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
	
	@DELETE
	public Response deleteBook(@FormParam("newUrl") String newUrl) {
		DeleteUrl delete = new DeleteUrl();
		if(delete.delete("id"))
			return Response.status(200).entity("the URL has been deleted ").build();
		else
			return Response.status(400).entity("The URL not in our data").build();
	}

	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_PLAIN})
	public Response updateUrl(@FormParam("newUrl") String newUrl) {
		try {
			if(search.searchByNewUrl(newUrl))
				if(createUrl.newUrl(search.originalUrl)){
					newUrl= createUrl.theNewUrl; 
					System.out.println("the NEW URL IS \t :"+ newUrl);
					output.put("url", newUrl);
					return Response.ok(new Viewable("/output", output)).build();
					}else 
						return Response.status(400).entity("The system couldn't update the URL").build();
			else
				return Response.status(400).entity("The URL not in our data").build();
		} catch (Exception e) {
			return Response.status(500).entity(e.toString()).build();
		}
	}	
}
