package hsystem.actormanager.impl;

import hsystem.actormanager.interfaces.ActorService;
import hsystem.db.impl.DbService;
import hsystem.foundation.SpringApplicationContext;
import hsystem.foundation.beans.Actor;

public class ActorServiceImpl implements ActorService {

	private DbService dbService;

	public Actor insertActor(Actor actor) {

		boolean res = false;
		dbService = (DbService) SpringApplicationContext.getBean("dbService");
		res = dbService.insertActor(actor);
		return res ? actor : null;
	}

	public Actor getActor(String publicId) {
		dbService = (DbService) SpringApplicationContext.getBean("dbService");
		return dbService.getActor(publicId);
	}

	public boolean removeActor(String publicId) {

		dbService = (DbService) SpringApplicationContext.getBean("dbService");
		return dbService.removeActor(publicId);
	}

	public boolean doLogin(String publicId, String password) 
	{
		dbService = (DbService) SpringApplicationContext.getBean("dbService"); 
		return dbService.doLogin(publicId, password);
	}

	public boolean updateActor(Actor actor) {
		dbService = (DbService) SpringApplicationContext.getBean("dbService"); 
		return dbService.updateActor(actor);
	}

}
