package Tracker;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.Graphics;

import com.IZ.core.AbstractGame;
import com.IZ.core.GameContainer;
import com.IZ.core.Renderer;

public class ProgressTracker extends AbstractGame 
{
	private static Timer timer;
	private static TimerTask task;
	
	public static void main(String[] args)
	{
		GameContainer gc = new GameContainer(new ProgressTracker());
		timer = new Timer();
		task = new TimeTracker();
		timer.scheduleAtFixedRate(task, 1000, 1000);

		gc.setTitle("Timer & ScoreBoard Rendering Tester");
		gc.setWidth(200);
		gc.setHeight(200);
		gc.setScale(3f);
		gc.setClearScreen(true);
		gc.start();
	}

	@Override
	public void update(GameContainer gameContainer, float dt) 
	{
		
	}

	@Override
	public void render(GameContainer gameContainer, Renderer r) 
	{
		r.drawText("Time: " + TimeTracker.getTime(), 0xffff0000, 5, 5);
		r.drawText("Bombs: ??", 0xffff0000, 155, 5);
	}
}
