package demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;



//main class of game
public class Game {
	//Attributes
	int TurnCount=0;
	int Turn=0;
	private Display display;
	public int Width,Height;
	public String Title;
	private boolean Running = true;
	private boolean Updatechecker = false;
	//Threads that will run a class separately from the main class
	private BufferStrategy bs;
	private Graphics g;	
	private Board BaseBoard;
	private Player Human; 
	private CPU CPU1;
	ArrayList<CPU> AiList;

	//Constructor
	public Game(String Title,int Width,int Height){
		this.Width = Width;
		this.Height = Height;
		this.Title = Title;
	}
	//Methods 
	private void init(){
		display = new Display(Title,Width,Height,this);
		AiList = new ArrayList<CPU>();
		BaseBoard = new Board(10);
		Player Human = new Player(Color.red, BaseBoard.TerrList, BaseBoard, Turn);
		CPU CPU1 = new CPU(Color.blue, BaseBoard.TerrList, BaseBoard, Turn);
		AiList.add(CPU1);
		//Here will need to split up land to players 
		for (int i = 0; i < BaseBoard.TerrList.size();i++)
		{
			int random = (int)(Math.random() * 100);
			if (random > 50 )
			{
				//System.out.println(random);
				Human.TerrOwned.add(BaseBoard.TerrList.get(i));
				BaseBoard.TerrList.get(i).SetColor(Human.CountryColor);
				CPU1.TerrNotOwned.add(BaseBoard.TerrList.get(i));
				System.out.println("Player Colored proviences are: " + (i+1) );
			}
			else
			{
				CPU1.TerrOwned.add(BaseBoard.TerrList.get(i));
				BaseBoard.TerrList.get(i).SetColor(CPU1.CountryColor);
				Human.TerrNotOwned.add(BaseBoard.TerrList.get(i));
				System.out.println("CPU Colored proviences are: " + (i+1) );
			}
		}
		//Checking player owned terrories 
	/*	for (int i = 1; i < BaseBoard.TerrList.size();i++)
		{
			if (Human.TerrOwned.contains(BaseBoard.TerrList.get(i-1)))
			{
			System.out.println("Player owned Proviences are: " + i );
			}
		}*/
	}
	private void Render(){
		bs = display.getCanvas().getBufferStrategy();
		//Bs is a way to computer to draw to the screen
		//buffer is a hidden computer screen inside the screen
		//used to prevent flickering of screens
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);//makes 3 differnt buffer screens 
			return;
		}
		g = bs.getDrawGraphics();// idea (used to make the paintbrush or allow to draw)
		//Clear screen
		g.clearRect(0, 0, Width, Height);
		//Draw
		Font currentFont = g.getFont();
		Font newFont = currentFont.deriveFont(currentFont.getSize() * 2.0F);
		g.setFont(newFont);
		BaseBoard.render(g, BaseBoard.TerrList,this);
		//End Draw
		bs.show(); //allows grpahic to move past bufferes
		g.dispose(); //REmoved previous buffere graphic
		
		
	}
	private void Update(){	
		for (int i = 0; i < BaseBoard.TerrList.size(); i++)
		{
			BaseBoard.TerrList.get(i).tick();
		}
	//HumanPlayer.tick();
	
	}
	public void run()
	{
		init();
		
		//Limiting the speed of game regardless of computer
		int fps = 60;
		double timeperTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime(); //return current time of computer in nanosecounds
		long timer = 0;
		int ticks = 0;
		
		
		while(Running)
		{
			now = System.nanoTime(); //might need to swtich to milisecs
			delta += (now-lastTime)/ timeperTick;
			timer += (now-lastTime);
			lastTime = now;
			if(delta >= 1) //change of time = more than 1 sec
			{
				if(TurnCount == Turn)
					{
					Render();
					}
				if (TurnCount % 2 == 0 && Updatechecker == true)
					{
						Update();
						this.Updatechecker = false;
					}
				ticks++;
				delta--;
				} 
			if(timer >= 1000000000) //1000000000 nanosecs = 1 sec
			{
				//System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
	}
	public void AttackCalc (int AttackerID,int DefenderID){
		{
			//if (BaseBoard.TerrList.get(DefenderID-1).number !=)
			System.out.println("Rolling Dice");
			//you attacking dice rolls
			int YourAttack1,YourAttack2,YourAttack3;
			YourAttack1 = (int)(Math.random() * 6);
			YourAttack2 = (int)(Math.random() * 6);
			YourAttack3 = (int)(Math.random() * 6);
			System.out.println("Attack rolled a " +YourAttack1 + ","+YourAttack2+",and"+YourAttack3);
			
			//enemy attacking dice rolls
			int EnemeyDef1,EnemeyDef2;
			EnemeyDef1 = (int)(Math.random() * 6);
			EnemeyDef2 = (int) (Math.random() * 6);
			System.out.println("Defender rolled a " +EnemeyDef1 + " and "+EnemeyDef2);
			int temp,temp2;
			//you attacks sorted to two highest rolls
				if (YourAttack1 >= YourAttack2 && YourAttack1 >= YourAttack3)
				{
					temp = YourAttack1;
					if (YourAttack2 >= YourAttack3)
						temp2 = YourAttack2;
					else
						temp2 = YourAttack3;
				}
				else if (YourAttack2 >= YourAttack1 && YourAttack2 >= YourAttack3)
				{
					temp = YourAttack2;
					if (YourAttack1 >= YourAttack3)
						temp2 = YourAttack1;
					else
						temp2 = YourAttack3;
				}
				else
				{
					temp = YourAttack3;
					if (YourAttack1 >= YourAttack2)
						temp2 = YourAttack1;
					else
						temp2 = YourAttack2;
				}
				if(BaseBoard.TerrList.get(BaseBoard.Attackerindex(BaseBoard.TerrList)).NumberofUnits > 3 && BaseBoard.TerrList.get(BaseBoard.Defenderindex(BaseBoard.TerrList)).NumberofUnits >= 1)
				{
					if (EnemeyDef1 >= EnemeyDef2) // which enemy roll is larger
					{
						System.out.println("Attack " + temp + " vs " + EnemeyDef1);
						System.out.println("Attack " + temp2 + " vs " + EnemeyDef2);
						if(EnemeyDef1 >= temp)
						{
							BaseBoard.TerrList.get(BaseBoard.Attackerindex(BaseBoard.TerrList)).NumberofUnits--;
							System.out.println("Attacker ID = " + AttackerID);
						}
						else
						{
							BaseBoard.TerrList.get(BaseBoard.Defenderindex(BaseBoard.TerrList)).NumberofUnits--;
							System.out.println("Defender ID = " + DefenderID);
						}
						if(EnemeyDef2 >= temp2)
						{
							BaseBoard.TerrList.get(BaseBoard.Attackerindex(BaseBoard.TerrList)).NumberofUnits--;
							System.out.println("Attacker ID = " + AttackerID);
						}
						else
						{
							BaseBoard.TerrList.get(BaseBoard.Defenderindex(BaseBoard.TerrList)).NumberofUnits--;
							System.out.println("Defender ID = " + DefenderID);
						}
			
					}
					else //EnemeyDef2 is larger
					{
						System.out.println("Attack " + temp + " vs " + EnemeyDef2);
						System.out.println("Attack " + temp2 + " vs " + EnemeyDef1);
						if(EnemeyDef2 >= temp)
						{
							BaseBoard.TerrList.get(BaseBoard.Attackerindex(BaseBoard.TerrList)).NumberofUnits--;
							System.out.println("Attacker ID = " + AttackerID);
						}
						else
						{
							BaseBoard.TerrList.get(BaseBoard.Defenderindex(BaseBoard.TerrList)).NumberofUnits--;
							System.out.println("Defender ID = " + DefenderID);
						}
						if(EnemeyDef1 >= temp2)
						{
							BaseBoard.TerrList.get(BaseBoard.Attackerindex(BaseBoard.TerrList)).NumberofUnits--;
							System.out.println("Attacker ID = " + AttackerID);
						}
						else
						{
							BaseBoard.TerrList.get(BaseBoard.Defenderindex(BaseBoard.TerrList)).NumberofUnits--;
							System.out.println("Defender ID = " + DefenderID);
						}
					}
				}
				else if (BaseBoard.TerrList.get(BaseBoard.Attackerindex(BaseBoard.TerrList)).NumberofUnits <= 3)
				{
					System.out.println("Invalid Attack");
				}
				else 
				{
					BaseBoard.TerrList.get(BaseBoard.Defenderindex(BaseBoard.TerrList)).NumberofUnits = 0;
					ChangeOfOwnership();
				}
		}
	}
	public void ChangeOfOwnership()
	{
		if (BaseBoard.TerrList.get(BaseBoard.Defenderindex(BaseBoard.TerrList)).NumberofUnits <= 0)
		{		
			if (BaseBoard.TerrList.get(BaseBoard.Defenderindex(BaseBoard.TerrList)).GetBackColor() == Color.BLUE)
			{
				for (int i = 0; i < 0 ; i++)
				{
					
				}
				//CPU.TerrOwned.remove(BaseBoard.Defenderindex(CPU1.TerrOwned));
				//HumanPlayer.TerrOwned.add(BaseBoard.TerrList.get(BaseBoard.Defenderindex(BaseBoard.TerrList)));
				//HumanPlayer.TerrOwned.get(BaseBoard.Defenderindex(HumanPlayer.TerrOwned)).SetColor(Color.RED);
			}
			else if (BaseBoard.TerrList.get(BaseBoard.Defenderindex(BaseBoard.TerrList)).GetBackColor()  == Color.RED)
			{
				//HumanPlayer.TerrOwned.remove(BaseBoard.Defenderindex(HumanPlayer.TerrOwned));
				//CPU1.TerrOwned.add(BaseBoard.TerrList.get(BaseBoard.Defenderindex(BaseBoard.TerrList)));
				//CPU1.TerrOwned.get(BaseBoard.Defenderindex(CPU1.TerrOwned)).SetColor(Color.BLUE);
			}
		}
	}
	public void setSelectedTerritory(int num) {
			BaseBoard.setSelectedTerritory(num);
		}
	public void setTargetTerritory(int num) {
		BaseBoard.setTargetTerritory(num);
	}
	public void Attack (boolean b){
		//checking if it is a valid move
		if (BaseBoard.ValidMove(BaseBoard.Attackernumber(BaseBoard.TerrList),BaseBoard.Defendernumber(BaseBoard.TerrList)))
			if (b)
			{
				AttackCalc(BaseBoard.Attackernumber(BaseBoard.TerrList),BaseBoard.Defendernumber(BaseBoard.TerrList));
			}
	}
	public void Move(boolean b){
		if (b)
		{
			if (BaseBoard.ValidMove(BaseBoard.Attackerindex(BaseBoard.TerrList), BaseBoard.Defenderindex(BaseBoard.TerrList)))
				BaseBoard.MoveUnits(BaseBoard.Attackerindex(BaseBoard.TerrList), BaseBoard.Defenderindex(BaseBoard.TerrList));
		}
	}
	public void TurnCheck(boolean b)
		{
			TurnCount++;
			do
			{
				if (b)
				{ 
					System.out.println("TurnCount: " + TurnCount + " Turn: " + Turn);
					Turn++;
					AI();
					break;
				}
			}while(b);
		}
	public void Updatechecker(boolean b) {
			if (b)
			{
				this.Updatechecker = true; 
			}
	}
	public void AI()
	{
		for (int i = 0; i < AiList.size(); i++)
		{
			System.out.println(AiList.size());
			AiList.get(i).Turn();
			System.out.println("AI turn");
		}
	}
}

			
