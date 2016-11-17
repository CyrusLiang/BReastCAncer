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
    private String aaA = "", aaB = "";
    private final int lenA, lenB;
    private final int[][] myGrid;
    
    // store input as amino acids and create grid
    public Grid(String inputA, String inputB) {
        this.aaA = " " + inputA;
        this.aaB = " " + inputB;
        this.lenA = aaA.length();
        this.lenB = aaB.length();
        this.myGrid = new int[lenA][lenB];
        System.out.println("lenA: " + lenA + " lenB: " + lenB);
        
        populateGrid(lenA, lenB);
    }
    
    // uses the amino acid seuqences and the grid to return the best score
    private void populateGrid(int lenA, int lenB) {
        
        // initialize all the variables
        int i, j, top, left, corner, temp;
        
        // nested for loop to run through the 2d array
        for (i = 0; i < lenA; i++) {
            for (j = 0; j < lenB; j++) {
                // test [0][0] case
                if (i == 0 && j == 0)
                    this.myGrid[i][j] = 0;
                // initialize the top row
                else if (i == 0 && j != 0)
                    this.myGrid[i][j] = this.myGrid[i][j-1] - 2;
                // initialize the left column
                else if (j == 0 && i != 0)
                    this.myGrid[i][j] = this.myGrid[i-1][j] - 2;
                // fill in the rest of the array with correct score
                else {
                    top = this.myGrid[i-1][j] - 2;
                    left = this.myGrid[i][j-1] - 2;
                    corner = this.myGrid[i-1][j-1];
                    temp = compareAA(aaA.charAt(i), aaB.charAt(j));
                    corner = corner + temp;
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

    // return integer score comparison of two amino acids
    private int compareAA(char charA, char charB) {
        
        // score based on BLOSUM62
        int score = 0;
        switch (charA) {
            case 'C': 
                switch (charB) {
                    case 'W': score = -2;
                    case 'Y': score = -2;
                    case 'F': score = -2;
                    case 'V': score = -1;
                    case 'L': score = -1;
                    case 'I': score = -1;
                    case 'M': score = -1;
                    case 'K': score = -3;
                    case 'R': score = -3;
                    case 'H': score = -3;
                    case 'Q': score = -3;
                    case 'E': score = -4;
                    case 'D': score = -3;
                    case 'N': score = -3;
                    case 'G': score = -3;
                    case 'A': score = 0;
                    case 'P': score = -3;
                    case 'T': score = -1;
                    case 'S': score = -1;
                    case 'C': score = 9;
                    break;
                }
            case 'S':
                switch (charB) {
                    case 'W': score = -3;
                    case 'Y': score = -2;
                    case 'F': score = -2;
                    case 'V': score = -2;
                    case 'L': score = -2;
                    case 'I': score = -2;
                    case 'M': score = -1;
                    case 'K': score = 0;
                    case 'R': score = -1;
                    case 'H': score = -1;
                    case 'Q': score = 0;
                    case 'E': score = 0;
                    case 'D': score = 0;
                    case 'N': score = 1;
                    case 'G': score = 0;
                    case 'A': score = 1;
                    case 'P': score = -1;
                    case 'T': score = 1;
                    case 'S': score = 4;
                    break;
                }
            case 'T':
                switch (charB) {
                    case 'W': score = -2;
                    case 'Y': score = -2;
                    case 'F': score = -2;
                    case 'V': score = 0;
                    case 'L': score = -1;
                    case 'I': score = -1;
                    case 'M': score = -1;
                    case 'K': score = -1;
                    case 'R': score = -1;
                    case 'H': score = -2;
                    case 'Q': score = -1;
                    case 'E': score = -1;
                    case 'D': score = -1;
                    case 'N': score = 0;
                    case 'G': score = -2;
                    case 'A': score = 0;
                    case 'P': score = -1;
                    case 'T': score = 5;
                    break;
                }
            case 'P':
                switch (charB) {
                    case 'W': score = -4;
                    case 'Y': score = -3;
                    case 'F': score = -4;
                    case 'V': score = -2;
                    case 'L': score = -3;
                    case 'I': score = -3;
                    case 'M': score = -2;
                    case 'K': score = -1;
                    case 'R': score = -2;
                    case 'H': score = -2;
                    case 'Q': score = -1;
                    case 'E': score = -1;
                    case 'D': score = -1;
                    case 'N': score = -2;
                    case 'G': score = -2;
                    case 'A': score = -1;
                    case 'P': score = 7;
                    break;
                }
            case 'A':
                switch (charB) {
                    case 'W': score = -3;
                    case 'Y': score = -2;
                    case 'F': score = -2;
                    case 'V': score = 0;
                    case 'L': score = -1;
                    case 'I': score = -1;
                    case 'M': score = -1;
                    case 'K': score = -1;
                    case 'R': score = -1;
                    case 'H': score = -2;
                    case 'Q': score = -1;
                    case 'E': score = -1;
                    case 'D': score = -2;
                    case 'N': score = -2;
                    case 'G': score = 0;
                    case 'A': score = 4;
                    break;
                }
            case 'G':
                switch (charB) {
                    case 'W': score = -2;
                    case 'Y': score = -3;
                    case 'F': score = -3;
                    case 'V': score = -3;
                    case 'L': score = -4;
                    case 'I': score = -4;
                    case 'M': score = -3;
                    case 'K': score = -2;
                    case 'R': score = -2;
                    case 'H': score = -2;
                    case 'Q': score = -2;
                    case 'E': score = -2;
                    case 'D': score = -1;
                    case 'N': score = 0;
                    case 'G': score = 6;
                    break;
                }
            case 'N':
                switch (charB) {
                    case 'W': score = -4;
                    case 'Y': score = -2;
                    case 'F': score = -3;
                    case 'V': score = -3;
                    case 'L': score = -3;
                    case 'I': score = -3;
                    case 'M': score = -2;
                    case 'K': score = 0;
                    case 'R': score = 0;
                    case 'H': score = 1;
                    case 'Q': score = 0;
                    case 'E': score = 0;
                    case 'D': score = 1;
                    case 'N': score = 6;
                    break;
                }
            case 'D':
                switch (charB) {
                    case 'W': score = -4;
                    case 'Y': score = -3;
                    case 'F': score = -3;
                    case 'V': score = -3;
                    case 'L': score = -4;
                    case 'I': score = -3;
                    case 'M': score = -3;
                    case 'K': score = -1;
                    case 'R': score = -2;
                    case 'H': score = -1;
                    case 'Q': score = 0;
                    case 'E': score = 2;
                    case 'D': score = 6;
                    break;
                }
            case 'E':
                switch (charB) {
                    case 'W': score = -3;
                    case 'Y': score = -2;
                    case 'F': score = -3;
                    case 'V': score = -2;
                    case 'L': score = -3;
                    case 'I': score = -3;
                    case 'M': score = -2;
                    case 'K': score = 1;
                    case 'R': score = 0;
                    case 'H': score = 0;
                    case 'Q': score = 2;
                    case 'E': score = 5;
                    break;
                }
            case 'Q':
                switch (charB) {
                    case 'W': score = -2;
                    case 'Y': score = -1;
                    case 'F': score = -3;
                    case 'V': score = -2;
                    case 'L': score = -2;
                    case 'I': score = -3;
                    case 'M': score = 0;
                    case 'K': score = 1;
                    case 'R': score = 1;
                    case 'H': score = 0;
                    case 'Q': score = 5;
                    break;
                }
            case 'H':
                switch (charB) {
                    case 'W': score = -2;
                    case 'Y': score = 2;
                    case 'F': score = -1;
                    case 'V': score = -3;
                    case 'L': score = -3;
                    case 'I': score = -3;
                    case 'M': score = -2;
                    case 'K': score = -1;
                    case 'R': score = 0;
                    case 'H': score = 8;
                    break;
                }
            case 'R':
                switch (charB) {
                    case 'W': score = -3;
                    case 'Y': score = -2;
                    case 'F': score = -3;
                    case 'V': score = 3;
                    case 'L': score = -2;
                    case 'I': score = -3;
                    case 'M': score = -1;
                    case 'K': score = 2;
                    case 'R': score = 5;
                    break;
                }
            case 'K':
                switch (charB) {
                    case 'W': score = -3;
                    case 'Y': score = -2;
                    case 'F': score = -3;
                    case 'V': score = 2;
                    case 'L': score = -2;
                    case 'I': score = -3;
                    case 'M': score = -1;
                    case 'K': score = 5;
                    break;
                }
            case 'M':
                switch (charB) {
                    case 'W': score = -1;
                    case 'Y': score = -1;
                    case 'F': score = 0;
                    case 'V': score = 1;
                    case 'L': score = 2;
                    case 'I': score = 1;
                    case 'M': score = 5;
                    break;
                }
            case 'I':
                switch (charB) {
                    case 'W': score = -3;
                    case 'Y': score = -1;
                    case 'F': score = 0;
                    case 'V': score = 3;
                    case 'L': score = 2;
                    case 'I': score = 4;
                    break;
                }
            case 'L':
                switch (charB) {
                    case 'W': score = -2;
                    case 'Y': score = -1;
                    case 'F': score = 0;
                    case 'V': score = 1;
                    case 'L': score = 4;
                    break;
                }
            case 'V':
                switch (charB) {
                    case 'W': score = -3;
                    case 'Y': score = -1;
                    case 'F': score = -1;
                    case 'V': score = 4;
                    break;
                }
            case 'F':
                switch (charB) {
                    case 'W': score = 1;
                    case 'Y': score = 3;
                    case 'F': score = 6;
                    break;
                }
            case 'Y':
                switch (charB) {
                    case 'W': score = 2;
                    case 'Y': score = 7;
                    break;
                }
            case 'W':
                switch (charB) {
                    case 'W': score = 11;
                    break;
                }
                break;
            default:
                System.out.println("Invalid Amino Acid");
                break;
        } // never again ...
        
        return score;
    }
}
