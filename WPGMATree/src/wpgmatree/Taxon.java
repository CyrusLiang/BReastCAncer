package wpgmatree;

public class Taxon {
	String name;
	float score;
	Taxon right;
	Taxon parent;
	Taxon left;
	int level;
	
	public Taxon(String name, float score)
	{
		this.name = name;
		this.score = score;
		left = null;
		right = null;
		level = 1;
	}
	
	public void setScore(float score)
	{
		if(left != null || right != null)
		{
			this.score = score - left.score;
		}
		else
		{
			this.score = score;
		}
	}
}
