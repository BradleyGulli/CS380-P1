import java.net.Socket;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Listener extends Thread {
	
	private Socket socket;
	/*
	 * server socket
	 */
	public Listener(Socket socket){
		this.socket = socket;
	}
	
	/*
	 * used to make a listener thread to get messages from other users and from the server
	 */
	public void run(){
		try{
			/*
			 * read in messages
			 */
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String message = "";
			while( (message = br.readLine()) != null){
				System.out.println(message);
			}
			System.out.println("connection closed, press any key to end session");
			socket.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
