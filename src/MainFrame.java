import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
    private WindowListener windowListener;

    public MainFrame()
    {
        mainFrame = new JFrame("Extreme Minsweeper");
        gameBoard = new GameBoard();
        backgroundMusic = new Sound("Background_music_smm.wav");
        windowListener = new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent windowEvent) {
                backgroundMusic.stop();
                getGameBoard().getTimer().cancel();
            }
        };
    }

    /**
     *create all the components such that a window is shown on screen
     *does not add the gameboard as of yet
     */
    public void createAndShowGUI()
    {
        gameBoard.setUp();
        mainFrame.setPreferredSize(new Dimension(600,550));
        mainFrame.add(gameBoard.getGameBoardRootPanel());
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        backgroundMusic.loop();
        mainFrame.addWindowListener(windowListener);

    }

    public GameBoard getGameBoard()
    {
        return gameBoard;
    }

    public static void main(String[] args)
    {
        Menu m = new Menu();
        m.createAndShowGUI();
    }
}
