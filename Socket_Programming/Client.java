// Client2 class that
// sends data and receives also

import java.io.*;
import java.net.*;

public class Client {

	public static void main(String args[])
		throws Exception
	{

		// Create client socket
		Socket s = new Socket("localhost", 8000);

		// to send data to the server
		DataOutputStream dos
			= new DataOutputStream(
				s.getOutputStream());

		// to read data coming from the server
		BufferedReader br
			= new BufferedReader(
				new InputStreamReader(
					s.getInputStream()));

		// to read data from the keyboard
		BufferedReader kb
			= new BufferedReader(
				new InputStreamReader(System.in));
		String str = "";

        // send to the server
		dos.write(kb.read());

		while (!(str = br.readLine()).equals("Congratulations!")) {

            System.out.println(str);
			// send to the server
			dos.write(kb.read());

		}

        System.out.println("Congratulations!");
		// close connection.
		dos.close();
		br.close();
		kb.close();
		s.close();
	}
}
