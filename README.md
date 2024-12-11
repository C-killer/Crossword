# Crossword CSP Solver

## 目录
- [简介](#简介)
- [项目结构](#项目结构)
- [功能特性](#功能特性)
- [安装指南](#安装指南)
- [使用方法](#使用方法)
    - [变量选择策略](#变量选择策略)
    - [值选择策略](#值选择策略)
- [优化技术](#优化技术)
- [测试](#测试)
- [未来计划](#未来计划)

## 简介
本项目实现了一个用于填字游戏的约束满足问题（CSP）求解器。通过约束传播和回溯技术，高效解决不同复杂度的网格问题。该求解器具有模块化和可扩展性，支持自定义变量和值选择策略。

## 项目结构
```
src/
├── com/crossword
│   ├── Case.java             # 表示网格中的单元格
│   ├── CroixContrainte.java  # 用于单词交叉的约束
│   ├── CSPSolver.java        # 核心求解逻辑
│   ├── Dictionnaire.java     # 字典管理
│   ├── Grille.java           # 表示填字游戏网格
│   ├── GrilleContrainte.java # 扩展 GrillePotentiel，包含约束
│   ├── GrilleLoader.java     # 从文件加载网格
│   ├── GrillePlaces.java     # 管理网格中的单词位置
│   ├── GrillePotentiel.java  # 管理网格插槽的潜在单词
│   ├── ICSP.java             # CSP问题接口
│   ├── IContrainte.java      # 约束接口
│   ├── IVariable.java        # 变量接口
│
├── com/crossword/adapt
│   ├── DicoVariable.java     # 将字典适配为CSP变量
│   ├── MotX.java             # 将 GrilleContrainte 适配为CSP框架
│
├── com/crossword/strategies
│   ├── IChoixVar.java        # 变量选择策略接口
│   ├── IChoixValeur.java     # 值选择策略接口
│   ├── StratFirst.java       # 选择第一个变量
│   ├── StratMin.java         # 选择具有最小领域的变量
│   ├── StratValeurBase.java  # 按定义顺序选择值
│   ├── StratValeurAleatoire.java # 随机选择值
│   ├── StratValeurFrequence.java # 按字母频率选择值
│
├── com/crossword/modules
│   ├── GrilleSolverTest.java # CSP求解器的测试套件
```

## 功能特性
- **约束传播**：通过过滤高效传播约束。
- **回溯求解器**：实现递归CSP求解。
- **可定制策略**：
    - 变量选择策略：
        - `StratFirst`：选择第一个变量。
        - `StratMin`：选择领域最小的变量。
    - 值选择策略：
        - `StratValeurBase`：按默认顺序选择值。
        - `StratValeurAleatoire`：随机选择值。
        - `StratValeurFrequence`：按字母频率排序选择值。
- **优化技术**：
    - 预过滤网格插槽的潜在单词。
    - 为字典操作引入缓存。

## 安装指南
1. 克隆仓库：
   ```bash
   git clone https://github.com/your-username/crossword-csp-solver.git
   ```
2. 进入项目目录：
   ```bash
   cd crossword-csp-solver
   ```
3. 使用首选的Java构建工具构建项目（例如Maven、Gradle，或直接使用`javac`）。

## 使用方法
### 变量选择策略
1. **StratFirst**：总是选择列表中的第一个变量。
2. **StratMin**：选择具有最小领域大小的变量，减少分支。

### 值选择策略
1. **StratValeurBase**：按自然顺序使用值。
2. **StratValeurAleatoire**：在赋值前随机排列值的顺序。
3. **StratValeurFrequence**：根据减少冲突的可能性，按字母频率排序值。

### 示例代码
```java
ICSP problem = new MotX(grilleContrainte);
CSPSolver solver = new CSPSolver();

solver.setChoixVarStrat(new StratFirst());
solver.setChoixValStrat(new StratValeurBase());

ICSP solution = solver.solve(problem);
System.out.println("Solution: " + solution);
```

## 优化技术
### 1. 预过滤潜在单词
在 `GrillePotentiel` 和 `GrilleContrainte` 中，根据当前网格状态预过滤潜在单词，避免冗余计算。

### 2. 字典操作缓存
在 `Dictionnaire` 中引入缓存 (`EnsembleLettre[]`)，避免在约束减少过程中重复计算每个索引的可能字母。

## 测试
- 测试用例实现于 `GrilleSolverTest.java`。
- 包括针对小型网格（`easy.grl`）和大型网格（`large.grl`）的测试。
- 运行测试：
  ```bash
  # 示例使用 Maven
  mvn test
  ```

## 未来计划
- 实现更多变量和值选择策略的启发式方法。
- 探索并行化处理以支持更大的网格。
- 集成多语言字典支持。

---

欢迎通过提交问题或拉取请求对本项目做出贡献！
