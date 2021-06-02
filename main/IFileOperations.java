package main;

import javax.swing.*;
import java.io.File;
import java.nio.file.Path;

public interface IFileOperations {

    String newFile(JFrame parent, String fileName);
    File openFile(JFrame parent);
    boolean saveFile(Path filePath, String textToWrite);
}
