//imports package used to utilize File objects
import java.io.File;
//imports packages used to read a file by bytes
import java.io.FileInputStream;
//imports package that handles I/O errors
import java.io.IOException;

public class fileReadBenchmark {
    public static void main (String[] args) {
        //Get path of input file from command line arguement
        String name = args[0];
        //name of file to write to
        File file = new File(name);
        //variables to measure bytes in file that is being read
        float B = file.length();
        //variables for start and stop time of file read
        float start;
        float stop;
        //variable to measure throughput
        float THR;
        //try reading file
        try {
            //FileRead object used to read from file
            FileInputStream fis = new FileInputStream(file);
            //starting time of file read
            start = System.nanoTime();
            //while loop reads file
            //no are done within the loop to show the true runtime of reading file
            while (fis.read() != -1) {}
            //stop time of file read
            stop = System.nanoTime();
            //number of bytes scaled to kilobytes
            float KB = B / 1000;
            //variable to calculate response time, scaled back to Seconds
            float RT = (stop - start) / 1000000000;
            //throughput is measure as Kilobytes per Seconds
            THR = KB / RT;
            System.out.printf("Number Kilobytes in file is: %.3f", KB);
            System.out.printf("\nNumber of Seconds it took to read the file was: %.3f", RT);
            System.out.printf("\nThroughput for reading the file was: %.3f", THR);
            System.out.print(" Kilobytes per Second\n");
            //close FileInputStream
            fis.close();
        }
        //error handling
        catch(IOException ex) {
            System.out.println("Error reading from file: " +file);
        }
    }
}

