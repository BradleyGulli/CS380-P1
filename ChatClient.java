import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;


public class ChatClient {
	public static void main(String[] args) throws Exception{
		try(Socket socket = new Socket("codebank.xyz", 38001)){
			
			/*
			 * used to send messages to the server
			 */
			OutputStream out = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(out);
			
			/*
			 * used to get messages from the client to send to server
			 */
			InputStreamReader cIn = new InputStreamReader(System.in);
			BufferedReader client = new BufferedReader(cIn);
			
			/*
			 * Listener is a separate thread that gets messages from the server (the other users)  
			 */
			Listener l = new Listener(socket);
			l.start();
			
			/*
			 * gets the user name from the user
			 */
			System.out.println("please enter a user name");
			String username = client.readLine();
			pw.println(username);
			pw.flush();
			Thread.sleep(1000);
			if(l.isAlive()){
				System.out.println("You are connected and can start sending messages");
			}
		
			/*
			 * continues to send messages to the server until the client is closed
			 */
			while(l.isAlive()){
				pw.println(client.readLine());
				pw.flush();
			}
			
		}
	}
}
