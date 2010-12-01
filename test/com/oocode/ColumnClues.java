// Copyright (c) 2010 Ivan Moore and Mick Killianey.
// All rights reserved.  See the LICENSE file for details.
package com.oocode;

public class ColumnClues {
    private final int[] numberOfShips;

    public ColumnClues(int... numberOfShips) {
        this.numberOfShips = numberOfShips;
    }

    public int length() {
        return numberOfShips.length;
    }

    public int get(int col) {
        return numberOfShips[col];
    }
}
