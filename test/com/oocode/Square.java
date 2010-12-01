// Copyright (c) 2010 Ivan Moore and Mick Killianey.
// All rights reserved.  See the LICENSE file for details.
package com.oocode;

import java.util.Set;

import static com.oocode.Possibilities.*;

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

    private final boolean isShip;
    private final char c;

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

    public Set<Square> possibilitiesAbove() {
        switch (this) {
            case GuessedShip:
                return SHIP_OR_WATER;
            case Single:
                return ONLY_WATER;
            case Centre:
                return SHIP_OR_WATER;
            case Top:
                return ONLY_WATER;
            case Bottom:
                return ONLY_SHIP;
            case Left:
                return ONLY_WATER;
            case Right:
                return ONLY_WATER;
            case Water:
                return SHIP_OR_WATER;
            case OutOfBounds:
                return SHIP_OR_WATER;
            case Unknown:
                return SHIP_OR_WATER;
        }
        throw new RuntimeException("unknown square type");
    }

    public Set<Square> possibilitiesRightOf() {
        switch (this) {
            case GuessedShip:
                return SHIP_OR_WATER;
            case Single:
                return ONLY_WATER;
            case Centre:
                return SHIP_OR_WATER;
            case Top:
                return ONLY_WATER;
            case Bottom:
                return ONLY_WATER;
            case Left:
                return ONLY_SHIP;
            case Right:
                return ONLY_WATER;
            case Water:
                return SHIP_OR_WATER;
            case OutOfBounds:
                return SHIP_OR_WATER;
            case Unknown:
                return SHIP_OR_WATER;
        }
        throw new RuntimeException("unknown square type");
    }

    public Set<Square> possibilitiesLeftOf() {
        switch (this) {
            case GuessedShip:
                return SHIP_OR_WATER;
            case Single:
                return ONLY_WATER;
            case Centre:
                return SHIP_OR_WATER;
            case Top:
                return ONLY_WATER;
            case Bottom:
                return ONLY_WATER;
            case Left:
                return ONLY_WATER;
            case Right:
                return ONLY_SHIP;
            case Water:
                return SHIP_OR_WATER;
            case OutOfBounds:
                return SHIP_OR_WATER;
            case Unknown:
                return SHIP_OR_WATER;
        }
        throw new RuntimeException("unknown square type");
    }

    public Set<Square> possibilitiesBelow() {
        switch (this) {
            case GuessedShip:
                return SHIP_OR_WATER;
            case Single:
                return ONLY_WATER;
            case Centre:
                return SHIP_OR_WATER;
            case Top:
                return ONLY_SHIP;
            case Bottom:
                return ONLY_WATER;
            case Left:
                return ONLY_WATER;
            case Right:
                return ONLY_WATER;
            case Water:
                return SHIP_OR_WATER;
            case OutOfBounds:
                return SHIP_OR_WATER;
            case Unknown:
                return SHIP_OR_WATER;
        }
        throw new RuntimeException("unknown square type");
    }
}
