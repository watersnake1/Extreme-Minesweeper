
/**
 * Created by cmurray17 on 5/23/16.
 */
public class MainFrameTester implements Runnable
{
    public static void main(String[] args)
    {
        final MainFrame mainFrame = new MainFrame();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                mainFrame.createAndShowGUI();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true)
                {
                    System.out.println(true);
                }
            }
        });
        thread1.start();
        thread2.start();

    }

    @Override
    public void run(){}
}
