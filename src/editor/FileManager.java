package editor;

import javax.swing.*;
import java.io.*;

public class FileManager {
    public static void openFile(TextEditor textEditor, JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                textArea.setText("");
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
            } catch (Exception e) {
                System.out.println("can't read the file");
            }
        }
    }

    public static void saveFile(TextEditor textEditor, JTextArea textArea) {
        if (textEditor.currentFile == null) {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(textArea.getText());
                    textEditor.currentFile = file;
                    System.out.println("file saved");
                } catch (Exception e) {
                    System.out.println("can't save the file");
                }
            } else {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(textEditor.currentFile))) {
                    writer.write(textArea.getText());
                    System.out.println("file saved");
                } catch (Exception e) {
                    System.out.println("can't save the file");
                }
            }
        }
    }

    public static void newFile(TextEditor textEditor, JTextArea textArea) {
        textArea.setText("");
        textEditor.currentFile = null;
        System.out.println("New file created");
    }
}
