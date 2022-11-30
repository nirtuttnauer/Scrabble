package test;

public class Board {
    private static Board SingleBoard;

    private Place[][] Places = new Place[15][15];
    private boolean[][] HasBeenUsed = new boolean[15][15];

    public static Board getBoard() {
        if (SingleBoard == null) {
            SingleBoard = new Board();
        }
        return SingleBoard;
    }

    private Board() {
        for (int i = 0; i < 15; i++)
            for (int j = 0; j < 15; j++) {
                Places[i][j] = new Place(initBonus(i, j), initIsWord(i, j));
                HasBeenUsed[i][j] = false;
            }
    }

    Tile[][] getTiles() {
        Tile[][] tiles = new Tile[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                tiles[i][j] = SingleBoard.Places[i][j].tile;
            }
        }
        return tiles;
    }

    private int initBonus(int i, int j) {
        int[][] matrix = {
                {3, 1, 1, 2, 1, 1, 1, 3, 1, 1, 1, 2, 1, 1, 3},
                {1, 2, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 2, 1},
                {1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1},
                {2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2},
                {1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1},
                {1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1},
                {1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1},
                {3, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 3},
                {1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1},
                {1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1},
                {1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1},
                {2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2},
                {1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1},
                {1, 2, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 2, 1},
                {3, 1, 1, 2, 1, 1, 1, 3, 1, 1, 1, 2, 1, 1, 3}};
        return matrix[i][j];
    }

    private boolean initIsWord(int i, int j) {
        boolean[][] mat = {
                {true, false, false, false, false, false, false, true, false, false, false, false, false, false, true},
                {false, true, false, false, false, false, false, false, false, false, false, false, false, true, false},
                {false, false, true, false, false, false, false, false, false, false, false, false, true, false, false},
                {false, false, false, true, false, false, false, false, false, false, false, false, true, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, true, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, true, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, true, false, false},
                {true, false, false, false, false, false, false, true, false, false, false, false, true, false, true},
                {false, false, false, false, false, false, false, false, false, false, false, false, true, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, true, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, true, false, false},
                {false, false, false, true, false, false, false, false, false, false, false, false, true, false, false},
                {false, false, true, false, false, false, false, false, false, false, false, false, true, false, false},
                {false, true, false, false, false, false, false, false, false, false, false, false, false, true, false},
                {true, false, false, false, false, false, false, true, false, false, false, false, false, false, true},
        };

        return mat[i][j];
    }

    private void PlaceWord(Word word){
        for (int i = 0; i< word.getLength() ; i++)
        {
            if (word.getTiles()[i] != null){
                if (word.isVertical()){
                    SingleBoard.Places[word.row+i][word.cols].tile = word.getTiles()[i];
                }
                else
                    SingleBoard.Places[word.row][word.cols+i].tile = word.getTiles()[i];
            }

        }
    }
    public int tryPlaceWord(Word word) {
//        if (boardLegal(word)) {
            PlaceWord(word);
            return getScore(word);
//        }
//        return 0;
    }

    public boolean boardLegal(Word word) {
        int x = (word.vertical) ? word.row : word.cols;
        int xtg = (word.vertical) ? word.cols : word.row;
        if ((xtg < 0 || x < 0) || (xtg > 15 || x > 15)) {
            return false;
        }
        if (isStarNotEmpty()) {
            return x + word.getLength() < 15;
        }
        if (xtg == 7) {
            return x + word.getLength() < 15 && x <= 7 && x + word.getLength() >= 7;
        }
        return false;
    }

    boolean isStarNotEmpty() {
        return SingleBoard.Places[7][7].tile != null;
    }

    private class Place {
        public Tile tile;
        public final int bonus;
        public final boolean isWordBonus;

        public Tile getTile() {
            return tile;
        }

        public void setTile(Tile tile) {
            this.tile = tile;
        }

        public int getBonus() {
            return bonus;
        }

        public boolean isWordBonus() {
            return isWordBonus;
        }

        public Place(int bonus, boolean isWordBonus) {
            this.tile = null;
            this.bonus = bonus;
            this.isWordBonus = isWordBonus;
        }
    }

    boolean dictionaryLegal(Word word) {
        return true;
    }

    int getScore(Word word) {
        int sum = 0, mul = 1;
        if (boardLegal(word)) {
            for (int i = 0; i < word.getLength(); i++) {
                if (word.Tiles[i] != null) {
                    if (word.vertical) {
                        if (Places[word.row + i][word.cols].isWordBonus) {
                            mul *= Places[word.row + i][word.cols].bonus;
                            HasBeenUsed[word.row + i][word.cols] = true;
                            sum += word.Tiles[i].getScore();
                        } else {
                            sum += word.Tiles[i].getScore() * Places[word.row + i][word.cols].bonus;
                            HasBeenUsed[word.row + i][word.cols] = true;
                        }
                    } else {
                        if (Places[word.row][word.cols + i].isWordBonus) {
                            mul *= Places[word.row][word.cols + i].bonus;
                            HasBeenUsed[word.row][word.cols + i] = true;
                            sum += word.Tiles[i].getScore();
                        } else {
                            sum += word.Tiles[i].getScore() * Places[word.row][word.cols + i].bonus;
                            HasBeenUsed[word.row][word.cols + i] = true;
                        }
                    }
                } else {

                    if (word.vertical) {
                        if (Places[word.row + i][word.cols].tile != null) {
                            if (Places[word.row + i][word.cols].isWordBonus && !HasBeenUsed[word.row + i][word.cols]) {
                                mul *= Places[word.row + i][word.cols].bonus;
                                HasBeenUsed[word.row + i][word.cols] = true;
                                sum += Places[word.row + i][word.cols].tile.getScore();
                            } else if (!HasBeenUsed[word.row + i][word.cols]) {
                                sum += Places[word.row + i][word.cols].tile.getScore() * Places[word.row + i][word.cols].bonus;
                                HasBeenUsed[word.row + i][word.cols] = true;
                            }
                        }

                    } else {
                        if (Places[word.row][word.cols + i].tile != null) {
                            if (Places[word.row][word.cols + i].isWordBonus && !HasBeenUsed[word.row][word.cols + i]) {
                                mul *= Places[word.row][word.cols + i].bonus;
                                HasBeenUsed[word.row][word.cols + i] = true;
                                sum += Places[word.row][word.cols + i].tile.getScore();
                            } else if (!HasBeenUsed[word.row][word.cols + i]){
                                sum += Places[word.row][word.cols + i].tile.getScore() * Places[word.row][word.cols + i].bonus;
                                HasBeenUsed[word.row][word.cols + i] = true;
                            }
                        }
                    }
                }

            }
            return sum * mul;
        }
        return 0;
    }
}
