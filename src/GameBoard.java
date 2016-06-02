import javax.swing.*;
import java.awt.*;
import java.lang.Override;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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
    private final int WIDTH = 9; //8
    private final int HEIGHT = 9; //8
    private final int NUM_BOMBS = 10; //16
    private JPanel GameBoardRootPanel;
    private Tile[][] tiles;
    private ArrayList<Tile> cellsWithBombs; //we need the cell class for this to work
    private ArrayList<Tile> openCells;
    private static int secondPassed;
    private int flagRemaining;
    private Timer timer;

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
        secondPassed = 0;
        flagRemaining = NUM_BOMBS;
        timer = new Timer();
    }

    /**
     * setUp configures the panel to have the all the necessary settings for the game.
     * Gridbaglayout seems to be the best layout to use, since it just arranges everything into a grid
     */
    public void setUp()
    {
        //GameBoardRootPanel.setPreferredSize(new Dimension(HEIGHT, WIDTH));
        GameBoardRootPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        timerTasks();
        constraints.gridx = 100;
        constraints.gridy = 0;

        
        JLabel remaining = new JLabel();
        constraints.gridx = 100;
        constraints.gridy = 100;

        remaining.setText("Number of bombs: " + flagRemaining);
        GameBoardRootPanel.add(remaining, constraints);



        for(int r = 0; r < tiles.length; r++)
        {
            for(int c = 0; c < tiles[r].length; c++)
            {
                constraints.fill = GridBagConstraints.HORIZONTAL;
                constraints.gridx = r;
                constraints.gridy = c;
                tiles[r][c] = new Tile(r, c);
                tiles[r][c].clickListener();
                GameBoardRootPanel.add(tiles[r][c].getLabel(), constraints);
            }
        }
        for(int i = 0; i < NUM_BOMBS; i++)
        {
            int r = (int) (Math.random() * HEIGHT);
            int c = (int) (Math.random() * WIDTH);
            while(tiles[r][c].getBomb())
            {
                r = (int) (Math.random() * HEIGHT);
                c = (int) (Math.random() * WIDTH);
            }
            tiles[r][c].addBomb();
            cellsWithBombs.add(tiles[r][c]);
        }
        for(int r = 0; r < HEIGHT; r++)
        {
            for(int c = 0; c < WIDTH; c++)
            {
                if(!tiles[r][c].getBomb())
                    tiles[r][c].setNumber(numSurroundingBombs(r,c));
                else
                    tiles[r][c].addBombList(cellsWithBombs);
            }
        }
        for(int r = 0; r < HEIGHT; r++) //adds the list of surrounding empty tiles to each empty tile
        {
            for(int c = 0; c < WIDTH; c++)
            {
                if(!tiles[r][c].getBomb())
                    tiles[r][c].addSurroundingEmptyTiles(findSurroundingEmptyTiles(r,c));

                //GameBoardRootPanel[r][c] = ; //this may not work, since it is a different object
                //GameBoardRootPanel.add(tiles[r][c].getLabel()); //check this. it probably won't work
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
                        if(t.getColumn() == c && t.getRow() == r)
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

    private ArrayList<Tile> findSurroundingEmptyTiles(int row, int col)
    {
        ArrayList<Tile> surroundingEmptyTiles = new ArrayList<Tile>(); //creates an ArrayList to hold the empty tiles

        if(numSurroundingBombs(row, col) == 0) //checks if the tile's number is 0
        {
            for(int r = Math.max(0, row - 1); r < Math.min(row + 2, HEIGHT); r++) //goes through all adjacent tiles to check if they contain bombs
            {
                for(int c = Math.max(0, col - 1); c < Math.min(col + 2, WIDTH); c++)
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

    /**
     * Lowers the count of remaining bombs whenever an undiscovered bomb is flagged
     */
    public void decrementBombCount()
    {
        for(int r = 0; r < tiles.length; r++)
        {
            for(int c = 0; c < tiles[r].length; c++)
            {
                if (tiles[r][c].getBombAndFlag())
                {
                    flagRemaining--;
                    getGameBoardRootPanel().updateUI();
                }
            }
        }
    }

    public void timerTasks()
    {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 100;
        constraints.gridy = 0;
        final JLabel time = new JLabel();
        secondPassed = 0;
        GameBoardRootPanel.add(time, constraints);
        TimerTask task = new TimerTask()
        {
            public void run()
            {
                time.setText("    Time:   " + secondPassed);
                secondPassed++;
            }
        };
        timer.scheduleAtFixedRate(task,1000,1000);
    }

    public Timer getTimer()
    {
        return timer;
    }

}
