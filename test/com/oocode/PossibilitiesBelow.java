package com.oocode;

public class PossibilitiesBelow extends Possibilities {
    PossibilitiesBelow(PossibleGuesses possibleGuesses) {
        super(possibleGuesses);
    }

    public static PossibilitiesBelow belowCanBe(PossibleGuesses possibleGuessesAbove) {
        return new PossibilitiesBelow(possibleGuessesAbove);
    }
}
