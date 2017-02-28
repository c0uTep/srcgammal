package GBallServer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import Shared.MsgData;

public class ClientConnection {
	private final InetAddress m_address;
	private final int m_port;

	public ClientConnection(InetAddress address, int port) {
		m_address = address;
		m_port = port;
	}
	
	public void sendMessage(MsgData data, DatagramSocket socket) {
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

		DatagramPacket packet = new DatagramPacket(serializedMessage, serializedMessage.length, m_address, m_port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			System.err.println("Failed to send message.");
			e.printStackTrace();
		}
	}

	public InetAddress getAddress() {
		return m_address;
	}

	public int getPort() {
		return m_port;
	}
}