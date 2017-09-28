import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer
{

    private static Socket socket;
   
    public static void main(String[] args) throws Exception
    {
	    
	
	try (ServerSocket serverSocket = new ServerSocket(22222))
	    {
		int temp = 1;
		//Server is running always. This is done using this while(true) loop
		while(true)
		    {
			try
			{
			Socket socket = serverSocket.accept();
			String address = socket.getInetAddress().getHostAddress();
			if(temp == 1)
			{
			    System.out.printf("Client connected: %s%n", address);
			    temp--;
			}

			//Reading the message from the client
			//socket = serverSocket.accept();
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String number = br.readLine();
			//System.out.println("Message received from client is "+number);
			if (number == null)
			    {
				System.out.println("Client disconnected: " + address);
			     
			    }
			String returnMessage = number + '\n';
				

			//Sending the response back to the client.
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write(returnMessage);
			//System.out.println("Message sent to the client is "+returnMessage);
			bw.flush();
			}//end inner-try
		       	catch (Exception e)
			    {
				e.printStackTrace();
			    }//end inner try-catch block

	       }//end while
	    }//end first try
	    catch (Exception e)
	    {
		e.printStackTrace();
	    }
	finally
	    {
	        try
		{
		    socket.close();
		}
		catch(Exception e){}
	    }
    }
}
			      
