// Copyright (c) 2010 Ivan Moore and Mick Killianey.
// All rights reserved.  See the LICENSE file for details.
package com.oocode;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

public class Possibilities {
    public static final Set<Square> SHIP_OR_WATER = asSet(Square.GuessedShip, Square.Water);
    public static final Set<Square> ONLY_WATER = asSet(Square.Water);
    public static final Set<Square> ONLY_SHIP = asSet(Square.GuessedShip);

    private static Set<Square> asSet(Square... squares) {
        return new HashSet<Square>(asList(squares));
    }
}
