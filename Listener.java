import java.net.Socket;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Listener extends Thread {
	
	private Socket socket;
	
	public Listener(Socket socket){
		this.socket = socket;
	}
	
	public void run(){
		try{
			
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			
			
			while(true){
				System.out.println(br.readLine());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
