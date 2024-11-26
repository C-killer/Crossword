package com.crossword;


public class Grille {
    private final Case[][] grille;

    public Grille(int hauteur, int largeur) {
        grille = new Case[hauteur][largeur];
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                grille[i][j] = new Case(i, j,' ');
            }
        }
    }

    public Case getCase(int lig, int col) {
        return grille[lig][col];
    }

    public int nbLig() { return grille.length; }
    public int nbCol() { return grille[0].length; }

    /**
     * Creates a deep copy of the current Grille object.
     *
     * @return A new Grille object that is a deep copy of the current instance.
     */
    public Grille copy() {
        Grille copy = new Grille(nbLig(), nbCol());
        for (int i = 0; i < nbLig(); i++) {
            for (int j = 0; j < nbCol(); j++) {
                copy.grille[i][j] = grille[i][j].copy();
            }
        }
        return copy;
    }

    /**
     * Returns a string representation of the grid, allowing it to be displayed
     * in a readable format on the console.
     *
     * @return A serialized string representation of the grid.
     */
    @Override
    public String toString() {
        return GrilleLoader.serialize(this, false);
    }
}
