package Shared;

import java.io.Serializable;

public class MsgData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String m_string;
	public int m_playerID;
	
	public Vector2D m_ballPosition;
	public Vector2D m_position;
	public Vector2D m_speed;
	public Vector2D m_direction; // Should always be unit vector; determines the
									// object's facing

	public MsgData() {

		m_playerID = -1;
		m_ballPosition = new Vector2D();
		m_position = new Vector2D();
		m_speed = new Vector2D();
		m_direction = new Vector2D();
		m_string = null;
	}

	public MsgData(int playerID, Vector2D position, Vector2D speed, Vector2D direction) {

		m_playerID = playerID;
		m_position = position;
		m_speed = speed;
		m_direction = direction;
	}
	
	public MsgData(String input) {
		m_string = input;
	}

}
