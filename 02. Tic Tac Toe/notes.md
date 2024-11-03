## Functional Requirements Clarification / Assumptions
1. timed turn/game?
2. undo move?
3. game has spectators?
4. user statistics? rating?

---

## Classes, Attributes, and Methods

### 1. **Board**
   - **Attributes**:
     - `size: int`
     - `board: PieceInterface[][]`
   - **Methods**:
     - `getSize(): int`
     - `getPiece(row: int, col: int): PieceInterface`
     - `placePiece(piece: PieceInterface, row: int, col: int): boolean`
     - `displayBoard(): void`

### 2. **Game**
   - **Attributes**:
     - `board: Board`
     - `players: Player[]`
     - `winningStrategy: WinningStrategyInterface`
     - `currentPlayerIndex: int`
   - **Methods**:
     - `play(): void`
     - `getValidMove(scanner: Scanner): int[]`
     - `makeMove(player: Player, row: int, col: int): boolean`
     - `checkWinOrDraw(player: Player, row: int, col: int): boolean`
     - `isValidMove(row: int, col: int): boolean`
     - `isBoardFull(): boolean`

### 3. **Player**
   - **Attributes**:
     - `name: String`
     - `piece: PieceInterface`
   - **Methods**:
     - `getName(): String`
     - `getPiece(): PieceInterface`

### 4. **BruteForceWinningStrategyImpl** (Implements WinningStrategyInterface)
   - **Methods**:
     - `checkWin(board: Board, piece: PieceInterface, row: int, col: int): boolean`
     - `checkLine(board: Board, piece: PieceInterface, startRow: int, startCol: int, rowIncrement: int, colIncrement: int): boolean`

### 5. **LinearWinningStrategyImpl** (Implements WinningStrategyInterface)
   - **Methods**:
     - `checkWin(board: Board, piece: PieceInterface, row: int, col: int): boolean`
     - `checkLine(board: Board, piece: PieceInterface, startRow: int, startCol: int, rowIncrement: int, colIncrement: int): boolean`

### 6. **OptimizedWinningStrategyImpl** (Implements WinningStrategyInterface)
   - **Attributes**:
     - `rows: int[]`
     - `cols: int[]`
     - `diag: int`
     - `antiDiag: int`
     - `boardSize: int`
   - **Methods**:
     - `checkWin(board: Board, piece: PieceInterface, row: int, col: int): boolean`

### 7. **SymbolPieceImpl** (Implements PieceInterface)
   - **Attributes**:
     - `symbol: String`
   - **Methods**:
     - `getSymbol(): String`

### 8. **PieceInterface** (Interface)
   - **Methods**:
     - `getSymbol(): String`

### 9. **WinningStrategyInterface** (Interface)
   - **Methods**:
     - `checkWin(board: Board, piece: PieceInterface, row: int, col: int): boolean`


https://youtu.be/12SdZxWYQb8?feature=shared

leetcode 348 Design Tic-Tac-Toe
--------------------------------
Design a Tic-tac-toe game that is played between two players on a  n  x  n  grid.

You may assume the following rules:

A move is guaranteed to be valid and is placed on an empty block.
Once a winning condition is reached, no more moves is allowed.
A player who succeeds in placing  n  of their marks in a horizontal, vertical, or diagonal row wins the game.
 

Example:

Given _n_ = 3, assume that player 1 is "X" and player 2 is "O" in the board.

TicTacToe toe = new TicTacToe(3);

```
toe.move(0, 0, 1); - > Returns 0 (no one wins)
|X| | |
| | | |    // Player 1 makes a move at (0, 0).
| | | |

toe.move(0, 2, 2); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 2 makes a move at (0, 2).
| | | |

toe.move(2, 2, 1); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 1 makes a move at (2, 2).
| | |X|

toe.move(1, 1, 2); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 2 makes a move at (1, 1).
| | |X|

toe.move(2, 0, 1); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 1 makes a move at (2, 0).
|X| |X|

toe.move(1, 0, 2); -> Returns 0 (no one wins)
|X| |O|
|O|O| |    // Player 2 makes a move at (1, 0).
|X| |X|

toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
|X| |O|
|O|O| |    // Player 1 makes a move at (2, 1).
|X|X|X|
```

Follow up:
Could you do better than O(n^2) per move()

Hint:

Could you trade extra space such that move() operation can be done in O(1)?
You need two arrays: int rows[n], int cols[n], plus two variables: diagonal, anti_diagonal.