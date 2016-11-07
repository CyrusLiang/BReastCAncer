/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pairwisealignment;

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
    
    // create obj and call Needleman-Wunsch
    public GlobalAlignment(String inputA, String inputB) {
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
    private void setNucA(String inputA) {
        this.nucA = inputA;
    }
    
    // set nucleotide B
    private void setNucB(String inputB) {
        this.nucB = inputB;
    }
    
    // takes 2 nucleotide sequences and creates a grid
    // call grid getScore() function for the best score
    private void needlemanWunsch(String nucA, String nucB) {
        Grid myGrid = new Grid(nucA, nucB);
        this.bestScore = myGrid.getScore(nucA.length(), nucB.length());
    }
}
