package fr.ex.demineur;
import java.util.*;

public class Main {
	
	// recalcul des case vide a cause de la découverte en cascade
	public static int recalculateEmptyCells(char[][] visibleGrid, char[][] grid, char symboleMine) {
		// We initialize a counter
	    int count = 0;
	    
	    // We check if the there is a '-' or a 'X' symbol which represent a mine 
	    for (int i = 0; i < visibleGrid.length; i++) {
	        for (int j = 0; j < visibleGrid[0].length; j++) {
	            if (visibleGrid[i][j] == '-' && grid[i][j] != symboleMine) {
	                count++;
	            }
	        }
	    }
	    return count;
	}
	
	// This method check if there's neighboring mines to the selected case (with minesCounting)
	// and reveal each cells around if there's none (serial discovery)
	public static void serialDiscovery(char[][] visibleGrid, char[][]hidenGrid, int row, int column, char mineSymbol) {
		int rows = visibleGrid.length;
		int columns = visibleGrid[0].length;
		
		// We first verify if row and column are on a valid range 
		if ( row < 0 || row >= rows || column < 0 || column >= columns) return;
		
		// We check if the cell was already found
		if (visibleGrid[row][column] != '-') return;
		
		// We check if there is a mine and just return if that's the case
		if (visibleGrid[row][column] == mineSymbol) return;
		
		// Then we initialize a variable to count the neighboring mines
		int closeMines = minesCounting(hidenGrid, row, column, mineSymbol);
		
		
		visibleGrid[row][column] = (closeMines == 0) ? ' ' : (char) ('0' + closeMines);
		
		// If there is no mines around then we reveal neighboring cells (and do it recursively)
		if (closeMines ==0) {
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (i != 0 || j != 0) {
						serialDiscovery(visibleGrid, hidenGrid, row + i, column + j, mineSymbol);
					}
				}
			}
		}
		
	}
	
	// Counting neighboring mines
	public static int minesCounting(char[][] grid, int row, int column, char mineSymbol) {
		int counter = 0;
		int rows = grid.length;
		int columns = grid[0].length;
		
		
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				
				// Ignore the self cell
				if (i == 0 && j == 0) continue;
				
				int numI =  row + i;
				int numJ = column +j;
				
				// grid limit verification
				if (numI >= 0 && numI < rows && numJ >=0 && numJ < columns) {
					if (grid[numI][numJ] == mineSymbol) {
						counter++;
					}
				}
				
			}
		}
		return counter;
	}
	
	// This method adding mines to the grid randomly
	public static void addMines(char[][] grid, int minesNumber, char mineSymbol ) {
		int rows = grid.length;
		int columns = grid[0].length;
		Random random = new Random();
		
		// A variable to store already placed mines
		int placedMines = 0;
		
		while(placedMines < minesNumber) {
			int i = random.nextInt(rows);
			int j = random.nextInt(columns);
			
			
			if (grid[i][j] != mineSymbol) {
				grid[i][j] = mineSymbol;
				placedMines++;
			}
		}
		
	}
	
	// This method will create our grid
	public static char[][] gameGrid(int rows, int columns, char initialSymbol){
		char[][] grid = new char[rows][columns];
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				grid[i][j] = initialSymbol;
			}
		}
		return grid;
	}
	
	// This method display the grid
	public static void displayGrid(char[][] grid) {
	    int rows = grid.length;
	    int columns = grid[0].length;

	    // numbers display of columns
	    System.out.print("    "); 
	    for (int j = 0; j < columns; j++) {
	        System.out.printf("%3d ", j + 1);
	    }
	    System.out.println();

	    // The upper line
	    System.out.print("    ");
	    for (int j = 0; j < columns; j++) {
	        System.out.print("----");
	    }
	    System.out.println("-");

	    // Number for rows
	    for (int i = 0; i < rows; i++) {
	        System.out.printf("%2d |", i + 1);
	        for (int j = 0; j < columns; j++) {
	            System.out.printf(" %c |", grid[i][j]);
	        }
	        System.out.println();

	        // The separators of our grid
	        System.out.print("    ");
	        for (int j = 0; j < columns; j++) {
	            System.out.print("----");
	        }
	        System.out.println("-");
	    }
	}
	
	// This method is used to validate user inputs
	public static int inputValidation(Scanner scan, String message, int min, int max) {
	    int valeur = -1;
	    while (true) {
	        System.out.print(message);
	        if (scan.hasNextInt()) {
	            valeur = scan.nextInt();
	            if (valeur >= min && valeur <= max) {
	                break;
	            } else {
	                System.out.println("\nValeur hors limites (" + min + " à " + max + ")!");
	            }
	        } else {
	            System.out.println("\nEntrée invalide! Veuillez saisir un nombre: ");
	            scan.next();
	        }
	    }
	    return valeur;
	}

	public static void main(String[] args) {
		
		// Grid dimensions
		int rows = 6;
		int columns = 12;
		int minesNumber = 9;
		char mineSymbol = 'X';
		
		// We create 2 grid, one for user display and one to check the mines
		char[][] hidenGrid = gameGrid(rows, columns, '-');
		char[][] grid = gameGrid(rows, columns, '-');
		
		// We add mines to our grid
		addMines(grid, minesNumber, mineSymbol);
		Scanner scan = new Scanner(System.in);
		
		// We create this variable to check and keep recalculating (for the serial discovery)
		// the number of empty cells
		int emptyCellsLeft = recalculateEmptyCells(hidenGrid, grid , mineSymbol);
		
		// Debug 
		//System.out.println("================DEBUG================");
		//displayGrid(grid);
		//System.out.println("================DEBUG================\n");
		
		
		while (emptyCellsLeft > 0) {
			displayGrid(hidenGrid);
			
			// User inputs
            int row = inputValidation(scan,"Choisissez une ligne (1 à " + (rows) + ") : ", 1, rows);
            int column = inputValidation(scan,"Choisissez une colonne (1 à " + (columns) + ") : ", 1, columns );
            
            // Check if coordinates are valid
            if (row < 1 || row > rows || column < 1 || column > columns ) {
            	System.out.println("\nErreur: Coordonnées invalides!");
            	continue;
            }
            
            //  Modifying the index to be on par with the actual logic of index 
            int rowIndex = row -1;
            int columnIndex = column -1;
            
            // Checking if the cells was already revealed
            if (hidenGrid[rowIndex][columnIndex] != '-') {
            	System.out.println("\nAttention: Case déja révélée!\n");
            	continue;
            }
            
            // Display loose condition
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
            	serialDiscovery(hidenGrid, grid , rowIndex, columnIndex, mineSymbol);
            }
            
		}
		// Victory's display
		scan.close();
		System.out.println("Félicitations! Vous avez gagné (rien du tout)!");
        displayGrid(grid);
        
	}

}
