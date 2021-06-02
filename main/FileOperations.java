package main;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;

public class FileOperations implements IFileOperations {


    @Override
    public String newFile(JFrame parent, String fileName) {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Select a directory");
        //fc.setAcceptAllFileFilterUsed(false);
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        fc.addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if(f.isDirectory())
                    return true;
                return false;
            }

            @Override
            public String getDescription() {
                return "Directory";
            }
        });

        int choice = fc.showOpenDialog(parent);

        if (choice == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String path = file.getPath() + "/" + fileName;

            if (new File(path).exists()) {
                JOptionPane.showMessageDialog(parent, "A file by that name already exists!");
                return null;
            }

            saveFile(Path.of(path), "");

            return path;
        }

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
        try {
            PrintWriter writer = new PrintWriter(filePath.toString());
            writer.write(textToWrite);
            writer.close();

            return true;
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        return false;
    }
}
