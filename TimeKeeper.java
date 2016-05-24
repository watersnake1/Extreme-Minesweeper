import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class TimeKeeper
{
    private static int secondPassed=0;
    private static String asString="";
    private static JLabel label = new JLabel();
    private static JFrame frame = new JFrame();
    private static JPanel panel = new JPanel();
    
    Timer myTimer= new Timer();
    TimerTask task= new TimerTask()
    {
        public void run()
        {
            asString= String.valueOf(secondPassed);
            //System.out.println("Time: " + secondPassed);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);
            panel.add(label);
            frame.pack();
            label.setText("Time: " + secondPassed);
            panel.updateUI();
            
            secondPassed++;
        }
    }; 
    public void start()
    {
        myTimer.scheduleAtFixedRate(task,1000,1000);
    }
    
    public void stop()
    {
        myTimer.cancel();
    }
    
    public void reset()
    {
        myTimer.cancel();
        secondPassed=0;
    }
    
    public void printToLabel()
    {
    
    }
    
    public static void main(String[] args)
    {
    	TimeKeeper keeper = new TimeKeeper();
    	keeper.start();
    }
}
