// Copyright (c) 2010 Ivan Moore and Mick Killianey.
// All rights reserved.  See the LICENSE file for details.
package com.oocode;

public class PossibilitiesAbove extends Possibilities {
    PossibilitiesAbove(PossibleGuesses possibleGuesses) {
        super(possibleGuesses);
    }

    public static PossibilitiesAbove aboveCanBe(PossibleGuesses possibleGuessesAbove) {
        return new PossibilitiesAbove(possibleGuessesAbove);
    }
}
