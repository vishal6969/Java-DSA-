// Server2 class that
// receives data and sends data

import java.io.*;
import java.net.*;
import java.util.Random;

public class Server {

	public static void main(String args[])
		throws Exception
	{

		// Create server Socket
		ServerSocket ss = new ServerSocket(8000);

		// connect it to client socket
		Socket s = ss.accept();
		System.out.println("Connection established");

		// to send data to the client
		PrintStream ps
		    = new PrintStream(s.getOutputStream());

	    // to read data coming from the client
	    BufferedReader br
		    = new BufferedReader(
			    new InputStreamReader(
				    s.getInputStream()));


		    int guess = -1;
			Random r = new Random();
			int number = r.nextInt(100);

			// server executes continuously
			while ((guess = br.read()) != number) {
	        	
				// send to client
				if(guess < number) {
					ps.println("too low...");
				}
				else if(guess > number) {
					ps.println("too high");
				}
			}

			ps.println("Congratulations!");

			// close connection
			ps.close();
			br.close();
			ss.close();
			s.close();
	}
}
