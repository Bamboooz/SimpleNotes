package pl.simpleNotes;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Objects;

import static pl.simpleNotes.makeNewNote.*;
import static pl.simpleNotes.noteGUI.*;

class mainUI {

    public static String runEverything = "";

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

        JButton openNote = new JButton("Open Note");
        frame.add(openNote);
        openNote.setVisible(true);
        openNote.setBackground(bgColor);
        openNote.setBounds(64, 440, 200, 50);
        openNote.setBorderPainted(true);

        newNote.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                createNewNote();
                frame.setVisible(false);
            }
        });

        openNote.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JFileChooser fileChooser=new JFileChooser();
                int a=fileChooser.showOpenDialog(null);
                if(a==JFileChooser.APPROVE_OPTION)
                {
                    File fileToOpen = fileChooser.getSelectedFile();
                    try
                    {
                        newNoteName = fileToOpen;
                        frame.setTitle("| Simple Notes, Note: " + "'" + newNoteName.toString() + "'" + " |");

                        try (BufferedReader br = new BufferedReader(new FileReader(fileToOpen))) {
                            StringBuilder sb = new StringBuilder();
                            String line = br.readLine();

                            while (line != null) {
                                sb.append(line);
                                sb.append("\n");
                                line = br.readLine();
                            }
                            runEverything = sb.toString();
                            ifNoteOpened();
                        } finally {
                            frame.setVisible(false);
                        }
                    }
                    catch(Exception exception)
                    {
                        System.out.println("Problem found when to open the file");
                    }
                }
            }
        });

    }
}