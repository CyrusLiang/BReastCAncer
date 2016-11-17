package pairwisealignment;
import java.io.*;
import java.util.ArrayList;

/**
 * @author Cyrus Liang
 * Prof. Phillip Heller
 * CS123A
 * Bioinformatics Project: BReastCAncer
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // store input strings
        ArrayList<String> input = new ArrayList<>();
        
        // grab input file names
        // The name of the file to open
        String fileName = args[0];
        
        // This will reference one line at a time
        String line;

        // read and write file implementation borrowed from
        // https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html
        try {
            // FileReader reads text files in the default encoding
            FileReader fileReader = new FileReader(fileName);

            // Wrap FileReader in BufferedReader
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // add file paths to arraylist
            while((line = bufferedReader.readLine()) != null) {
                input.add(line);
            }   

            // Close files
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // ex.printStackTrace();
        }
        // check inputA string
        // System.out.println(input);
        
        // create array to hold file paths
        int size = input.size();
        String[] filenames = new String[size];
        
        // move file paths into array for easier access
        for (int mySeq = 0; mySeq < size; mySeq++) {
            filenames[mySeq] = input.get(mySeq);
            System.out.println("File: " + mySeq + " " + filenames[mySeq]);
        }
        
        // display execution confirmation of transering file paths to array
        System.out.println("Done with reading files.");
        
        
        // create arraylist to hold scores
        ArrayList<String> pairScores = new ArrayList<>();
        
        // run global alignment on A and B
        GlobalAlignment myObj;
        int i = 0, j = 0;
        
        // neseted loop to compare all values for handshakes
        for (i = 0; i < size - 1; i++) {
            for (j = i + 1; j < size; j++) {
                System.out.println("Working on: " + i + " and " + j);
                myObj = new GlobalAlignment(filenames[i], filenames[j]);
                pairScores.add(i + " " + j + " " + myObj.getBestScore());
            }
        }
        
        // calculate size of difference chart needed to hold all scores
        int msaSize = (size - 1) * (size) / 2;
        String[] multiSeqAlignment = new String[msaSize];
        
        // move scores from array list to array
        for (int myMSA = 0; myMSA < msaSize; myMSA++) {
            multiSeqAlignment[myMSA] = pairScores.get(myMSA);
        }
        
        // display execution confirmation of Multiple Sequence Alignment
        System.out.println("Done with MSA.");
        
        // The name of the output file to open
        fileName = args[1];

        try {
            FileWriter fileWriter = new FileWriter(fileName);

            // Wrap FileWriter in BufferedWriter
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            // Write number of sequences
            bufferedWriter.write(size + "");
            bufferedWriter.newLine();
            
            // Write number of scores in the difference chart
            bufferedWriter.write(multiSeqAlignment.length + "");
            bufferedWriter.newLine();
            
            // Write file names and index to output.txt
            for (i = 0; i < size; i++) {
                bufferedWriter.write(i + ": " + filenames[i]);
                bufferedWriter.newLine();
            }
            
            // Write scores into output.txt
            // format: "{A} {B} {Score}"
            int sizeOut = multiSeqAlignment.length;
            for (i = 0; i < sizeOut; i++) {
                bufferedWriter.write(multiSeqAlignment[i]);
                bufferedWriter.newLine();
                System.out.println(multiSeqAlignment[i]);
            }

            // Close files
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '" + fileName + "'");
            // ex.printStackTrace();
        }
        
        // display execution confirmation
        System.out.println("Done :D");
    }
}
