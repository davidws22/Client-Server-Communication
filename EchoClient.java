import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient
{

    private static Socket socket;

    public static void main(String args[])
    {
	Scanner sc = new Scanner(System.in);

	while(true)
	{
	    try
	    {
		       
			Socket socket = new Socket("localhost", 22222);
			//Send the message to the server
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);

						//Get the return message from the server
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String number, sendMessage, message;
			
			System.out.print("Client> ");
			number = sc.nextLine();
			
			if(number.equals("exit"))
			   {
			       try{ socket.close();
				   return;}
			       catch(Exception e)
				   {e.printStackTrace();
				   }
			   }
			
			sendMessage = number + "\n";
			bw.write(sendMessage);
			bw.flush();

			//System.out.println("Message sent to the server: " + sendMessage);
			message = br.readLine();
			System.out.println("Server> " + message);
   
	    }//end try block
			
	

    
	catch (Exception exception)
	    {
		exception.printStackTrace();
	    }

	}//end while loop

	/*
	finally
	    {
		//Closing the socket
	            try
			{
			    //    socket.close();
			}
		    catch(Exception e)
			{
			    e.printStackTrace();
			}
	    }//end finally
	*/
    }//end main
}//end class
