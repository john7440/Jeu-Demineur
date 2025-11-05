package fr.ex.demineur;

public class Main {

	
	
	public static void main(String[] args) {
		char[][] grille = new char[6][12];

		for (int i = 0; i < grille.length; i++) {
		    for (int j = 0; j < grille[i].length; j++) {
		        grille[i][j] = '_';
		    }
		}
		
		// Affichage de la grille
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                System.out.print(grille[i][j] + "\t");
            }
            System.out.println();
        }

	}

}
