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
        return null;
    }

    @Override
    public boolean saveFile(Path filePath, String textToWrite) {
        return false;
    }
}
