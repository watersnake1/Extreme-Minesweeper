import javax.swing.*;
import java.awt.*;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.Override;
imoprt java.util.ArrayList;

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
   private static boolean HAS_BOMB = true;
   private static boolean BEEN_CLICKED = true;

   private JPanel GameBoardRootPanel;
   private JLabel square1;
   private ImageIcon unclickedCellIcon;
   private ImageIcon clickedCellIcon;
   private Tile[][] tiles;
   private int numBombs;
   private List<Tile> cellsWithBombs; //we need the cell class for this to work

   /**
    * The icons and labels are being created in the gameboard class for the sake of testing, normally there would
    * only be cell objects within here.
    */
   public GameBoard()
   {
      GameBoardRootPanel = new JPanel();
      unclickedCellIcon = new ImageIcon("images/UnclickedCell.png");
      clickedCellIcon = new ImageIcon("images/ClickedCell.png");
      square1 = new JLabel(unclickedCellIcon);

      numBombs = 16; //sets the number of bombs
      tiles = new Tile[8][8]; //sets the grid of squares
      cellsWithBombs = new ArrayList<Tile>();
   }

   /**
    * setUp configures the panel to have the all the necessary settings for the game.
    * Gridbaglayout seems to be the best layout to use, since it just arranges everything into a grid
    */
   public void setUp()
   {
      GameBoardRootPanel.setLayout(new GridBagLayout());
      GameBoardRootPanel.add(square1);
      cellListeners();

      for(int r = 0; r < tiles.length; r++)
      {
         for(int c = 0; c < tiles[0].length; c++)
         {
            tiles[r][c] = new Tile(!BEEN_CLICKED, !HAS_BOMB); //placeholder
         }
      }
      for(int i = 0; i < numBombs; i++)
      {
         int r = (int) (Math.random() * 8);
         int c = (int) (Math.random() * 8);
         while(tiles[r][c].hasBomb())  //placeholder
         {
            r = (int) (Math.random() * 8);
            c = (int) (Math.random() * 8);
         }
         tiles[r][c].addBomb(); //placeholder
         cellsWithBombs.add(tiles[r][c]);
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

   /**
    * This is only here for the sake of testing, normally each cell would handle its own click events
    * This is an anonymous inner class that processes the mouse click events for square 1, saying what happens
    * when the mouse is pressed, released, etc.
    */
   public void cellListeners()
   {
      square1.addMouseListener(new MouseListener() {
         @Override
         public void mouseClicked(MouseEvent e) {
            square1.setIcon(clickedCellIcon);
         }

         @Override
         public void mousePressed(MouseEvent e) {
         }

         @Override
         public void mouseReleased(MouseEvent e) {
            square1.setIcon(unclickedCellIcon);
         }

         @Override
         public void mouseEntered(MouseEvent e) {

         }

         @Override
         public void mouseExited(MouseEvent e) {

         }
      });
   }
}
