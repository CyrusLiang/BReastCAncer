package wpgmatree
;

public class Tree {
	private int n;
	private int currentSize;
	private int[] scores;
	private int[][] grid;
	
	public Tree(int[] scores, int numberOfSequences)
	{
		this.scores = scores;
		n = numberOfSequences;
		currentSize = n;
		grid = new int[n][n];
		makeGrid();
	}
	
	public void makeGrid()
	{
		int k = 0;	
		for(int i = 1; i < n; i++)
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
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < n; j++)
			{
				System.out.print(grid[i][j] + "\t");
			}
			System.out.println("");
		}

	}
	
	public void pair()
	{
		int min = scores[0];
		int x = 0;
		int y = 0;
		for(int i = 1; i < n; i++)
		{
			for(int j = 0; j < i; j++)
			{
				if(grid[i][j] <= min && grid[i][j] != 0)
				{
					min = grid[i][j];
					x = i;
					y = j;
				}
			}
		}
		System.out.println(min + " " + x + " " + y);
		
		
		currentSize--;
		int[][] newGrid = new int[currentSize][currentSize];
		for(int i = 1; i < n; i++)
		{
			for(int j = 0; j < i; j++)
			{

			}
		}
	}
	
	public static void main(String[] args)
	{
		int[] scores = {20, 60, 50, 100, 90, 40};
		Tree tree = new Tree(scores, 4);
		tree.printGrid();
		tree.pair();
	}
}
