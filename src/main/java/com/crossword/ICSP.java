package com.crossword;

import java.util.List;

public interface ICSP {
    // accéder aux variables du problème,
    List<IVariable> getVars();

    // tester si un problème est encore satisfiable
    boolean isConsistent();

    // pour affecter une des variables du problème.
    // Le résultat de assign est un nouveau problème CSP,
    // de même nature que le précédent, mais qui compte une variable de moins (sa valeur étant fixée).
    ICSP assign(IVariable vi, String val);
}
