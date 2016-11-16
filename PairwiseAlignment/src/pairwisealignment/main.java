/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pairwisealignment;
import java.io.*;
import java.util.ArrayList;

/**
 *
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
        // TODO code application logic here
        
        // store input strings
        ArrayList<String> input = new ArrayList<>();
        
        // grab input file names
        // The name of the file to open.
        String fileName = args[0];
        
        // This will reference one line at a time
        String line;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                input.add(line);
            }   

            // Close files.
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
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        // check inputA string
        // System.out.println(input);
        
        int size = input.size();
        String[] filenames = new String[size];
        for (int mySeq = 0; mySeq < size; mySeq++) {
            filenames[mySeq] = input.get(mySeq);
            System.out.println("File: " + mySeq + " " + filenames[mySeq]);
        }
        
        System.out.println("Done with reading files.");
        
        ArrayList<String> pairScores = new ArrayList<>();
        
        // run global alignment on A and B
        GlobalAlignment myObj;
        int i = 0, j = 0;
        
        for (i = 0; i < size - 1; i++) {
            for (j = i + 1; j < size; j++) {
                System.out.println("Working on: " + i + " and " + j);
                myObj = new GlobalAlignment(filenames[i], filenames[j]);
                pairScores.add(i + " " + j + " " + myObj.getBestScore());
            }
        }
        
        int msaSize = (size - 1) * (size) / 2;
        String[] multiSeqAlignment = new String[msaSize];
        for (int myMSA = 0; myMSA < msaSize; myMSA++) {
            multiSeqAlignment[myMSA] = pairScores.get(myMSA);
        }
        
        System.out.println("Done with MSA.");
        
        // The name of the file to open.
        fileName = args[1];

        try {
            FileWriter fileWriter = new FileWriter(fileName);

            // Wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            bufferedWriter.write(size + "");
            bufferedWriter.newLine();
            bufferedWriter.write(multiSeqAlignment.length + "");
            bufferedWriter.newLine();
            
            for (i = 0; i < size; i++) {
                bufferedWriter.write(i + ": " + filenames[i]);
                bufferedWriter.newLine();
            }
            
            // append a newline character.
            int sizeOut = multiSeqAlignment.length;
            for (i = 0; i < sizeOut; i++) {
                bufferedWriter.write(multiSeqAlignment[i]);
                bufferedWriter.newLine();
                System.out.println(multiSeqAlignment[i]);
            }

            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '" + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
        
        // display execution confirmation
        System.out.println("done.");
    }
    
}
