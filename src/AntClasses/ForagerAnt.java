package AntClasses;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import GUI.ColonyNodeView;

public class ForagerAnt extends Ant 
{
	
	public ForagerAnt(int lspan, int id, int row, int col)
	{
		super(lspan,id, row, col);
		caryingFood = false;
		foragerMode = true;
		returnMode = false;
		ammountOfFood = 0;
		ammountOfPheromone = 0;
	}

  public Integer totalPheromone;

  private boolean squareIsOpen;
  private boolean caryingFood; 
  private boolean foragerMode;
  private boolean returnMode;
  private int ammountOfFood;
  private int ammountOfPheromone;
  
  
    public int move(ColonyNodeView[][] grid, LinkedList<Integer>[][] nodeViewParam)
    {
    	int food = 0;
    	if(returnMode)
    	{food = reverseMove( grid, nodeViewParam);}
    	else if(foragerMode)
    	{
    		int[] coord = lookForPheromone(grid, nodeViewParam);
    		if(coord[0] != -1)
    		{
    			int row = coord[0];
    			int col = coord[1];
    		ColonyNodeView node = grid[row][col];
    		LinkedList<Integer> param = nodeViewParam[row][col];
    		
    		
    		if(param.get(7)== 1)
    		{
    		xloc = row;
    		yloc = col;
    		if(param.get(5) > 0 && xloc != 13 && yloc != 13)
    		{getFood(node, param);}
    		}
    		param = nodeViewParam[xloc][yloc];
    		node = grid[xloc][yloc];
    		addForager(node, param);
    		addLocation(xloc, yloc);
    		}
    		
    		
    	}
    	return food;
    	
    }
    
	public void randomMove(ColonyNodeView[][] grid, LinkedList<Integer>[][] nodeViewParam) 
	{
		int row = getXLoc();
		int col = getYLoc();
		LinkedList<Integer> param = nodeViewParam[row][col];
		ColonyNodeView node = grid[row][col];
		int row2 = row;
		int col2 = col;
		removeForager(node, param);
		int direction = (int )(Math.random() * 4 + 1);
		if(direction == 1)
		{
			if(col >= 2)
			{col2 = col - 1;}
		}
		if(direction == 2)
		{
			if(row <= 26);
			{row2 = row + 1;}
		}
		if(direction == 3)
		{
			if(col <= 26)
			{col2 = col + 1;}
		}
		if(direction == 4)
		{
			if(row >= 2)
			{row2 = row - 1;}
		}
		if(row2 >= 28)
		{row2 = 27;}
		if(col2 <= 0)
		{col2 = 1;}
		
		param = nodeViewParam[row2][col2];
		if(param.get(7)== 1)
		{
		xloc = row2;
		yloc = col2;
		}
		param = nodeViewParam[getXLoc()][getYLoc()];
		node = grid[xloc][yloc];
		addForager(node, param);
		addLocation(xloc, yloc);
		if(param.get(5) > 0 && xloc != 13 && yloc != 13)
		{getFood(node, param);}
	}
  
//	Queen, Forager, Scout, Soldier, Bala, Food, Pheromone
	public void removeForager(ColonyNodeView node, LinkedList<Integer> param)
	{
		int count = param.get(1);
		count--;
		param.set(1, count);
		node.setForagerCount(count);
		if(count == 0)
		{node.hideForagerIcon();}
	}
	
	public void addForager(ColonyNodeView node, LinkedList<Integer> param)
	{
		int count = 0;
		if(param.get(1) != null)
		{count = param.get(1);}
		else
		{count = 0;}
		count++;
	    param.set(1, count);
		node.setForagerCount(count);
		node.showForagerIcon();
	}
	
	public void getFood(ColonyNodeView node, LinkedList<Integer> param)
	{
		node.setFoodAmount(0);
		ammountOfFood = param.get(5);
		param.set(5, 0);
		caryingFood = true;
		returnMode= true;	
		foragerMode = false;
	}
	
	public int[] lookForPheromone(ColonyNodeView[][] grid, LinkedList<Integer>[][] nodeViewParam)
	{
		int[] coord = new int[2];
		coord[0] = -1;
		coord[1] = -1;
		boolean pheromone = false;
		int row = xloc;
		int col = yloc;
		LinkedList<Integer> param = nodeViewParam[row][col];
		ColonyNodeView node = grid[row][col];
		int row2 = row;
		int col2 = col;
//		if(row >= 28)
//		{row = 27;}
//		if(col <= 0)
//		{col = 2;}
			
//		Check all adjacent cells for pheromone
		
		if(col > 2)
		{
			param = nodeViewParam[row][col - 1];
		    if(param.get(6) != 0 && pheromone == false)
		    {
			    pheromone = true;
			    row2 = row;
			    col2 = col - 1;
		    }
		
		if(row <= 26 && col >= 2)
		{
			param = nodeViewParam[row + 1][col - 1];
			if(param.get(6) != 0 && pheromone == false)
			{
				pheromone = true;
				row2 = row + 1;
				col2 = col - 1;
			}
		}
		if(row <= 26)
		{
			param = nodeViewParam[row + 1][col];
			if(param.get(6) != 0 && pheromone == false)
			{
				pheromone = true;
				row2 = row + 1;
				col2 = col;
			}
		}
		
		if(col <= 26 && row <= 26)
		{
			param = nodeViewParam[row + 1][col + 1];
			if(param.get(6) != 0 && pheromone == false)
			{
				pheromone = true;
				row2 = row + 1;
				col2 = col + 1;
			}
		}
		
		if(col <= 26)
		{
			param = nodeViewParam[row][col + 1];
			if(param.get(6) != 0 && pheromone == false)
			{
				pheromone = true;
				row2 = row;
				col2 = col + 1;
			}
		}
		
		if(col <= 26 && row >= 2)
		{
			param = nodeViewParam[row - 1][col + 1];
			if(param.get(6) != 0 && pheromone == false)
			{
				pheromone = true;
				row2 = row - 1;
				col2 = col + 1;
			}
		}
		
		if(row >= 2)
		{
			param = nodeViewParam[row - 1][col];
			if(param.get(6) != 0 && pheromone == false)
			{
				pheromone = true;
				row2 = row - 1;
				col2 = col;
			}
		}
		
		if(row >= 2 && col >= 2)
		{
			param = nodeViewParam[row - 1][col - 1];
			if(param.get(6) != 0 && pheromone == false)
			{
				pheromone = true;
				row2 = row - 1 ;
				col2 = col - 1;
			}
		}
		
		}
		
		if(pheromone)
		{
			param = nodeViewParam[row2][col2];
			node = grid[row2][col2];
			coord[0] = row2;
			coord[1] = col2;
			if(row2 >= 28)
			{row2 = 27;}
			if(col2 <= 0)
			{col2 = 1;}
			xloc = row2;
			yloc = col2;
			pickUpPheromone(node, param);
			removeForager(node, param);
		}
		else
		{
			randomMove( grid, nodeViewParam);
		}
		return coord;
	}
	
	public int reverseMove(ColonyNodeView[][] grid, LinkedList<Integer>[][] nodeViewParam)
	{
		int food = 0;
		int row = getXLoc();
		int col = getYLoc();
		LinkedList<Integer> param = nodeViewParam[row][col];
		ColonyNodeView node = grid[row][col];
		removeForager(node, param);
		getsetLastMove();
		
		row = getXLoc();
		col = getYLoc();
		param = nodeViewParam[row][col];
		node = grid[row][col];
		
		addForager(node, param);
		addLocation(xloc, yloc);
		if(xloc == 13 && yloc == 13)
		{
			food = dropFood(node, param);
			returnMode = false;
			caryingFood = false;
			foragerMode = true;
			ammountOfFood = 0;
			
		}
		return food;
		

	}
	
	public int dropFood(ColonyNodeView node, LinkedList<Integer> param)
	{
		int food = param.get(5);
		food = food + ammountOfFood;
		param.set(5, food);
		node.setFoodAmount(food);
		ammountOfFood = 0;
		return food;
	}
	
	public void pickUpPheromone(ColonyNodeView node, LinkedList<Integer> param)
	{
		int pheromone = param.get(6);
		ammountOfPheromone = pheromone + ammountOfPheromone;
		param.set(6, 0);
		node.setPheromoneLevel(0);
		
	}



}