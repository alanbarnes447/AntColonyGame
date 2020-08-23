package AntClasses;

import java.util.LinkedList;

import GUI.ColonyNodeView;

public class ScoutAnt extends Ant 
{
	public ScoutAnt(int lspan, int id, int row, int col)
	{
		super(lspan,id, row, col);
	}

  public void moveIntoSquare() {
  }

  public int revealFood() 
  {
	  int random = randomContents();
	  int foodAmmount = 0;
	  int i = 0;
//	  param{Queen, Forager, Scout, Soldier, Bala, Food, Pheromone}
	  if(random <= 75)
	  {foodAmmount = 0;}
	  else if(random > 75 && random <= 100)
	  {foodAmmount = randomFoodAmount();}
	  return foodAmmount;
  }
  
  public int randomMove(ColonyNodeView[][] grid, LinkedList<Integer>[][] nodeViewParam) 
	{
		int row = getXLoc();
		int col = getYLoc();
		LinkedList<Integer> param = nodeViewParam[row][col];
		ColonyNodeView node = grid[row][col];
		int row2 = row;
		int col2 = col;
		removeScout(node, param);
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

		xloc = row2;
		yloc = col2;
		param = nodeViewParam[xloc][yloc];
		node = grid[xloc][yloc];
		addScout(node, param);
//  	create chance of square containing food
  	if(param.get(7) == 0)
	{
  		int foodAmmount = revealFood();
  		while(foodAmmount > 1000)
  		{foodAmmount = foodAmmount - 500;}
  		param.set(5, foodAmmount);
  		node.setFoodAmount(foodAmmount);
  		
	}
  	param.set(7, 1);
  	node.showNode();
  	  return direction;
  }
  
//	Queen, Forager, Scout, Soldier, Bala, Food, Pheromone
	public void removeScout(ColonyNodeView node, LinkedList<Integer> param)
	{
		int count = param.get(2);
		count--;
		param.set(2, count);
		node.setScoutCount(count);
		if(count == 0)
		{node.hideScoutIcon();}
	}
	
	public void addScout(ColonyNodeView node, LinkedList<Integer> param)
	{
		int count = 0;
		if(param.get(2) != null)
		{count = param.get(2);}
		else
		{count = 0;}
		count++;
	    param.set(2, count);
		node.setScoutCount(count);
		node.showScoutIcon();
	}


  public void nonRandomMove() {
  }

  public int randomContents() 
  {
	  int random = (int )(Math.random() * 100) + 1;
	  return random;
  }
  
  public int randomFoodAmount()
  {
	  int random = (int )(Math.random() * 1000) + 500;
	  return random;
  }

  public void newOperation() {
  }

  public void recordPath() {
  }

  public void reversePath() {
  }

  public void resetPath() {
  }

}