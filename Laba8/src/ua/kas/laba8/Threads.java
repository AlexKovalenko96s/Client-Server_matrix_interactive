package ua.kas.laba8;

import java.awt.HeadlessException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Threads implements Runnable{

	static private Socket connection;
	static private ObjectOutputStream output1;
	static private ObjectOutputStream output2;
	static private ObjectOutputStream output3;
	static private ObjectOutputStream output4;
	static private ObjectInputStream input;
	
	@Override
	public void run() {
		try {
			while (true) {
				connection = new Socket(InetAddress.getByName("127.0.0.1"), 5678);
				output1 = new ObjectOutputStream(connection.getOutputStream());
				output2 = new ObjectOutputStream(connection.getOutputStream());
				output3 = new ObjectOutputStream(connection.getOutputStream());
				output4 = new ObjectOutputStream(connection.getOutputStream());
				input = new ObjectInputStream(connection.getInputStream());
				System.out.println((String) input.readObject());
				JOptionPane.showMessageDialog(null, (String) input.readObject());
			}
		} catch (IOException e) {e.printStackTrace();
		} catch (HeadlessException e) {e.printStackTrace();
		} catch (ClassNotFoundException e) {e.printStackTrace();}
	}
	
	static void sendData(Object obj1 , Object obj2, Object obj3, Object obj4) throws IOException {
		output1.flush();
		output2.flush();
		output3.flush();
		output4.flush();
		output1.writeObject(obj1);
		output2.writeObject(obj2);
		output3.writeObject(obj3);
		output4.writeObject(obj4);
	}
}
