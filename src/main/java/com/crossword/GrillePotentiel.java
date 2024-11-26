package com.crossword;

import java.util.ArrayList;
import java.util.List;

public class GrillePotentiel {
    private GrillePlaces grillePlaces;  //  la grille actuelle (partiellement remplie)
    private Dictionnaire dict;  // le dictionnaire français complet
    private List<Dictionnaire> motsPot;  // le domaine de chaque emplacement de la grille

    public GrillePotentiel(GrillePlaces grille, Dictionnaire dicoComplet) {
        grillePlaces = grille;
        dict = dicoComplet.copy();
        motsPot = new ArrayList<>();
        for (Emplacement e : grillePlaces.getPlaces()) {
            int len = e.size();     // 该位置上的长度
            Dictionnaire d = dict.copy();
            d.filtreLongueur(len);  // 根据长度筛选
            motsPot.add(d);     // 将结果加入motPots
        }
    }

    public List<Dictionnaire> getMotsPot() {
        return motsPot;
    }

    public boolean isDead() {
        return motsPot.contains(null);
    }

}
