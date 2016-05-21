package com.IZ.core;

public class GameContainer implements Runnable
{
	public Thread thread;
	
	private AbstractGame game;
	private Window window;	
	private Renderer renderer;
	
	private int width = 640, height = 360;
	private float scale = 1f;
	private double frameCap = 1.0 / 60.0;
	
	private boolean isRunning = false;
	private boolean dynamicLighting = false;
	private boolean clearScreen = false;
	private boolean lightingOn = false;
	private boolean devMode = false;
	private String title = "2D Engine WIP By IZ";
	
	/**
	 * initialize game
	 * @param game
	 */
	public GameContainer(AbstractGame game)
	{
		this.game = game;
	}
	
	/*
	 * start the game passed into the GameContainer
	 * If the the isRnning, do nothing, otherwise 
	 * construct elements for the game.
	 */
	public void start()
	{
		if(isRunning)
		{
			return;
		}
		window = new Window(this);
		renderer = new Renderer(this);
		thread = new Thread(this);
		thread.run();
	}
	
	/*
	 * if the game is already running, stop it.
	 * otherwise do nothing.
	 */
	public void stop()
	{
		if(!isRunning)
		{
			return;
		}
		isRunning = false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run()
	{
		isRunning = true;
		
		double passedTime = 0;
		double unprocessedTime = 0;
		double firstTime = 0;
		double lastTime = System.nanoTime()/ 1000000000.0;
		double frameTime = 0;
		int frames = 0;
		
		while(isRunning)
		{
			boolean render = true;
			
			//keep track the system run time
			//helps with updating
			firstTime = System.nanoTime() / 1000000000.0;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;
			
			unprocessedTime += passedTime;
			frameTime += passedTime;
			
			while(unprocessedTime >= frameCap)
			{
				//updates frame rate, physics, and input
				game.update(this, (float)frameCap);
				unprocessedTime -= frameCap;		
				render = true;
				
				//calculates frame rate
				if(frameTime >= 1)
				{
					frameTime = 0;
					frames = 0;
				}
			}
			
			//if anything has been rendered on the screen
			//and the clear method is called, then clear
			//the screen
			if(render)
			{			
				if(clearScreen)
				{
					renderer.clear();
				}
				
				game.render(this, renderer);	
				
				//if devMode is enabled, display the predefined texts
				//if(devMode) 
				{
					//renderer.drawText("2D Engine Running", 0xffffff00, 0, 8);
					//renderer.drawText("FPS:" + fps, 0xffffff00, 0, 0);
				}
				
				//update window
				window.update();
				frames++;
			}
			else
			{
				try 
				{
					Thread.sleep(1);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}	
			}
		}
		cleanUp();
	}
	
	private void cleanUp()
	{
		window.cleanUp();
	}

	public int getWidth() 
	{
		return width;
	}

	public void setWidth(int width) 
	{
		this.width = width;
	}

	public int getHeight() 
	{
		return height;
	}

	public void setHeight(int height) 
	{
		this.height = height;
	}

	public float getScale() 
	{
		return scale;
	}

	public void setScale(float scale) 
	{
		this.scale = scale;
	}

	public String getTitle() 
	{
		return title;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}

	public Window getWindow() 
	{
		return window;
	}

	public boolean isDynamicLighting() 
	{
		return dynamicLighting;
	}

	public void setDynamicLighting(boolean dynamicLighting) 
	{
		this.dynamicLighting = dynamicLighting;
	}

	public boolean isClearScreen() 
	{
		return clearScreen;
	}

	public void setClearScreen(boolean clearScreen) 
	{
		this.clearScreen = clearScreen;
	}

	public boolean isLightingOn() 
	{
		return lightingOn;
	}

	public void setLightingOn(boolean lightingOn) 
	{
		this.lightingOn = lightingOn;
	}

	public double getFrameCap() 
	{
		return frameCap;
	}

	public void setFrameCap(int frame) 
	{
		this.frameCap = 1.0 / frame;
	}

	public AbstractGame getGame() 
	{
		return game;
	}

	public void setGame(AbstractGame game) 
	{
		this.game = game;
	}

	public boolean isDevMode() 
	{
		return devMode;
	}

	public void setDevMode(boolean devMode) 
	{
		this.devMode = devMode;
	}
}
