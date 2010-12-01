// Copyright (c) 2010 Ivan Moore and Mick Killianey.
// All rights reserved.  See the LICENSE file for details.
package com.oocode;

class BoardSquare {
    public final int row;
    public final int col;
    public final Square square;

    public BoardSquare(int row, int col, Square square) {
        this.row = row;
        this.col = col;
        this.square = square;
    }

    @Override
    public String toString() {
        return "BoardSquare{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}
