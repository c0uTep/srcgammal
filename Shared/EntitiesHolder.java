package Shared;

public class EntitiesHolder {
	
	/* 
	 * EntitiesHolder - Holder for all entities that are going to be sent to clients.
	 */
	
	public GameEntity ship1 = null;
	public GameEntity ship2 = null;
	public GameEntity ship3 = null;
	public GameEntity ship4 = null;
	public GameEntity ball = null;
	
	public EntitiesHolder(GameEntity ship1, GameEntity ship2, GameEntity ship3, GameEntity ship4, GameEntity ball){
		this.ship1 = ship1;
		this.ship2 = ship2;
		this.ship3 = ship3;
		this.ship4 = ship4;
		this.ball = ball;
	}

}
