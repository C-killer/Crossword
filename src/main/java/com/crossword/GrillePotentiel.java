package com.crossword;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestion des potentiels de mots pour chaque emplacement de la grille.
 */
public class GrillePotentiel {
    private final GrillePlaces grillePlaces;  // La grille actuelle (partiellement remplie).
    private final Dictionnaire dict;  // Le dictionnaire français complet
    private final List<Dictionnaire> motsPot;  // Le domaine potentiel pour chaque emplacement

    /**
     * Constructeur : initialise les potentiels pour chaque emplacement.
     *
     * @param grille      La grille contenant les emplacements
     * @param dicoComplet Le dictionnaire complet
     */
    public GrillePotentiel(GrillePlaces grille, Dictionnaire dicoComplet) {
        this.grillePlaces = grille;
        this.dict = dicoComplet.copy();
        this.motsPot = new ArrayList<>();

        for (Emplacement e : grillePlaces.getPlaces()) {
            int len = e.size(); // Longueur de l'emplacement
            Dictionnaire d = dict.copy();
            d.filtreLongueur(len); // Filtrage des mots par longueur
            // 根据当前网格中的已填字母进一步过滤
            for (int i = 0; i < len; i++) {
                Case c = e.getCase(i);
                if (c.getChar() != ' ') {  // 如果格子已经有字母
                    d.filtreParLettre(c.getChar(), i);
                }
            }
            motsPot.add(d);
        }

    }

    /**
     * Accède aux potentiels de mots pour chaque emplacement.
     *
     * @return Une liste des dictionnaires potentiels.
     */
    public List<Dictionnaire> getMotsPot() {
        return motsPot;
    }

    // 从motsPot中index为i的domain中移除mots
    public void removeMot(int i, String mot) {
        Dictionnaire dict = motsPot.get(i);
        dict.getMots().remove(mot);
    }

    public GrillePlaces getGrillePlaces() {
        return grillePlaces;
    }

    public Dictionnaire getDict() {
        return dict;
    }

    /**
     * Vérifie si la grille est dans un état "mort" (sans solution possible).
     *
     * @return true si au moins un emplacement n'a pas de mots possibles.
     */
    public boolean isDead() {
        for (Dictionnaire d : motsPot) {
            if (d.size() == 0) { // Aucun mot possible pour cet emplacement
                return true;
            }
        }
        return false;
    }

    /**
     * Fixe un mot dans un emplacement donné et met à jour les potentiels.
     *
     * @param m      L'indice de l'emplacement dans la grille
     * @param soluce Le mot à fixer dans cet emplacement
     * @return Une nouvelle instance de GrillePotentiel avec les mises à jour.
     */
    public GrillePotentiel fixer(int m, String soluce) {
        if (m < 0 || m >= motsPot.size()) {
            throw new IllegalArgumentException("Indice m hors des limites.");
        }
        // 创建新的网格，固定单词
        GrillePlaces newGrillePlaces = grillePlaces.fixer(m, soluce);

        // 更新候选字典
        List<Dictionnaire> updatedMotsPot = new ArrayList<>();
        for (int i = 0; i < motsPot.size(); i++) {
            if (i == m) {
                // 固定位置的候选字典只包含固定的单词
                Dictionnaire fixedDict = new Dictionnaire();
                fixedDict.add(soluce);
                updatedMotsPot.add(fixedDict);
            } else {
                // 动态更新其他位置
                Dictionnaire updatedDict = motsPot.get(i).copy();
                Emplacement emplacement = grillePlaces.getPlaces().get(i);
                for (int j = 0; j < emplacement.size(); j++) {
                    Case c = emplacement.getCase(j);
                    if (c.getChar() != ' ') {  // 如果格子有固定字母
                        updatedDict.filtreParLettre(c.getChar(), j);
                    }
                }
                updatedMotsPot.add(updatedDict);
            }
        }
        // 返回新的 GrillePotentiel 对象
        return new GrillePotentiel(newGrillePlaces, dict);
    }

}
