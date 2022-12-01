package test;

import java.util.ArrayList;

public class Board {
    private static Board SingleBoard;
    private final Place[][] Places = new Place[15][15];
    ArrayList<Word> ExistingWords = new ArrayList<Word>();


    //ctor
    private Board() {
        for (int i = 0; i < 15; i++)
            for (int j = 0; j < 15; j++) {
                Places[i][j] = new Place(initBonus(i, j), initIsWord(i, j));
            }
    }

    //inits for the ctor
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
                {false, false, false, true, false, false, false, false, false, false, false, true, false, false, false},
                {false, false, false, false, true, false, false, false, false, false, true, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {true, false, false, false, false, false, false, true, false, false, false, false, false, false, true},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, true, false, false, false, false, false, true, false, false, false, false},
                {false, false, false, true, false, false, false, false, false, false, false, true, false, false, false},
                {false, false, true, false, false, false, false, false, false, false, false, false, true, false, false},
                {false, true, false, false, false, false, false, false, false, false, false, false, false, true, false},
                {true, false, false, false, false, false, false, true, false, false, false, false, false, false, true},
        };

        return mat[i][j];
    }

    //getter
    public static Board getBoard() {
        if (SingleBoard == null) {
            SingleBoard = new Board();
        }
        return SingleBoard;
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
            return x + word.getLength() - 1 < 15 && x <= 7 && x + word.getLength() - 1 >= 7;
        }
        return false;
    }

    public int tryPlaceWord(Word word) {
        if (boardLegal(word) && dictionaryLegal(word)) {
            PlaceWord(word);
            ArrayList<Word> newWords = filterWords(getWords(word));
            int score = getScore(newWords);
            addNewWords(newWords);
            return score;
        }
        return 0;
    }

    private ArrayList<Word> filterWords(ArrayList<Word> words) {
        for (Word word : words) {
            if (!dictionaryLegal(word) || SingleBoard.ExistingWords.contains(word)) {
                words.remove(word);
            }

        }
        return words;
    }

    private Tile[][] getTiles() {
        Tile[][] tiles = new Tile[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                tiles[i][j] = SingleBoard.Places[i][j].tile;
            }
        }
        return tiles;
    }

    private ArrayList<Word> getWords(Word word) {
        ArrayList<Word> newWords = new ArrayList<>();
        newWords.add(word);

        return newWords;
    }


    private boolean isStarNotEmpty() {
        return SingleBoard.Places[7][7].tile != null;
    }

    private boolean dictionaryLegal(Word word) {
        return true;
    }

    private int getScore(ArrayList<Word> words) {
        int wordsSum = 0;
        for (Word word : words) {
            int x, y;
            int sum = 0, mul = 1;
            if (boardLegal(word)) {
                for (int i = 0; i < word.getLength(); i++) {
                    if (word.isVertical()) {
                        x = i;
                        y = 0;
                    } else {
                        x = 0;
                        y = i;
                    }
                    if (word.Tiles[i] != null) {
                        if (Places[word.row + x][word.cols + y].isWordBonus) {
                            mul *= Places[word.row + x][word.cols + y].bonus;
                            sum += word.Tiles[i].getScore();
                        } else if (HasBeenUsed(word.row + x, word.cols + y)) {
                            sum += word.Tiles[i].getScore() * Places[word.row + i][word.cols].bonus;
                        } else {
                            sum += word.Tiles[i].getScore();
                        }
                    } else {
                        if (Places[word.row + x][word.cols + y].tile != null) {
                            if (Places[word.row + x][word.cols + y].isWordBonus) {
                                if (HasBeenUsed(word.row + x, word.cols + y)) {
                                    mul *= Places[word.row + x][word.cols + y].bonus;
                                }
                                sum += Places[word.row + x][word.cols + y].tile.getScore();
                            } else if (HasBeenUsed(word.row + x, word.cols + y)) {
                                sum += Places[word.row + x][word.cols + y].tile.getScore() * Places[word.row + x][word.cols + y].bonus;
                            } else {
                                sum += Places[word.row + x][word.cols + y].tile.getScore();
                            }
                        }
                    }
//                    System.out.println(sum * mul);
                }
                wordsSum += sum * mul;
            }

        }
        return wordsSum;
    }

    private boolean HasBeenUsed(int i, int j) {
        return SingleBoard.Places[i][j].tile == null;
    }

    private boolean isWordNextToTile(Word word) {
        for (int i = 0; i < word.getLength(); i++) {

            if (word.isVertical()) {
                if (SingleBoard.Places[word.row + i][word.cols + 1].tile != null || SingleBoard.Places[word.row + i][word.cols - 1].tile != null) {
                    return true;
                }
            } else {
                if (SingleBoard.Places[word.row + 1][word.cols + i].tile != null || SingleBoard.Places[word.row - 1][word.cols + i].tile != null) {
                    return true;
                }
            }


        }
        if (word.isVertical()) {
            if (SingleBoard.Places[word.row - 1][word.cols].tile != null || SingleBoard.Places[word.row + word.getLength() - 1][word.cols].tile != null) {
                return true;
            }
        } else {
            if (SingleBoard.Places[word.row][word.cols - 1].tile != null || SingleBoard.Places[word.row][word.cols + word.getLength() - 1].tile != null) {
                return true;
            }
        }
        return false;
    }

    private boolean isPlaceFree(int i, int j) {
        return SingleBoard.Places[i][j].tile == null;
    }

    private void PlaceWord(Word word) {
        for (int i = 0; i < word.getLength(); i++) {
            if (word.getTiles()[i] != null) {
                if (word.isVertical()) {
                    SingleBoard.Places[word.row + i][word.cols].tile = word.getTiles()[i];
                } else
                    SingleBoard.Places[word.row][word.cols + i].tile = word.getTiles()[i];
            }

        }
    }

    private void addNewWords(ArrayList<Word> words) {
        SingleBoard.ExistingWords.addAll(words);
    }

    private boolean isConverging(Word w1, Word w2) {
        return false;
    }

    public void printBoard() {
        System.out.println("Letters:");
        System.out.println("-----------------------------------");
        this.printBoardLetters();
        System.out.println("Scores:");
        System.out.println("-----------------------------------");
        this.printBoardScores();
        System.out.println("Bonuses:");
        System.out.println("-----------------------------------");
        this.printBoardBonus();
        System.out.println("-----------------------------------");
    }

    public void printBoardLetters() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (SingleBoard.Places[i][j].tile != null) {
                    System.out.print(SingleBoard.Places[i][j].tile.letter + " ");
                } else
                    System.out.print("  ");

            }
            System.out.println();
        }

    }

    public void printBoardScores() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (SingleBoard.Places[i][j].tile != null) {
                    System.out.print(SingleBoard.Places[i][j].tile.getScore() + " ");
                } else
                    System.out.print("  ");

            }
            System.out.println();
        }

    }

    public void printBoardBonus() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (SingleBoard.Places[i][j].tile != null) {
                    System.out.print(SingleBoard.Places[i][j].bonus + " ");
                } else
                    System.out.print("  ");

            }
            System.out.println();
        }

    }

    private static class Place {
        private Tile tile;
        public final int bonus;
        public final boolean isWordBonus;

        //ctor
        public Place(int bonus, boolean isWordBonus) {
            this.tile = null;
            this.bonus = bonus;
            this.isWordBonus = isWordBonus;
        }

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


    }

}
