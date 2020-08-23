package AntClasses;

import java.util.LinkedList;
import java.util.Stack;

import GUI.ColonyNodeView;

public abstract class Ant
{

  private int lifespan;

  private int integerID;
  protected int xloc;
  protected int yloc;
//  Stack of linked lists that holds coordinates of past moves
  private Stack<LinkedList> path = new Stack<LinkedList>();
  
  
  /**
   * 
   * @param Ant(int lspan, int id)
   */
  public Ant(int lspan, int id, int row, int col)
  {
	  lifespan = lspan;
	  integerID = id;
	  xloc = row;
	  yloc = col;
	  LinkedList<Integer> coordinateList = new LinkedList<Integer>(); 
	  coordinateList.add(xloc);
	  coordinateList.add(yloc);
	  path.push(coordinateList);
  }
  


  /**
   * 
   * @param setIntegerID(int result)
   */
  public void setIntegerID(int result) 
  {integerID = result;}
  public void addLocation(int x, int y)
  {
	  LinkedList<Integer> coordinateList = new LinkedList<Integer>(); 
	  coordinateList.add(xloc);
	  coordinateList.add(yloc);
	  path.push(coordinateList);
  }
  
  
  public void getsetLastMove()
  {
	  LinkedList<Integer> lastMove = path.pop();
	  if(lastMove != null)
	  {
	  xloc = lastMove.get(0);
	  yloc = lastMove.get(1);
	  }
  }
  
  public int getXLoc()
  {return xloc;}
  public int getYLoc()
  {return yloc;}
  public int getIntegerID()
  {return integerID;}
  public int getLspan()
  {return lifespan;}

  /**
   * 
   * @param setLifeSpan(int result)
   */
  public void setLifeSpan(int result) 
  {lifespan = result;}

  /**
   * 
   * @param validateAge(int ageInDays)
   * @param returns 1 if age is valid
   * @param returns 0 if age is invalid
   * @param returns -1 if input is invalid
   */
  public int validateAge(int ageInDays) 
  {
	  if(ageInDays <= 365)
	  {return 1;}
	  if(ageInDays > 365)
	  {return 0;}
	  return -1;
  }


}