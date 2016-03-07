//import necessary packages
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MulticastSocketSender {

  // This example is from: http://examples.javacodegeeks.com/core-java/net/multicastsocket-net/java-net-multicastsocket-example/

  final static String INET_ADDR = "224.0.0.3"; //sets the INET address for clients to connect to
  final static int PORT = 12345; //sets the port number for clients to connect to

  public static void main(String[] args) throws UnknownHostException, InterruptedException {
    // Get the address that we are going to connect to.
    InetAddress addr = InetAddress.getByName(INET_ADDR);

    // Open a new DatagramSocket, which will be used to send the data.
    try (DatagramSocket serverSocket = new DatagramSocket()) {
      for (int i = 0; i < 5; i++) {
        String msg = "Sent message number: " + i; //sends multiple messages over the network

        // Create a packet that will contain the data
        // (in the form of bytes) and send it.
        DatagramPacket msgPacket = new DatagramPacket(msg.getBytes(),
            msg.getBytes().length, addr, PORT);
        serverSocket.send(msgPacket);
        //output to show the messages were successfully sent
        System.out.println("The server sent a packet with this message: " + msg);
        Thread.sleep(500); //makes thread sleep for 500 milliseconds
      } //for
    } //try
    //error handling
    catch (IOException ex) {
      ex.printStackTrace();
    } //catch- errors
  } //main
} //class- MulticastSocketSender
