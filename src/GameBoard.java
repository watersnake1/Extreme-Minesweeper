import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.Override;
import java.util.ArrayList;

/**
 * Created by Christian on 5/15/16.
 * This is just a preliminary version of what the full gameboard class would look like
 * basically, the gameboard class creates a jpanel, sets it up with all the clickable jlabels,
 * then when the frame class wants to display the panel it just gets reference to the panel from the
 * gameboard class. That way, extending swing classes is not necessary. This method could be applied to the
 * cell class as well......
 */
public class GameBoard
{
   private final int WIDTH = 8; //8
   private final int HEIGHT = 8; //8
   private final int NUM_BOMBS = 8; //16
   private JPanel GameBoardRootPanel;
   private Tile[][] tiles;
   private ArrayList<Tile> cellsWithBombs; //we need the cell class for this to work
   private ArrayList<Tile> openCells;

   /**
    * The icons and labels are being created in the gameboard class for the sake of testing, normally there would
    * only be cell objects within here.
    */
   public GameBoard()
   {
      GameBoardRootPanel = new JPanel();

      tiles = new Tile[HEIGHT][WIDTH]; //sets the grid of squares
      cellsWithBombs = new ArrayList<Tile>();
      openCells = new ArrayList<Tile>();     
   }

   /**
    * setUp configures the panel to have the all the necessary settings for the game.
    * Gridbaglayout seems to be the best layout to use, since it just arranges everything into a grid
    */
   public void setUp()
   {
   	GameBoardRootPanel.setPreferredSize(new Dimension(HEIGHT, WIDTH));
      GameBoardRootPanel.setLayout(new GridLayout(HEIGHT, WIDTH));
      
      for(int r = 0; r < tiles.length; r++)
      {
         for(int c = 0; c < tiles[0].length; c++)
         {
            tiles[r][c] = new Tile(r, c); //placeholder
            tiles[r][c].clickListener();
            GameBoardRootPanel.add(tiles[r][c].getLabel());
         }
      }
      for(int i = 0; i < NUM_BOMBS; i++)
      {
         int r = (int) (Math.random() * HEIGHT);
         int c = (int) (Math.random() * WIDTH);
         while(tiles[r][c].getBomb())  //placeholder
         {
            r = (int) (Math.random() * HEIGHT);
            c = (int) (Math.random() * WIDTH);
         }
         tiles[r][c].addBomb(); //placeholder
         cellsWithBombs.add(tiles[r][c]);
      }
      for(int r = 0; r < HEIGHT; r++)
      {
      	for(int c = 0; c < WIDTH; c++)
      	{
      		if(!tiles[r][c].getBomb())
               tiles[r][c].setNumber(numSurroundingBombs(r,c));
            //GameBoardRootPanel[r][c] = ; //this may not work, since it is a different object
      		GameBoardRootPanel.add(tiles[r][c].getLabel()); //check this. it probably won't work
      	}
      }
   }

   public void revealSpaces(int row, int col)
   {
   	tiles[row][col].show(); //add the show method to the Tile class.
   	openCells.add(tiles[row][col]);
   	if(tiles[row][col].getBomb())
   	  System.out.println("This code has yet to be implemented!");

   	else if(numSurroundingBombs(row, col) == 0)
   	{
   	  	for(int r = Math.max(0, row - 1); r < Math.min(row + 1, HEIGHT); r++)
   	  	{
   	  		for(int c = Math.max(0, col - 1); c < Math.min(row + 1, WIDTH); c++)
   	  		{
   	  			if((r != row || c != col) && !tiles[r][c].getClicked()) // add the isOpen method to the Tile class.
	  			   {
	  				   revealSpaces(r,c);
	  			   }
   	  		}
   	  	}
   	}
   }

   public int numSurroundingBombs(int row, int col)
   {
   	int surroundingBombs = 0;
   	for(int r = Math.max(0, row - 1); r < Math.min(row + 2, HEIGHT); r++)
   	{
	    	for(int c = Math.max(0, col - 1); c < Math.min(col + 2, WIDTH); c++)
	  	   {
	  			if((r != row || c != col) && tiles[r][c].getBomb())
	  			{
	  				surroundingBombs++;
	  			}
	  		}
  	   }
  	   return surroundingBombs;
   }

   public void checkNewClicks()
   {
      for(int r = 0; r < HEIGHT; r++)
      {
         for(int c = 0; c < WIDTH; c++)
         {
            if(tiles[r][c].getClicked())
            {
               System.out.println("this is working");
               boolean inList = false;
               for(Tile t : openCells)
               {
                  if(t.getCol() == c && t.getRow() == r)
                     inList = true;
               }
               if(!inList)
               {
                  revealSpaces(r,c);
               }
            }

         }
      }
   }

   /**
    * This method is for the frame class, so that it can add the panel to its jframe
    * @return a reference to the root panel
    */
   public JPanel getGameBoardRootPanel()
   {
      return GameBoardRootPanel;
   }
}
