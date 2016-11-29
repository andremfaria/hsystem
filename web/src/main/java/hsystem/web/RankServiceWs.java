package hsystem.web;

import java.util.Iterator;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import hsystem.actormanager.interfaces.ActorService;
import hsystem.foundation.SpringApplicationContext;
import hsystem.foundation.beans.Highscore;
import hsystem.rankmanager.interfaces.RankService;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;


@Path("/highscores")
public class RankServiceWs {

	RankService rankService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertHighscore(Highscore highscore) {
		rankService = (RankService) SpringApplicationContext.getBean("highscoreService");

		if(rankService.insertHighscore(highscore) != null)
			return Response.status(200).build();
		
		return Response.status(430).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateHighscore(Highscore highscore) {
		rankService = (RankService) SpringApplicationContext.getBean("highscoreService");

		if(rankService.updateHighscore(highscore))
			return Response.status(200).build();
		
		return Response.status(430).build();
	}
	
	@DELETE
	public Response removeHighscore(@QueryParam("userId") String publicId) 
	{
		rankService = (RankService) SpringApplicationContext.getBean("highscoreService");
		
		if(rankService.removeHighscore(publicId))
			return Response.status(200).build();
		
		return Response.status(430).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Highscore getHighscore(@QueryParam("userId") String userId) {
		
		rankService = (RankService) SpringApplicationContext.getBean("highscoreService");
		Highscore highscore = rankService.getHighscore(userId); 
		return highscore == null? new Highscore() : highscore; 
	}

	@GET
	@Path("/getall")
	@Produces(MediaType.APPLICATION_JSON)
	public Iterator<Highscore> getAll() 
	{
		rankService = (RankService) SpringApplicationContext.getBean("highscoreService");
		return rankService.getAll();
	}

	@GET
	@Path("/getbetween")
	@Produces(MediaType.APPLICATION_JSON)
	public Iterator<Highscore> getBetween(@QueryParam("first") int first, @QueryParam("last") int last) {
		
		rankService = (RankService) SpringApplicationContext.getBean("highscoreService");
		return rankService.getBetween(first, last);
		
	}

}
