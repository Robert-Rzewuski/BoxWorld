package demo;

import java.awt.Color;
import java.awt.Font;
//import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;


@SuppressWarnings("serial") // ask about this
public class Board extends JPanel
	{
		//Attribute
		int size;
		int NumberofTerritories;
		Player Players;
		int ValueofTerritories; //make this an arraylist
		Color Base = Color.WHITE;
		ArrayList<Territories> TerrList = new ArrayList<Territories>();
		Graphics g;
		int TempSelectnum = -1;
		int TempTargetnum = -1;
		public Move GameMoves;
		//Constructor
		Board(int NumberofTerritories){
			Baseboard();
			GameMoves = new Move();
		}
		//Functions
		public  void render(Graphics g,ArrayList<Territories> TerrList, Game game) {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0,0, 1200, 800);
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 150, 50);
			g.setColor(Color.BLACK);
			Font myFont = new Font("Serif", Font.ITALIC | Font.BOLD, 40);
		    Font newFont = myFont.deriveFont(50F);
		    
		    String t= "Turn: ";
		    Font OrignalFont = g.getFont();
		    g.setFont(myFont);
		    g.drawString(t+game.Turn, 10, 30);
		    g.setFont(OrignalFont);

			for (int i = 0; i < TerrList.size();i++)
			{
				TerrList.get(i).render(g);	
			}
		}
		public ArrayList<Territories> getTerrList(){
			return TerrList;
		}
		//Creates BaseBoard
		public void Baseboard()
		{

			TerrList.add(new Territories(Base,TerrList,55, 345, 300, 100,1));
			TerrList.add(new Territories(Base,TerrList,305, 50, 100, 300,2));
			TerrList.add(new Territories(Base,TerrList,350, 340, 200, 150,3));
			TerrList.add(new Territories(Base,TerrList,400, 250, 200, 100,4));
			TerrList.add(new Territories(Base,TerrList,545, 340, 150, 150,5));
			TerrList.add(new Territories(Base,TerrList,450, 485, 150, 150,6));
			TerrList.add(new Territories(Base,TerrList,550, 250, 200, 100,7));
			TerrList.add(new Territories(Base,TerrList,595, 485, 100, 200,8));
			TerrList.add(new Territories(Base,TerrList,730, 250, 100, 200,9));
			TerrList.add(new Territories(Base,TerrList,690, 485, 250, 100,10));
			
		}
		public void setSelectedTerritory(int num) {
			for (Territories t : TerrList)
				if (t.number == num)
					t.setSelected(true);
				else
					t.setSelected(false);
		}
		public void setTargetTerritory(int num) {
			for (Territories t1 : TerrList)
				if (t1.number == num)
					t1.setTarget(true);
				else
					t1.setTarget(false);
		}
		public int Attackernumber(ArrayList<Territories> Test){
			int num = -1;
			for (Territories t : Test)
			{
				if (t.CheckifSelected())
					num = t.number;
			}
			return num;
		}
		public int Attackerindex(ArrayList<Territories> Test){
			int num = -1;
			for (int i = 1; i < Test.size();i++)
			{
				if (Test.get(i).CheckifSelected())
					num = i;
			}
			return num;
		}
		public int Defendernumber(ArrayList<Territories> Test){
			int num = -1;
			for (Territories t : Test)
			{
				if (t.CheckifTarget())
					num = t.number;
			}
			return num;
		}
		public int Defenderindex(ArrayList<Territories> Test){
			int num = -1;
			for (int i = 1; i < Test.size();i++)
			{
				if (Test.get(i).CheckifTarget())
					num = i;
			}
			return num;
			}
		public boolean ValidMove(int Select,int Target)
		{
			//Check which is selected with attackernum
			//check using defender num if target is within the possible moves of the selected territory
			boolean Check = false;
			GameMoves.MyLocation = GameMoves.AllMoves.get(Select);
			for (int i = 0; i < GameMoves.MyLocation.Moves.size();i++)
				if (GameMoves.MyLocation.Moves.get(i) == GameMoves.AllMoves.get(Target))
				{
					Check = true;
				}
			return Check;
			}
		public void MoveUnits(int select, int target)
		{
			int temp = TerrList.get(select).NumberofUnits - 1;
			int temp2 = TerrList.get(target).NumberofUnits - 1;
			TerrList.get(select).NumberofUnits -= temp;
			TerrList.get(target).NumberofUnits += temp2;
		}
}
	
	
	

