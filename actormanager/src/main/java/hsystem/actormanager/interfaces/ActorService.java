package hsystem.actormanager.interfaces;

import hsystem.foundation.beans.Actor;

public interface ActorService {
	
	public Actor insertActor(Actor actor);
	public Actor getActor(String publicId);
	public boolean removeActor(String publicId);
	public boolean doLogin(String publicId, String password);
	public boolean updateActor(Actor actor);
	
}
