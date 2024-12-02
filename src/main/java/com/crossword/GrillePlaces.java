package com.crossword;

import java.util.ArrayList;
import java.util.List;

public class GrillePlaces {
    private final Grille grille;
    private final List<Emplacement> places;
    private final int nbHorizontal;

    public GrillePlaces(Grille grille) {
        this.grille = grille.copy();
        int lig = grille.nbLig();
        int col = grille.nbCol();
        places = new ArrayList<>();
        for (int i = 0; i < lig; i++) {
            cherchePlaces(getLig(i));
        }
        nbHorizontal = places.size();
        for (int i = 0; i < col; i++) {
            cherchePlaces(getCol(i));
        }
    }


    public List<Emplacement> getPlaces() {
        return places;
    }

    public int getNbHorizontal() {
        return nbHorizontal;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Horizontal : ").append(nbHorizontal).append("\n");
        int cpt = 0;
        for (Emplacement e : places) {
            if (cpt == nbHorizontal) {
                str.append("===============\n");
                str.append("Vertical : ").append(places.size() - nbHorizontal).append("\n");
            }
            str.append(e.toString());
            str.append("\n");
            cpt++;
        }
        return str.toString();
    }

    private List<Case> getLig(int lig) {
        List<Case> ligs = new ArrayList<>();
        int col = grille.nbCol();
        for (int i = 0; i < col; i++) {
            ligs.add(grille.getCase(lig, i));
        }
        return ligs;
    }

    private List<Case> getCol(int col) {
        List<Case> cols = new ArrayList<>();
        int lig = grille.nbLig();
        for (int i = 0; i < lig; i++) {
            cols.add(grille.getCase(i, col));
        }
        return cols;
    }


    private void cherchePlaces(List<Case> cases) {
        Emplacement e = null;
        for (Case aCase : cases) {
            if (!aCase.isPleine()) {
                if (e == null) {
                    e = new Emplacement();
                }
                e.add(aCase);
            } else {
                if (e != null && e.size() > 1) {
                    places.add(e);
                }
                e = null; // 直接清空
            }
        }
        if (e != null && e.size() > 1) {
            places.add(e);
        }
    }

    /**
     * @param index    l'index
     * @param solution la lettre
     * @return une nouvelle grille où les cases constituant l’emplacement de mot d’indice index
     * qui ont pour contenu les lettres de solution
     */
    public GrillePlaces fixer(int index, String solution) {
        int placesSize = places.size();
        if (index < 0 || index >= placesSize) {
            throw new IllegalArgumentException("index must be between 0 and " + (placesSize - 1));
        }
        Grille newGrille = grille.copy();
        Emplacement emplacement = getPlaces().get(index);
        for (int i = 0; i < solution.length(); i++) {
            Case c = emplacement.getCase(i);
            newGrille.getCase(c.getLig(), c.getCol()).setChar(solution.charAt(i));
        }
        return new GrillePlaces(newGrille);
    }
}