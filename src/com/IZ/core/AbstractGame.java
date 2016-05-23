package com.IZ.core;

public abstract class AbstractGame 
{	
	//abstract methods, to be extended by sub class
	public abstract void update(GameContainer gameContainer, float dt);
	public abstract void render(GameContainer gameContainer, Renderer r);	
}
