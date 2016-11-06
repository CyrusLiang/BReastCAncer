package pairwisealignment;

public class Tree {
	private int[] scores;
	private int[][] grid;
	
	public Tree(int[] scores)
	{
		this.scores = scores;
		makeGrid();
	}
	
	public void makeGrid()
	{
		int k = 0;
		grid = new int[scores.length][scores.length];
		for(int i = 1; i < scores.length; i++)
		{
			for(int j = 0; j < i; j++)
			{
				grid[i][j] = scores[k];
				k++;
			}
		}
	}
	
	public void printGrid()
	{
		for(int i = 0; i < scores.length; i++)
		{
			for(int j = 0; j < scores.length; j++)
			{
				System.out.print(grid[i][j] + " ");
			}
			System.out.println("\n");
		}

	}
}
