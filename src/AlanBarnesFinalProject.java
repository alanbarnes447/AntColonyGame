import GUI.*;
public class AlanBarnesFinalProject
{
	
//	main method
	public static void main(String[] args)
	{
//		initialize GUI, ColonyView, and create the control panel
		GameFunctions gf = new GameFunctions();
		AntSimGUI newGUI = new AntSimGUI();
		gf.run(newGUI);
	}

}

