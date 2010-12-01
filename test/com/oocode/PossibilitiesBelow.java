// Copyright (c) 2010 Ivan Moore and Mick Killianey.
// All rights reserved.  See the LICENSE file for details.
package com.oocode;

public class PossibilitiesBelow extends Possibilities {
    PossibilitiesBelow(PossibleGuesses possibleGuesses) {
        super(possibleGuesses);
    }

    public static PossibilitiesBelow belowCanBe(PossibleGuesses possibleGuessesAbove) {
        return new PossibilitiesBelow(possibleGuessesAbove);
    }
}
