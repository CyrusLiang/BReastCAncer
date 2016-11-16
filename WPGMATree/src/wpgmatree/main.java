package wpgmatree;

import java.io.*;
import java.util.ArrayList;

public class main {
	
	public static void main(String[] args)
	{
		ArrayList<String> input = new ArrayList<String>();
		
		String fileName = args[0];
		
		String line;
		float[] scores = new float[0];
		int size = 0;
		
		try{
			FileReader fileReader = new FileReader(fileName);
			
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			size = Integer.parseInt(bufferedReader.readLine());
			
			int scoreSize = Integer.parseInt(bufferedReader.readLine());
			
			scores = new float[scoreSize];
			
			for(int i = 0; i < size; i++)
			{
				bufferedReader.readLine();
			}
			int i = 0;
			while(bufferedReader.ready())
			{
				//bufferedReader.skip(4);
				line = bufferedReader.readLine();
				line  = line.substring(4, line.length());
				scores[i] = Float.parseFloat(line);
				i++;
			}
			
			bufferedReader.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open File '" + fileName + "'");
		}
		catch(IOException ex){
			System.out.println("Error reading File '" + fileName + "'");
		}
		
		
		ArrayList<Taxon> sequences = new ArrayList<Taxon>();
		for(int i = 0; i < size; i++)
		{
			sequences.add(new Taxon(i + "", 0));
		}

		Tree tree = new Tree(scores, size, sequences);
		float grid[][] = new float[size][size];
		
		int k = 0;
		
		for(int i = 0; i < size; i++)
		{
			for(int j = i+1; j < size; j++)
			{
				grid[j][i] = scores[k];
				k++;
			}
		}
		
		tree.printGrid(grid, size);
		tree.pair(grid);
		tree.printTree(tree.pairedSequences.get(tree.pairedSequences.size()-1));
	}
}
