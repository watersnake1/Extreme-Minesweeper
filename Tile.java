import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class Tile
{
    private boolean isFlagged;
    private boolean hasBomb;
    private int r;
    private int c;
    private boolean isClicked;
    private ImageIcon unclickedCellIcon;
    private ImageIcon clickedCellIcon;
    private ImageIcon flaggedCellIcon;
    private ImageIcon bombedCellIcon;
    private ImageIcon hoveredCellIcon;
    private JFrame tile;
    private JLabel p;
    public Tile(int r, int c)
    {
        unclickedCellIcon = new ImageIcon("images/UnclickedCell.png");
        clickedCellIcon = new ImageIcon("images/ClickedCell.png");
        flaggedCellIcon = new ImageIcon("images/FlagMarkedCell.png");
        hoveredCellIcon = new ImageIcon("images/HoveredCell.png");
        bombedCellIcon = new ImageIcon("images/BombedCell.png");
        p = new JLabel(unclickedCellIcon);
        isClicked = false;
        isFlagged = false;
        this.r = r;
        this.c = c;
        hasBomb = false;
    }
    
    public static void main(String [] args)
    {
        Tile newTile = new Tile(2,3);
        newTile.setTile();
        newTile.clickListener();
        
    }
    
    public void setTile()
    {
        tile = new JFrame("wow");
        tile.setSize(800, 800);
        
        tile.setLayout(new GridBagLayout());
        p = new JLabel(unclickedCellIcon);
        tile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        tile.add(p);
        tile.setVisible(true);
    }
    
    public void clickListener()
    {
        p.addMouseListener(new MouseListener()
        {
        public void mouseClicked(MouseEvent e)
        {
            p.setIcon(clickedCellIcon); 
        }
        public void mousePressed(MouseEvent e)
        {
            p.setIcon(flaggedCellIcon);
        }
        public void mouseReleased(MouseEvent e)
        {
           
        }
        public void mouseEntered(MouseEvent e)
        {
             p.setIcon(hoveredCellIcon);
        }
        public void mouseExited(MouseEvent e)
        {
             p.setIcon(unclickedCellIcon);
        }
    });
    }
    
    public void unclickedImage()
    {
       p.setIcon(unclickedCellIcon); 
    }
    
    public JLabel getLabel()
    {
        return p;
    }
    
    public boolean getClicked()
    {
        return isClicked;
    }
    
    public void setClicked(boolean clicked)
    {
        isClicked = clicked;
    }
    
    public boolean getFlagged()
    {
        return isFlagged;
    }
    
    public void setFlagged(boolean flagged)
    {
        isFlagged = flagged;
    }
    
    public boolean getBomb()
    {
        return hasBomb;
    }
    
    public void setBomb(boolean bomb)
    {
        hasBomb = bomb;
    }
    
    public int getRow()
    {
        return r;
    }
    
    public int getColumn()
    {
        return c;
    }
    
    public void addBomb()
    {
        hasBomb = true;
    }
}
