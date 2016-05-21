package com.IZ.core;

import java.awt.image.DataBufferInt;

import com.IZ.core.render.Font;
import com.IZ.core.render.Image;

public class Renderer 
{
	private GameContainer gc;
	
	private int width, height;
	private int[] pixels;
	private Font font = Font.DEFAULT;	
	private int defaultColor = 0xff000000;
	
	private int transX, transY;
	
	/**
	 * initialize all instance field
	 * @param GameContainer gc
	 */
	public Renderer(GameContainer gc)
	{
		this.gc = gc;
		width = gc.getWidth();
		height = gc.getHeight();
		pixels = ((DataBufferInt)gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
	}
	
	/**
	 * sets the pixel data for the rendering space
	 * @param int w, h , color; ShadowType lightBlock
	 */
	public void setPixel(int w, int h, int color)
	{
		w -= transX;
		h -= transY;
		
		if(w < 0 || w >= width || h < 0 || h >= height || color == 0xffff00ff || color == 0x00000000)
		{
			return;
		}
		
		pixels[w + h * width] = color;
	}
	
	/**
	 * draws the text from existing font file
	 * refers to Unicode character list.
	 * @param String text; int color, offX, offY
	 */
	public void drawText(String text, int color, int offX, int offY)
	{
		text = text.toUpperCase();
		
		int offset = 0;
		
		for(int i = 0; i < text.length(); i++)
		{
			int unicode = text.codePointAt(i) - 32;
			
			for(int x = 0; x < font.widths[unicode]; x++)
			{
				for(int y = 1; y < font.image.height; y++)
				{
					if(font.image.pixels[(x + font.offsets[unicode]) + y * font.image.width] == 0xffffffff)
					{
						setPixel(x + offX + offset, y + offY - 1, color);
					}
				}
			}
			offset += font.widths[unicode];
		}
	}
	
	/**
	 * draws the image on the rendering space
	 * @param image
	 * @param offX
	 * @param offY
	 */
	public void drawImage(Image image, int offX, int offY)
	{
		for(int w = 0; w < image.width; w++)
		{
			for(int h = 0; h < image.height; h++)
			{
				setPixel(w + offX, h + offY, image.pixels[w + h * image.width]);
			}
		}
	}
	
	/**
	 * same thing as drawImage method above, only
	 * this one is optimized to draw tile specifically
	 * @param image
	 * @param offX
	 * @param offY
	 * @param tileX
	 * @param tileY
	 */
	
	/**
	 * draws a simple rectangle
	 * @param offX
	 * @param offY
	 * @param w
	 * @param h
	 * @param color
	 * @param type
	 */
	public void drawRectangles(int offX, int offY, int w, int h, int color)
	{
		for(int x = 0; x < w; x++)
		{
			for(int y = 0; y < h; y++)
			{
				setPixel(x + offX, y + offY, color);
			}
		}
	}
	
	/**
	 * sets the light map specified
	 * @param w
	 * @param h
	 * @param color
	 */
	
	// void dumpMap()
	{
			for(int w = 0; w < width; w++)
			{
				for(int h = 0; h < height; h++)
				{
					//setPixel(w, h, Pixel.getLightBlend(pixels[w + h * width], lightMap[w + h * width]));
				}
			}
	}
	
	public void clear()
	{
		for(int w = 0; w < width; w++)
		{
			for(int h = 0; h < height - 100; h++)
			{
				pixels[w + h * (width - 100)] = defaultColor;
			}
			
		}
	}

	public int getDefaultColor() 
	{
		return defaultColor;
	}

	public void setDefaultColor(int defaultColor) 
	{
		this.defaultColor = defaultColor;
	}

	public int getTransX() 
	{
		return transX;
	}

	public void setTransX(int transX) 
	{
		this.transX = transX;
	}

	public int getTransY() 
	{
		return transY;
	}

	public void setTransY(int transY) 
	{
		this.transY = transY;
	}

	public Font getFont() 
	{
		return font;
	}

	public void setFont(Font font) 
	{
		this.font = font;
	}
	
	public void drawImage(Image image) {drawImage(image, 0, 0);}
}
