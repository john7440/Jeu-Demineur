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

	public static void main(String[] args) {
		
		// dimensions grille
		int rows = 6;
		int columns = 12;
		
		// on créer la grille
		char[][] grid = gameGrid(rows, columns, '*');
		
		//affichage grille
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + "\t");
            }
            System.out.println();
        }

	}

}
