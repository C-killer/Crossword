package com.crossword;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CroixContrainte implements IContrainte{
    private final int m1;  // 第一个单词的索引
    private final int c1;  // 第一个单词交叉位置的索引
    private final int m2;  // 第二个单词的索引
    private final int c2;  // 第二个单词交叉位置的索引

    public CroixContrainte(int m1, int c1, int m2, int c2) {
        this.m1 = m1;
        this.c1 = c1;
        this.m2 = m2;
        this.c2 = c2;
    }

    @Override
    public int reduce(GrillePotentiel grille) {
        Dictionnaire dico1 = grille.getMotsPot().get(m1);
        Dictionnaire dico2 = grille.getMotsPot().get(m2);
        // 提取交叉位置的候选字母集合
        Set<Character> lettres1 = dico1.getLettres(c1);
        Set<Character> lettres2 = dico2.getLettres(c2);

        // 计算交集
        Set<Character> intersection = new HashSet<>(lettres1);
        intersection.retainAll(lettres2);
        // 无解
        if (intersection.isEmpty()) {
            System.err.println("Constraint unsolvable: " + this);
            return -1;
        }
        // 有解
        int nbRemove1 = dico1.filtreParEnsembleLettre(intersection, c1);
        int nbRemove2 = dico2.filtreParEnsembleLettre(intersection, c2);

        return nbRemove1 + nbRemove2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CroixContrainte that = (CroixContrainte) o;
        return m1 == that.m1 && c1 == that.c1 && m2 == that.m2 && c2 == that.c2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(m1, c1, m2, c2);
    }


    @Override
    public String toString() {
        return "CroixContrainte{" +
                "m1=" + m1 +
                ", c1=" + c1 +
                ", m2=" + m2 +
                ", c2=" + c2 +
                '}';
    }
}
