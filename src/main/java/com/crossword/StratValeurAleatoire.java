package com.crossword;

import java.util.Collections;
import java.util.List;

public class StratValeurAleatoire implements IChoixValeur {
    @Override
    public List<String> orderValues(ICSP problem, IVariable variable) {
        List<String> values = variable.getDomain();
        Collections.shuffle(values); // 随机打乱值的顺序
        return values;
    }
}
