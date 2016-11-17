package pairwisealignment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Cyrus Liang
 * Prof. Phillip Heller
 * CS123A
 * Bioinformatics Project: BReastCAncer
 */
public class GlobalAlignment {
    
    // store amino acid sequences
    private String aaA = "", aaB = "";
    // store the bottom right grid score
    private int globalScore = 0;
    
    // stores grid of scores
    private Grid myGrid;
    
    // create obj
    public GlobalAlignment() {
        
    }
    
    // create obj and call Needleman-Wunsch
    public GlobalAlignment(String fileNameA, String fileNameB) {
        
        String inputA = "", inputB = "";
        
        // This will reference one line at a time
        String line;

        try {
            // FileReader reads text files in the default encoding
            FileReader fileReader = new FileReader(fileNameA);

            // Wrap FileReader in BufferedReader
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // concatenate amino acid sequence
            while((line = bufferedReader.readLine()) != null) {
                inputA = inputA + line;
            }   

            // Close files
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileNameA + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileNameA + "'");                  
            // ex.printStackTrace();
        }
        
        // check inputA string
        System.out.println(inputA);
        
        try {
            // FileReader reads text files in the default encoding
            FileReader fileReader = new FileReader(fileNameB);

            // Wrap FileReader in BufferedReader
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // concatenate amino acid sequence
            while((line = bufferedReader.readLine()) != null) {
                inputB = inputB + line;
            }   

            // Close files
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileNameB + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileNameB + "'");                  
            // ex.printStackTrace();
        }
        
        // check inputA string
        System.out.println(inputB);
        
        // set class variables
        this.aaA = inputA;
        this.aaB = inputB;
        
        // takes 2 amino acid sequences and creates a grid
        this.myGrid = new Grid(aaA, aaB);
        
        // sets global score from the final cell in the grid
        this.globalScore = myGrid.getScore(aaA.length(), aaB.length());
    }
    
    // return string amino acid A
    public String getNucA() {
        return aaA;
    }
    
    // return string amino acid B
    public String getNucB() {
        return aaB;
    }
    
    // return integer global score
    public int getGlobalScore() {
        return globalScore;
    }
    
    // return integer best score
    public int getBestScore() {
        
        // lengths of aaA and aaB
        int aLength = aaA.length(), bLength = aaB.length();
        // initialize best as minimum integer value
        int best = Integer.MIN_VALUE;
        
        // nested for loop to go through 2d array searching for greatest integer
        for (int i = 0; i < aLength; i++) {
            for (int j = 0; j < bLength; j++) {
                int temp = myGrid.getScore(i, j);
                best = (temp > best) ? temp : best;
            }
        }
        
        return best;
    }
    
    // set amino acid A
    public void setNucA(String inputA) {
        this.aaA = inputA;
    }
    
    // set amino acid B
    public void setNucB(String inputB) {
        this.aaB = inputB;
    }
    
}
