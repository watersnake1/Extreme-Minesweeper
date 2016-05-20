import sun.applet.Main;

import javax.swing.*;import javax.swing.JFrame;
import java.awt.*;import java.awt.Dimension;import java.lang.String;

/**
*Jframe window class, should be run from a static class.
*needs to contain a gameboard object to fill itself with.
*/
public class MainFrame implements Runnable
{
    private JFrame mainFrame;
    private static GameBoard gameBoard;

    public MainFrame()
    {
	   mainFrame = new JFrame("Extreme Minsweeper");
	   gameBoard = new GameBoard();
    }
	
    /**
    *create all the components such that a window is shown on screen
    *does not add the gameboard as of yet
    */
    public void createAndShowGUI()
    {
       gameBoard.setUp();
        mainFrame.setPreferredSize(new Dimension(500,500));
       mainFrame.setResizable(false);
       mainFrame.add(gameBoard.getGameBoardRootPanel());
	    mainFrame.setVisible(true);
	    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    mainFrame.pack();
    }

   public static void main(String[] args)
   {
      Thread mainStart = new Thread(new Runnable() {
      	public void run() {
      		tick();
      	}
      });
      Thread ticker = new Thread(new MainFrame());
      mainStart.start();
      ticker.start();
   }
   
   @Override
   public void run()
   {
   	  MainFrame frame = new MainFrame();
      frame.createAndShowGUI();
   }
   
   public static void tick()
   {
   		boolean val = true;
   		while(val)
   		{
   			try {
   				gameBoard.getGameBoardRootPanel().updateUI();
   				Thread.sleep(1000);
   			}
   			catch (InterruptedException e)
   			{
   				e.printStackTrace();
   			}
   		}
   		
   }
}
