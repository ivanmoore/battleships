// Copyright (c) 2010 Ivan Moore and Mick Killianey.
// All rights reserved.  See the LICENSE file for details.
package com.oocode;

import java.util.Set;

import static com.oocode.PossibilitiesAbove.aboveCanBe;
import static com.oocode.PossibleGuesses.*;

public enum Square {
    GuessedShip('*', true, aboveCanBe(SHIP_OR_WATER), PossibilitiesToRight.toRightCanBe(SHIP_OR_WATER), PossibilitiesToLeft.toLeftCanBe(SHIP_OR_WATER), PossibilitiesBelow.belowCanBe(SHIP_OR_WATER)),
    Single('o', true, aboveCanBe(ONLY_WATER), PossibilitiesToRight.toRightCanBe(ONLY_WATER), PossibilitiesToLeft.toLeftCanBe(ONLY_WATER), PossibilitiesBelow.belowCanBe(ONLY_WATER)),
    Centre('#', true, aboveCanBe(SHIP_OR_WATER), PossibilitiesToRight.toRightCanBe(SHIP_OR_WATER), PossibilitiesToLeft.toLeftCanBe(SHIP_OR_WATER), PossibilitiesBelow.belowCanBe(SHIP_OR_WATER)),
    Top('^', true, aboveCanBe(ONLY_WATER), PossibilitiesToRight.toRightCanBe(ONLY_WATER), PossibilitiesToLeft.toLeftCanBe(ONLY_WATER), PossibilitiesBelow.belowCanBe(ONLY_SHIP)),
    Bottom('v', true, aboveCanBe(ONLY_SHIP), PossibilitiesToRight.toRightCanBe(ONLY_WATER), PossibilitiesToLeft.toLeftCanBe(ONLY_WATER), PossibilitiesBelow.belowCanBe(ONLY_WATER)),
    Left('<', true, aboveCanBe(ONLY_WATER), PossibilitiesToRight.toRightCanBe(ONLY_SHIP), PossibilitiesToLeft.toLeftCanBe(ONLY_WATER), PossibilitiesBelow.belowCanBe(ONLY_WATER)),
    Right('>', true, aboveCanBe(ONLY_WATER), PossibilitiesToRight.toRightCanBe(ONLY_WATER), PossibilitiesToLeft.toLeftCanBe(ONLY_SHIP), PossibilitiesBelow.belowCanBe(ONLY_WATER)),
    Water('~', false, aboveCanBe(SHIP_OR_WATER), PossibilitiesToRight.toRightCanBe(SHIP_OR_WATER), PossibilitiesToLeft.toLeftCanBe(SHIP_OR_WATER), PossibilitiesBelow.belowCanBe(SHIP_OR_WATER)),
    OutOfBounds('X', false, aboveCanBe(SHIP_OR_WATER), PossibilitiesToRight.toRightCanBe(SHIP_OR_WATER), PossibilitiesToLeft.toLeftCanBe(SHIP_OR_WATER), PossibilitiesBelow.belowCanBe(SHIP_OR_WATER)),
    Unknown('?', false, aboveCanBe(SHIP_OR_WATER), PossibilitiesToRight.toRightCanBe(SHIP_OR_WATER), PossibilitiesToLeft.toLeftCanBe(SHIP_OR_WATER), PossibilitiesBelow.belowCanBe(SHIP_OR_WATER));

    private final boolean isShip;
    private final PossibilitiesAbove possibilitiesAbove;
    private final PossibilitiesBelow possibilitiesBelow;
    private final PossibilitiesToRight possibilitiesToRight;
    private final PossibilitiesToLeft possibilitiesToLeft;
    private final char c;

    Square(char representation, boolean isShip, PossibilitiesAbove possibilitiesAbove, PossibilitiesToRight possibilitiesToRight, PossibilitiesToLeft possibilitiesToLeft, PossibilitiesBelow possibilitiesBelow) {
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
