package demo;

import java.awt.Color;
import java.util.ArrayList;

public class CPU extends Player
{
	int BiggestOwnedArmy = -1; 
	int BiggestEnemyArmy = -1;
	public CPU(Color color, ArrayList<Territories> TerrList, Board b,int turn) {
		super(color, TerrList,b,turn);	
	}
	public void RUN()
	{	
		int NumofMoves = 1;
		int EnemeyUnits = -1;
		int Units = -1;
		int Target = 0;
		int Selected = 0;
		while (NumofMoves > 0)
		{		
			FindBiggestOwnedArmy();
			Selected = BiggestOwnedArmy;
			FindBiggestEnemyArmy();
			Target = BiggestEnemyArmy;
			for(int i = 0; i < this.TerrOwned.size();i++)
			{
				Units += this.TerrOwned.get(i).NumberofUnits;
			}
			for(int i = 0; i < this.TerrNotOwned.size();i++)
			{
				EnemeyUnits += this.TerrNotOwned.get(i).NumberofUnits;
			}
			
		NumofMoves--;
		}
	}		
	
	public void CPUAttack()
	{
		for (Territories t: TerrList)
			if (this.TerrList.get(board.Attackerindex(TerrList)).NumberofUnits < t.NumberofUnits   )
				continue;
			else
				game.Attack(true);
	}
	public void FindBiggestOwnedArmy ()
	{
		int BA = 0;
		int temp = 0;
		int temp2 = 0;
		for (int i = 0; i < this.TerrOwned.size();i++)
		{
			temp = this.TerrOwned.get(i).NumberofUnits;
			if (i == this.TerrOwned.size())
				{
					temp2 = this.TerrOwned.get(0).NumberofUnits;
				}
			else
				{
					temp2 = this.TerrOwned.get(i+1).NumberofUnits;
				}
			if (temp > temp2)
			{
				BA = temp;
			}
			else if (temp2 > temp)
			{
				BA = temp2;
			}
			else
			{
				continue;
			}
		}
		BiggestOwnedArmy = BA;
	}
	public void FindBiggestEnemyArmy ()
	{
		int BA = 0;
		int temp = 0;
		int temp2 = 0;
		for (int i = 0; i < this.TerrList.size();i++)
		{
			for (int j = 0; j < this.TerrOwned.size();i++)
			{
				temp = this.TerrList.get(i).NumberofUnits;
				if (this.TerrList.get(i) != this.TerrList.get(j))
				{
					if (i == this.TerrList.size())
						{
							temp2 = this.TerrList.get(0).NumberofUnits;
						}
					else
						{
							temp2 = this.TerrList.get(i+1).NumberofUnits;
						}
					if (temp > temp2)
					{
						BA = temp;
					}
					else if (temp2 > temp)
					{
						BA = temp2;
					}
					else
					{
						continue;
					}
				}
				else 
				{
					continue;
				}
			}
		}
		BiggestEnemyArmy = BA;
	}
	public void Turn()
	{
		RUN();
	}
	
}
