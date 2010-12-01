// Copyright (c) 2010 Ivan Moore and Mick Killianey.
// All rights reserved.  See the LICENSE file for details.
package com.oocode;

import java.util.Set;

public class Possibilities {
    private final PossibleGuesses possibleGuesses;

    public Possibilities(PossibleGuesses possibleGuesses) {
        this.possibleGuesses = possibleGuesses;
    }

    public Set<Square> canBe() {
        return possibleGuesses.canBe();
    }
}
