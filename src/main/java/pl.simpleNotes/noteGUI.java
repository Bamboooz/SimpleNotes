package pl.simpleNotes;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

import static pl.simpleNotes.makeNewNote.newNoteName;

public class noteGUI {

    public static String fileText;

    public static void note() {
        JFrame frame = new JFrame("| Simple Notes |");

        Image icon = Toolkit.getDefaultToolkit().getImage(Objects.requireNonNull(mainUI.class.getClassLoader().getResource("Graphics/notepadIcon.png")));
        frame.setIconImage(icon);

        frame.pack();
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setTitle("| Simple Notes, Note: " + "'" + newNoteName + "'" + " |");
        frame.setSize(748, 880);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar utilsMenu = new JMenuBar();
        utilsMenu.setVisible(true);
        frame.add(utilsMenu);
        frame.setJMenuBar(utilsMenu);
        JMenu menu = new JMenu("File");
        utilsMenu.add(menu);

        JTextArea noteText = new JTextArea();
        frame.add(noteText);
        noteText.setVisible(true);
        noteText.setBounds(0, 0, 748, 880);

        final JMenuItem saveAs = new JMenuItem("Save As:");
        saveAs.addActionListener((e -> {

                fileText = noteText.getText();
                try {
                    File txtFile = new File(newNoteName);
                    if (txtFile.createNewFile()) {
                        System.out.println("File created: " + txtFile.getName());
                        FileWriter myWriter = new FileWriter(newNoteName);
                        myWriter.write(fileText);
                        myWriter.close();
                    } else {
                        System.out.println("File already exists.");
                    }
                } catch (IOException exept) {
                    System.out.println("An error occurred.");
                    exept.printStackTrace();
                }

        }));

        menu.add(saveAs);

    }

}
