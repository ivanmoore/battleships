// Copyright (c) 2010 Ivan Moore and Mick Killianey.
// All rights reserved.  See the LICENSE file for details.
package com.oocode;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

public enum Square {
    Unknown('?', false),
    OutOfBounds('X', false),
    Water('~', false),
    GuessedShip('*', true),
    Top('^', true),
    Bottom('v', true),
    Left('<', true),
    Right('>', true),
    Centre('#', true),
    Single('o', true);

    private final char c;
    private final boolean isShip;

    Square(char representation, boolean isShip) {
        this.c = representation;
        this.isShip = isShip;
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

    private static Set<Square> asSet(Square... squares) {
        return new HashSet<Square>(asList(squares));
    }

    public Set<Square> possibilitiesAbove() {
        switch (this) {
            case GuessedShip:
                return asSet(GuessedShip, Water);
            case Single:
                return asSet(Water);
            case Centre:
                return asSet(GuessedShip, Water);
            case Top:
                return asSet(Water);
            case Bottom:
                return asSet(GuessedShip);
            case Left:
                return asSet(Water);
            case Right:
                return asSet(Water);
            case Water:
                return asSet(GuessedShip, Water);
            case OutOfBounds:
                return asSet(GuessedShip, Water);
            case Unknown:
                return asSet(GuessedShip, Water);
        }
        throw new RuntimeException("unknown square type");
    }

    public Set<Square> possibilitiesRightOf() {
        switch (this) {
            case GuessedShip:
                return asSet(GuessedShip, Water);
            case Single:
                return asSet(Water);
            case Centre:
                return asSet(GuessedShip, Water);
            case Top:
                return asSet(Water);
            case Bottom:
                return asSet(Water);
            case Left:
                return asSet(GuessedShip);
            case Right:
                return asSet(Water);
            case Water:
                return asSet(GuessedShip, Water);
            case OutOfBounds:
                return asSet(GuessedShip, Water);
            case Unknown:
                return asSet(GuessedShip, Water);
        }
        throw new RuntimeException("unknown square type");
    }

    public Set<Square> possibilitiesLeftOf() {
        switch (this) {
            case GuessedShip:
                return asSet(GuessedShip, Water);
            case Single:
                return asSet(Water);
            case Centre:
                return asSet(GuessedShip, Water);
            case Top:
                return asSet(Water);
            case Bottom:
                return asSet(Water);
            case Left:
                return asSet(Water);
            case Right:
                return asSet(GuessedShip);
            case Water:
                return asSet(GuessedShip, Water);
            case OutOfBounds:
                return asSet(GuessedShip, Water);
            case Unknown:
                return asSet(GuessedShip, Water);
        }
        throw new RuntimeException("unknown square type");
    }

    public Set<Square> possibilitiesBelow() {
        switch (this) {
            case GuessedShip:
                return asSet(GuessedShip, Water);
            case Single:
                return asSet(Water);
            case Centre:
                return asSet(GuessedShip, Water);
            case Top:
                return asSet(GuessedShip);
            case Bottom:
                return asSet(Water);
            case Left:
                return asSet(Water);
            case Right:
                return asSet(Water);
            case Water:
                return asSet(GuessedShip, Water);
            case OutOfBounds:
                return asSet(GuessedShip, Water);
            case Unknown:
                return asSet(GuessedShip, Water);
        }
        throw new RuntimeException("unknown square type");
    }
}
