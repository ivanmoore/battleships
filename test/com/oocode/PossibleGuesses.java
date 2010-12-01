// Copyright (c) 2010 Ivan Moore and Mick Killianey.
// All rights reserved.  See the LICENSE file for details.
package com.oocode;

import java.util.HashSet;
import java.util.Set;

import static com.oocode.Square.GuessedShip;
import static com.oocode.Square.Water;
import static java.util.Arrays.asList;

public enum PossibleGuesses {
    SHIP_OR_WATER, ONLY_WATER, ONLY_SHIP;

    //a bit tricky - has to be like this because used in definition of Squares before they all exist
    public Set<Square> canBe() {
        switch (this) {
            case ONLY_SHIP:
                return asSet(GuessedShip);
            case ONLY_WATER:
                return asSet(Water);
            case SHIP_OR_WATER:
                return asSet(GuessedShip, Water);
        }
        throw new RuntimeException("Must be one of known possibilities");
    }

    private static Set<Square> asSet(Square... squares) {
        return new HashSet<Square>(asList(squares));
    }
}
