// Copyright (c) 2010 Ivan Moore and Mick Killianey.
// All rights reserved.  See the LICENSE file for details.
package com.oocode;

import java.util.Set;

import static com.oocode.PossibilitiesAbove.aboveCanBe;
import static com.oocode.PossibilitiesBelow.belowCanBe;
import static com.oocode.PossibilitiesToLeft.toLeftCanBe;
import static com.oocode.PossibilitiesToRight.toRightCanBe;
import static com.oocode.PossibleGuesses.*;

public enum Square {
    GuessedShip('*', true, aboveCanBe(SHIP_OR_WATER), belowCanBe(SHIP_OR_WATER), toLeftCanBe(SHIP_OR_WATER), toRightCanBe(SHIP_OR_WATER)),
    Single('o', true, aboveCanBe(ONLY_WATER), belowCanBe(ONLY_WATER), toLeftCanBe(ONLY_WATER), toRightCanBe(ONLY_WATER)),
    Centre('#', true, aboveCanBe(SHIP_OR_WATER), belowCanBe(SHIP_OR_WATER), toLeftCanBe(SHIP_OR_WATER), toRightCanBe(SHIP_OR_WATER)),
    Top('^', true, aboveCanBe(ONLY_WATER), belowCanBe(ONLY_SHIP), toLeftCanBe(ONLY_WATER), toRightCanBe(ONLY_WATER)),
    Bottom('v', true, aboveCanBe(ONLY_SHIP), belowCanBe(ONLY_WATER), toLeftCanBe(ONLY_WATER), toRightCanBe(ONLY_WATER)),
    Left('<', true, aboveCanBe(ONLY_WATER), belowCanBe(ONLY_WATER), toLeftCanBe(ONLY_WATER), toRightCanBe(ONLY_SHIP)),
    Right('>', true, aboveCanBe(ONLY_WATER), belowCanBe(ONLY_WATER), toLeftCanBe(ONLY_SHIP), toRightCanBe(ONLY_WATER)),
    Water('~', false, aboveCanBe(SHIP_OR_WATER), belowCanBe(SHIP_OR_WATER), toLeftCanBe(SHIP_OR_WATER), toRightCanBe(SHIP_OR_WATER)),
    OutOfBounds('X', false, aboveCanBe(SHIP_OR_WATER), belowCanBe(SHIP_OR_WATER), toLeftCanBe(SHIP_OR_WATER), toRightCanBe(SHIP_OR_WATER)),
    Unknown('?', false, aboveCanBe(SHIP_OR_WATER), belowCanBe(SHIP_OR_WATER), toLeftCanBe(SHIP_OR_WATER), toRightCanBe(SHIP_OR_WATER));

    private final boolean isShip;
    private final PossibilitiesAbove possibilitiesAbove;
    private final PossibilitiesBelow possibilitiesBelow;
    private final PossibilitiesToRight possibilitiesToRight;
    private final PossibilitiesToLeft possibilitiesToLeft;
    private final char c;

    Square(char representation, boolean isShip, PossibilitiesAbove possibilitiesAbove, PossibilitiesBelow possibilitiesBelow, PossibilitiesToLeft possibilitiesToLeft, PossibilitiesToRight possibilitiesToRight) {
        this.c = representation;
        this.isShip = isShip;
        this.possibilitiesAbove = possibilitiesAbove;
        this.possibilitiesToRight = possibilitiesToRight;
        this.possibilitiesToLeft = possibilitiesToLeft;
        this.possibilitiesBelow = possibilitiesBelow;
    }

    public String toString() {
        return Character.toString(c);
    }

    public boolean isShip() {
        return isShip;
    }

    public static Square from(char square) {
        for (Square match : Square.values()) {
            if (match.c == square) {
                return match;
            }
        }
        throw new RuntimeException("could not find square for " + square);
    }

    public Set<Square> possibilitiesAbove() {
        return possibilitiesAbove.canBe();
    }

    public Set<Square> possibilitiesRightOf() {
        return possibilitiesToRight.canBe();
    }

    public Set<Square> possibilitiesLeftOf() {
        return possibilitiesToLeft.canBe();
    }

    public Set<Square> possibilitiesBelow() {
        return possibilitiesBelow.canBe();
    }
}
