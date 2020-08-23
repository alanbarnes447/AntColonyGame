package AntClasses;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import GUI.SimulationEvent;
import GUI.SimulationEventListener;
import GUI.AntSimGUI;
import GUI.ColonyNodeView;

public class QueenAnt extends Ant implements SimulationEventListener
{
	SimulationEvent queenSimEvent;
	public QueenAnt(int lspan, int id, int row, int col)
	{
		super(lspan,id, row, col);
//		queenSimEvent = new SimulationEvent(this, SimulationEvent.QUEEN_TEST_EVENT);
//		SimulationEventListener simlistener = null;
//		.addSimulationEventListener(simlistener);
	}

  public int hatchAnt(LinkedList<ForagerAnt> forager, LinkedList<ScoutAnt> scout, LinkedList<SoldierAnt> soldier, int idNum, LinkedList<Integer> L, ColonyNodeView node) 
  {
	  int random = randomAntSelector();
	  int i = 0;
//	  param{Queen, Forager, Scout, Soldier, Bala, Food, Pheromone}
	  if(random <= 50)
	  {
		  ForagerAnt ant = new ForagerAnt(365, idNum, 13, 13);
		  forager.add(ant);
		  i = 2;
		  int a = L.get(1);
		  a++;
		  L.set(1, a);
		  node.setForagerCount(a);
	  }
	  else if(random > 50 && random <= 75)
	  {
		  ScoutAnt ant = new ScoutAnt(365, idNum, 13, 13);
		  scout.add(ant);
		  i = 1;
		  int a = L.get(2);
		  a++;
		  L.set(2, a);
		  node.setScoutCount(a);
	  }
	  else if(random > 75 && random <= 100)
	  {
		  SoldierAnt ant = new SoldierAnt(365, idNum,13 ,13);
		  soldier.add(ant);
		  i = 3;
		  int a = L.get(3);
		  a++;
		  L.set(3, a);
		  node.setSoldierCount(a);
		  
	  }
	  return i;
	  
  }

  public int randomAntSelector() 
  {
//	  method to randomlyselectAnt
	  int random = (int )(Math.random() * 100 + 1);
	  return random;
  }
  public int randomMove()
  {
	  return 0;
  }

  public void consumeFood(LinkedList<Integer> L, ColonyNodeView node) 
  {
	  
	  int food = L.get(5);
	  food--;
	  L.set(5, food);
	  node.setFoodAmount(food);;
}

  public void endSimulation() {
  }
  
  public QueenAnt remove(QueenAnt queen)
  {
	  queen = null;
	  return queen;
  }

//  added implemented methods
@Override
public void simulationEventOccurred(SimulationEvent simEvent) 
{
//	JOptionPane.showMessageDialog(null, "this works");
	
}

}