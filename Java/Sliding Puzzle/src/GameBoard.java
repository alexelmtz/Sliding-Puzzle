//	Alejandro Elizondo
//	alexelmtz@outlook.com
//	Linkedin: linkedin.com/in/alejandro-elizondo
//	Github: github.com/alexelmtz/Sliding-Puzzle

import java.util.*;

public class GameBoard {
	private int[][] iarrGameBoard;
	private int iSize;
	private int iRowCero;
	private int iColCero;

	GameBoard(int iSize) {
		iarrGameBoard = new int[iSize][iSize];
		this.iSize = iSize;
		Generate();
	}

	// Switches blank space with user's input. Return false if the switch failed.
	public boolean Switch(int iNum) {
		int iRow = 0, iCol = 0;
		// Finds the position of the number to be switched
		for (int i = 0; i < iSize; i++)
			for (int j = 0; j < iSize; j++)
				if (iarrGameBoard[i][j] == iNum)
				{
					iRow = i;
					iCol = j;
				}

		// If the distance between the numbers is equal to 1, then that means that they are next to each other
		double dDistance = Math.sqrt(Math.pow(iRow - iRowCero, 2) + Math.pow(iCol - iColCero, 2));
		// Checks if the number is next to the blank space
		if (dDistance == 1)
		{
			iarrGameBoard[iRow][iCol] = 0;
			iarrGameBoard[iRowCero][iColCero] = iNum;
			iColCero = iCol;
			iRowCero = iRow;
			return true;
		}
		return false;
	}

	// Generates a puzzle with a random order
	public void Generate() {		
		Random rand = new Random();
		int iRand;
		List<Integer> lintUsed = new ArrayList<>();		// List of numbers that have been added to the game board

		for (int i = 0; i < iSize; i++)
			for (int j = 0; j < iSize; j++)
			{
				do {
					iRand = rand.nextInt(iSize*iSize - 0) + 0;
				} while (lintUsed.contains(iRand));		// The number is already in the game board
				
				if (iRand == 0)
				{
					iRowCero = i;
					iColCero = j;
				}
				
				iarrGameBoard[i][j] = iRand;
				lintUsed.add(iRand);
			}
	}

	// Displays the game board
	public void Display() {
		String sBoarder = "";
		for (int i = 0; i < iSize; i++)
			sBoarder += "•----";
		sBoarder += "•";

		System.out.println(sBoarder);
		for (int i = 0; i < iSize; i++)
		{
			System.out.print("| ");
			for (int j = 0; j < iSize; j++)
				if (iarrGameBoard[i][j] == 0)
					System.out.print("   | ");
				else if (iarrGameBoard[i][j] < 10)
					System.out.print(" " + iarrGameBoard[i][j] + " | ");
				else
					System.out.print(iarrGameBoard[i][j] + " | ");

			System.out.println("\n" + sBoarder);
		}
	}

}