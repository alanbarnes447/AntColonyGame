import GUI.ColonyNodeView;

public class NodeArry
{
	private  ColonyNodeView[][] nodeGrid = new ColonyNodeView[27][27];
	
	
	public NodeArry()
	{
	for(ColonyNodeView[] arry : nodeGrid)
		for(ColonyNodeView i : arry)
		{
			ColonyNodeView nNode = new ColonyNodeView();
		}
	}
	
	public ColonyNodeView getNode(int x, int y)
	{
		ColonyNodeView node = nodeGrid[x][y];
		return node;
	}
	
	public void setNode(ColonyNodeView node, int row, int col)
	{
		nodeGrid[row][col] = node;
	}
	
}
