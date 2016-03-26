import java.io.*;
import java.net.*;

/** "Hello World" level RECEIVE communications example */

public class Server
{
public static void main(String[] args) throws Exception
  {
  ServerSocket ss = new ServerSocket(7734);  
  String networkAddress = InetAddress.getLocalHost().getHostAddress();//get numeric version
  System.out.println("Waiting for a client to connect at adress " + networkAddress + " on port " + ss.getLocalPort()); 
  
  while (true)
  	{
	  Socket s = ss.accept(); // WAIT for a client to connect
	  String callerNetworkAddress = s.getInetAddress().getHostAddress();// get caller's address!
	  int PeerPortNumber = s.getPort();//get caller's port!
	  System.out.println("Connected to " + callerNetworkAddress + "At Port Number: " + PeerPortNumber);
	  DataInputStream dis = new DataInputStream(s.getInputStream());
	  DataOutputStream send = new DataOutputStream(s.getOutputStream());
	  String message = dis.readUTF(); // WAIT for client SEND
	  
	  if(message.contains("method RFC number"))
	  {
		  
	  }
	  send.writeUTF("Thank you it has been received");
	  //dis.close(); // hang up! 
	  System.out.println("Received: " + message);
  	}
  }

}