import java.util.Random;

public class Computer extends AbstractPlayer {

    GameField gameField;
    String userDot = "";


    public Computer(String dot, String userDot) {
        this.dot = dot;
        this.userDot = userDot;
    }

    @Override
    boolean makeMove(int x, int y) {
        gameField = GameField.getInstance();
        x = -1;
        y = -1;
        boolean computer_win = false;
        boolean user_win = false;

        // Находим выигрышный ход
        for (int i = 0; i < gameField.linesCount; i++) {
            for (int j = 0; j < gameField.linesCount; j++) {
                if (!gameField.isCellBusy(i, j)) {
                    gameField.cell[i][j] = dot;
                    if (gameField.checkWin(dot)) {
                        x = i;
                        y = j;
                        computer_win = true;
                    }
                    gameField.cell[i][j] = gameField.EMPTY;
                }
            }
        }

        // Блокировка хода пользователя, если он побеждает на следующем ходу
        if (!computer_win) {
            for (int i = 0; i < gameField.linesCount; i++) {
                for (int j = 0; j < gameField.linesCount; j++) {
                    if (!gameField.isCellBusy(i, j)) {
                        gameField.cell[i][j] = this.userDot;
                        if (gameField.checkWin(this.userDot)) {
                            x = i;
                            y = j;
                            user_win = true;
                        }
                        gameField.cell[i][j] = gameField.EMPTY;
                    }
                }
            }
        }

        if (!computer_win && !user_win) {
            do {
                Random rnd = new Random();
                x = rnd.nextInt(gameField.linesCount);
                y = rnd.nextInt(gameField.linesCount);
            }
            while (gameField.isCellBusy(x, y));
        }
        gameField.cell[x][y] = dot;
        return true;
    }

    @Override
    boolean win() {
        return gameField.checkWin(this.dot);
    }
}
