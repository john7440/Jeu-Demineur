package fr.ex.demineur;
import java.util.*;

public class Main {
	
	public static void addMines(char[][] grid, int minesNumber, char mineSymbol ) {
		int rows = grid.length;
		int columns = grid[0].length;
		Random random = new Random();
		// verif du nombre de mines placées
		int placedMines = 0;
		
		while(placedMines < minesNumber) {
			int i = random.nextInt(rows);
			int j = random.nextInt(columns);
			
			// vérification de présence
			if (grid[i][j] != mineSymbol) {
				grid[i][j] = mineSymbol;
				placedMines++;
			}
		}
		
	}
	
	// method de création de la grille
	public static char[][] gameGrid(int rows, int columns, char initialSymbol){
		char[][] grid = new char[rows][columns];
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				grid[i][j] = initialSymbol;
			}
		}
		return grid;
	}
	
	public static void displayGrid(char[][] grid) {
		int rows = grid.length;
		int columns = grid[0].length;
		
		// les lignes (sans la dérnière)
		for (int i = 0; i < rows; i++) {
			System.out.print("+");
			for (int j = 0; j < columns; j++) {
				System.out.print("---+");
			}
			System.out.println();
			
			//nos colonnes
			System.out.print("|");
	        for (int j = 0; j < columns; j++) {
	            System.out.print(" " + grid[i][j] + " |");
	        }
	        System.out.println();	
		}
		// la derniere ligne
		System.out.print("+");
		for (int j = 0; j < columns; j++ ) {
			System.out.print("---+");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		
		// dimensions grille +nb mines
		int rows = 6;
		int columns = 12;
		int minesNumber = 9;
		
		// on créer la grille
		char[][] grid = gameGrid(rows, columns, '-');
		
		// ajout des mines
		addMines(grid, minesNumber, '*');
		
		//affichage grille
        displayGrid(grid);

	}

}
