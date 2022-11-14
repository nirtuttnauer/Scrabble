package test;

import java.util.Objects;

public class Tile {
    //1. make final
    final public char letter;
    final public int score;
    //add to constructor
    private Tile(char letter, int score) {
        this.letter = letter;
        this.score = score;
    }
    //create equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return letter == tile.letter && score == tile.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, score);
    }

    class Bag{
        int[] amountsLetters = {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
        Tile[] Tiles = new Tile[26];
        Tile getRand(){

            //randomly choose a letter from the bag
            // decriment the value from the array
            Tile tile = new Tile();
            return tile;
        }
        void put(){
            //returns to the bag
        }
        int size(){
            int sum = 0;
            for (int amount : amountsLetters){
                sum += amount;
            }
            return sum;
        }

        private Bag() {
        }
        Bag getBag(){
            Bag b = null;
            return b;
        }
    }

}
