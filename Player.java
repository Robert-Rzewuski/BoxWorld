package demo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Player {

	//Attributes
	private int StartingNumberofTroops;
	int NumberofTerritoriesOwned;
	Color CountryColor;
	ArrayList<Territories> TerrList;
	ArrayList<Territories> TerrOwned = new ArrayList<Territories>();
	ArrayList<Territories> TerrNotOwned = new ArrayList<Territories>();
	public Game game;
	public Board board;
	int TurnID;

	//Constructor
	public Player(Color color,ArrayList<Territories> TerrList,Board b,int turn)
	{
		CountryColor = color;
		this.TerrList = TerrList;
		this.board = b;
		this.TurnID = turn;
		Territories NullTerr = new Territories(CountryColor, null, 0, 0, 0, 0, -1);
		TerrOwned.add(NullTerr);
		
	}
	//Methods
	public void tick() { 
	}
	//Only render is if i decide to add little unit symbols. 
	//public void render(Graphics g){
	//}
	public void attack()
	{
		game.Attack(true);
	}
	public void move()
	{
		
	}
}
