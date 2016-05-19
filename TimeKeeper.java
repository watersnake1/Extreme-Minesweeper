import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TimeKeeper
{
    private static int secondPassed=0;
    private static String asString="";
    
    Timer myTimer= new Timer();
    TimerTask task= new TimerTask()
    {
        public void run()
        {
            while(secondPassed<1000)
            {
              secondPassed++;
            }
            asString= String.valueOf(secondPassed);
            System.out.println("Time: " + secondPassed);
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
    
    public static void main(String[] args)
    {
        TimeKeeper timePiece= new TimeKeeper();
        JLabel timer= new JLabel();
        
        JPanel main = new JPanel();
        
        timer.setText(asString);
        timer.setLocation(0,0);
        main.add(timer);
    }
}