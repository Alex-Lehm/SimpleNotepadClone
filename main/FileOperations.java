package main;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.nio.file.Path;

public class FileOperations implements IFileOperations {


    @Override
    public String newFile(JFrame parent, String fileName) {
        return null;
    }

    @Override
    public File openFile(JFrame parent) {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Select a file");

        fc.addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if(!f.isDirectory()) {
                    return f.getName().toLowerCase().endsWith(".txt");
                }

                return true;
            }

            @Override
            public String getDescription() {
                return "Plain text files (.txt)";
            }
        });

        fc.setAcceptAllFileFilterUsed(false);

        int choice = fc.showOpenDialog(parent);

        if(choice == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            System.out.println("Opening " + file.getPath());
            return file;
        }

        return null;
    }

    @Override
    public boolean saveFile(Path filePath, String textToWrite) {
        return false;
    }
}
