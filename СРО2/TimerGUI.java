import javax.swing.*;
import java.awt.*;

public class TimerGUI extends JFrame {

    private JLabel label;
    private JTextField input;
    private JButton startButton;

    public TimerGUI() {
        setTitle("Таймер");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        label = new JLabel("Уақыт енгіз (сек):");
        input = new JTextField(10);
        startButton = new JButton("Бастау");

        add(label);
        add(input);
        add(startButton);

        startButton.addActionListener(e -> {
            int time = Integer.parseInt(input.getText());
            startTimer(time);
        });

        setVisible(true);
    }

    private void startTimer(int time) {

        Thread thread = new Thread(new Runnable() {
            public void run() {
                int t = time; // 🔥 бөлек айнымалы

                try {
                    while (t >= 0) {

                        int current = t;

                        // UI жаңарту (дұрыс тәсіл)
                        SwingUtilities.invokeLater(() -> {
                            label.setText("Қалған уақыт: " + current + " сек");
                        });

                        Thread.sleep(1000);
                        t--;
                    }

                    SwingUtilities.invokeLater(() -> {
                        label.setText("Уақыт бітті!");
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

    public static void main(String[] args) {
        new TimerGUI();
    }
}