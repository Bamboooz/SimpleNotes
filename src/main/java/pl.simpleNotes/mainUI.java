package pl.simpleNotes;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static pl.simpleNotes.makeNewNote.*;
import static pl.simpleNotes.noteGUI.*;

class mainUI {
    public static void main(String[] args) {

        JFrame frame = new JFrame("| Simple Notes |");

        Image icon = Toolkit.getDefaultToolkit().getImage(Objects.requireNonNull(mainUI.class.getClassLoader().getResource("Graphics/notepadIcon.png")));
        frame.setIconImage(icon);

        frame.setLayout(new BorderLayout());
        frame.setContentPane(new JLabel(new ImageIcon(Objects.requireNonNull(mainUI.class.getClassLoader().getResource("Graphics/noteBackground.png")))));

        frame.pack();
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setTitle("| Simple Notes |");
        frame.setSize(348, 549);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Color bgColor = new Color(238, 238, 238);

        JLabel newNoteText = new JLabel();
        frame.add(newNoteText);
        newNoteText.setVisible(true);
        ImageIcon newNoteIcon = new ImageIcon(Objects.requireNonNull(mainUI.class.getClassLoader().getResource("Graphics/newNote.png")));
        newNoteText.setIcon(newNoteIcon);
        newNoteText.setBounds(50, 50, 296, 146);

        JButton newNote = new JButton("+");
        frame.add(newNote);
        newNote.setVisible(true);
        newNote.setBackground(bgColor);
        newNote.setBounds(64, 220, 200, 200);
        newNote.setBorderPainted(true);

        newNote.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                createNewNote();
                frame.setVisible(false);
            }
        });

    }
}