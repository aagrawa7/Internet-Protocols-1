
public class PeerInformation {

	
	private int rfcNumber;
	private String host;
	private int portNumber;
	private String title;
	
	public PeerInformation(int rfcNumber, String host, int portNumber, String title ) {
		this.rfcNumber = rfcNumber;
		this.host = host;
		this.portNumber = portNumber;
		this.title = title;
	}

	//getter
	public int getRFC () {return rfcNumber;}
	public String getHost() {return host;}
	public int getPortNumber() {return portNumber;}
	public String getTitle() {return title;}
	
	//setter
	public void setRFC (int rfcNumber) {this.rfcNumber = rfcNumber;}
	public void setHost(String host) {this.host = host;}
	public void setPortNumber(String host) {this.host = host;}
	public void setTitle(String host) {this.host = host;}
	
	
	
	
}
