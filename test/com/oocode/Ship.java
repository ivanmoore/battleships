// Copyright (c) 2010 Ivan Moore and Mick Killianey.
// All rights reserved.  See the LICENSE file for details.
package com.oocode;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private final List<Square> squares;

    private Ship(List<Square> squares) {
        this.squares = squares;
    }

    public static Ship shipFromSquares(List<Square> squares) {
        return new Ship(squares);
    }

    public static Ship shipFromBoardSquares(List<BoardSquare> squares) {
        return shipFromSquares(squaresFrom(squares));
    }

    public int length() {
        return squares.size();
    }

    private static List<Square> squaresFrom(List<BoardSquare> squares) {
        List<Square> result = new ArrayList<Square>();
        for (BoardSquare square : squares) {
            result.add(square.square);
        }
        return result;
    }

    public boolean isWellFormed() {
        if (firstSquare() == Square.Centre) return false;
        if (lastSquare() == Square.Centre) return false;
        return true;
    }

    private Square firstSquare() {
        return squares.get(0);
    }

    private Square lastSquare() {
        return squares.get(squares.size() - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ship ship = (Ship) o;

        if (squares != null ? !squares.equals(ship.squares) : ship.squares != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return squares != null ? squares.hashCode() : 0;
    }
}
