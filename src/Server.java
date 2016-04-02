import java.io.*;
import java.net.*;
import java.util.LinkedList;

public class Server 
{
@SuppressWarnings("null")
public static void main(String[] args)  throws Exception 
  {
  ServerSocket ss = new ServerSocket(7734);  //ServerSocket Connection
  String networkAddress = InetAddress.getLocalHost().getHostAddress();//Get Host Address
  System.out.println("Waiting for a client to connect at adress " + networkAddress + " on port " + ss.getLocalPort()); 
  LinkedList<PeerInformation> activePeers = new LinkedList<PeerInformation>(); //Linked List to hold Active Peer Information
  LinkedList indexRFCs = new LinkedList();
  while (true)
  	{
	  Socket s = ss.accept(); // WAIT for a client to connect
	  String callerNetworkAddress = s.getInetAddress().getHostAddress();// get caller's address!
	  int PeerPortNumber = s.getPort();//get caller's port!
	  System.out.println("Connected to " + callerNetworkAddress + " At Port Number: " + PeerPortNumber);
	  
	  DataInputStream dis = new DataInputStream(s.getInputStream());//Methods to send and receive data
	  DataOutputStream send = new DataOutputStream(s.getOutputStream());
	  
	  String message = dis.readUTF(); // WAIT for client SEND
	  
	  	//cut incoming message into sections that are needed to store
	  	String[] sentences = message.split(": ",4);
		String[] host_divider = sentences[1].split("\n",2);
		String[] port_divider = sentences[2].split("\n",2);
		String[] title_space = sentences[0].split(" ",4);
		String host = host_divider[0].trim();
		String title = sentences[3];//title of message
		String version = title_space[3];//this gets the P2P version for later check 
		int portNumber = Integer.parseInt(port_divider[0].trim()); //this gets port number
		int rfcNumber = Integer.parseInt(title_space[2].trim()); //this gets RFC number
		
	  //Test incoming message to determine what the peer is doing
	  if(message.contains("ADD"))
	  {
		PeerInformation Peeraddition  = new PeerInformation(rfcNumber,host,portNumber,title);
		activePeers.add(Peeraddition); 
		System.out.println(activePeers.get(0).getPortNumber());
		//what else to output
		send.writeUTF("200 OK");
		System.out.println("I added");
	  }
	  else if(message.contains("LOOKUP"))
	  {
		  PeerInformation Peeraddition2  = new PeerInformation(3457,"NCSU.EDU",3975,"TEST 1");
		  PeerInformation Peeraddition1  = new PeerInformation(3257,"NCSU.EDU",3857,"TEST 3");
		  PeerInformation Peeraddition3  = new PeerInformation(3457,"NCSU.EDU",3857,"TEST 3");
		  activePeers.add(Peeraddition2); 
		  activePeers.add(Peeraddition1);
		  activePeers.add(Peeraddition3);
		  System.out.println(activePeers.size());
		  StringBuilder a = new StringBuilder();
		  String lookup_name = null;
		  try
		  {
			  
			  System.out.println(activePeers.size());
			  for (int x=0;x<activePeers.size();x++)
			  	{
				  System.out.println(rfcNumber);
				  System.out.println(activePeers.get(x).getRFC());
				  System.out.println(" i made inside for");
				  if(activePeers.get(x).getRFC()==rfcNumber)
				  {
					  lookup_name.concat("RFC " + activePeers.get(x).getRFC() + " " + activePeers.get(x).getTitle() + " " + activePeers.get(x).getHost() + " " + activePeers.get(x).getPortNumber() + "\n"); 
					  System.out.println(lookup_name);
					  System.out.println(" iam inside");
				  }
			  	}
		  send.writeUTF(lookup_name);
		  //send.writeUTF("200 OK");
		  //send.writeUTF("404 Not Found");
		  System.out.println("I am here two");
		  }
		  catch (NullPointerException e)
		  {
			  send.writeUTF("bad");
		  }
	  }
	  else if(message.contains("LIST"))
	  {
		  System.out.println(portNumber + "\n");
		  System.out.println(host);
		  StringBuilder a = new StringBuilder();
		  for(int x=0;x<activePeers.size();x++)
		  {
			  String list_name = "RFC " + activePeers.get(x).getRFC() + " " + activePeers.get(x).getTitle() + " " + activePeers.get(x).getHost() + " " + activePeers.get(x).getPortNumber() + "\n"; 
			  a.append(list_name);
		  }
		  System.out.println(a);
		  //send.writeUTF(a);
		  send.writeUTF("200 OK");
		  System.out.println("here three");
	  }
	  else
	  {
		  send.writeUTF("400 Bad Request");
	  }
	  send.writeUTF("505 P2P-CI Version not supported");
	  send.writeUTF("Thank you it has been received");
	  //dis.close(); // hang up! 
	  //send.close();// hang up!
	  //System.out.println(message);
	  //ss.close();
  	}
  }
}