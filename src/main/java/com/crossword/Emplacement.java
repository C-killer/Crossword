package com.crossword;

import java.util.ArrayList;
import java.util.List;

public class Emplacement {
    private final List<Case> cases;

    public Emplacement() {
        cases = new ArrayList<>();
    }

    public void add(Case e) { cases.add(e); }
    public int size() { return cases.size(); }
    public void clear() { cases.clear(); }
    public Case getCase(int i) { return cases.get(i); }

    /**
     * Returns a string representation of the Emplacement object.
     * If the list of cases is null, an empty string is returned.
     * Otherwise, this method constructs a string by concatenating
     * the characters of each Case object in the list of cases.
     *
     * @return A string representation of the Emplacement object,
     *         or an empty string if the list of cases is null.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Case c : cases) {
            sb.append(c.getChar());
        }
        return sb.toString();
    }
}
