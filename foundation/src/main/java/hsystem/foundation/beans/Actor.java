package hsystem.foundation.beans;

import java.util.Date;

public class Actor {

	private String public_id;
	private String cryptedPassword;
	private String nationality;
	private Date creationDate;
	private boolean isDeleted;
	
	//Constructors
	public Actor(Actor actor)
	{
		public_id = actor.getPublic_id();
		cryptedPassword = actor.getCryptedPassword();
		nationality = actor.getNationality();
		creationDate = actor.getCreationDate();
		isDeleted = actor.getIsDeleted();
	}
	
	public Actor()
	{
		public_id = "";
		cryptedPassword = "";
		nationality = "";
		creationDate = null;
		isDeleted = false;
	}
	
	//Setters and Getters
	public String getPublic_id() {
		return public_id;
	}
	public void setPublic_id(String public_id) {
		this.public_id = public_id;
	}
	public String getCryptedPassword() {
		return cryptedPassword;
	}
	public void setCryptedPassword(String cryptedPassword) {
		this.cryptedPassword = cryptedPassword;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	//Overrides
	
	@Override
	public Object clone()
	{
		return new Actor(this);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null || !this.getClass().getSimpleName().equals(obj.getClass().getSimpleName())) 
			return false;
		
		if(obj == this) return true;
		
		Actor actor = (Actor) obj;
		return this.getPublic_id().equals(actor.getPublic_id());
	}
}
