package com.oocode;

public class PossibilitiesToRight extends Possibilities {
    PossibilitiesToRight(PossibleGuesses possibleGuesses) {
        super(possibleGuesses);
    }

    public static PossibilitiesToRight toRightCanBe(PossibleGuesses possibleGuessesAbove) {
        return new PossibilitiesToRight(possibleGuessesAbove);
    }
}
