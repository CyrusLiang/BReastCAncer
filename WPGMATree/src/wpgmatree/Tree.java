package wpgmatree;

import java.util.ArrayList;
import java.util.Arrays;

public class Tree {
	private int n;
	private int currentSize;
	private ArrayList<Taxon> sequences;
	ArrayList<Taxon> pairedSequences = new ArrayList<Taxon>();
	
	public Tree(float[] scores, int numberOfSequences, ArrayList<Taxon> sequences)
	{
		n = numberOfSequences;
		currentSize = n;
		this.sequences = sequences;
	}
	
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
	
	public void printGrid(float[][] grid, int size)
	{
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				System.out.print(grid[i][j] + "\t");
			}
			System.out.println("");
		}
		System.out.println("\n");
	}
	
	public void pair(float[][] grid)
	{
		float[][] newGrid = new float[n-1][n-1];
		int x = 0, y = 0, newPair = 0, oldPair = 0;
		float dx = 0, dy = 0, average = 0;
		float min = grid[1][0];
				
		//Finds min of current grid
		float[] oldScores = new float[currentSize*currentSize];
		
		float[] newScores = new float[currentSize*currentSize];
		for(int i = 1; i < currentSize; i++)
		{
			for(int j = 0; j < i; j++)
			{
				if(grid[i][j] <= min && grid[i][j]!= 0)
				{
					min = grid[i][j];
					x = i;
					y = j;
				}
			}
		}
		
		System.out.println(min + "\n");
		sequences.get(y).setScore(min/2);
		sequences.get(x).setScore(min/2);
		
		int level = sequences.get(y).level;
		if(sequences.get(x).level > level){	
			level = sequences.get(x).level;
		}
		level++;

		
		sequences.add(new Taxon(sequences.get(y).name + sequences.get(x).name, 0));
		sequences.get(sequences.size()-1).left = sequences.get(y);
		sequences.get(sequences.size()-1).right = sequences.get(x);
		sequences.get(sequences.size()-1).level = level+1;
		sequences.get(y).parent = sequences.get(sequences.size()-1);
		sequences.get(x).parent = sequences.get(sequences.size()-1);
		
			//Checks if size of grid is greater than three.
		if(currentSize > 3)
		{
			for(int i = 0; i < currentSize; i++)
			{
				if(i != x || i != y)
				{
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
					newScores[newPair] = (dx + dy) / 2;
					newPair++;
				}
			}
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
		
			pairedSequences.add(sequences.get(y));
			pairedSequences.add(sequences.get(x));
			sequences.remove(y);
			sequences.remove(x-1);
		
			currentSize--;
			System.arraycopy(newScores, 0, oldScores, oldPair, newPair);
			newGrid = makeGrid(oldScores, currentSize);
			printGrid(newGrid, currentSize);
			pair(newGrid);
		}
		else
		{
			sequences.get(y).setScore(min/2);
			sequences.get(x).setScore(min/2);

			pairedSequences.add(sequences.get(y));
			pairedSequences.add(sequences.get(x));
			sequences.remove(y);
			sequences.remove(x-1);
			
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
			sum /= 4;
			
			sequences.get(0).setScore(sum);
			sequences.get(1).setScore(sum);
			level = sequences.get(0).level;
			if(sequences.get(1).level > level){
				level = sequences.get(1).level;
			}
			level++;
			
			sequences.add(new Taxon(sequences.get(0).name + sequences.get(1).name,0));
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
	
	public void printScores()
	{
		for(int i = 0; i < pairedSequences.size(); i++)
		{
			System.out.println(pairedSequences.get(i).name + " = " + pairedSequences.get(i).score);
		}
	}
	
	public void printTree(Taxon t)
	{
		if(t != null)
		{
			printTree(t.left);
			int i = 0;
			if(t.level != 1 && (t.left != null && t.right != null)){
				while(i < t.level-1)
				{
					System.out.print("\t");
					i++;
				}
			}
			String line = t.name + ": " + t.score;
			while(line.length() < 16)
			{
				line = line + "-";
			}
			System.out.print(line);
			i = 0;
			
			if(t.parent != null)
			{
				while(i < t.parent.level - t.level-2)
				{
					System.out.print("--------");		
					i++;
				}
			}
			System.out.print("|");
			System.out.println("\n");
			printTree(t.right);
		}
		
	}
}
