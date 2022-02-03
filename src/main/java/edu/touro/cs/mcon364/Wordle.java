package edu.touro.cs.mcon364;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Wordle extends JFrame {
    private static final String ANSWER = "SUPER";

    Wordle() {
        super("My 2nd App 1.1"); // must be first line

        this.setSize(500, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel canvasPanel = new JPanel();
        this.add(canvasPanel, BorderLayout.CENTER);

        canvasPanel.setLayout(new GridLayout(6, 5, 4, 4));

        // Initialize virtual game board
        JTextField[][] cells = new JTextField[6][5];

        // Populate the GUI and virtual game boards
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                JTextField tf = new JTextField();
                // Limit each cell to one character
                tf.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        if (tf.getText().length() >= 1) e.consume();
                    }
                });
                // Add the cell to the virtual game board
                cells[i][j] = tf;
                // Add the cell to the GUI game board
                canvasPanel.add(tf);
            }
        }

        // Enter Button
        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(e -> {
            StringBuilder entry = new StringBuilder(5);
            for (JTextField cell : cells[0]) {
                entry.append(cell.getText().charAt(0));
            }
            boolean correct = entry.toString().equalsIgnoreCase(ANSWER);
            for (JTextField cell : cells[0]) {
                cell.setBackground(correct ? Color.BLUE : UIManager.getColor("Panel.background"));
            }
        });
        this.add(enterButton, BorderLayout.SOUTH);

        this.setVisible(true);
    }
}
