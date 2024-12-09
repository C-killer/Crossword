package com.crossword;

import java.util.ArrayList;
import java.util.List;

public class GrilleContrainte extends GrillePotentiel {
    private final List<IContrainte> contraintes;

    // 原始构造器，生成约束
    public GrilleContrainte(GrillePlaces grille, Dictionnaire dicoComplet) {
        super(grille, dicoComplet);
        this.contraintes = initializeContraintes(grille);
        propage();
    }

    // 新的构造器，复用已有的约束
    public GrilleContrainte(GrillePlaces grille, Dictionnaire dicoComplet, List<IContrainte> contraintesExistantes) {
        super(grille, dicoComplet);
        this.contraintes = contraintesExistantes;
        propage();
    }

    // 初始化约束的逻辑
    private List<IContrainte> initializeContraintes(GrillePlaces grille) {
        List<IContrainte> contraintes = new ArrayList<>();
        List<Emplacement> places = grille.getPlaces();
        int nbHorizontal = grille.getNbHorizontal();

        for (int i = 0; i < nbHorizontal; i++) {
            Emplacement horizontal = places.get(i); // 水平位置
            for (int j = nbHorizontal; j < places.size(); j++) {
                Emplacement vertical = places.get(j); // 垂直位置

                for (int x = 0; x < horizontal.size(); x++) {
                    Case horizX = horizontal.getCase(x);
                    if (horizX.getChar() == ' ') {
                        for (int y = 0; y < vertical.size(); y++) {
                            Case vertY = vertical.getCase(y);
                            if (vertY.getChar() == ' ' && horizX == vertY) {
                                contraintes.add(new CroixContrainte(i, x, j, y));
                            }
                        }
                    }
                }
            }
        }
        return contraintes;
    }

    public List<IContrainte> getContraintes() {
        return contraintes;
    }

    @Override
    public GrilleContrainte fixer(int m, String soluce) {
        // 调用父类的 fixer 方法生成新的 GrillePotentiel
        GrillePotentiel newGrillePotentiel = super.fixer(m, soluce);
        if (newGrillePotentiel == this) {
            return this;
        }
        return new GrilleContrainte(newGrillePotentiel.getGrillePlaces(), this.getDict(), this.contraintes);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Contraintes:\n");
        for (IContrainte contrainte : contraintes) {
            sb.append(contrainte.toString()).append("\n");
        }
        return sb.toString();
    }

    private boolean propage() {
        while (true) {
            int totalremoved = 0;
            for (IContrainte contrainte : contraintes) {
                int reduceResult = contrainte.reduce(this);
                if (reduceResult == -1) {
                    System.out.println("Dead state detected due to constraint: " + contrainte);
                    return false;
                }
                totalremoved += reduceResult;
            }
            if (totalremoved == 0) {
                System.out.println("Propagation stabilized.");
                return true;
            }
            if (this.isDead()) {
                System.out.println("Dead state detected.");
                return false;
            }
        }
    }
}
