import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import AntClasses.Ant;
import AntClasses.BalaAnt;
import AntClasses.ForagerAnt;
import AntClasses.QueenAnt;
import AntClasses.ScoutAnt;
import AntClasses.SoldierAnt;
import GUI.AntSimGUI;
import GUI.ColonyNodeView;
import GUI.ColonyView;
import GUI.SimulationEvent;
import GUI.SimulationEventListener;

public class GameFunctions implements SimulationEventListener, ActionListener
{
//		Linked lists to hold ants
		private LinkedList<ScoutAnt> listOfScouts = new LinkedList<ScoutAnt>();
		private int scoutAntCount = 0;
		private LinkedList<ForagerAnt> listOfForagers = new LinkedList<ForagerAnt>();
		private int foragerAntCount = 0;
		private LinkedList<SoldierAnt> listOfSoldiers = new LinkedList<SoldierAnt>();
		private int soldierAntCount = 0;
		private LinkedList<BalaAnt> listOfBalas = new LinkedList<BalaAnt>();
		private int balaAntCount = 0;
		private final ColonyNodeView[][] nodeGrid = new ColonyNodeView[28][28];
		private final LinkedList<Integer>[][] nodeViewParam = new LinkedList[28][28];
//		In each nodeViewParam is a LinkedList param{Queen, Forager, Scout, Soldier, Bala, Food, Pheromone}
		private QueenAnt queen;
		private int idNum = 1;
		private int turns = 0;  //10 turns = 1 day
		private int days = 0;  //number of days in the game
//		GUI components
		private ColonyView entireColony = new ColonyView(27, 27);
		private JButton startButton = new JButton("Start");
		private boolean auto = false;
		private Timer gameTimer;
		int on = 0;
		
//		Constructor for GameFunctionClass
		public GameFunctions()
		{
			createNodes(entireColony, 0, 0);
		}

//		run method called by the main method
	 public void run(AntSimGUI gui) 
	{
	    gui.addSimulationEventListener(this);	 
	    gui.initGUI(entireColony);
	}

	private void createNodes(ColonyView CV, int row, int col) 
	{	
		ColonyNodeView nodeView = new ColonyNodeView();
		LinkedList<Integer> param = new LinkedList<Integer>();
		if(!(col >= 27))
		{
		    
		    if(row < 27 && !(col >= 27))
		    {
		    	nodeView = new ColonyNodeView();
				CV.addColonyNodeView(nodeView, row, col);
				nodeGrid[row + 1][col + 1] = nodeView;
				nodeViewParam[row + 1][col + 1] = param;
				nodeView.setID((Integer.toString(row + 1)) + "," +  (Integer.toString(col + 1)));
				nodeView.hideNode();
			    row++;
			    createNodes(CV, row, col);
		    }
		    else if(row >= 27)
		    {
			    col++;
			    row = 0;
			    createNodes(CV, row, col);
		    }
		}
	return;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		
	}

	////////////////////////////////////////////////////////////////
    //button Event handeler
	////////////////////////////////////////////////////////////////
		@Override
		public void simulationEventOccurred(SimulationEvent simEvent) 
		{
//			simEvent.getEventType() will return the number of the button
//			NORMAL_SETUP_EVENT = 0
//			QUEEN_TEST_EVENT = 1
//			SCOUT_TEST_EVENT = 2
//			FORAGER_TEST_EVENT = 3
//			SOLDIER_TEST_EVENT = 4
//			RUN_EVENT = 5
//			STEP_EVENT = 6
			
			if(simEvent.getEventType() == 0)
			{NormSetup();}
			else if(simEvent.getEventType() == 1)
			{queenTest();}
			else if(simEvent.getEventType() == 2)
			{scoutTest();  }
			else if(simEvent.getEventType() == 3)
			{foragerTest();}
			else if(simEvent.getEventType() == 4)
			{soldierTest();}
			else if(simEvent.getEventType() == 5)
			{start();}
			else if(simEvent.getEventType() == 6)
			{stepMode();}
			
		}

		

		/////////////////////////////////////////////////////////////////////
		//Button Methods
		////////////////////////////////////////////////////////////////////
		private void start()
		{
//			param{Queen, Forager, Scout, Soldier, Bala, Food, Pheromone}
//			LinkedList<Integer> param = nodeViewParam[13][13];
//			param.set(0, 1);
//			param.set(1, 50);
//			param.set(2, 4);
//			param.set(3, 10);
//			param.set(4, 0);
//			param.set(5, 1000);
//			param.set(6, 1000);
			ActionListener taskPerformer = new ActionListener() {
			      public void actionPerformed(ActionEvent evt) {
			    	  LinkedList<Integer> param = nodeViewParam[13][13];
			    	  boolean cont = true;
						cont = gameOperation();
						if(!cont)
				    	  {gameTimer.stop();}
			      }};
			gameTimer = new Timer(250, taskPerformer);
			gameTimer.start();
		}
		
		private void stepMode() 
		{
//			Call method for taking a turn in the game
			gameOperation();
		}

		private void NormSetup() 
		{
//			initialize all variables to starting values in the beginning of the game
			on = 0;
			turns = 0;
			days = 0;
			idNum = 1;
			scoutAntCount = 0;
			foragerAntCount = 0;
			soldierAntCount = 0;
			balaAntCount = 0;
//			clear the lists of ants in the game
			listOfScouts.clear();
			listOfForagers.clear();
			listOfSoldiers.clear();
			listOfBalas.clear();
//			remove the old queen ant and create a new queen ant
			if(queen != null)
			{queen = queen.remove(queen);}
//			clear all the data on the gameboard
			clearGameBoard();
			entireColony.revalidate();
			entireColony.repaint();
			
		}

		

		private void queenTest() 
		{
			
			// TODO Auto-generated method stub
			
		}

		private void scoutTest() 
		{
//			listOfScouts.add(new ScoutAnt(1, idNum));
			// TODO Auto-generated method stub
			
		}

		private void foragerTest() 
		{
			// TODO Auto-generated method stub
			
		}

		private void soldierTest() 
		{
			// TODO Auto-generated method stub
			
		}
		
		
		
		
		/////////////////////////////////////////////////////////
		//method that controls the game operation
		////////////////////////////////////////////////////////
		private boolean gameOperation()
		{
			
			LinkedList<Integer> param = nodeViewParam[13][13];
			ColonyNodeView node = nodeGrid[13][13];
			turns++;
			if(turns == 10)
			{ 
//				Increase the day every 10 turns
				days++;
				turns = 0;
//				decrease pheromone by half here
				decreaseAllPheromone();
				CheckLifeSpans();
				queen.consumeFood(param, node);
//				Queen hatches a new ant
				int i = queen.hatchAnt(listOfForagers, listOfScouts, listOfSoldiers, idNum, param, node);
			    if(i == 1)
			    {scoutAntCount++;}
			    else if(i ==2)
			    {foragerAntCount++;}
			    else if(i == 3)
			    {soldierAntCount++;}
			}

			System.out.println("turns: " + turns + ",   days: " + days);
			boolean cont = queenStatus();
			moveAllAnts();
			createBalaAnt();
			
			entireColony.revalidate();
			entireColony.repaint();
			return cont;
			
		}
		
		

		/////////////////////////////////////////////////////////
		//simulation methods for the game
		////////////////////////////////////////////////////////
		private void decreaseAllPheromone() 
		{
//			Queen, Forager, Scout, Soldier, Bala, Food, Pheromone
			LinkedList<Integer> param = new LinkedList<Integer>();
			
			for(int row = 1; row < nodeGrid.length; row++)
			{
				for(int col = 1; col < nodeGrid[row].length; col++)
				{
					param = nodeViewParam[row][col];
					int currentPheromone = param.get(6);
					currentPheromone = currentPheromone / 2;
					nodeGrid[row][col].setPheromoneLevel(currentPheromone);
					param.set(6, currentPheromone);
				}
			}
			// TODO Auto-generated method stub
			
		}
		
        //Method for initializing the game board with the beginning parameters
		private void clearGameBoard() 
		{

			LinkedList<Integer> param = new LinkedList<Integer>();
			for(int row = 1; row < nodeGrid.length; row++)
			{
				for(int col = 1; col < nodeGrid[row].length; col++)
				{
//					param{Queen, Forager, Scout, Soldier, Bala, Food, Pheromone}
					param = nodeViewParam[row][col];
//					SetQueen
					nodeGrid[row][col].setQueen(false);
					param.add(0, 0);
//					SetForager
					nodeGrid[row][col].setForagerCount(0);
					param.add(1, 0);
//					SetScout
					nodeGrid[row][col].setScoutCount(0);
					param.add(2, 0);
//					SetSoldier
					nodeGrid[row][col].setSoldierCount(0);
					param.add(3, 0);
//					SetBala
					nodeGrid[row][col].setBalaCount(0);
					param.add(4, 0);
//					SetFoodAmmount
					nodeGrid[row][col].setFoodAmount(0);
					param.add(5, 0);
//					SetPheromoneLevel
					nodeGrid[row][col].setPheromoneLevel(1000);
					param.add(6, 1000);
//					Parameter that tells if the node is visible
					param.add(7, 0);
				}
			}
			
//			Create the ants in the center square (13,13)
			ColonyNodeView centerNode = nodeGrid[13][13];
			centerNode.showNode();
			
//			initialize 50 forager ants
			addForagerAnt(50, 13, 13);
//			initialize 4 scout ants
			addScoutAnt(4, 13, 13);
//			initialize 10 soldiers
			addSoldierAnt(10, 13, 13);
//			Create the QueenAnt
//			addBalaAnt(5, 1, 13);
			param = nodeViewParam[13][13];
			queen = new QueenAnt((365 * 20), idNum, 13, 13);
			idNum++;
			centerNode.setQueen(true);
			centerNode.setFoodAmount(1000);
			param.set(0, 1);
			param.set(1, 50);
			param.set(2, 4);
			param.set(3, 10);
			param.set(4, 0);
			param.set(5, 1000);
			param.set(7, 1);
//			queenStatus();t
			
		}
		
		private void CheckLifeSpans() 
		{
//			param{Queen, Forager, Scout, Soldier, Bala, Food, Pheromone}
			@SuppressWarnings("rawtypes")

//          Check the Forager ants life span
			LinkedList<Integer> param1 = nodeViewParam[13][13];
			Iterator<ForagerAnt>  foragerIterator = listOfForagers.iterator();
			while (foragerIterator.hasNext()) 
			{
				ForagerAnt ant = foragerIterator.next();
				ColonyNodeView node = nodeGrid[ant.getXLoc()][ant.getYLoc()];
				LinkedList<Integer> param = nodeViewParam[ant.getXLoc()][ant.getYLoc()];
				int lspan = ant.getLspan();
				ant.setLifeSpan(lspan--);
				if(ant.getLspan() <= 0)
				{
					int count = param.get(1);
					count--;
					param.set(1, count);
					node.setForagerCount(count);
					foragerIterator.remove();
					foragerAntCount--;
				}
		    }
			
//			Check the scout ant life span
			Iterator<ScoutAnt>  scoutIterator = listOfScouts.iterator();
			while (scoutIterator.hasNext()) 
			{
				ScoutAnt ant = scoutIterator.next();
				ColonyNodeView node = nodeGrid[ant.getXLoc()][ant.getYLoc()];
				LinkedList<Integer> param = nodeViewParam[ant.getXLoc()][ant.getYLoc()];
				int lspan = ant.getLspan();
				ant.setLifeSpan(lspan++);
				if(ant.getLspan() <= 0)
				{
					int count = param.get(2);
					count--;
					param.set(2, count);
					node.setScoutCount(count);
					scoutIterator.remove();
					scoutAntCount--;
				}
		    }
			
//			check the soldier ant life span
			Iterator<SoldierAnt>  soldierIterator = listOfSoldiers.iterator();
			while (soldierIterator.hasNext()) 
			{
				SoldierAnt ant = soldierIterator.next();
				ColonyNodeView node = nodeGrid[ant.getXLoc()][ant.getYLoc()];
				LinkedList<Integer> param = nodeViewParam[ant.getXLoc()][ant.getYLoc()];
				int lspan = ant.getLspan();
				ant.setLifeSpan(lspan++);
				if(ant.getLspan() <= 0)
				{
					int count = param.get(3);
					count--;
					param.set(3, count);
					node.setSoldierCount(count);
					soldierIterator.remove();
					soldierAntCount--;
				}
		    }
//			Check the bala ant life span
			Iterator<BalaAnt>  balaIterator = listOfBalas.iterator();
			while (balaIterator.hasNext()) 
			{
				BalaAnt ant = balaIterator.next();
				ColonyNodeView node = nodeGrid[ant.getXLoc()][ant.getYLoc()];
				LinkedList<Integer> param = nodeViewParam[ant.getXLoc()][ant.getYLoc()];
				int lspan = ant.getLspan();
				ant.setLifeSpan(lspan++);
				if(ant.getLspan() <= 0)
				{
					int count = param.get(4);
					count--;
					param.set(4, count);
					node.setBalaCount(count);
					balaIterator.remove();
					balaAntCount--;
				}
		    }
			
			
			
			
		}
		
		
		//////////////////////////////////////////////////////////////
		//methods to set the ColonyNodeView parameters data structure
		//////////////////////////////////////////////////////////////
		
//		Method that checks for end of game
		public boolean queenStatus()
		{
			LinkedList<Integer> param = nodeViewParam[13][13];
			ColonyNodeView node = nodeGrid[13][13];
			int queenInt = param.get(0);
//			JOptionPane.showMessageDialog(null, param.get(0));
			if(queenInt == 0)
			{
				node.hideQueenIcon();
				return false;
			}
//			if food is empty end game
			else if(param.get(5) <= 0)
			{return false;}
//			if queen is older than 20 years old end game
			else if(queen.getLspan() > (20 * 365))
			{return false;}
			else
//			{throws error}
			return true;
			
		}
		
		public void addForagerAnt(int numOfAnts, int row, int col)
		{
//			param{Queen, Forager, Scout, Soldier, Bala, Food, Pheromone}
			ColonyNodeView node = nodeGrid[row][col];
			for(int i = 0; i < numOfAnts; i++)
			{
				ForagerAnt ant = new ForagerAnt(365, idNum, row, col);
				listOfForagers.add(ant);
				idNum++;
				foragerAntCount++;
			}
			LinkedList<Integer> param = nodeViewParam[row][col];
			numOfAnts = numOfAnts + param.get(1);
			param.set(1, numOfAnts);
			node.setForagerCount(numOfAnts);
			if(numOfAnts > 0)
			{node.showForagerIcon();}
			else
			{node.hideForagerIcon();}

		}
		
		
		public void addScoutAnt(int numOfAnts,  int row, int col)
		{
//			param{Queen, Forager, Scout, Soldier, Bala, Food, Pheromone}
			ColonyNodeView node = nodeGrid[row][col];
			for(int i = 0; i < numOfAnts; i++)
			{
				ScoutAnt ant = new ScoutAnt(365, idNum, row, col);
				listOfScouts.add(ant);
				idNum++;
				scoutAntCount++;
			}
			LinkedList<Integer> param = nodeViewParam[row][col];
			numOfAnts = numOfAnts + param.get(2);
			param.set(2, numOfAnts);
			node.setScoutCount(numOfAnts);
			if(numOfAnts > 0)
			{node.showSoldierIcon();}
			else
			{node.hideScoutIcon();}
		}
		
		
		
		public void addSoldierAnt(int numOfAnts, int row, int col)
		{
//			param{Queen, Forager, Scout, Soldier, Bala, Food, Pheromone}
			ColonyNodeView node = nodeGrid[row][col];
			for(int i = 0; i < numOfAnts; i++)
			{
				SoldierAnt ant = new SoldierAnt(365, idNum, row, col);
				listOfSoldiers.add(ant);
				idNum++;
				soldierAntCount++;
			}
			LinkedList<Integer> param = nodeViewParam[row][col];
			numOfAnts = numOfAnts + param.get(3);
			param.set(3, numOfAnts);
			node.setSoldierCount(numOfAnts);
			if(numOfAnts > 0)
			{node.showSoldierIcon();}
			else
			{node.hideSoldierIcon();}
		}
		
		public void createBalaAnt()
		{
			boolean bala = false;
			int random = (int )(Math.random() * 100 + 1);
			if(random > 97)
			{
				addBalaAnt(1, 1, 13);
			}
		}
		
		public void addBalaAnt(int numOfAnts, int row, int col)
		{
//			param{Queen, Forager, Scout, Soldier, Bala, Food, Pheromone}
			ColonyNodeView node = nodeGrid[row][col];
			for(int i = 0; i < numOfAnts; i++)
			{
				BalaAnt ant = new BalaAnt(365, idNum, row, col);
				listOfBalas.add(ant);
				idNum++;
				balaAntCount++;
			}
			LinkedList<Integer> param = nodeViewParam[row][col];
			numOfAnts = numOfAnts + param.get(4);
			param.set(4, numOfAnts);
			node.setBalaCount(numOfAnts);
			node.showNode();
			if(numOfAnts > 0)
			{node.showBalaIcon();}
			else
			{node.hideBalaIcon();}
		}
		
		public void addFood(int foodAmmount, int row, int col)
		{
			LinkedList<Integer> param = nodeViewParam[row + 1][col + 1];
			foodAmmount = foodAmmount + param.get(5);
			param.set(5, foodAmmount);
			nodeGrid[row][col].setFoodAmount(foodAmmount);
			
		}

		public void addPheromone(int pheromone, int row, int col)
		{
			LinkedList<Integer> param = nodeViewParam[row + 1][col + 1];
			pheromone = pheromone + param.get(6);
			param.set(6, pheromone);
			nodeGrid[row][col].setPheromoneLevel(pheromone);
		}
		
		/////////////////////////////////////////////////////////////////
		//Methods for moving the ants
		////////////////////////////////////////////////////////////////
		
		public void moveAllAnts()
		{
//		    Move all of the forager ants
			int food;
			Iterator<ForagerAnt>  foragerIterator = listOfForagers.iterator();
			while (foragerIterator.hasNext()) 
			{
				Object ant = foragerIterator.next();
				food = ((ForagerAnt)ant).move(nodeGrid, nodeViewParam);
//				if(food > 0);
//				{
////					addFood(food, 13, 13);
//				}
			}
			
//		    Move all of the scout ants
			Iterator<ScoutAnt>  scoutIterator = listOfScouts.iterator();
			while (scoutIterator.hasNext()) 
			{
				
				Object ant = scoutIterator.next();
				((ScoutAnt)ant).randomMove(nodeGrid, nodeViewParam);
			
			}
			
//		    Move all of the soldier ants
			Iterator<SoldierAnt>  soldierIterator = listOfSoldiers.iterator();
			while (soldierIterator.hasNext()) 
			{
				
				Object ant = soldierIterator.next();
				int ant1 = ((SoldierAnt)ant).randomMove(nodeGrid, nodeViewParam, listOfSoldiers, listOfBalas);
//				if(ant1 == 1)
//				{listOfForagers.removeLast();}
//				if(ant1 == 2)
//				{listOfScouts.removeLast();}
//				if(ant1 == 3)
//				{listOfSoldiers.removeLast();}
//				if(ant1 == 4)
//				{
//					listOfBalas.removeLast();
//				}
			
			}
			
//		    Move all of the Bala ants
			Iterator<BalaAnt>  balaIterator = listOfBalas.iterator();
			while (balaIterator.hasNext()) 
			{
				
				int ant1 = (balaIterator.next()).randomMove(nodeGrid, nodeViewParam, listOfForagers, listOfScouts, listOfSoldiers, listOfBalas);
//				if(ant1 == 4)
//				{listOfBalas.removeLast();}
//				if(ant1 == 3)
//				{listOfSoldiers.removeLast();}
			}

}
}
		


	//removeSimulationEventListener(SimulationEventListener listener)
	//addSimulationEventListener(SimulationEventListener listener)
	//GUI.setTime(String time)
	//ControlPanel.setTime(String time)

