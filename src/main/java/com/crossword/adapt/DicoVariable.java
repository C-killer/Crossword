package com.crossword.adapt;

import com.crossword.Dictionnaire;
import com.crossword.GrilleContrainte;
import com.crossword.IVariable;

import java.util.List;

public class DicoVariable implements IVariable{
    private final int indice;   // l’emplacement de mot correspondant
    private final GrilleContrainte grilleContrainte;

    public DicoVariable(int index, GrilleContrainte gp) {
        indice = index;
        grilleContrainte = gp;
    }

    @Override
    public List<String> getDomain() {
        Dictionnaire listMot = grilleContrainte.getMotsPot().get(indice);
        System.out.println("DicoVariable [index=" + indice + "] domain: " + listMot.getMots());
        return listMot.getMots();
    }


    @Override
    public String toString() {
        return "DicoVariable{"
                + "indice="
                + indice
                + ", variable domain="
                + this.getDomain().toString()
                + '}';
    }

    public GrilleContrainte getGrilleContrainte() { return grilleContrainte; }
    public int getIndice() { return indice; }
}
