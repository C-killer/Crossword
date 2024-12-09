package com.crossword;

import java.util.List;

public class StratValeurBase implements IChoixValeur {
    @Override
    public List<String> orderValues(ICSP problem, IVariable variable) {
        return variable.getDomain(); // 直接返回域中的值
    }
}
