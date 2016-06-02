

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Christian on 6/1/16.
 */
public class Menu {
   private JFrame frame;
   private JPanel panel;
   private ImageIcon titleIcon;
   private JLabel titleLabel;
   private MainFrame mainFrame;

   public Menu() {
      frame = new JFrame();
      panel = new JPanel();
      titleIcon = new ImageIcon("../images/MenuScreen.png");
      titleLabel = new JLabel();
      mainFrame = new MainFrame();
   }

   public void createAndShowGUI()
   {
      frame.setVisible(true);
      frame.setPreferredSize(new Dimension(640,360));
      frame.add(panel);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      titleLabel.setIcon(titleIcon);
      panel.add(titleLabel);
      panel.updateUI();
      frame.pack();
      labelClickListener();
   }

   public void labelClickListener()
   {
      titleLabel.addMouseListener(new MouseListener() {
         @Override
         public void mouseClicked(MouseEvent e) {
            mainFrame.createAndShowGUI();
         }

         @Override
         public void mousePressed(MouseEvent e) {

         }

         @Override
         public void mouseReleased(MouseEvent e) {

         }

         @Override
         public void mouseEntered(MouseEvent e) {

         }

         @Override
         public void mouseExited(MouseEvent e) {

         }
      });
   }

   public static void main(String[] args)
   {
      Menu m = new Menu();
      m.createAndShowGUI();
   }
}
