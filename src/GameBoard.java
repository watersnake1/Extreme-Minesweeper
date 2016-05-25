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
      
      for(int r = 0; r < tiles.length; r++) //creates all the tiles on the board
      {
         for(int c = 0; c < tiles[0].length; c++)
         {
            tiles[r][c] = new Tile(r, c);
            tiles[r][c].clickListener();
            GameBoardRootPanel.add(tiles[r][c].getLabel()); //this is necessary to make the tile visible on the actual board
         }
      }

      for(int i = 0; i < NUM_BOMBS; i++)
      {
         int r = (int) (Math.random() * HEIGHT); //find a random row position
         int c = (int) (Math.random() * WIDTH); //find a random column position
         while(tiles[r][c].getBomb())  //continues to calculate random positions until one without a bomb is found
         {
            r = (int) (Math.random() * HEIGHT);
            c = (int) (Math.random() * WIDTH);
         }
         tiles[r][c].addBomb(); //adds a bomb to this new position
         cellsWithBombs.add(tiles[r][c]); //adds this tile to the ArrayList of tiles with bombs
      }

      for(int r = 0; r < HEIGHT; r++) //assigns the number of surrounding bombs to each tile
      {
      	for(int c = 0; c < WIDTH; c++)
      	{
      		if(!tiles[r][c].getBomb())
               tiles[r][c].setNumber(numSurroundingBombs(r,c));
      	}
      }

      for(int r = 0; r < HEIGHT; r++) //adds the list of surrounding empty tiles to each empty tile
      {
         for(int c = 0; c < WIDTH; c++)
         {
            if(!tiles[r][c].getBomb())
               tiles[r][c].addSurroundingEmptyTiles(findSurroundingEmptyTiles(r,c));

            //GameBoardRootPanel[r][c] = ; //this may not work, since it is a different object
            GameBoardRootPanel.add(tiles[r][c].getLabel()); //check this. it probably won't work
         }
      }
   }

   /**
    * [intended to] find the empty spaces surrounding an empty space, so that when the player
    * clicks on an empty space, all the spaces around it are revealed. This only partially works
    * @param int row the row of the space being checked
    * @param int col the colum of the spaces being checked
    * @return the ArrayList of empty tiles surrounding the tile in question
    */
   private ArrayList<Tile> findSurroundingEmptyTiles(int row, int col)
   {
      ArrayList<Tile> surroundingEmptyTiles = new ArrayList<Tile>(); //creates an ArrayList to hold the empty tiles
     
      if(numSurroundingBombs(row, col) == 0) //checks if the tile's number is 0
      {
         for(int r = Math.max(0, row - 1); r < Math.min(row + 2, HEIGHT); r++) //goes through all adjacent tiles to check if they contain bombs
         {
            for(int c = Math.max(0, col - 1); c < Math.min(row + 2, WIDTH); c++)
            {
               if(!tiles[r][c].getBomb() && (r != row || c != col) /*&& !surroundingEmptyTiles.contains(tiles[r][c])*/) //if the tile does not have a bomb
               {
                  surroundingEmptyTiles.add(tiles[r][c]); //adds the tile to the list
                  //if(numSurroundingBombs(r,c) == 0)
                     //surroundingEmptyTiles.addAll(findSurroundingEmptyTiles(r,c));
               }
            }
         }
      }
      return surroundingEmptyTiles;
   }

   //this class is not currently being used, ignore it
   public void revealSpaces(int row, int col)
   {
   	tiles[row][col].show();
   	openCells.add(tiles[row][col]);
   	if(tiles[row][col].getBomb())
   	  System.out.println("This code has yet to be implemented!");

   	else if(numSurroundingBombs(row, col) == 0)
   	{
   	  	for(int r = Math.max(0, row - 1); r < Math.min(row + 1, HEIGHT); r++)
   	  	{
   	  		for(int c = Math.max(0, col - 1); c < Math.min(row + 1, WIDTH); c++)
   	  		{
   	  			if((r != row || c != col) && !tiles[r][c].getClicked()) 
	  			   {
	  				   revealSpaces(r,c);
	  			   }
   	  		}
   	  	}
   	}
   }

   /**
    * finds the number of spaces with bombs surrounding the position inputted
    * @param int row the row of the space being checked
    * @param int col the colum of the spaces being checked
    * @return the number of adjacent tiles containing bombs
    */
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

   //this is not currently used, ignore it.
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
