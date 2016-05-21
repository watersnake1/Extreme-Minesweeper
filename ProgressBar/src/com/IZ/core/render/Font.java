package com.IZ.core.render;

public enum Font 
{
	DEFAULT("/Font/font.png");
	
	public final int NUM_UNICODES = 59;
	public int[] offsets = new int[NUM_UNICODES];
	public int[] widths = new int[NUM_UNICODES];
	public Image image;
	
	Font(String path)
	{
		image = new Image(path);
		
		int unicode = -1; 
		
		for(int i = 0; i < image.width; i++)
		{
			int color = image.pixels[i];
			
			if(color == 0xff0000ff)
			{
				unicode++;
				offsets[unicode] = i;
			}
			
			if(color == 0xffffff00)
			{
				widths[unicode] = i - offsets[unicode];
			}
		}
	}
}
 