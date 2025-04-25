public class Cell {
    private int row;
    private int col;
    private boolean hasShip;
    private boolean isHit;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.hasShip = false;
        this.isHit = false;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean hasShip() {
        return hasShip;
    }

    public void placeShip() {
        this.hasShip = true;
    }

    public boolean isHit() {
        return isHit;
    }

    public void hit() {
        this.isHit = true;
    }
}