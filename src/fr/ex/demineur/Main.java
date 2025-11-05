package fr.ex.demineur;

public class Main {
	
	// method de créztion de la grille
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
		
		// dimensions grille
		int rows = 6;
		int columns = 12;
		
		// on créer la grille
		char[][] grid = gameGrid(rows, columns, '*');
		
		//affichage grille
        displayGrid(grid);

	}

}
