package com.oocode;

public class PossibilitiesAbove extends Possibilities {
    PossibilitiesAbove(PossibleGuesses possibleGuesses) {
        super(possibleGuesses);
    }

    public static PossibilitiesAbove aboveCanBe(PossibleGuesses possibleGuessesAbove) {
        return new PossibilitiesAbove(possibleGuessesAbove);
    }
}
