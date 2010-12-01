// Copyright (c) 2010 Ivan Moore and Mick Killianey.
// All rights reserved.  See the LICENSE file for details.
package com.oocode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.oocode.Square.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BattleshipSolverTest {
    //"6x6 example from conceptis",
    private static final Board BOARD1 = new Board(new RowClues(4, 0, 2, 1, 2, 1), new ColumnClues(1, 0, 4, 0, 3, 2), new Ships(3, 2, 2, 1, 1, 1)).with(2, 2, Water);
    private static final String SOLUTION1 = "*~*~**\n" +
            "~~~~~~\n" +
            "~~~~**\n" +
            "~~*~~~\n" +
            "~~*~*~\n" +
            "~~*~~~\n";

    //"8x8 easy from conceptis",
    private static final Board BOARD2 = new Board(new RowClues(2, 4, 2, 3, 2, 1, 4, 2), new ColumnClues(5, 0, 5, 1, 2, 1, 2, 4), new Ships(4, 3, 3, 2, 2, 2, 1, 1, 1, 1)).with(0, 2, Water).with(4, 6, Top);
    private static final String S2 = "*~~~~~~*\n" +
            "*~*~~*~*\n" +
            "~~*~~~~*\n" +
            "*~*~*~~~\n" +
            "~~*~~~^~\n" +
            "~~~~~~*~\n" +
            "*~***~~~\n" +
            "*~~~~~~*\n";

    //"8x8 difficult from conceptis",
    private static final Board BOARD3 = new Board(new RowClues(1, 3, 1, 4, 2, 4, 0, 5), new ColumnClues(4, 3, 2, 1, 4, 0, 5, 1), new Ships(4, 3, 3, 2, 2, 2, 1, 1, 1, 1)).with(7, 2, Single);
    private static final String S3 = "~~~*~~~~\n" +
            "**~~~~*~\n" +
            "~~~~~~*~\n" +
            "**~~*~*~\n" +
            "~~~~*~*~\n" +
            "***~*~~~\n" +
            "~~~~~~~~\n" +
            "*~o~*~**\n";

    //"10x10 easy from conceptis",
    private static final Board BOARD4 = new Board(new RowClues(3, 2, 3, 3, 1, 1, 2, 1, 3, 1), new ColumnClues(4, 0, 3, 1, 2, 2, 1, 2, 1, 4), new Ships(4, 3, 3, 2, 2, 2, 1, 1, 1, 1)).with(0, 2, Water).with(3, 7, Centre).with(5, 4, Top);
    private static final String S4 = "*~~~~*~*~~\n" +
            "*~~~~*~~~~\n" +
            "*~**~~~~~~\n" +
            "~~~~~~*#*~\n" +
            "~~*~~~~~~~\n" +
            "~~~~^~~~~~\n" +
            "~~~~*~~~~*\n" +
            "~~~~~~~~~*\n" +
            "*~*~~~~~~*\n" +
            "~~~~~~~~~*\n";

    //"GAMES World of Puzzles, Nov 2009, 1-Seaman",
    private static final Board BOARD5 = new Board(new RowClues(4, 2, 1, 1, 1, 1, 1, 3, 5, 1), new ColumnClues(0, 7, 0, 5, 0, 3, 0, 2, 0, 3), new Ships(4, 3, 3, 2, 2, 2, 1, 1, 1, 1)).with(4, 9, Top).with(6, 1, Water);
    private static final String S5 = "~*~*~*~*~~\n" +
            "~*~*~~~~~~\n" +
            "~*~~~~~~~~\n" +
            "~*~~~~~~~~\n" +
            "~~~~~~~~~^\n" +
            "~~~~~~~~~*\n" +
            "~~~*~~~~~~\n" +
            "~*~*~*~~~~\n" +
            "~*~*~*~*~*\n" +
            "~*~~~~~~~~\n";

    //"GAMES World of Puzzles, Nov 2009, 2-Petty Officer",
    private static final Board BOARD6 = new Board(new RowClues(0, 2, 4, 3, 2, 2, 3, 1, 2, 1), new ColumnClues(3, 0, 0, 4, 0, 3, 2, 4, 0, 4), new Ships(4, 3, 3, 2, 2, 2, 1, 1, 1, 1)).with(4, 3, Centre).with(4, 5, Centre).with(7, 0, Water);
    private static final String S6 = "~~~~~~~~~~\n" +
            "~~~~~~~*~*\n" +
            "*~~*~~~*~*\n" +
            "~~~*~*~*~~\n" +
            "~~~#~#~~~~\n" +
            "~~~*~*~~~~\n" +
            "*~~~~~~*~*\n" +
            "~~~~~~~~~*\n" +
            "*~~~~~*~~~\n" +
            "~~~~~~*~~~\n";

    //"GAMES World of Puzzles, Nov 2009, 3-Ensign",
    private static final Board BOARD7 = new Board(new RowClues(2, 1, 1, 1, 0, 1, 0, 6, 2, 6), new ColumnClues(0, 5, 0, 5, 0, 2, 2, 2, 2, 2), new Ships(4, 3, 3, 2, 2, 2, 1, 1, 1, 1)).with(0, 1, Top).with(2, 3, Water).with(5, 5, Single).with(7, 5, Water);
    private static final String S7 = "~^~*~~~~~~\n" +
            "~*~~~~~~~~\n" +
            "~~~~~~~*~~\n" +
            "~~~*~~~~~~\n" +
            "~~~~~~~~~~\n" +
            "~~~~~o~~~~\n" +
            "~~~~~~~~~~\n" +
            "~*~*~~****\n" +
            "~*~*~~~~~~\n" +
            "~*~*~**~**\n";

    //"GAMES World of Puzzles, Nov 2009, 4-Captain",
    private static final Board BOARD8 = new Board(new RowClues(0, 2, 2, 5, 1, 1, 6, 1, 2, 0), new ColumnClues(5, 0, 3, 1, 1, 3, 1, 2, 1, 3), new Ships(4, 3, 3, 2, 2, 2, 1, 1, 1, 1)).with(2, 0, Bottom).with(2, 9, Centre).with(8, 4, Single);
    private static final String S8 = "~~~~~~~~~~\n" +
            "*~~~~~~~~*\n" +
            "v~~~~~~~~#\n" +
            "~~**~*~*~*\n" +
            "~~~~~*~~~~\n" +
            "*~~~~~~~~~\n" +
            "*~*~~****~\n" +
            "*~~~~~~~~~\n" +
            "~~*~o~~~~~\n" +
            "~~~~~~~~~~\n";

    //"GAMES World of Puzzles, Nov 2009, 5-Commodore",
    private static final Board BOARD9 = new Board(new RowClues(4, 2, 1, 0, 2, 2, 2, 4, 0, 3), new ColumnClues(1, 0, 5, 0, 4, 2, 3, 2, 0, 3), new Ships(4, 3, 3, 2, 2, 2, 1, 1, 1, 1)).with(0, 5, Left).with(0, 9, Single).with(5, 9, Bottom);
    private static final String S9 = "~~~~~<**~o\n" +
            "*~*~~~~~~~\n" +
            "~~~~*~~~~~\n" +
            "~~~~~~~~~~\n" +
            "~~*~~~~~~*\n" +
            "~~*~~~~~~v\n" +
            "~~*~*~~~~~\n" +
            "~~*~*~**~~\n" +
            "~~~~~~~~~~\n" +
            "~~~~***~~~\n";

    //"GAMES World of Puzzles, Nov 2009, 6-Admiral",
    private static final Board BOARD10 = new Board(new RowClues(2, 1, 4, 3, 3, 2, 3, 1, 1, 0), new ColumnClues(4, 0, 1, 1, 2, 4, 0, 3, 0, 5), new Ships(4, 3, 3, 2, 2, 2, 1, 1, 1, 1)).with(3, 0, Bottom).with(2, 3, Centre).with(5, 9, Water);
    private static final String S10 = "~~~~*~~~~*\n" +
            "~~~~~~~~~*\n" +
            "*~*#*~~~~~\n" +
            "v~~~~~~*~*\n" +
            "~~~~~*~*~*\n" +
            "~~~~~*~*~~\n" +
            "*~~~~*~~~*\n" +
            "~~~~~*~~~~\n" +
            "*~~~~~~~~~\n" +
            "~~~~~~~~~~\n";

    @Test
    public void smallBoard() {
        check(BOARD1, SOLUTION1);
    }

    @Test
    public void mediumBoard() {
        check(BOARD2, S2);
    }

    @Test
    public void mediumDifficultBoard() {
        check(BOARD3, S3);
    }

    @Test
    public void largeBoard() {
        check(BOARD4, S4);
    }

    @Test
    public void Seaman() {
        check(BOARD5, S5);
    }

    @Test
    public void PettyOfficer() {
        check(BOARD6, S6);
    }

    @Test
    public void Ensign() {
        check(BOARD7, S7);
    }

    @Test
    public void Captain() {
        check(BOARD8, S8);
    }

    @Test
    public void Commodore_64() {
        check(BOARD9, S9);
    }

    @Test
    public void Admiral() {
        check(BOARD10, S10);
    }

    @Test
    public void canSplitBoardIntoShips() {
        Board board = board(
                "~~~~~<>~~#\n" +
                        "~~<>~~~~~~\n" +
                        "~~~~~#~#~o\n" +
                        "^~o~~~~#~~\n" +
                        "v~~~~~~~~~\n" +
                        "~~~~^~~~~~\n" +
                        "~~~~v~~~~o\n" +
                        "~~o~~~~~~~\n" +
                        "^~~~~~~~<>\n" +
                        "v~~~~~~~~~\n");

        List<Ship> expectedShips = ships("<>", "#", "<>", "#", "##", "o", "^v", "o", "^v", "o", "o", "^v", "<>");
        assertEquals(expectedShips, board.ships());
    }

    private List<Ship> ships(String... ships) {
        List<Ship> result = new ArrayList<Ship>();
        for (String ship : ships) {
            result.add(ship(ship));
        }
        return result;
    }

    private Ship ship(String ship) {
        List<Square> squares = new ArrayList<Square>();
        for (int i = 0; i < ship.length(); i++) {
            char c = ship.charAt(i);
            squares.add(Square.from(c));
        }
        return Ship.shipFromSquares(squares);
    }

    @Test
    public void possibilitiesAreAsExpectedBasedOnNearNeighbours1() {
        Board board = new Board(new RowClues(0, 0), new ColumnClues(0, 0), new Ships()); //clues don't matter for this
        Set<Square> squareSet = board.allPossibilitiesBasedOnNearNeighbours(0, 1);
        assertEquals(asSet(GuessedShip, Water), squareSet);
    }

    @Test
    public void possibilitiesAreAsExpectedBasedOnNearNeighbours2() {
        Board board = new Board(new RowClues(0, 0), new ColumnClues(0, 0), new Ships()).with(0, 0, Left); //clues don't matter for this
        System.out.println(board);
        Set<Square> squareSet = board.allPossibilitiesBasedOnNearNeighbours(0, 1);
        assertEquals(asSet(GuessedShip), squareSet);
    }

    @Test
    public void possibilitiesAreAsExpectedBasedOnNearNeighbours3() {
        Board board = new Board(new RowClues(0, 0), new ColumnClues(0, 0), new Ships()).with(0, 0, Single); //clues don't matter for this
        System.out.println(board);
        Set<Square> squareSet = board.allPossibilitiesBasedOnNearNeighbours(0, 1);
        assertEquals(asSet(Water), squareSet);
    }

    @Test
    public void possibilitiesAreAsExpectedBasedOnNearNeighbours4() {
        Board board = board(
                "^~^~^?\n" +
                        "??????\n" +
                        "??~???\n" +
                        "??????\n" +
                        "??????\n" +
                        "??????");
        System.out.println("board = " + board);
        Set<Square> squareSet = board.allPossibilitiesBasedOnNearNeighbours(0, 5);
        assertEquals(asSet(Water), squareSet);
    }

    private Board board(String stringVersion) {
        String[] rows = stringVersion.split("\n");
        Board board = new Board(new RowClues(new int[rows.length]), new ColumnClues(new int[rows[0].length()]), new Ships());
        for (int r = 0; r < rows.length; r++) {
            String row = rows[r];
            for (int c = 0; c < row.length(); c++) {
                char square = row.charAt(c);
                board = board.with(r, c, Square.from(square));
            }
        }
        return board;
    }

    private Set<Square> asSet(Square... squares) {
        return new HashSet<Square>(asList(squares));
    }

    private void check(Board board, String solution) {
        System.out.println(board);
        long start = System.currentTimeMillis();
        Board solvedBoard = board.solved();
        System.out.println("Took " + (System.currentTimeMillis() - start) + "ms");
        assertNotNull("should have been solved", solvedBoard);
        assertEquals(solution, solvedBoard.toString());
    }
}
