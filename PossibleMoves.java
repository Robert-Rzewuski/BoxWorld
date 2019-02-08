package demo;

import java.util.ArrayList;

public class PossibleMoves
{
	public ArrayList<PossibleMoves> Moves = new ArrayList<PossibleMoves>();
	public String name;	
	public PossibleMoves(String n, PossibleMoves next)
	{
		name = n;
		Moves.add(next);
	}
	public String toString()
	{
		return name;
	}
}
