// Copyright (c) 2010 Ivan Moore and Mick Killianey.
// All rights reserved.  See the LICENSE file for details.
package com.oocode;

public class PossibilitiesToLeft extends Possibilities {
    PossibilitiesToLeft(PossibleGuesses possibleGuesses) {
        super(possibleGuesses);
    }

    public static PossibilitiesToLeft toLeftCanBe(PossibleGuesses possibleGuessesAbove) {
        return new PossibilitiesToLeft(possibleGuessesAbove);
    }
}
