public class User extends AbstractPlayer {

    GameField gameField;

    public User(String dot) {
        this.dot = dot;
    }

    @Override
    boolean makeMove(int x, int y) {
        gameField = GameField.getInstance();
        if (!gameField.isCellBusy(x, y))
        {
            gameField.cell[x][y] = dot;
            return true;
        }
        return false;
    }

    @Override
    boolean win() {
        gameField = GameField.getInstance();
        return gameField.checkWin(this.dot);
    }
}

