import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private static final int SIZE = 10;
    private JButton[][] buttons = new JButton[SIZE][SIZE];
    private Gameboard gameBoard;
    private GameLogic gameLogic;

    public Main() {
        gameBoard = new Gameboard();
        gameLogic = new GameLogic(gameBoard);

        setTitle("Amiral Battı");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE));

        // 10x10 tahtayı ve butonları oluştur
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                JButton button = new JButton();
                button.setBackground(Color.CYAN);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                int r = row;
                int c = col;

                // Hücreye tıklama olayı
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String result = gameLogic.fire(r, c);
                        button.setEnabled(false);  // Tıklanamaz yap
                        if (result.equals("Gemi vuruldu!")) {
                            button.setBackground(Color.RED);  // Vurulduğunda kırmızı
                        } else {
                            button.setBackground(Color.GRAY);  // Iskalandığında gri
                        }
                        JOptionPane.showMessageDialog(null, result);  // Sonucu mesaj olarak göster

                        // Oyun bitmişse, kazananı bildir
                        if (gameLogic.isGameOver()) {
                            JOptionPane.showMessageDialog(null, "Oyunu Kazandınız!");
                        }
                    }
                });

                buttons[row][col] = button;
                add(button);
            }
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}

