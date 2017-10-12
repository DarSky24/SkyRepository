import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsForm extends JFrame {
    public SettingsForm() {
        // разметка формы
        setTitle("Настройки игры");
        setBounds(450, 450, 240, 190);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));


        JLabel jLabelLinesCount = new JLabel("Установите размер поля: ");
        add(jLabelLinesCount);
        JTextField jTextFieldLinesCount = new JTextField();
        jTextFieldLinesCount.setMaximumSize(new Dimension(100, 20));
        add(jTextFieldLinesCount);
        JButton jButtonSetSettings = new JButton("Начать игру!");
        add(jButtonSetSettings);
        setVisible(true);

        jButtonSetSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameField gameField = GameField.getInstance();
                if (jTextFieldLinesCount.getText().isEmpty()) {
                    gameField.linesCount = 3;
                }
                else {
                    try {
                        gameField.linesCount = Integer.parseInt(jTextFieldLinesCount.getText());
                    }
                    catch (NumberFormatException ex) {
                        System.out.println("Необходимо ввести целое число!");
                    }
                }
                // Запускаем игру
                gameField.startNewGame();
                setVisible(false);



            }
        });
    }
}