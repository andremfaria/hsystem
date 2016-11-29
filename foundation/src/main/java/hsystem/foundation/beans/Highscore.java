package hsystem.foundation.beans;

import java.sql.Date;
import java.util.GregorianCalendar;

public class Highscore {

	private Long identifier;
	public Long getIdentifier() {
		return identifier;
	}
	public void setIdentifier(Long identifier) {
		this.identifier = identifier;
	}

	private Long points;
	private Date creationDate;
	private String actorId;
	
	//Constructors
	
	//Setters and Getters
	public Long getPoints() {
		return points;
	}
	public void setPoints(Long points) {
		this.points = points;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getActorId() {
		return actorId;
	}
	public void setActorId(String actorId) {
		this.actorId = actorId;
	}
	
	//Overrides
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null || !this.getClass().getSimpleName().equals(obj.getClass().getSimpleName())) 
			return false;
		
		if(obj == this) return true;
		
		Highscore highscore = (Highscore) obj;
		return this.getActorId().equals(highscore.getActorId())
				&& this.getIdentifier() == highscore.getIdentifier();
	}
	
}
