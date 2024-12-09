package com.crossword.adapt;

import com.crossword.GrilleContrainte;
import com.crossword.ICSP;
import com.crossword.IVariable;

import java.util.List;
import java.util.ArrayList;

public class MotX implements ICSP {
    private final List<DicoVariable> dicoVariableList;

    public MotX(GrilleContrainte gc) {
        dicoVariableList  = new ArrayList<>();
        int lenMotsPot = gc.getMotsPot().size();
        for (int i=0;i<lenMotsPot;i++) {
            dicoVariableList.add(new DicoVariable(i,gc));
        }
    }
    public MotX(List<DicoVariable> dicoVariables, GrilleContrainte grilleContrainte) {
        this.dicoVariableList = dicoVariables;
    }

    @Override
    public List<IVariable> getVars() {
        return new ArrayList<>(dicoVariableList);
    }

    @Override
    public boolean isConsistent() {
        for (DicoVariable dicoVar : dicoVariableList) {
            if (dicoVar.getGrilleContrainte().isDead()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ICSP assign(IVariable vi, String val) {
        DicoVariable dicoVar = (DicoVariable) vi;
        int index = dicoVar.getIndice();
        System.out.println("Assigning variable at index " + index + " to value: " + val);
        GrilleContrainte newGrille = dicoVar.getGrilleContrainte().fixer(index, val);
        List<DicoVariable> newDicoVariables = new ArrayList<>();
        for (DicoVariable dv : dicoVariableList) {
            if (dv.getIndice() != index) {  // 排除当前已固定的变量
                newDicoVariables.add(new DicoVariable(dv.getIndice(), newGrille));
            }
        }
        return new MotX(newDicoVariables, newGrille);
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MotX{\n");
        for (DicoVariable dicoVar : dicoVariableList) {
            sb.append("  ").append(dicoVar.toString()).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }

}
