package GBallServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Shared.MsgData;

public class ServerThread extends Thread
{
	private Socket m_socket;
	private Server m_server;
	
	public ServerThread(Socket socket, Server server)
	{
		m_socket = socket;
		m_server = server;
	}
	
	public void run()
	{
		try
		{
			ObjectOutputStream output = new ObjectOutputStream(m_socket.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(m_socket.getInputStream());
			
			MsgData message = (MsgData)input.readObject();
			
			if(message.m_string.equals("join"))
			{
				m_server.handShake("join", m_socket);
			}
			
			else
			{
				m_server.updatePos();
			}
		}
		
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}