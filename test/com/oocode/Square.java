// Copyright (c) 2010 Ivan Moore and Mick Killianey.
// All rights reserved.  See the LICENSE file for details.
package com.oocode;

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
}
