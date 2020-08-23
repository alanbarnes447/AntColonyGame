package AntClasses;

import java.util.LinkedList;

import GUI.ColonyNodeView;

public class SoldierAnt extends Ant 
{
	
	public SoldierAnt(int lspan, int id, int row, int col)
	{
		super(lspan,id, row, col);
	}
	
	public int randomMove(ColonyNodeView[][] grid, LinkedList<Integer>[][] nodeViewParam, LinkedList<SoldierAnt> soldier, LinkedList<BalaAnt> listOfBalas)
	{
		int row = getXLoc();
		int col = getYLoc();
		LinkedList<Integer> param = nodeViewParam[row][col];
		ColonyNodeView node = grid[row][col];
		int direction = 0;
		int ant = 0;
		
		if(param.get(4) > 0)
		{ant = fight(node, param, soldier, listOfBalas);}
		else
		{
		
		int row2 = row;
		int col2 = col;
		removeSoldier(node, param);
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
		if(param.get(7)== 1)
		{
		xloc = row2;
		yloc = col2;
		}
		param = nodeViewParam[getXLoc()][getYLoc()];
		node = grid[xloc][yloc];
		addSoldier(node, param);
		}

		  return ant;
		
	}
//	Queen, Forager, Scout, Soldier, Bala, Food, Pheromone
	public void removeSoldier(ColonyNodeView node, LinkedList<Integer> param)
	{
		int count = param.get(3);
		count--;
		param.set(3, count);
		node.setSoldierCount(count);
		if(count == 0)
		{node.hideSoldierIcon();}
	}
	
	public void addSoldier(ColonyNodeView node, LinkedList<Integer> param)
	{
		int count = 0;
		if(param.get(3) != null)
		{count = param.get(3);}
		else
		{count = 0;}
		count++;
	    param.set(3, count);
		node.setSoldierCount(count);
		node.showSoldierIcon();
	}
	
	public int fight(ColonyNodeView node, LinkedList<Integer> param, LinkedList<SoldierAnt> soldier, LinkedList<BalaAnt> listOfBalas) 
	  {
		      boolean win = false;
			  int random = (int )(Math.random() * 10) + 1;
			  int numOfAnts = 0;
			  int paramNo = 0;
			  if(random < 5)
			  {win = true;}
			  if(win)
			  {paramNo = 4; }
			  else
			  {paramNo = 3;}
			  numOfAnts = param.get(paramNo);
			  numOfAnts--;
			  if(numOfAnts < 0)
			  {numOfAnts = 0;}
			  param.set(paramNo, numOfAnts);
			  if(paramNo == 4)
			  {
				  node.setBalaCount(numOfAnts);
				  node.hideBalaIcon();
				  return 4;
			  }
			  else if(paramNo == 3)
			  {
				  node.setSoldierCount(numOfAnts);
				  node.hideSoldierIcon();
				  return 3;
			  }
			  return 0;

			  
				  
	  }

  public Integer newAttr;

  public void attackMode() {
  }

  public void scoutMode() {
  }

  public void checkForBala() {
  }

  public void moveToAdjacentbala() {
  }

  public int randomMove() 
  {
	  int move = (int )(Math.random() * 4 + 1);
	  return move;
  }

  public void chooseBalaToAttack() {
  }

  public void randomWin() {
  }

}