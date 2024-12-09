package com.crossword;

import java.util.List;

public class StratFirst implements IChoixVar {
    // 选择第一变量策略
    @Override
    public IVariable chooseVar(ICSP problem) {
        List<IVariable> vars = problem.getVars();
        return vars.isEmpty() ? null : vars.get(0);
    }
}
