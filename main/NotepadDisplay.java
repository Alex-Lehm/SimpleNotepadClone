package main;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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
    private final Color enabledTextAreaColor = Color.WHITE;

    private Path CURRENT_FILE_PATH;

    FileOperations fileOps = new FileOperations();

    public NotepadDisplay() {
        JFrame frame = NotepadDisplay.this;
        frame.add(panel1);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        lblTitle.setText(noFileText);
        textArea1.setText(noFileText);

        setUpTextAreaListener();
        setUpOpenButton();
        setUpSaveButton();

        btnSaveFile.setEnabled(false);
        textArea1.setEnabled(false);
        textArea1.setBackground(disabledTextAreaColor);
        textArea1.setDisabledTextColor(Color.BLACK);
    }

    private void setUpTextAreaListener() {
        textArea1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(lblTitle.getText().equals(noFileText))
                    return;

                btnSaveFile.setEnabled(true);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                btnSaveFile.setEnabled(true);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                btnSaveFile.setEnabled(true);
            }
        });
    }

    private void setUpOpenButton() {
        btnOpenFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = fileOps.openFile(NotepadDisplay.this);
                openFileGUI(file);
            }
        });
    }

    private void setUpSaveButton() {
        btnSaveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFileGUI();
            }
        });
    }

    private void openFileGUI(File file) {
        if (file == null) {
            JOptionPane.showMessageDialog(NotepadDisplay.this, "Operation was unsuccessful");
            return;
        }

        CURRENT_FILE_PATH = file.toPath();

        lblTitle.setText(file.getName());

        textArea1.setText("");

        try {
            List lines = Files.readAllLines(CURRENT_FILE_PATH);

            for(Object line : lines) {
                textArea1.append((String) line + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!textArea1.isEnabled()) {
            enableTextAndSaveButton();
        }
    }

    private void saveFileGUI() {
        if (CURRENT_FILE_PATH == null) {
            JOptionPane.showMessageDialog(NotepadDisplay.this, "No file open. Please select 'New' or 'Open'.");
            return;
        }

        String text = textArea1.getText();

        fileOps.saveFile(CURRENT_FILE_PATH, text);

        JOptionPane.showMessageDialog(NotepadDisplay.this, "Saved successfully");

        if(!textArea1.isEnabled()) {
            enableTextAndSaveButton();
        }

        btnSaveFile.setEnabled(false);
    }

    private void enableTextAndSaveButton() {
        btnSaveFile.setEnabled(true);
        textArea1.setEnabled(true);
        textArea1.setBackground(enabledTextAreaColor);
    }
}
