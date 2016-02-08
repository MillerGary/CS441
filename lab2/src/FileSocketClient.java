import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.io.File;
import java.lang.*;

public class FileSocketClient {
    //instance variables
    private Socket s;

    public FileSocketClient(String host, int port, String fileName, File file) {
        try {
            //try initializing a new Socket object
            s = new Socket(host, port);
            //try sending size of file
            sendSize(file);
            //try sending file
            sendFile(fileName, file);
        } //error handling
        catch (Exception e) {
            e.printStackTrace();
        }
    } //FileSocketClient (constructor)

    //method to send files over client-server connection
    public void sendFile(String fileName, File file)
     //error handling
     throws IOException {
        //initialize output stream and file object to send
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        FileInputStream fis = new FileInputStream(fileName);
        //int size = fis.read(byte[] file);
        byte[] buffer = new byte[(int)file.length()];
        //System.out.println("Buffer size is: " +buffer.size()); //debugging output
        //write to server bytes of file
        while (fis.read(buffer) > 0) {
            dos.write(buffer);
        }
        //terminate connection

        fis.close();
        dos.close();
    }

    //method to send file size over client-server connection
    public void sendSize(File file)
     //error handling
     throws IOException {
        //initialize output stream and file object to send
        DataOutputStream dosFS = new DataOutputStream(s.getOutputStream());
        FileInputStream fisFS = new FileInputStream(file);
        int numBytes = (int)file.length();
        System.out.println("Client is sending " +numBytes+ " bytes to the server");
        dosFS.writeInt(numBytes);
        //terminate connection
        //dosFS.flush();
        //dosFS.close();
    }

    public static void main(String[] args) {
        //main method used to send the specified file to the server
        File file = new File(args[0]);
        String fileName = args[0];
        long start, stop;
        start = System.currentTimeMillis();
        FileSocketClient fc = new FileSocketClient("141.195.226.177", 12348, fileName, file);
        stop = System.currentTimeMillis();
        System.out.println("Elapsed Execution Time Was: " +((stop - start) / 1000.0));
    }

}
