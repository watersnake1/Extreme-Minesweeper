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
    private boolean isBomb;
    private int number;
    private ImageIcon unclickedCellIcon;
    private ImageIcon clickedCellIcon;
    private ImageIcon flaggedCellIcon;
    private ImageIcon bombedCellIcon;
    private ImageIcon hoveredCellIcon;
    private ImageIcon oneCellIcon;
    private ImageIcon twoCellIcon;
    private ImageIcon threeCellIcon;
    private ImageIcon fourCellIcon;
    private ImageIcon fiveCellIcon;
    private ImageIcon sixCellIcon;
    private ImageIcon sevenCellIcon;
    private ImageIcon eightCellIcon;
    private JFrame tile;
    private JLabel p;
    public Tile(int r, int c)
    {
        unclickedCellIcon = new ImageIcon("../images/UnclickedCell.png");
        clickedCellIcon = new ImageIcon("../images/ClickedCell.png");
        flaggedCellIcon = new ImageIcon("../images/FlagMarkedCell.png");
        hoveredCellIcon = new ImageIcon("../images/HoveredCell.png");
        bombedCellIcon = new ImageIcon("../images/BombedCell.png");
        oneCellIcon = new ImageIcon("../images/OneClickedCell.png");
        twoCellIcon = new ImageIcon("../images/TwoClickedCell.png");
        threeCellIcon = new ImageIcon("../images/ThreeClickedCell.png");
        fourCellIcon = new ImageIcon("../images/FourClickedCell.png");
        fiveCellIcon = new ImageIcon("../images/FiveClickedCell.png");
        sixCellIcon = new ImageIcon("../images/SixClickedCell.png");
        sevenCellIcon = new ImageIcon("../images/SevenClickedCell.png");
        eightCellIcon = new ImageIcon("../images/EightClickedCell.png");
        p = new JLabel(unclickedCellIcon);
        number = 0;
        isClicked = false;
        isFlagged = false;
        isBomb = false;
        this.r = r;
        this.c = c;
        hasBomb = false;
    }
    
    public static void main(String [] args)
    {
        Tile newTile = new Tile(2,3);
        newTile.setNumber(2);
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
            
            }
            public void mousePressed(MouseEvent e)
            {
                if(SwingUtilities.isLeftMouseButton(e) && isFlagged != true && hasBomb != true)
                {
                    if(number == 0){p.setIcon(clickedCellIcon);}
                    else if(number == 1){p.setIcon(oneCellIcon);}
                    else if(number == 2){p.setIcon(twoCellIcon);}
                    else if(number == 3){p.setIcon(threeCellIcon);}
                    else if(number == 4){p.setIcon(fourCellIcon);}
                    else if(number == 5){p.setIcon(fiveCellIcon);}
                    else if(number == 6){p.setIcon(sixCellIcon);}
                    else if(number == 7){p.setIcon(sevenCellIcon);}
                    else if(number == 8){p.setIcon(eightCellIcon);}
                    
                    isClicked = true;
                }
                else if(SwingUtilities.isRightMouseButton(e) && isFlagged != true && isClicked != true && isBomb != true)
                {
                    p.setIcon(flaggedCellIcon);
                    isFlagged = true;
                }
                else if(SwingUtilities.isRightMouseButton(e) && isFlagged == true && isClicked != true)
                {
                    p.setIcon(hoveredCellIcon);
                    isFlagged = false;
                }
                else if(SwingUtilities.isLeftMouseButton(e) && isFlagged != true && isClicked != true && hasBomb == true)
                {
                    p.setIcon(bombedCellIcon);
                    isBomb = true;
                }
            }
            public void mouseReleased(MouseEvent e)
            {
           
            }
            public void mouseEntered(MouseEvent e)
            {
                if(isClicked != true && isFlagged != true && isBomb != true)
                {
                    p.setIcon(hoveredCellIcon);
                }
            }
            public void mouseExited(MouseEvent e)
            {   
                if(isClicked != true && isFlagged != true && isBomb != true)
                {
                    p.setIcon(unclickedCellIcon);
                }
            }
        });
    }
    
    public void show()
    {
        p.addMouseListener(new MouseListener()
        {
            public void mouseClicked(MouseEvent e)
            {
                
            }
            public void mousePressed(MouseEvent e)
            {
                if(SwingUtilities.isLeftMouseButton(e) && isFlagged != true)
                {
                    if(number == 0){p.setIcon(clickedCellIcon);}
                    else if(number == 1){p.setIcon(oneCellIcon);}
                    else if(number == 2){p.setIcon(twoCellIcon);}
                    else if(number == 3){p.setIcon(threeCellIcon);}
                    else if(number == 4){p.setIcon(fourCellIcon);}
                    else if(number == 5){p.setIcon(fiveCellIcon);}
                    else if(number == 6){p.setIcon(sixCellIcon);}
                    else if(number == 7){p.setIcon(sevenCellIcon);}
                    else if(number == 8){p.setIcon(eightCellIcon);}
                    isClicked = true;
                }
                
            }
            public void mouseReleased(MouseEvent e)
            {
                
            }
            public void mouseEntered(MouseEvent e)
            {
            
            }
            public void mouseExited(MouseEvent e)
            { 
               
            }
        });
    }
    
    public void setNumber(int number)
    {
        this.number = number;
    }
    
    public int getNumber()
    {
        return number;
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
    
    public void addBomb()
    {
        hasBomb = true;
    }
    
    public int getRow()
    {
        return r;
    }
    
    public int getColumn()
    {
        return c;
    }
}
