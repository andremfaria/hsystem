package hsystem.web;

import java.util.Date;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import hsystem.actormanager.interfaces.ActorService;
import hsystem.foundation.SpringApplicationContext;
import hsystem.foundation.beans.Actor;

@Path("/actors")
public class ActorServiceWs {
	
	private ActorService actorService;	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Actor getActor(@QueryParam("publicId") String publicId)
	{
		actorService = (ActorService) SpringApplicationContext.getBean("actorService");
		Actor actor = actorService.getActor(publicId);
		return actor == null? new Actor(): actor; 
		
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login")
	public boolean doLogin(@QueryParam("publicId") String publicId, 
						   @QueryParam("password") String password)
	{
		actorService = (ActorService) SpringApplicationContext.getBean("actorService");
		return actorService.doLogin(publicId, password);
	}
	
	@DELETE
	public Response removeActor(@QueryParam("publicId") String publicId)
	{
		actorService = (ActorService) SpringApplicationContext.getBean("actorService");
		
		if(actorService.removeActor(publicId))
			return Response.status(200).build();
		
		return Response.status(430).build();
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertActor(Actor actP)
	{
		actorService = (ActorService) SpringApplicationContext.getBean("actorService");
		Actor actor = new Actor();
		actor.setPublic_id(actP.getPublic_id());
		actor.setCryptedPassword(actP.getCryptedPassword());
		actor.setNationality(actP.getNationality());
		actor.setIsDeleted(false);
		actor.setCreationDate(new Date());
		
		if(actorService.insertActor(actor) != null)
			return Response.status(200).build();
		
		return Response.status(430).build();
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateActor(Actor act)
	{
		actorService = (ActorService) SpringApplicationContext.getBean("actorService");
		
		if(actorService.updateActor(act))
			return Response.status(200).build();
		
		return Response.status(430).build();
	}	
}
