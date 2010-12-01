// Copyright (c) 2010 Ivan Moore and Mick Killianey.
// All rights reserved.  See the LICENSE file for details.
package com.oocode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ships {
    private final int[] lengths;

    public Ships(int... lengths) {
        this.lengths = lengths;
    }

    public int numberOfShipSquares() {
        int result = 0;
        for (int length : lengths) {
            result += length;
        }
        return result;
    }

    public boolean equivalentTo(List<Ship> ships) {
        List<Integer> expectedLengths = new ArrayList<Integer>();
        for (int length : lengths) {
            expectedLengths.add(length);
        }
        Collections.sort(expectedLengths);
        List<Integer> actualLengths = new ArrayList<Integer>();
        for (Ship ship : ships) {
            actualLengths.add(ship.length());
        }
        Collections.sort(actualLengths);
        return actualLengths.equals(expectedLengths);
    }
}
