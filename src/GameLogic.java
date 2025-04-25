public class GameLogic {
    private Gameboard board;

    public GameLogic(Gameboard board) {
        this.board = board;
    }

    public String fire(int row, int col) {
        Cell cell = board.getCell(row, col);

        if (cell.isHit()) {
            return "Bu hücreye zaten ateş ettiniz!";
        }

        cell.hit(); // Hücreye ateş et
        if (cell.hasShip()) {
            // Eğer gemi varsa, vuruldu
            return "Gemi vuruldu!";
        } else {
            return "Iska! Bu hücrede gemi yok.";
        }
    }

    public boolean isGameOver() {
        return board.isGameOver();
    }
}