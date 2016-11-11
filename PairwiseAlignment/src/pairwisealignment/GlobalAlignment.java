/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pairwisealignment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Cyrus Liang
 * Prof. Phillip Heller
 * CS123A
 * Bioinformatics Project: BReastCAncer
 */
public class GlobalAlignment {
    
    // store nucleotide sequences
    private String nucA = "", nucB = "";
    // store the bottom right grid score
    private int bestScore = 0;
    
    // create obj
    public GlobalAlignment() {
        
    }
    
    // create obj and call Needleman-Wunsch
    public GlobalAlignment(String fileNameA, String fileNameB) {
        
        String inputA = "", inputB = "";
        
        // This will reference one line at a time
        String line;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileNameA);

            // Wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                inputA = inputA + line;
            }   

            // Close files.
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
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        // check inputA string
        System.out.println(inputA);
        
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileNameB);

            // Wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                inputB = inputB + line;
            }   

            // Close files.
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
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        // check inputA string
        System.out.println(inputB);
        
        setNucA(inputA);
        setNucB(inputB);
        
        // this function is mutable for more optimal solutions
        needlemanWunsch(inputA, inputB);
    }
    
    // return string nucleotide A
    public String getNucA() {
        return nucA;
    }
    
    // return string nucleotide B
    public String getNucB() {
        return nucB;
    }
    
    // return integer best score
    public int getBestScore() {
        return bestScore;
    }
    
    // set nucleotide A
    public void setNucA(String inputA) {
        this.nucA = inputA;
    }
    
    // set nucleotide B
    public void setNucB(String inputB) {
        this.nucB = inputB;
    }
    
    // takes 2 nucleotide sequences and creates a grid
    // call grid getScore() function for the best score
    public void needlemanWunsch(String nucA, String nucB) {
        Grid myGrid = new Grid(nucA, nucB);
        this.bestScore = myGrid.getScore(nucA.length(), nucB.length());
    }
}
