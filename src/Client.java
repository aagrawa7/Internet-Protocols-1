import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
	public static void main(String[] args) throws Exception {
		String name = "Peer C, no local RFCs";
		Socket s = new Socket("localhost", 7734);//Set up Server
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		dos.writeUTF(name);
		System.out.println("Sent");
		DataInputStream dis = new DataInputStream(s.getInputStream());
		String answer = dis.readUTF();
		System.out.println(answer);// get confirmation
	} // end of main
} // end of class"