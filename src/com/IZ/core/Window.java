package com.IZ.core;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Window 
{
	private JFrame frame;
	private Canvas canvas;
	private BufferedImage image;
	private static int windowWidth, windowHeight;
	private Graphics g;
	private BufferStrategy bs;
	
	public Window(GameContainer gc)
	{
		image = new BufferedImage(gc.getWidth(), gc.getHeight(), BufferedImage.TYPE_INT_RGB);		
		canvas = new Canvas();	
		Dimension s = new Dimension ((int)(gc.getWidth() * gc.getScale()), (int)(gc.getHeight() * gc.getScale()));
		windowWidth = gc.getWidth();
		windowHeight = gc.getHeight();
		
		canvas.setPreferredSize(s);
		canvas.setMaximumSize(s);

		frame = new JFrame(gc.getTitle());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(canvas, BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		canvas.createBufferStrategy(3);
		bs = canvas.getBufferStrategy();
		g = bs.getDrawGraphics(); 
	}
	
	public void update()
	{
		g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
		bs.show();
	}
	
	public void cleanUp()
	{
		g.dispose();
		bs.dispose();
		image.flush();
		frame.dispose();
	}

	public Canvas getCanvas() 
	{
		return canvas;
	}

	public BufferedImage getImage() 
	{
		return image;
	}

	public static int getWindowWidth() 
	{
		return windowWidth;
	}

	public static int getWindowHeight() 
	{
		return windowHeight;
	}
}
