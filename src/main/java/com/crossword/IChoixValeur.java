package com.crossword;

import java.util.List;

// 用于值选择策略的接口
public interface IChoixValeur {
    List<String> orderValues(ICSP problem, IVariable variable);
}
