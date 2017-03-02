package GBallServer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;

import Shared.Const;
import Shared.GameEntity;
import Shared.MsgData;
import Shared.Vector2D;

public class Ship extends GameEntity{

	private Color m_color;
	private int rotation = 0; // Set to 1 when rotating clockwise, -1 when
								// rotating counterclockwise
	private boolean braking = false;

	public Ship(final Vector2D position, final Vector2D speed, final Vector2D direction, final Color col) {
		super(position, speed, direction, Const.SHIP_MAX_ACCELERATION, Const.SHIP_MAX_SPEED, Const.SHIP_FRICTION);
		m_color = col;
	}
	
	public void updatePos(MsgData message)
	{
		String[] inputSplit = message.m_string.split(" ", 4);
		
		if(inputSplit[0].equals("1"))
			rotation = -1;
		
		if(inputSplit[1].equals("1"))
			setAcceleration(1);
		
		if(inputSplit[2].equals("1"))
			setAcceleration(0);
		
		if(inputSplit[3].equals("1"))
			rotation = 1;
	}
	
	@Override
	public void move() {
		if (rotation != 0) {
			rotate(rotation * Const.SHIP_ROTATION);
			scaleSpeed(Const.SHIP_TURN_BRAKE_SCALE);
		}
		if (braking) {
			scaleSpeed(Const.SHIP_BRAKE_SCALE);
			setAcceleration(0);
		}
		super.move();
	}

	@Override
	public boolean givesPoints() {
		return false;
	}

	@Override
	public double getRadius() {
		return Const.SHIP_RADIUS;
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}