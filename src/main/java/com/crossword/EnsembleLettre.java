package com.crossword;

import java.util.Set;

public class EnsembleLettre {
    private final Set<Character> lettres;

    public EnsembleLettre() {
        this.lettres = Set.of(); // 空集合
    }

    public EnsembleLettre(Set<Character> lettres) {
        this.lettres = lettres;
    }

    public Set<Character> getLettres() {
        return lettres;
    }

    @Override
    public String toString() {
        return lettres.toString();
    }
}
