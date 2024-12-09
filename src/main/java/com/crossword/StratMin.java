package com.crossword;

import java.util.List;

public class StratMin implements IChoixVar {

    // 选择当前拥有最小领域的变量
    @Override
    public IVariable chooseVar(ICSP problem) {
        List<IVariable> vars = problem.getVars();
        if (vars.isEmpty()) {
            return null;
        }

        IVariable minVar = vars.get(0);
        int minDomainSize = minVar.getDomain().size();

        for (IVariable var : vars) {
            if (var.getDomain().size() < minDomainSize) {
                minVar = var;
                minDomainSize = var.getDomain().size();
            }
        }

        return minVar;
    }
}
