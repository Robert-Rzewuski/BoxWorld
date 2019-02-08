package demo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Territories 
{
	//Attributes
	private Color color;
	int NumberofUnits = 10;
	Boolean isSelected = false;
	Boolean Target = false;
	int x,y,width,height;
	int tempx;
	int tempy;
	int number=0;
	int Area;
	boolean IsAbleMove;
	private Color TempColor;
	
	Territories(Color Inputcolor,ArrayList<Territories> TerrList,int x,int y,int width,int height,int num)
	{
		this.TempColor = Inputcolor;
		this.color = Inputcolor;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.number = num;
		this.Area = height * width;
	}
	public void setSelected(boolean b) {
		//System.out.println("setSelected: "+number+" " +b);
		isSelected = b;
		if (isSelected)
			this.TempColor = Color.CYAN;
		else
		{
			//check if another terr is target and dont change color
			if (!Target)
				TempColor = color;
		}
	}
	public void setTarget(boolean a) {
		//System.out.println("setTarget: "+number+" " +a);
		Target = a;
		if (Target)
			this.TempColor = Color.MAGENTA;
		else
		{
		if(!isSelected)
			TempColor = color;
		}
	}
	public Color getColor(){
		return TempColor;
	}
	public Color GetBackColor(){
		return color;
	}
	public void SetColor(Color color){
		this.TempColor = color;
		this.color = color;
	}
	public  void tick() {
		
			this.NumberofUnits += 5;
	}
	public  void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRoundRect(x, y, width, height, 1, 1);
		g.setColor(TempColor);
		g.fillRoundRect(x+5, y+5, width-10, height-10, 1, 1);
		g.setColor(Color.BLACK);
		g.drawString(""+this.number, x+50, y+50);
		g.drawString(""+NumberofUnits, x+(width/2), y+(height/2));
	}
	public boolean CheckifSelected ()
	{
		if (isSelected)
			return true;
		else
			return false;
	}
	public boolean CheckifTarget ()
	{
		if (Target)
			return true;
		else
			return false;
	}
	
	
}