import javax.swing.*;import javax.swing.ImageIcon;import javax.swing.JLabel;import javax.swing.JPanel;
import java.awt.*;
import java.awt.GridBagLayout;import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;import java.lang.Override;

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
   private JPanel GameBoardRootPanel;
   private JLabel square1;
   private ImageIcon unclickedCellIcon;
   private ImageIcon clickedCellIcon;

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
