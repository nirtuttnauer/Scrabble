package test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;

public class Board {
    //
    private Tile[][] scrabbleBoard;
    private static HashSet<String> dict;
    private static HashMap<String, String> boardScores;
    //ctor
    public Board() {
        this.initBoard();
        this.initBoardScores();

        try { this.initDict();  }
        catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException: " + e);
        }
    }

    public static Board getBoard() {
        return Board;
    }

    private void initBoard() {
        this.scrabbleBoard = new Tile[15][15];
        for (int i=0; i<15; i++) {
            for (int j=0; j<15; j++) {
                this.scrabbleBoard[i][j].set_letter(' ');
            }
        }
    }

    private void initDict() throws FileNotFoundException {

    }

    private void initBoardScores() {

    }


    public boolean boardLegal(Word w0) {
    }

    public int tryPlaceWord(Word bit) {
    }
}
