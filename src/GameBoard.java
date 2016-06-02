import javax.swing.*;
import java.awt.*;
import java.lang.Override;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This is the gameboard class.
 * It primarily serves as how we set up the different tile locations
 * It also houses many of the different methods that we use to check the tiles with
 */
public class GameBoard
{
    private final int WIDTH = 9; //8
    private final int HEIGHT = 9; //8
    private final int NUM_BOMBS = 10; //16
    private JPanel GameBoardRootPanel;
    private Tile[][] tiles;
    private ArrayList<Tile> cellsWithBombs; //we need the cell class for this to work
    private ArrayList<Tile> emptyCells;
    private static int secondPassed;
    private int flagRemaining;

    /**
     * The icons and labels are being created in the gameboard class for the sake of testing, normally there would
     * only be cell objects within here.
     */
    public GameBoard()
    {
        GameBoardRootPanel = new JPanel();

        tiles = new Tile[HEIGHT][WIDTH]; //sets the grid of squares
        cellsWithBombs = new ArrayList<Tile>();
        emptyCells = new ArrayList<Tile>();
        secondPassed = 0;
        flagRemaining = NUM_BOMBS;
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
        
        constraints.gridx = 100;
        constraints.gridy = 0;
        final JLabel time = new JLabel();
        secondPassed = 0;
        GameBoardRootPanel.add(time, constraints);
        /**
         * This is a timer task
         * its main purpose is to constantly check every second and then update the screen
         **/
        TimerTask task = new TimerTask() 
        {
                public void run() 
                {
                    time.setText("    Time:   " + secondPassed);
                    secondPassed++;
                }
            };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task,1000,1000);
        
        JLabel remaining = new JLabel();
        constraints.gridx = 100;
        constraints.gridy = 100;

        remaining.setText("Bombs Remaining: " + flagRemaining);
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
        //This will go through the spaces, check for bombs and place them accordingly
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
                {
                    tiles[r][c].setNumber(numSurroundingBombs(r,c));
                    emptyCells.add(tiles[r][c]);
                }
                else
                    tiles[r][c].addBombList(cellsWithBombs);
            }
        }
        for(int r = 0; r < HEIGHT; r++) //adds the list of surrounding empty tiles to each empty tile
        {
            for(int c = 0; c < WIDTH; c++)
            {
                if(!tiles[r][c].getBomb())
                {
                    tiles[r][c].addSurroundingEmptyTiles(findSurroundingEmptyTiles(r,c));
                    tiles[r][c].addEmptyList(emptyCells);
                }

                //GameBoardRootPanel[r][c] = ; //this may not work, since it is a different object
                //GameBoardRootPanel.add(tiles[r][c].getLabel()); //check this. it probably won't work
            }
        }
    }
    
    /**
     * This method will count the number of bombs a tile is touching
     * That way we can display the number on screen
     * @param row the row in which we are looking for surrounding bombs
     * @param col the column in which we are looking for surrounding bombs
     **/
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

    /**
     * This method is for the frame class, so that it can add the panel to its jframe
     * @return a reference to the root panel
     */
    public JPanel getGameBoardRootPanel()
    {
        return GameBoardRootPanel;
    }
    
    /**
     * This is the method used to find the empty surrounding spaces
     * currently a bit lag intensive
     **/
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
}
