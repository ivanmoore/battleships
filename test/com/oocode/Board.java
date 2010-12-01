// Copyright (c) 2010 Ivan Moore and Mick Killianey.
// All rights reserved.  See the LICENSE file for details.
package com.oocode;

import java.util.*;

import static com.oocode.Ship.shipFromBoardSquares;
import static com.oocode.Square.*;

public class Board {
    private final ColumnClues columnClues;
    private final RowClues rowClues;
    private final Ships ships;
    private final Square[][] squares;

    public interface Condition {
        boolean isWanted(Square square);
    }

    public Board(RowClues rowClues, ColumnClues columnClues, Ships ships) {
        this.columnClues = columnClues;
        this.rowClues = rowClues;
        this.ships = ships;
        squares = new Square[rowClues.length()][columnClues.length()];
        for (int r = 0; r < squares.length; r++) {
            for (int c = 0; c < squares[r].length; c++) {
                squares[r][c] = Unknown;
            }
        }
    }

    public Board solved() {
        return new Solver().solved(copy());
    }

    public List<Square> guessesToTry(int row, int col) {
        Set<Square> possibilities = allPossibilitiesBasedOnNearNeighbours(row, col);
        if (cannotBeWater(row, col)) {
            possibilities.remove(Water);
        }
        if (ifAnythingMustBeWater(row, col)) {
            if (possibilities.contains(Water)) {
                return asArrayList(Water);
            } else {
                return asArrayList();
            }
        }
        if (cannotBeShip()) {
            possibilities.remove(GuessedShip);
        }
        return asArrayList(possibilities);
    }

    private boolean cannotBeWater(int row, int col) {
        return numberOfRows() - rowClues.get(row) == numberInRow(row, Water) ||
                numberOfCols() - columnClues.get(col) == numberInCol(col, Water);
    }

    private boolean cannotBeShip() {
        return ships.numberOfShipSquares() == numberOfShipSquares();
    }

    public boolean ifAnythingMustBeWater(int row, int col) {
        return hasShipOnDiagonal(row, col) ||
                numberOfShipsInCol(col) == columnClues.get(col) ||
                numberOfShipsInRow(row) == rowClues.get(row);
    }

    Set<Square> allPossibilitiesBasedOnNearNeighbours(int row, int col) {
        Set<Square> squareSet = new HashSet<Square>();
        squareSet.addAll(squareBelow(row, col).possibilitiesAbove());
        squareSet.retainAll(squareAbove(row, col).possibilitiesBelow());
        squareSet.retainAll(squareRightOf(row, col).possibilitiesLeftOf());
        squareSet.retainAll(squareLeftOf(row, col).possibilitiesRightOf());
        return squareSet;
    }

    private Square squareLeftOf(int row, int col) {
        return square(row, col - 1);
    }

    private Square squareRightOf(int row, int col) {
        return square(row, col + 1);
    }

    private Square squareAbove(int row, int col) {
        return square(row - 1, col);
    }

    private Square squareBelow(int row, int col) {
        return square(row + 1, col);
    }

    private boolean hasShipOnDiagonal(int row, int col) {
        return square(row - 1, col - 1).isShip() ||
                square(row - 1, col + 1).isShip() ||
                square(row + 1, col - 1).isShip() ||
                square(row + 1, col + 1).isShip();
    }

    boolean isSolved() {
        return allSquaresAreFilled() && isValid();
    }

    private boolean allSquaresAreFilled() {
        return next(Unknown) == null;
    }

    public BoardSquare next(final Condition condition) {
        for (int r = 0; r < squares.length; r++) {
            for (int c = 0; c < squares[r].length; c++) {
                if (condition.isWanted(squares[r][c])) {
                    return new BoardSquare(r, c, squares[r][c]);
                }
            }
        }
        return null;
    }

    public BoardSquare next(final Square squareWanted) {
        return next(new Condition() {
            public boolean isWanted(Square square) {
                return square == squareWanted;
            }
        });
    }

    public boolean isValid() {
        for (int r = 0; r < squares.length; r++) {
            for (int c = 0; c < squares[r].length; c++) {
                if (squares[r][c].isShip()) {
                    if (hasShipOnDiagonal(r, c)) {
                        return false;
                    }
                }
            }
        }
        for (int r = 0; r < numberOfRows(); r++) {
            if (numberOfShipsInRow(r) != rowClues.get(r)) {
                return false;
            }
        }
        for (int c = 0; c < numberOfCols(); c++) {
            if (numberOfShipsInCol(c) != columnClues.get(c)) {
                return false;
            }
        }
        List<Ship> shipList = ships();
        return hasCorrectNumberOfShipsOfSpecificSizes(shipList) && shipsAreWellFormed(shipList);
    }

    private boolean hasCorrectNumberOfShipsOfSpecificSizes(List<Ship> ships) {
        return numberOfShipSquares() == this.ships.numberOfShipSquares()
                && this.ships.equivalentTo(ships);
    }

    private int numberOfShipSquares() {
        return numberOf(new Condition() {
            public boolean isWanted(Square square) {
                return square.isShip();
            }
        });
    }

    private int numberOf(Condition squareToCount) {
        int result = 0;
        for (Square[] row : squares) {
            for (Square square : row) {
                if (squareToCount.isWanted(square)) {
                    result++;
                }
            }
        }
        return result;
    }

    private int numberOfShipsInRow(int r) {
        return numberInRow(r, new Condition() {
            public boolean isWanted(Square square) {
                return square.isShip();
            }
        });
    }

    private int numberOfShipsInCol(int col) {
        return numberInCol(col, new Condition() {
            public boolean isWanted(Square square) {
                return square.isShip();
            }
        });
    }

    private int numberInRow(int rowNumber, final Square squareToCount) {
        return numberIn(squares[rowNumber], new Condition() {
            public boolean isWanted(Square square) {
                return square == squareToCount;
            }
        });
    }

    private int numberInRow(int rowNumber, Condition squareToCount) {
        return numberIn(squares[rowNumber], squareToCount);
    }

    private int numberInCol(int colNumber, final Square squareToCount) {
        return numberInCol(colNumber, new Condition() {
            public boolean isWanted(Square square) {
                return square == squareToCount;
            }
        });
    }

    private int numberInCol(int colNumber, Condition squareToCount) {
        Square[] col = new Square[numberOfRows()];
        for (int r = 0; r < numberOfRows(); r++) {
            col[r] = squares[r][colNumber];
        }
        return numberIn(col, squareToCount);
    }

    private int numberIn(Square[] squares, Condition squareToCount) {
        int result = 0;
        for (Square square : squares) {
            if (squareToCount.isWanted(square)) {
                result++;
            }
        }
        return result;
    }

    private Square square(int row, int col) {
        if (row < 0 || row >= numberOfRows() || col < 0 || col >= numberOfCols()) {
            return OutOfBounds;
        }
        return squares[row][col];
    }

    private int numberOfCols() {
        return columnClues.length();
    }

    private int numberOfRows() {
        return rowClues.length();
    }

    private boolean shipsAreWellFormed(List<Ship> ships) {
        for (Ship ship : ships) {
            if (!ship.isWellFormed()) {
                return false;
            }
        }
        return true;
    }

    public List<Ship> ships() {
        List<Ship> result = new ArrayList<Ship>();
        Board board = copy();
        BoardSquare shipSquare = board.nextShip();
        while (shipSquare != null) {
            List<BoardSquare> shipSquares = board.shipStartingFrom(shipSquare);
            result.add(shipFromBoardSquares(shipSquares));
            board = board.without(shipSquares);
            shipSquare = board.nextShip();
        }
        return result;
    }

    private List<BoardSquare> shipStartingFrom(BoardSquare shipSquare) {
        int row = shipSquare.row;
        int col = shipSquare.col;
        Direction direction = directionOfShip(row, col);
        List<BoardSquare> result = new ArrayList<BoardSquare>();
        result.add(shipSquare);
        BoardSquare next = direction.next(this, row, col);
        while (next.square.isShip()) {
            result.add(next);
            next = direction.next(this, next.row, next.col);
        }
        return result;
    }

    private Direction directionOfShip(int row, int col) {
        if (squareAbove(row, col).isShip()) {
            return Direction.Up;
        }
        if (squareBelow(row, col).isShip()) {
            return Direction.Down;
        }
        if (squareRightOf(row, col).isShip()) {
            return Direction.Right;
        }
        if (squareLeftOf(row, col).isShip()) {
            return Direction.Left;
        }
        return Direction.Nowhere;
    }

    private enum Direction {
        Up(-1, 0),
        Down(1, 0),
        Left(0, -1),
        Right(0, 1),
        Nowhere(0, 0) {
            @Override
            public BoardSquare next(Board board, int row, int col) {
                return new BoardSquare(row, col, OutOfBounds);
            }};

        private final int rowDelta;
        private final int colDelta;

        Direction(int rowDelta, int colDelta) {
            this.rowDelta = rowDelta;
            this.colDelta = colDelta;
        }

        public BoardSquare next(Board board, int row, int col) {
            return board.squareAt(row + rowDelta, col + colDelta);
        }
    }

    private BoardSquare squareAt(int row, int col) {
        return new BoardSquare(row, col, square(row, col));
    }

    private BoardSquare nextShip() {
        return next(new Condition() {
            public boolean isWanted(Square square) {
                return square.isShip();
            }
        });
    }

    public Board with(int row, int col, Square value) {
        Board result = copy();
        result.squares[row][col] = value;
        return result;
    }

    private Board without(List<BoardSquare> shipSquares) {
        Board result = copy();
        for (BoardSquare shipSquare : shipSquares) {
            result.squares[shipSquare.row][shipSquare.col] = Unknown;
        }
        return result;
    }

    private Board copy() {
        final Board board = new Board(rowClues, columnClues, ships);
        for (int r = 0; r < squares.length; r++) {
            for (int c = 0; c < squares[r].length; c++) {
                board.squares[r][c] = squares[r][c];
            }
        }
        return board;
    }

    private List<Square> asArrayList(Square... squares) {
        List<Square> result = new ArrayList<Square>();
        Collections.addAll(result, squares);
        return result;
    }

    private List<Square> asArrayList(Set<Square> squareSet) {
        List<Square> result = new ArrayList<Square>();
        result.addAll(squareSet);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Square[] row : squares) {
            String rowString = "";
            for (Square square : row) {
                rowString += square;
            }
            result.append(rowString).append("\n");
        }
        return result.toString();
    }
}
