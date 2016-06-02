package com.IZ.core;

public abstract class AbstractGame 
{	
	//abstract methods, to be extended by sub class
	public abstract void update(com.IZ.core.GameContainer gameContainer, float dt);
	public abstract void render(com.IZ.core.GameContainer gameContainer, com.IZ.core.Renderer r);
}
