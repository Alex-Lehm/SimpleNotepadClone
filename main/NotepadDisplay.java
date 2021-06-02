package main;

import javax.swing.*;
import java.awt.*;

public class NotepadDisplay extends JFrame {
    private JPanel panel1;
    private JPanel menuBarPanel;
    private JPanel buttonsPanel;
    private JButton btnNewWindow;
    private JButton btnNewFile;
    private JButton btnSaveFile;
    private JButton btnOpenFile;
    private JPanel mainPanel;
    private JTextArea textArea1;
    private JPanel titlepanel;
    private JLabel lblTitle;

    private final String noFileText = "No file selected.";

    private final Color disabledTextAreaColor = Color.LIGHT_GRAY;

    public NotepadDisplay() {
        JFrame frame = NotepadDisplay.this;
        frame.add(panel1);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        lblTitle.setText(noFileText);
        textArea1.setText(noFileText);

        btnSaveFile.setEnabled(false);
        textArea1.setEnabled(false);
        textArea1.setBackground(disabledTextAreaColor);
        textArea1.setDisabledTextColor(Color.BLACK);
    }
}
