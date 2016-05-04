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
import command.GetUrlcommand;
import command.SearchUrl;
import util.PropertiesLookup;

import org.glassfish.jersey.server.mvc.Viewable;

@Path("/shortenerUrl")

public class urlService extends Application {
	ObjectMapper mapper = new ObjectMapper();
	public Map<String, String> output = new HashMap<String, String>();
	SearchUrl search = new SearchUrl();
	CreateURL createUrl = new CreateURL();
	DeleteUrl delete = new DeleteUrl();

	@GET
	public Response get() {
		return Response.ok(new Viewable("/index")).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getUrl(@PathParam("id") String id) {
		GetUrlcommand cmd= new GetUrlcommand();
		System.out.print("\n\nResponse getUrl(@PathParam(id) String id)"+ id);

		String oldUrl = cmd.execute(id);

		System.out.print("\n old URL \n"+ oldUrl +"\n\n");
		output.put("url", oldUrl);
		return Response.ok(new Viewable("/show", output)).build();
	}

	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response shortenUrl(@FormParam("givenURL") String givenURL) {
		try {
			String newUrl = new String();
			CheckUrl checker = new CheckUrl();
			if(givenURL != null)		
				if (checker.isExiste(givenURL))
					if(search.searchByOriginalUrl(givenURL))
						if(createUrl.newUrl(givenURL)){
							newUrl= createUrl.theNewUrl; 
							output.put("url", newUrl);
							return Response.ok(new Viewable("/show", output)).build();}
						else 
							return Response.status(400).entity("We cant create the new URL : this message form urlService").build();
						else {
						newUrl= search.newUrl;
						output.put("url", newUrl);
						return Response.ok(new Viewable("/show", output)).build();}
				else
					return Response.status(201).entity("the URL doesn't Existe").build();	
			else 
				return Response.status(201).entity("the filed is null").build();
				
		} catch (Exception e) {
			System.out.println("====================66=====================");
			System.out.println("the mistake is \t"+ e);
			return Response.status(400).entity(e.toString()).build();
		}
	}

	@DELETE
	@Path("/{id}")
	public Response deleteBook(@PathParam("id") String id) {
		try{
			if (delete.delete(id)) 
				return Response.status(200).build();			
		}catch (Exception e) {
			return Response.status(400).entity("The URL not in our data").build();
			}
		return Response.status(400).entity("the URL doesn't correct").build();
		}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN) 
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.TEXT_PLAIN})
	public Response updateUrl(@PathParam("id") String id) { 
		try {
			String newUrl;
			if(search.searchByID(id))
				if(createUrl.newUrl(search.originalUrl))
					if (delete.delete(id)){
						newUrl= createUrl.theNewUrl;
						output.put("url", newUrl);					
						return Response.status(200).entity(newUrl).build();}
					else 
						return Response.status(400).entity("The system couldn't delete the URL").build(); 
				else 
					return Response.status(400).entity("The system couldn't update the URL").build(); 
			else 
				return Response.status(400).entity("The URL not in our data").build(); 
			}catch (Exception e) {
			return Response.status(500).entity(e.toString()).build();
			}
		}
 
	@GET
	@Path("properties/{property}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response propertiesCheck(@PathParam("property") String property) {
		PropertiesLookup pl = new PropertiesLookup();
		String pValue = pl.getProperty(property);
		return Response.status(200).entity(pValue).build();
	}
}
