package pl.simpleNotes.simpleNotesEngine;

import pl.simpleNotes.simpleNotesUI.mainUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Objects;

import static pl.simpleNotes.simpleNotesUI.noteGUI.*;

public class makeNewNote {

    public static File newNoteName;

    public static void createNewNote() {
        JFrame newNoteFrame = new JFrame("| Simple Notes - Create New Note|");

        Image icon = Toolkit.getDefaultToolkit().getImage(Objects.requireNonNull(mainUI.class.getClassLoader().getResource("Graphics/notepadIcon.png")));
        newNoteFrame.setIconImage(icon);

        newNoteFrame.pack();
        newNoteFrame.setVisible(true);
        newNoteFrame.setLayout(null);
        newNoteFrame.setLocationRelativeTo(null);
        newNoteFrame.setResizable(false);
        newNoteFrame.setTitle("| Simple Notes |");
        newNoteFrame.setSize(348, 225);
        newNoteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField noteNameSpace = new JTextField();
        newNoteFrame.add(noteNameSpace);
        noteNameSpace.setVisible(true);
        noteNameSpace.setBounds(40, 30 ,244, 50);
        noteNameSpace.setText("Enter Note Name Here");

        JButton submit = new JButton();
        newNoteFrame.add(submit);
        submit.setVisible(true);
        submit.setBounds(40, 90, 244, 50);
        submit.setText("Submit");

        submit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newNoteName = new File(((noteNameSpace.getText()) + ".txt"));
                note();
                newNoteFrame.setVisible(false);
            }
        });

    }

}
