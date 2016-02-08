import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileSocketServer extends Thread {
    //instance variables
    private ServerSocket ss;

    public FileSocketServer(int port) {
        try {
            //try initializing a new server socket with given port number
            ss = new ServerSocket(port);
        } //error handling
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //method to run server until terminated
    public void run() {
        while (true) {
            try {
                Socket clientSock = ss.accept();
                int size = getSize(clientSock);
                saveFile(clientSock, size);
            } //error handling
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //method used to get size of file being sent by the client
    private int getSize(Socket clientSock)
     throws IOException { //error handling
        DataInputStream disFS = new DataInputStream(clientSock.getInputStream());
        int FS = disFS.readInt();
        System.out.println("Server thinks the file size is: " +FS);
        return FS;
    }

    //method used to save the file received from the client in specified directory
    private void saveFile(Socket clientSock, int FS)
     throws IOException { //error handling
        //initialize an input stream and file to write to
        DataInputStream dis = new DataInputStream(clientSock.getInputStream());
        FileOutputStream fos = new FileOutputStream("files/received/Received.pdf");
        byte[] buffer = new byte[FS];
        System.out.println("Buffer size is: " +buffer.length);
        //parameters for file object
        //Send the file size in a separate message, hard-coded for now
        int filesize = FS;
        int read = 0;
        int totalRead = 0;
        int remaining = filesize;
        System.out.println("Now it thinks file size is: " +filesize);
        //writing to file
        while((read = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
            totalRead += read;
            remaining -= read;
            System.out.println("read " + totalRead + " bytes.");
            fos.write(buffer, 0, read);
        }

        //close DataInputStream and file writer
        fos.close();
        dis.close();
    }

    //main method used to start the server connection
    public static void main(String[] args) {
        //initiate a server with a port number
        FileSocketServer fs = new FileSocketServer(12348);
        //start the server connection
        fs.start();
    }

}
