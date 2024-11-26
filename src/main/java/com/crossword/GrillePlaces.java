package com.crossword;

import java.util.ArrayList;
import java.util.List;

public class GrillePlaces {
    private Grille grille;
    private List<Emplacement> places;
    private int NbHorizontal;

    public GrillePlaces (Grille grille) {
        this.grille = grille.copy();
        int lig = grille.nbLig();
        int col = grille.nbCol();
        places = new ArrayList<>();
        for (int i = 0; i < lig; i++) {
            cherchePlaces(getLig(i));
        }
        NbHorizontal = places.size();
        for (int i = 0; i < col; i++) {
            cherchePlaces(getCol(i));
        }
    }

    public List<Emplacement> getPlaces() { return places; }
    public int getNbHorizontal() { return NbHorizontal; }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Horizontal : ").append(NbHorizontal).append("\n");
        int cpt = 0;
        for (Emplacement e : places) {
            if (cpt == NbHorizontal) {
                str.append("===============\n");
                str.append("horizon : ").append(places.size() - NbHorizontal).append("\n");
            }
            str.append(e.toString());
            str.append("\n");
            cpt++;
        }
        return str.toString();
    }

    private List<Case> getLig (int lig) {
        List<Case> ligs = new ArrayList<>();
        int col = grille.nbCol();
        for (int i = 0; i < col ; i++) {
            ligs.add(grille.getCase(lig, i));
        }
        return ligs;
    }

    private List<Case> getCol (int col) {
        List<Case> cols = new ArrayList<>();
        int lig = grille.nbLig();
        for (int i = 0; i < lig ; i++) {
            cols.add(grille.getCase(i, col));
        }
        return cols;
    }


    private void cherchePlaces(List<Case> cases) {
        Emplacement e = new Emplacement();
        for (Case aCase : cases) {
            if (!aCase.isPleine()) {
                e.add(aCase);
            } else {
                if (e.size() > 1) {
                    places.add(e);
                }
                e = new Emplacement(); // 重置e为新实例
            }
        }
        if (e.size() > 1) {
            places.add(e);
        }
    }
}