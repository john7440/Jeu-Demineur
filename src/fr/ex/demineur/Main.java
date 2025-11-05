package fr.ex.demineur;
import java.util.*;

public class Main {
	
	// system de comptage des mines alentour
	public static int minesCounting(char[][] grid, int row, int column, char mineSymbol) {
		int counter = 0;
		int rows = grid.length;
		int columns = grid[0].length;
		
		// on parcours toutes les directions
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				
				// ignore la case du centre
				if (i == 0 && j == 0) continue;
				
				int numI =  row + i;
				int numJ = column +j;
				
				// vérif de la limite de la grile
				if (numI >= 0 && numI < rows && numJ >=0 && numJ < columns) {
					if (grid[numI][numJ] == mineSymbol) {
						counter++;
					}
				}
				
			}
		}
		return counter;
	}
	
	// ajout des mines
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
	
	//affichage grille
	public static void displayGrid(char[][] grid) {
	    int rows = grid.length;
	    int columns = grid[0].length;

	    // numéro colonnes
	    System.out.print("    "); 
	    for (int j = 0; j < columns; j++) {
	        System.out.printf("%3d ", j + 1);
	    }
	    System.out.println();

	    //la ligne supérieure
	    System.out.print("    ");
	    for (int j = 0; j < columns; j++) {
	        System.out.print("----");
	    }
	    System.out.println("-");

	    // lignes avec les numéros
	    for (int i = 0; i < rows; i++) {
	        System.out.printf("%2d |", i + 1);
	        for (int j = 0; j < columns; j++) {
	            System.out.printf(" %c |", grid[i][j]);
	        }
	        System.out.println();

	        // séparateurs
	        System.out.print("    ");
	        for (int j = 0; j < columns; j++) {
	            System.out.print("----");
	        }
	        System.out.println("-");
	    }
	}


	public static void main(String[] args) {
		
		// dimensions grille +nb mines
		int rows = 6;
		int columns = 12;
		int minesNumber = 9;
		char mineSymbol = 'X';
		
		// on créer 2 grille (une caché)
		char[][] hidenGrid = gameGrid(rows, columns, '-');
		char[][] grid = gameGrid(rows, columns, '-');
		// ajout des mines
		addMines(grid, minesNumber, mineSymbol);
		Scanner scan = new Scanner(System.in);
		// on créer un compteur pour le nombre de case vide restantes
		int emptyCellsLeft = rows * columns - minesNumber;
		
		// Debug 
		System.out.println("================DEBUG================");
		displayGrid(grid);
		System.out.println("================DEBUG================\n");
		
		
		while (emptyCellsLeft > 0) {
			displayGrid(hidenGrid);
			// inputs utilisateur
			System.out.print("Choisissez une ligne (1 à " + (rows) + ") : ");
            int row = scan.nextInt();
            System.out.print("Choisissez une colonne (1 à " + (columns) + ") : ");
            int column = scan.nextInt();
            
            if (row < 1 || row > rows || column < 1 || column > columns ) {
            	System.out.println("Coordonnées invalides!");
            	continue;
            }
            
            // modif pour prendre en compte que l'on part de 1 et plus de 0
            int rowIndex = row -1;
            int columnIndex = column -1;
            
            if (hidenGrid[rowIndex][columnIndex] != '-') {
            	System.out.println("Case déja révélée!");
            	continue;
            }
            
            if (grid[rowIndex][columnIndex] == mineSymbol) {
            	System.out.println("======================================================");
            	System.out.println("[Mode Michael Bay]: Vous avez cliqué sur une mine!!!!!");
            	System.out.println("======================================================\n");
            	displayGrid(grid);
            	System.out.println("\n======================================================");
            	System.out.println("       Partie Terminé! Game Over! Tu as perdu!");
            	System.out.println("======================================================");
            	return;
            } else {
            	int closeMines = minesCounting(grid, rowIndex, columnIndex, mineSymbol);
            	hidenGrid[rowIndex][columnIndex] = (closeMines == 0) ? ' ' : (char) ('0' + closeMines);
            	emptyCellsLeft--;
            	System.out.println("\n========================================================");
            	System.out.println("Il y as très exactement " + closeMines + " mines autour!");
            	System.out.println("========================================================");
            }
            
		}
		//victoire
		System.out.println("Félicitations! Vous avez gagné (rien du tout)!");
        displayGrid(grid);
        scan.close();

	}

}
