import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameField extends JPanel {
    public static final int FIELD_SIZE = 450;
    static int linesCount = 3;
    // реализация синглтона
    private static GameField instance = null;
    public final String EMPTY = "*";
    public String[][] cell;
    boolean gameOver = false;
    String gameOverMessage = "";
    int cellSize;
    int x;
    int y;

    // Конструктор приватный, т.к. делаем синглтон
    private GameField() {
        setVisible(false);
        // Считываем координаты клика мышью
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                x = e.getX() / cellSize;
                y = e.getY() / cellSize;
                System.out.println("Mouse clicked on " + e.getX() + " " + e.getY());
                if (!gameOver) {
                    gaming();
                }
            }
        });
    }

    // Получить экземпляр GameField
    public static synchronized GameField getInstance() {
        if (instance == null)
            instance = new GameField();
        return instance;
    }

    // Запуск
    void startNewGame() {
        gameOver = false;
        gameOverMessage = "";
        // Размер одной ячейки
        cellSize = FIELD_SIZE / linesCount;
        cell = new String[linesCount][linesCount];
        repaint();
        for (int i = 0; i < linesCount; i++) {
            for (int j = 0; j < linesCount; j++) {
                cell[i][j] = EMPTY;
            }
        }
        setVisible(true);
    }

    void gaming() {
        User user = new User("X");
        Computer computer = new Computer("O", user.dot);
        if (!gameOver) {
            if (user.makeMove(x, y)) {
                if (user.win()) {
                    System.out.println("User win!");
                    gameOver = true;
                    gameOverMessage = "User win!";
                }
                if (isFieldFull()) {
                    gameOver = true;
                    gameOverMessage = "Draw!";
                }
                repaint();
                if (!gameOver) {
                    computer.makeMove(x, y);
                }
                if (computer.win()) {
                    System.out.println("Computer win!");
                    gameOver = true;
                    gameOverMessage = "Computer win!";
                }
                repaint();
                if (isFieldFull() && !computer.win()) {
                    gameOver = true;
                    gameOverMessage = "Draw!";
                }
            }
        }
    }


    boolean isCellBusy(int x, int y) {
        if (x < 0 || y < 0 || x > linesCount - 1 || y > linesCount - 1) {
            return false;
        }
        return cell[x][y] != EMPTY;
    }


    public boolean isFieldFull() {
        for (int i = 0; i < linesCount; i++) {
            for (int j = 0; j < linesCount; j++) {
                if (cell[i][j] == EMPTY)
                    return false;
            }
        }
        return true;
    }

    // Проверяем одну линию
    public boolean checkLine(int start_x, int start_y, int dx, int dy, String dot) {
        for (int i = 0; i < linesCount; i++) {
            if (cell[start_x + i * dx][start_y + i * dy] != dot)
                return false;
        }
        return true;
    }

    // Проверка победы
    public boolean checkWin(String dot) {
        for (int i = 0; i < linesCount; i++) {
            // проверяем строки
            if (checkLine(i, 0, 0, 1, dot)) return true;
            // проверяем столбцы
            if (checkLine(0, i, 1, 0, dot)) return true;
        }
        // проверяем диагонали
        if (checkLine(0, 0, 1, 1, dot)) return true;
        if (checkLine(0, linesCount - 1, 1, -1, dot)) return true;
        return false;
    }

    // отрисовка всей графики на форме
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Рисуем сетку
        for (int i = 0; i <= this.linesCount; i++) {
            g.drawLine(0, i * this.cellSize, FIELD_SIZE, i * this.cellSize);
            g.drawLine(i * this.cellSize, 0, i * this.cellSize, FIELD_SIZE);
        }
        for (int i = 0; i < linesCount; i++) {
            for (int j = 0; j < linesCount; j++) {
                if (cell[i][j] != EMPTY) {
                    if (cell[i][j] == "X") {
                        // крестик
                        g.setColor(Color.RED);
                        g.drawLine((i * cellSize), (j * cellSize), (i + 1) * cellSize, (j + 1) * cellSize);
                        g.drawLine((i + 1) * cellSize, (j * cellSize), (i * cellSize), (j + 1) * cellSize);
                    }
                    if (cell[i][j] == "O") {
                        // нолик
                        g.setColor(Color.BLUE);
                        g.drawOval((i * cellSize), (j * cellSize), cellSize, cellSize);
                    }
                }
            }
        }

        if (gameOver) {
            // сообщение при завершении
            g.setColor(Color.BLACK);
            g.fillRect(0, FIELD_SIZE / 2, FIELD_SIZE, FIELD_SIZE / 8);
            g.setColor(Color.RED);
            g.setFont(new Font("Tahoma", 10, 40));
            g.drawString(gameOverMessage, 0, 19 * FIELD_SIZE / 32);
        }
    }
}
