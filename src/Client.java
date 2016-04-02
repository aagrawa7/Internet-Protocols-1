import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
	public static void main(String[] args) throws Exception {
		String addMessage = "ADD RFC 123 P2P-CI/1.0 \n"
					 +"Host: thishost.csc.ncsu.edu \n"
					 + "Port: 5678 \n"
					 + "Title: A proferred Official ICP";
		
		String lookMessage = "LOOKUP RFC 3457 P2P-CI/1.0 \n"
				 		+"Host: thishost.csc.ncsu.edu \n"
				 		+ "Port: 5780 \n"
				 		+ "Title: A proferred Official ICP";
				
		String listMessage = "LIST ALL P2P-CI/1.0\n"
							 +"Host: thishost.csc.ncsu.edu\n"
							 +"Port: 5678\n";
		
		Socket s = new Socket("localhost", 7734);//Set up Server
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		//dos.writeUTF(addMessage);
		dos.writeUTF(lookMessage);
		System.out.println("Sent");
		DataInputStream dis = new DataInputStream(s.getInputStream());
		String answer = dis.readUTF();
		System.out.println(answer);// get confirmation
	} // end of main
} // end of class"