package Shared;

import java.io.Serializable;

public class MsgData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String m_string;
	public EntitiesHolder m_entities;
	public int m_playerID = -1;
	
	/*public Vector2D m_position;
	public Vector2D m_initialPosition;
	public Vector2D m_initialDirection;
	public Vector2D m_speed;
	public Vector2D m_direction; // Should always be unit vector; determines the
									// object's facing*/

	public MsgData() {
		/*m_position = new Vector2D();
		m_initialPosition = new Vector2D();
		m_initialDirection = new Vector2D();
		m_speed = new Vector2D();
		m_direction = new Vector2D();*/
		
		m_string = null;
		m_entities = null;
	}

	/*public MsgData(Vector2D position, Vector2D initialPosition, Vector2D initialDirection, Vector2D speed,
			Vector2D direction) {

		m_playerID = playerID;
		m_position = position;
		m_speed = speed;
		m_direction = direction;
	}*/
	
	public MsgData(EntitiesHolder gameEntites){
		m_entities = gameEntites;
	}
	
	public MsgData(EntitiesHolder gameEntites, String score){
		m_entities = gameEntites;
		m_string = score;
	}
	
	public MsgData(String input, int playerIdentification){
		m_string = input;
		m_playerID = playerIdentification;
	}

}
