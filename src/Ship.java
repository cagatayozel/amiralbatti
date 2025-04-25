import java.util.ArrayList;
import java.util.List;

public class Ship {
    private List<Cell> cells;

    public Ship() {
        this.cells = new ArrayList<>();
    }

    public void addCell(Cell cell) {
        cells.add(cell);
        cell.placeShip();
    }

    public boolean isSunk() {
        for (Cell cell : cells) {
            if (!cell.isHit()) {
                return false;
            }
        }
        return true;
    }

    public List<Cell> getCells() {
        return cells;
    }
}