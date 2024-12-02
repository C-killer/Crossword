package com.crossword;

public class Case {
    private final int lig;
    private final int col;
    private char c;

    /**
     * Constructs a new Case with specified row, column, and character.
     *
     * @param lig the row index of the case
     * @param col the column index of the case
     * @param c the character contained in the case
     */
    public Case(int lig, int col, char c) {
        this.lig = lig;
        this.col = col;
        this.c = c;
    }

    /**
     * Retrieves the row index (lig) of the current Case.
     *
     * @return the row index of the current Case.
     */
    public int getLig() { return lig; }
    public int getCol() { return col; }

    public char getChar() { return c; }
    public void setChar(char c) { this.c = c;}

    public boolean isVide() { return c == ' ';}
    public boolean isPleine() { return c == '*';}

    /**
     * Creates a new Case object that is a copy of the current instance.
     *
     * @return a new Case object with the same lig, col, and c values as the current instance.
     */
    public Case copy() {
        return new Case(lig, col, c);
    }
}
