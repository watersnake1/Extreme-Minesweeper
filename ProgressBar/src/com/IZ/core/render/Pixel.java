package com.IZ.core.render;

public class Pixel 
{
	public static float getAlpha(int color)
	{
		return (0xff & (color >> 24)) / 255f;
	}
	
	public static float getRed(int color)
	{
		return (0xff & (color >> 16)) / 255f;
	}
	
	public static float getGreen(int color)
	{
		return (0xff & (color >> 8)) / 255f;
	}
	
	public static float getBlue(int color)
	{
		return (0xff & (color >> 0)) / 255f;
	}
	
	public static int getColor(float a, float r, float g, float b)
	{
		return ((int)(a * 255f + 0.5f) << 24 |
				(int)(r * 255f + 0.5f) << 16 |
				(int)(g * 255f + 0.5f) << 8 |
				(int)(b * 255f + 0.5f));
	}
	
	public static int getColorPower(int color,float power)
	{
		return getColor(1, getRed(color) * power,
						   getGreen(color) * power,
						   getBlue(color) * power);
	}
	
	public static int getLightBlend(int color, int light, int ambientLight)
	{
		float r = getRed(light);
		float g = getGreen(light);
		float b = getBlue(light);
		
		if(r < getRed(ambientLight))
		{
			r = getRed(ambientLight);
		}
		
		if(g < getGreen(ambientLight))
		{
			g = getGreen(ambientLight);
		}
		
		if(b < getBlue(ambientLight))
		{
			b = getBlue(ambientLight);
		}
		
		return getColor(1, getRed(color) * r, getGreen(color) * g, getBlue(color) * b);
	}
	
	public static int getMax(int color1, int color2)
	{
		return getColor(1, Math.max(getRed(color1), getRed(color2)),
						   Math.max(getGreen(color1), getGreen(color2)),
				           Math.max(getBlue(color1), getBlue(color2)));
	}
}
