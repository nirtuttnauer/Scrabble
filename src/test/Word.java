package test;

import test.Tile;

import java.util.Arrays;

public class Word {
    test.Tile[] Tiles;
    int row,cols;
    boolean vertical;

    public Word(test.Tile[] tiles, int row, int cols, boolean vertical) {
        Tiles = tiles;
        this.row = row;
        this.cols = cols;
        this.vertical = vertical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return row == word.row && cols == word.cols && vertical == word.vertical && Arrays.equals(Tiles, word.Tiles);
    }

}
