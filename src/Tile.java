import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;

public class Tile
{
    private boolean isFlagged;
    private boolean hasBomb;
    private int r;
    private int c;
    private boolean isClicked;
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
    private ArrayList<Tile> surroundingEmptyTiles;
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
        this.r = r;
        this.c = c;
        hasBomb = false;
        surroundingEmptyTiles = new ArrayList<Tile>();
        clickListener();
    }
    
    public static void main(String [] args)
    {
        Tile newTile = new Tile(2,3);
        newTile.setNumber(1);
        newTile.setTile();
        newTile.clickListener();
        Tile newTile2 = new Tile(3,3);
        newTile2.addBomb();
        newTile2.setTile();
        newTile2.clickListener();     
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
                if(SwingUtilities.isLeftMouseButton(e))
                {
                    show();
                    openSurroundingEmptyTiles();
                }
                else if(SwingUtilities.isRightMouseButton(e) && !isFlagged && !isClicked)
                {
                    p.setIcon(flaggedCellIcon);
                    isFlagged = true;
                }
                else if(SwingUtilities.isRightMouseButton(e) && isFlagged && !isClicked)
                {
                    p.setIcon(hoveredCellIcon);
                    isFlagged = false;
                }
            }
            
            public void mouseReleased(MouseEvent e)
            {
           
            }

            public void mouseEntered(MouseEvent e)
            {
                if(!isClicked && !isFlagged)
                {
                    p.setIcon(hoveredCellIcon);
                }
            }

            public void mouseExited(MouseEvent e)
            {   
                if(!isClicked && !isFlagged)
                {
                    p.setIcon(unclickedCellIcon);
                }
            }
        });
    }
    
    public void show()
    {
        if(!isFlagged)
        {
            isClicked = true;
            if(hasBomb)
            {
                p.setIcon(bombedCellIcon);
            }
            else
            {
                switch(number)
                {
                    case 1: p.setIcon(oneCellIcon); break;
                    case 2: p.setIcon(twoCellIcon); break;
                    case 3: p.setIcon(threeCellIcon); break;
                    case 4: p.setIcon(fourCellIcon); break;
                    case 5: p.setIcon(fiveCellIcon); break;
                    case 6: p.setIcon(sixCellIcon); break;
                    case 7: p.setIcon(sevenCellIcon); break;
                    case 8: p.setIcon(eightCellIcon); break;
                    default: p.setIcon(clickedCellIcon); break;
                }
            }
        }
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
    
    public int getCol()
    {
        return c;
    }

    public void addSurroundingEmptyTiles(ArrayList<Tile> surroundingEmptyTiles)
    {
        this.surroundingEmptyTiles = surroundingEmptyTiles;
    }

    private void openSurroundingEmptyTiles()
    {
        for(Tile emptyTile : surroundingEmptyTiles)
        {
            emptyTile.show();
        }
    }
}
