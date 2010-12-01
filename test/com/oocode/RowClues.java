// Copyright (c) 2010 Ivan Moore and Mick Killianey.
// All rights reserved.  See the LICENSE file for details.
package com.oocode;

public class RowClues {
    private final int[] numberOfShips;

    public RowClues(int... numberOfShips) {
        this.numberOfShips = numberOfShips;
    }

    public int length() {
        return numberOfShips.length;
    }

    public int get(int row) {
        return numberOfShips[row];
    }
}
