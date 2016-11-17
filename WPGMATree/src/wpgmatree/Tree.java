package wpgmatree;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * 
 * @author Patrick Solis
 * Prof. Phillip Heller
 * CS123A
 * Bioinformatics Project: BReastCAncer
 */
public class Tree {
	private int n;
	private int currentSize;
	private ArrayList<Node> sequences;
	ArrayList<Node> pairedSequences = new ArrayList<Node>();
	
	/**
	 * Creates new Tree object
	 * @param scores Current scores from the file
	 * @param numberOfSequences Amount of sequences 
	 * @param sequences ArrayList<Node> of the sequences
	 */
	public Tree(float[] scores, int numberOfSequences, ArrayList<Node> sequences)
	{
		n = numberOfSequences;
		currentSize = n;
		this.sequences = sequences;
	}
	
	/**
	 * Creates a new 2D array based on sized and places scores in it
	 * @param scores	The list of scores 
	 * @param size	The current size of the 2D array
	 * @return updated version of grid
	 */
	public float[][] makeGrid(float[] scores, int size)
	{
		float[][] grid = new float[size][size];
		int k = 0;	
		for(int i = 1; i < size; i++)
		{
			for(int j = 0; j < i; j++)
			{
				grid[i][j] = scores[k];
				k++;
			}
		}
		return grid;
	}
	
	/** 
	 * Prints out a formatted grid
	 * @param grid Uses content within 2D array
	 * @param size The current size of the 2D array
	 */
	public void printGrid(float[][] grid, int size)
	{
		//Formatting purposes
		System.out.print("\t");
		for(int i = 0; i < size; i++)
		{
			System.out.print(sequences.get(i).name + "\t\t");
		}
		System.out.println("");
		
		for(int i = 0; i < size; i++)
		{
			System.out.print(sequences.get(i).name + "\t");
			for(int j = 0; j < size; j++)
			{
				if(Float.toString(grid[i][j]).length() >= 8)
				{
					System.out.print(grid[i][j] + "\t");
				}
				else
				{
					System.out.print(grid[i][j] + "\t\t");
				}
			}
			System.out.println("");
		}
	}
	
	/**
	 * Pairs the closest nodes together and reduces the size of the array.
	 * Pairs will go be put into separate ArrayList<Node> called pairedSequences
	 * and removed from ArrayList<Node> sequences
	 * @param grid
	 */
	public void pair(float[][] grid)
	{
		float[][] newGrid = new float[n-1][n-1];
		int x = 0, y = 0, newPair = 0, oldPair = 0;
		float dx = 0, dy = 0, average = 0;
		float min = grid[1][0];
			
		//Creates arrays to separate paired scores and unpaired scores
		float[] oldScores = new float[currentSize*currentSize];
		float[] newScores = new float[currentSize*currentSize];
		
		//Finds max of current grid
		for(int i = 1; i < currentSize; i++)
		{
			for(int j = 0; j < i; j++)
			{
				if(grid[i][j] <= min && grid[i][j]!= 0)
				{
					//stores max
					//Saves location of max
					min = grid[i][j];
					x = i;
					y = j;
				}
			}
		}
		
		System.out.println("\nmin: " + min + "\nloc: " + x + y +"\n");
		
		//Sets scores to the appropriate node
		sequences.get(y).setScore(min/2);
		sequences.get(x).setScore(min/2);
		
		//Sets level based on pairing
		//Mainly used for tree printing
		
		int level = sequences.get(y).level;
		if(sequences.get(x).level > level){	
			level = sequences.get(x).level;
		}
		level++;

		//Creates newly paired Node
		//pairs parent and child nodes together
		sequences.add(new Node(sequences.get(y).name + sequences.get(x).name, 0));
		sequences.get(sequences.size()-1).left = sequences.get(y);
		sequences.get(sequences.size()-1).right = sequences.get(x);
		sequences.get(sequences.size()-1).level = level+1;
		sequences.get(y).parent = sequences.get(sequences.size()-1);
		sequences.get(x).parent = sequences.get(sequences.size()-1);
		
		//Checks if size of grid is greater than three.
		if(currentSize > 3)
		{
			//Averages scores based on the current nodes of the current minimum score
			//Example:
			//		Min location at [1,2]
			//		Averages out scores from 0 node: ([0,2] + [0,1]) /2		
			
			for(int i = 0; i < currentSize; i++)
			{
				if(i != x && i != y)
				{
					//if location of the grid is 0
					//swap x/y and i 
					if(grid[x][i] == 0){
						dx = grid[i][x];
					}
					else{
						dx = grid[x][i];
					}
					if(grid[y][i] == 0){
						dy = grid[i][y];
					}
					else{
						dy = grid[y][i];
					}
					//Stores averaged score
					newScores[newPair] = (dx + dy) / 2;
					//Increments up
					newPair++;
				}
			}
			//Puts unAveraged scores into separate oldScores array
			for(int i = 1; i < currentSize; i++)
			{
				for(int j = 0; j < i; j++)
				{
					if(i != x && i != y && j != x && j != y)
					{
						oldScores[oldPair] = grid[i][j];
						oldPair++;
					}
				}
			}
		
			//Adds updated nodes to pairedSequences then removes them from sequences
			pairedSequences.add(sequences.get(y));
			pairedSequences.add(sequences.get(x));
			sequences.remove(y);
			sequences.remove(x-1);
		
			//Reduces the current size by 1
			currentSize--;
			
			//Combines scores, oldScores being first then newScores after
			System.arraycopy(newScores, 0, oldScores, oldPair, newPair);
			//Creates new grid based on size and current scores
			newGrid = makeGrid(oldScores, currentSize);
			//prints out current grid
			printGrid(newGrid, currentSize);
			//Calls itself again
			pair(newGrid);
		}
		else
		{
			//Sets scores to Appropriate Nodes
			sequences.get(y).setScore(min/2);
			sequences.get(x).setScore(min/2);

			//Adds updated nodes to pairedSequences then removes them from sequences
			pairedSequences.add(sequences.get(y));
			pairedSequences.add(sequences.get(x));
			sequences.remove(y);
			sequences.remove(x-1);
			
			//Averages out remaining scores other than min
			float sum = 0;
			for(int i = 1; i < currentSize; i++)
			{
				for(int j = 0; j < i; j++)
				{
					if(grid[i][j] != min)
					{
						sum += grid[i][j];
					}
				}
			}
			sum/=2;
			//stores last score
			newScores[0] = sum;
			
			//prints last score in grid
			printGrid(makeGrid(newScores, currentSize-1),currentSize-1);
			
			System.out.println("\nmax: " + newScores[0] + "\nloc: 10");
			
			//Halves very last score
			sum /= 2;
			
			//Stores last score into remaining Nodes
			sequences.get(0).setScore(sum);
			sequences.get(1).setScore(sum);
			level = sequences.get(0).level;
			if(sequences.get(1).level > level){
				level = sequences.get(1).level;
			}
			level++;
			
			//Adds updated nodes to pairedSequences then removes them from sequences
			sequences.add(new Node(sequences.get(0).name + sequences.get(1).name,0));
			sequences.get(sequences.size()-1).level = level+1;
			sequences.get(sequences.size()-1).left = sequences.get(0);
			sequences.get(sequences.size()-1).right = sequences.get(1);
			sequences.get(0).parent = sequences.get(sequences.size()-1);
			sequences.get(1).parent = sequences.get(sequences.size()-1);
			
			pairedSequences.add(sequences.get(0));
			pairedSequences.add(sequences.get(1));
			pairedSequences.add(sequences.get(sequences.size()-1));
		}
	}
	
	/**
	 * Prints each Node and its score
	 */
	public void printScores()
	{
		for(int i = 0; i < pairedSequences.size(); i++)
		{
			System.out.println(pairedSequences.get(i).name + " = " + pairedSequences.get(i).score);
		}
	}
	
	/**
	 * Creates a String in order to write in text file using BufferedWriter
	 * @param t	the Root of the tree
	 * @return
	 */
	public String printTree(Node t)
	{
		String line = "";
		if(t != null)
		{
			//Moves all the way to leftmost node		
			line += printTree(t.left);
			int i = 0;
			
			//Used for spacing purposes
			if(t.level != 1 && (t.left != null && t.right != null)){
				while(i < t.level-1)
				{
					line +="\t";
					i++;
				}
			}
			
			//Used to format for spacing
			String subLine = t.name + ": " + t.score;
			while(subLine.length() < 16)
			{
				subLine = subLine + "-";
			}
			i = 0;
			
			//Adds formated String subLine to String line
			line += subLine;
			
			//Used to show connection between nodes
			if(t.parent != null)
			{
				while(i < t.parent.level - t.level-2)
				{
					line += "--------";		
					i++;
				}
			}
			line += "|\n";
			
			//Moves to rightmost node
			return line + printTree(t.right);
		}
		return line;
	}
}
