package AntClasses;

import java.util.LinkedList;

import GUI.ColonyNodeView;

public class BalaAnt extends Ant 
{
	
	public BalaAnt(int lspan, int id, int row, int col)
	{
		super(lspan,id, row, col);
	}
//	.randomMove(nodeGrid, nodeViewParam, listOfForagers, listOfScouts, listOfSoldiers);
	public int randomMove(ColonyNodeView[][] grid, LinkedList<Integer>[][] nodeViewParam, LinkedList<ForagerAnt> forager, LinkedList<ScoutAnt> scout, LinkedList<SoldierAnt> soldier, LinkedList<BalaAnt> listOfBalas) 
	{
		int row = getXLoc();
		int col = getYLoc();
		LinkedList<Integer> param = nodeViewParam[row][col];
		ColonyNodeView node = grid[row][col];
		int direction = 0;
		int ant = 0;
		
		if(param.get(1) > 0 || param.get(2) > 0 || param.get(3) > 0)
		{ant = fight(node, param, forager, scout, soldier, listOfBalas);}
		else
		{
		
		int row2 = row;
		int col2 = col;
		removeBala(node, param);
		direction = (int )(Math.random() * 4 + 1);
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
		addBala(node, param);
//		node.showNode();
		}
		

		  return ant;
	}
	
//	Queen, Forager, Scout, Soldier, Bala, Food, Pheromone
	public void removeBala(ColonyNodeView node, LinkedList<Integer> param)
	{
		int count = param.get(4);
		count--;
		param.set(4, count);
		node.setBalaCount(count);
		if(count == 0)
		{node.hideBalaIcon();}
	}
	
	public void addBala(ColonyNodeView node, LinkedList<Integer> param)
	{
		int count = 0;
		if(param.get(4) != null)
		{count = param.get(4);}
		else
		{count = 0;}
		count++;
	    param.set(4, count);
		node.setBalaCount(count);
		node.showBalaIcon();
	}

  public void chooseEntrance() {
  }

  public void enterColony() {
  }


  public int fight(ColonyNodeView node, LinkedList<Integer> param, LinkedList<ForagerAnt> forager, LinkedList<ScoutAnt> scout, LinkedList<SoldierAnt> soldier, LinkedList<BalaAnt> listOfBalas) 
  {
	  int paramNo = (int )(Math.random() * 3) + 1;
	  boolean win = false;
	  if(param.get(paramNo) != 0)
	  {
		  int random = (int )(Math.random() * 10) + 1;
		  if(random < 5)
		  {win = true;}
		  else
		  {paramNo = 4;}
		  int numOfAnts = param.get(paramNo);
		  numOfAnts--;
		  if(numOfAnts < 0)
		  {numOfAnts = 0;}
		  param.set(paramNo, numOfAnts);
		  if(win)
		  {
		  if(paramNo == 1)
		  {
			  node.setForagerCount(numOfAnts);
			  node.hideForagerIcon();
			  return 1;
		  }
		  else if(paramNo == 2)
		  {
			  node.setScoutCount(numOfAnts);
			  node.hideScoutIcon();
			  return 2;
			  
		  }
		  else if(paramNo == 3)
		  {
			  node.setSoldierCount(numOfAnts);
			  node.hideSoldierIcon();
			  return 3;
		  }
		  else if (paramNo == 4)
		  {
			  node.setBalaCount(numOfAnts); 
			  node.hideBalaIcon();
			  return 4;
		  }
		  }
	  }
	  else
	  {fight(node, param, forager, scout, soldier, listOfBalas);}
	  return 0;
		  
			  
  }

 

}