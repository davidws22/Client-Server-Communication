import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.Thread;


public class EchoServer
{
    static Socket socket;
   
    public static void main(String[] args) throws Exception
    {
	try 
        {
	    
	        ServerSocket serverSocket = new ServerSocket(22222);
		//Server is running always. This is done using this while(true) loop
	
		while(true)
		    {
			Runnable server = () -> {
			try
			{
			    
			Socket sock = socket;
			String address = socket.getInetAddress().getHostAddress();
			System.out.printf("Client connected: %s%n", address);
			
						
			//Reading the message from the client
			InputStream is = sock.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String number;
			String message;


			//Sending the response back to the client.
			OutputStream os = sock.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);

			while((number = br.readLine()) != null)
			    {
				if(number.equals("exit"))
				    {
					System.out.printf("Client disconnected: %s%n", address);
					sock.close();
					number = null;
					break;
				    }
				else
				    {
					//System.out.println("Message sent to the client is "+returnMessage);
					message = number + '\n';
					bw.write(message);
					bw.flush();
				    }
			    }//end while
		      
			}//end inner-try
		       	catch (Exception e)
		        {
			    e.printStackTrace();
			}//end inner try-catch block
			}; //end runnable

			try{
			    Socket s = serverSocket.accept();
			    EchoServer.socket = s;
			    Thread myThread = new Thread(server);
			    myThread.start();
			}
				
			catch (Exception e)
			    {
				e.printStackTrace();
			    }//end try-catch block

		    }//end while
		
	}//end first try block
	catch(Exception e)
	    {
		e.printStackTrace();
	    }
    }
}

			      
