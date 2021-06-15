package pl.simpleNotes.simpleNotesEngine;

import pl.simpleNotes.simpleNotesUI.mainUI;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static pl.simpleNotes.simpleNotesEngine.makeNewNote.newNoteName;
import static pl.simpleNotes.simpleNotesUI.noteGUI.currentTextGetter;
import static pl.simpleNotes.simpleNotesUI.noteGUI.noteText;

public class simpleNotesUtils {

    public static String replaceWithNeeded = "";

    public static void replaceWith() {

        JFrame frame = new JFrame("| Simple Notes |");

        Image icon = Toolkit.getDefaultToolkit().getImage(Objects.requireNonNull(mainUI.class.getClassLoader().getResource("Graphics/notepadIcon.png")));
        frame.setIconImage(icon);

        frame.pack();
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setTitle("| Simple Notes, Note: " + "'" + newNoteName + "'" + " |");
        frame.setSize(448, 180);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel replaceWithText = new JLabel("Replace Text With:");
        frame.add(replaceWithText);
        replaceWithText.setVisible(true);
        replaceWithText.setBounds(160, 5 , 250, 20);
        replaceWithText.setText("Replace Text With:");

        JLabel replacedTextName = new JLabel("Replace Text:");
        frame.add(replacedTextName);
        replacedTextName.setVisible(true);
        replacedTextName.setBounds(10, 40 , 350, 20);
        replacedTextName.setText("Replace Text:");

        JLabel replacedTextNewName = new JLabel("Text To Replace With:");
        frame.add(replacedTextNewName);
        replacedTextNewName.setVisible(true);
        replacedTextNewName.setBounds(10, 65 , 350, 20);
        replacedTextNewName.setText("Text To Replace With:");

        JTextArea replaceText = new JTextArea();
        frame.add(replaceText);
        replaceText.setVisible(true);
        replaceText.setBounds(92, 40, 322, 20);

        JTextArea replacingText = new JTextArea();
        frame.add(replacingText);
        replacingText.setVisible(true);
        replacingText.setBounds(139, 65, 275, 20);

        String currentText = currentTextGetter;

        JButton replace = new JButton("Replace");
        frame.add(replace);
        replace.setVisible(true);
        replace.setBounds(10, 96 , 405, 30);
        replace.setText("Replace");

        replace.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                CharSequence oldChar1 = replaceText.getText();
                CharSequence newChar1 = replacingText.getText();
                replaceWithNeeded = currentText.replace(oldChar1, newChar1);
                if (!replaceWithNeeded.equals("")) {
                    noteText.setText(replaceWithNeeded);
                }
                frame.setVisible(false);

        }});

    }

}
