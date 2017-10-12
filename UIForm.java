import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIForm extends JFrame {
    public UIForm() {

        setTitle("Tic Tac Toe! Let's play the game!");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 455, 525);
        setResizable(false);


        GameField gameField = GameField.getInstance();
        JPanel buttonPanel = new JPanel(new GridLayout());
        add(gameField, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        JButton bStart = new JButton("Начать новую игру");
        JButton bEnd = new JButton("Закрыть игру");
        buttonPanel.add(bStart);
        buttonPanel.add(bEnd);

        bEnd.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        bStart.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                System.out.println(bStart.getText());
                SettingsForm SettingsForm = new SettingsForm();
            }
        });
        setVisible(true);
    }
}