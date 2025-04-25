import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Gameboard {
    private static final int SIZE = 10;
    private Cell[][] board;
    private List<Ship> ships;

    public Gameboard() {
        board = new Cell[SIZE][SIZE];
        ships = new ArrayList<>();
        initBoard();
        placeShips(); // Gemileri yerleştir
    }

    private void initBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = new Cell(row, col);
            }
        }
    }

    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    public boolean isGameOver() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

    public List<Ship> getShips() {
        return ships;
    }

    // Gemileri yerleştir
    private void placeShips() {
        // Şimdi 3 farklı boyutta gemi yerleştireceğiz
        placeShip(4); // 1 adet 4 birimlik gemi
        placeShip(3); // 2 adet 3 birimlik gemi
        placeShip(2); // 2 adet 2 birimlik gemi
    }

    // Boyutları verilen gemiyi yerleştir
    private void placeShip(int size) {
        Random random = new Random();
        int placed = 0;

        while (placed < (size == 4 ? 1 : size == 3 ? 2 : 2)) { // 4'lük 1, 3'lük 2, 2'lik 2 adet
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);
            boolean isVertical = random.nextBoolean(); // Yatay mı dikey mi?

            // Eğer gemiyi yerleştirebilirsiniz
            if (canPlaceShip(row, col, size, isVertical)) {
                Ship ship = new Ship();
                for (int i = 0; i < size; i++) {
                    Cell cell;
                    if (isVertical) {
                        cell = board[row + i][col];
                    } else {
                        cell = board[row][col + i];
                    }
                    ship.addCell(cell);
                }
                ships.add(ship);
                placed++;
            }
        }
    }

    // Gemiyi yerleştirmeye uygun mu?
    private boolean canPlaceShip(int row, int col, int size, boolean isVertical) {
        if (isVertical) {
            if (row + size > SIZE) return false; // Tahtanın dışına taşar
            for (int i = 0; i < size; i++) {
                if (board[row + i][col].hasShip()) return false; // Hücrede zaten gemi var
            }
        } else {
            if (col + size > SIZE) return false; // Tahtanın dışına taşar
            for (int i = 0; i < size; i++) {
                if (board[row][col + i].hasShip()) return false; // Hücrede zaten gemi var
            }
        }
        return true;
    }
}

