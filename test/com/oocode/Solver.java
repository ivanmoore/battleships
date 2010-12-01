// Copyright (c) 2010 Ivan Moore and Mick Killianey.
// All rights reserved.  See the LICENSE file for details.ls.
package com.oocode;

import java.util.List;

import static com.oocode.Square.Unknown;

public class Solver {
    private int counter = 0;

    public Board solved(final Board board) {
        debug(board);
        if (board.isSolved()) {
            return board;
        }
        BoardSquare boardSquare = board.next(Unknown);
        if (boardSquare == null) {
            return null;
        }
        List<Square> list = board.guessesToTry(boardSquare.row, boardSquare.col);
        for (Square thingToTry : list) {
            Board solvedBoard = solved(board.with(boardSquare.row, boardSquare.col, thingToTry));
            if (solvedBoard != null) {
                return solvedBoard;
            }
        }
        return null;
    }

    private void debug(Board board) {
        if (counter % 100000 == 0) {
            System.out.println(board);
        }
        counter++;
    }
}
