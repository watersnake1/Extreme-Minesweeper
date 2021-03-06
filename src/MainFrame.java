import javax.swing.JFrame;
import java.awt.Dimension;
import java.lang.String;

/**
 *Jframe window class, should be run from a static class.
 *needs to contain a gameboard object to fill itself with.
 */
public class MainFrame
{
    private JFrame mainFrame;
    private GameBoard gameBoard;
    private Sound backgroundMusic;

    public MainFrame()
    {
        mainFrame = new JFrame("Extreme Minsweeper");
        gameBoard = new GameBoard();
        backgroundMusic = new Sound("Background_music_smm.wav");
    }

    /**
     *create all the components such that a window is shown on screen
     *does not add the gameboard as of yet
     */
    public void createAndShowGUI()
    {
        gameBoard.setUp();
        mainFrame.setPreferredSize(new Dimension(600,550));
        mainFrame.setResizable(true);
        mainFrame.add(gameBoard.getGameBoardRootPanel());
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        backgroundMusic.loop();
    }

    /**
     * <bold>This method is not necessary for the screen to appear to be updating</bold>
     * at with the current mouse event setup, but could be used later on for other components
     * gets the gameboard panel and causes it to update itself infinitely
     */
    public void tick()
    {
        boolean val = true;
        while(val)
        {
            try 
            {
                gameBoard.getGameBoardRootPanel().updateUI();
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

    }

    /**
     * This just sets everything up that has been already made
     * Opens up the JFrame 
     **/
    public static void main(String[] args)
    {
        Menu m = new Menu();
        m.createAndShowGUI();
    }
}
