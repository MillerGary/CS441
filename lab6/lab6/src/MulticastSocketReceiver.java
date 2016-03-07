//import necessary packages
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class MulticastSocketReceiver {

  // This example is from: http://examples.javacodegeeks.com/core-java/net/multicastsocket-net/java-net-multicastsocket-example/

  final static String INET_ADDR = "224.0.0.3"; //sets the INET address for the receivers to connect to
  final static int PORT = 12345; //sets the port number for the receivers to connect to

  public static void main(String[] args) throws UnknownHostException {
    // Get the address that we are going to connect to.
    InetAddress address = InetAddress.getByName(INET_ADDR);

    // Create a buffer of bytes, which will be used to store
    // the incoming bytes containing the information from the server.
    // Since the message is small here, 256 bytes should be enough.
    byte[] buf = new byte[256];

    // Create a new Multicast socket (that will allow other sockets/programs
    // to join it as well.
    try (MulticastSocket clientSocket = new MulticastSocket(PORT)){
      //Joint the Multicast group.
      clientSocket.joinGroup(address);

      while (true) {
        // Receive the information and print it.
        DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
        clientSocket.receive(msgPacket); //receive message from server

        String msg = new String(buf, 0, buf.length); //allocate memory for incoming messages
        System.out.println("The socket received this message: " + msg); //indicates that message was received
      } //while
    } //try
    //error handling
    catch (IOException ex) {
      ex.printStackTrace();
    } //catch- errors
  } //main
} //class- MulticastSocketReceiver