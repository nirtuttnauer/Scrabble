package test;

public class Tile {
    public char letter;

    public char get_letter() {
        return letter;
    }

    public void set_letter(char _letter) {
        this.letter = _letter;
    }

    public static class Bag{
    Tile Tiles[52];
    Bag(){

    }

        public static Bag getBag() {

            return Bag;
        }

        public int[] getQuantities() {
        }

        public Tile getRand() {
        }

        public void put(Tile t) {
        }

        public Tile getTile(char $) {
            return null;
        }
    }

}
