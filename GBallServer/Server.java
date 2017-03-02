package GBallServer;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;

import GBallServer.ServerThread;
import Shared.*;

public class Server
{
	private ArrayList<ClientConnection> m_connectedClients = new ArrayList<ClientConnection>();
	private static ServerSocket m_socket;
	
	public void main(String[] args)
	{
		if (args.length < 1)
		{
			System.err.println("Insert portnumber");
			System.exit(-1);
		}
		
		try
		{
			m_socket = new ServerSocket(Integer.parseInt(args[0]));
		}
		
		catch (NumberFormatException | IOException e)
		{
			e.printStackTrace();
		}
		
		listenForMessages();
	}
	
	private void listenForMessages()
	{
		while (true)
		{
			if (m_connectedClients.size() < 4)
			{
				try
				{
					new ServerThread(m_socket.accept(), this).start();
				}
				
				catch (IOException e)
				{
					e.printStackTrace();
				}	
			}
		}
	}
	
	public boolean handShake(String command, Socket socket)
	{
		if(command.equals("join") || m_connectedClients.isEmpty())
			return addClient(socket.getInetAddress(), socket.getPort());
		
		else
			return false;
		
	}
	
	public void updatePos(MsgData message)
	{
		
	}
	
	public boolean addClient(InetAddress address, int port) {
		ClientConnection c;
		for (Iterator<ClientConnection> itr = m_connectedClients.iterator(); itr.hasNext();) {
			c = itr.next();
			if (c.getAddress().equals(address) && c.getPort() == port) {
				return false; // Already exists a client with this IP and port.
			}
		}
		System.out.println("Added a new client.");
		m_connectedClients.add(new ClientConnection(address, port));
		return true;
	}
	
	public ClientConnection getClient(InetAddress address, int port)
	{
		ClientConnection c;
		for (Iterator<ClientConnection> itr = m_connectedClients.iterator(); itr.hasNext();) {
			c = itr.next();
			if (c.getAddress().equals(address) && c.getPort() == port) {
				return c;
			}
		}
		return null;
	}
}