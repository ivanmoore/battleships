// Copyright (c) 2010 Ivan Moore and Mick Killianey.
// All rights reserved.  See the LICENSE file for details.
package com.oocode;

public class PossibilitiesToRight extends Possibilities {
    PossibilitiesToRight(PossibleGuesses possibleGuesses) {
        super(possibleGuesses);
    }

    public static PossibilitiesToRight toRightCanBe(PossibleGuesses possibleGuessesAbove) {
        return new PossibilitiesToRight(possibleGuessesAbove);
    }
}
