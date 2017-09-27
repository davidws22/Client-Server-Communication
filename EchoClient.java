// ============================================================================
// file: EchoClient.java
// ============================================================================
// Programmer: David Shin
// Date: 09/26/2017
// Class: CS 380("Computer Networks")
// Time: TTH 3:00pm
// Instructor: Mr. Davarpanah
// Assignment: 01
//
// Description:
//      
//
// ============================================================================

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public final class EchoClient {

    public static void main(String[] args) throws Exception {
	try (Socket socket = new Socket("localhost", 22222)) {
	    InputStream is = socket.getInputStream();
	    InputStreamReader isr = new InputStreamReader(is, "UTF-8");
	    BufferedReader br = new BufferedReader(isr);
	    System.out.println(br.readLine());
	}
    }
}
