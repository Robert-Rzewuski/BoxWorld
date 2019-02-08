package demo;

import java.util.ArrayList;

public class Move
{
	public PossibleMoves MyLocation;
	ArrayList<PossibleMoves> AllMoves = new ArrayList<PossibleMoves>();
	Move()
	{
		//creating possible move objects with 1 next pos.
		PossibleMoves p1 = new PossibleMoves("1",null);
		PossibleMoves p2 = new PossibleMoves("2",p1);
		PossibleMoves p3 = new PossibleMoves("3",p1);
		PossibleMoves p4 = new PossibleMoves("4",p2);
		PossibleMoves p5 = new PossibleMoves("5",p3);
		PossibleMoves p6 = new PossibleMoves("6",p3);
		PossibleMoves p7 = new PossibleMoves("7",p4);
		PossibleMoves p8 = new PossibleMoves("8",p5);
		PossibleMoves p9 = new PossibleMoves("9",p7);
		PossibleMoves p10 = new PossibleMoves("10",p8);
		//add the rest of the next possible moves
		//1st Territory
		p1.Moves.add(p2);
		p1.Moves.add(p3);
		//2nd Territory
		p2.Moves.add(p3);
		p2.Moves.add(p4);
		//3rd Territory
		p3.Moves.add(p4);
		p3.Moves.add(p5);
		p3.Moves.add(p6);
		//4th Territory
		p4.Moves.add(p3);
		p4.Moves.add(p7);
		//5th Territory
		p5.Moves.add(p6);
		p5.Moves.add(p7);
		p5.Moves.add(p8);
		//6th Territory
		p6.Moves.add(p5);
		p6.Moves.add(p8);
		//7th Territory
		p7.Moves.add(p5);
		p7.Moves.add(p9);
		//8th Territory
		p8.Moves.add(p6);
		p8.Moves.add(p10);
		//9th Territory
		p9.Moves.add(p10);
		//10th Territory
		p10.Moves.add(p9);
		
		AllMoves.add(p1);
		AllMoves.add(p2);
		AllMoves.add(p3);
		AllMoves.add(p4);
		AllMoves.add(p5);
		AllMoves.add(p6);
		AllMoves.add(p7);
		AllMoves.add(p8);
		AllMoves.add(p9);
		AllMoves.add(p10);
		
			
	}
}