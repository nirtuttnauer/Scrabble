package test;

import test.*;

import java.util.Arrays;

public class Word {
    Tile[] Tiles;
    int row, cols;
    boolean vertical;

    public Word(Tile[] tiles, int row, int cols, boolean vertical) {
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

    int getLength() {
        return this.Tiles.length;
    }

    public Tile[] getTiles() {
        return Tiles;
    }

    public void setTiles(Tile[] tiles) {
        Tiles = tiles;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public boolean isVertical() {
        return vertical;
    }

    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }
}
