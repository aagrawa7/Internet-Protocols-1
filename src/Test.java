
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("test file to make sure everything works.");
		System.out.println("test 2");
		String name = "Test 2 string";
		String name2 = "Test 3 String";
		System.out.println("hello test 3");
		
		String names = "ADD RFC 12354 P2P-CI/1.0 \n"
				 +"Host: thishost.csc.ncsu.edu \n"
				 + "Port: 5678 \n"
				 + "Title: A proferred Official ICP";
		
		String[] sentences = names.split(": ",4);
		String[] host = sentences[1].split("\n",2);
		String title = sentences[3];
		String[] port_divider = sentences[2].split("\n",2);
		String[] title_space = sentences[0].split(" ",4);
		String version = title_space[3];//this gets the P2P version for later check 
		int portNumber = Integer.parseInt(port_divider[0].trim()); //this gets port number
		int rfcNumber = Integer.parseInt(title_space[2].trim()); //this gets RFC number
		System.out.println("RFC: "+ rfcNumber);
		System.out.println("Host: " + host[0].trim());
		System.out.println("Port Number: " + portNumber);
		System.out.println("Title: " + title);
		
	}
}
