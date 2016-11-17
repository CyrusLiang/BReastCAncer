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
public class Grid {
    
    // store final score
    private String nucA = "", nucB = "";
    private final int lenA, lenB;
    private final int[][] myGrid;
    
    // store input as nucleotides and create grid
    public Grid(String inputA, String inputB) {
        this.nucA = " " + inputA;
        this.nucB = " " + inputB;
        this.lenA = nucA.length();
        this.lenB = nucB.length();
        this.myGrid = new int[lenA][lenB];
        System.out.println("lenA: " + lenA + " lenB: " + lenB);
        
        populateGrid(lenA, lenB);
    }
    
    // uses the nucleotide seuqences and the grid to return the best score
    private void populateGrid(int lenA, int lenB) {
        
        // initialize all the variables
        int i, j, top, left, corner, temp;
        
        // nested for loop to run through the 2d array
        for (i = 0; i < lenA; i++) {
            for (j = 0; j < lenB; j++) {
                // initialize top row and left column to 0
                if (i == 0 || j == 0)
                    this.myGrid[i][j] = 0;
                // fill in the rest of the array with correct score
                else {
                    top = this.myGrid[i-1][j] - 2;
                    left = this.myGrid[i][j-1] - 2;
                    corner = this.myGrid[i-1][j-1];
                    corner = (nucA.charAt(i) == nucB.charAt(j)) ? corner + 1 : corner - 1;
                    
                    // compare top and left score first
                    temp = (top >= left) ? top : left;
                    // then compare corner with temp
                    this.myGrid[i][j] = (temp >= corner) ? temp : corner;
                }
            }
        }
    }
    
    // return integer score at row i and column j
    public int getScore(int i, int j) {
        if (i < 0 || j < 0 || i > lenA || j > lenB) {
            System.out.println("out of bounds.");
            System.out.println("please pass integer between 0 and " + lenA
                    + "and integer between 0 and " + lenB);
        }
        return myGrid[i][j];
    }
}
