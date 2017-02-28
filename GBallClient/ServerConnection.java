package GBallClient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import Shared.MsgData;

public class ServerConnection {
	// behöver hantera client sidan på en handshake för att etablera
	// anslutningen när clienten ansluter.
	// samt även ta hand om att skicka meddelanden till servern under spelets
	// gång.

	private InetAddress m_serverAddress;
	private int m_serverPort;
	private DatagramSocket m_socket;

	public ServerConnection(InetAddress address, int port) {
		m_serverAddress = address;
		m_serverPort = port;

		try {
			m_socket = new DatagramSocket(m_serverPort, m_serverAddress);
		}

		catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean handshake(MsgData data) {
		
		//Skicka meddelande till servern
		ByteArrayOutputStream bStream = new ByteArrayOutputStream();
		ObjectOutput output;
		try {
			output = new ObjectOutputStream(bStream);
			output.writeObject(data);
			output.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		byte[] serializedMessage = bStream.toByteArray();

		DatagramPacket packet = new DatagramPacket(serializedMessage, serializedMessage.length, m_serverAddress,	m_serverPort);
		try {
			m_socket.send(packet);
		} catch (IOException e) {
			System.err.println("Failed to send message.");
			e.printStackTrace();
		}
		
		//Vänta på svar från servern
		byte[] buff = new byte[1024];
		ObjectInputStream iStream;
		MsgData message = null;
		packet = new DatagramPacket(buff, buff.length);
		
		try {
			m_socket.receive(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			iStream = new ObjectInputStream(new ByteArrayInputStream(buff));
			message = (MsgData) iStream.readObject();
			iStream.close();
		} catch (IOException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		if(message.message.equals("true")){
			return true;
		}
		else{
			return false;
		}
	}

	public void sendMessage(MsgData data) {
		ByteArrayOutputStream bStream = new ByteArrayOutputStream();
		ObjectOutput output;
		try {
			output = new ObjectOutputStream(bStream);
			output.writeObject(data);
			output.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		byte[] serializedMessage = bStream.toByteArray();

		DatagramPacket packet = new DatagramPacket(serializedMessage, serializedMessage.length, m_serverAddress,	m_serverPort);
		try {
			m_socket.send(packet);
		} catch (IOException e) {
			System.err.println("Failed to send message.");
			e.printStackTrace();
		}
	}

	public MsgData receiveMessage() {
		byte[] buff = new byte[1024];
		ObjectInputStream iStream;
		MsgData message = null;
		DatagramPacket packet = new DatagramPacket(buff, buff.length);
		
		try {
			m_socket.receive(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			iStream = new ObjectInputStream(new ByteArrayInputStream(buff));
			message = (MsgData) iStream.readObject();
			iStream.close();
		} catch (IOException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		return message;

	}
}