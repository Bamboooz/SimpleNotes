package pl.simpleNotes;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Objects;

import static pl.simpleNotes.mainUI.isNoteOpened;
import static pl.simpleNotes.mainUI.runEverything;
import static pl.simpleNotes.makeNewNote.newNoteName;

public class noteGUI {

    public static String fileText;
    public static File filePath;
    public static File txtFile;
    public static FileWriter myWriter;
    public static File txtFile1;
    public static FileWriter myWriter1;

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
        noteText.setVisible(true);
        JScrollPane scrollBar = new JScrollPane(noteText,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        if (isNoteOpened.equals("1")) {
            noteText.setText(runEverything);
        }
        scrollBar.setBorder(BorderFactory.createTitledBorder("Note Text:"));
        noteText.setLineWrap(true);
        noteText.setWrapStyleWord(true);
        scrollBar.setBounds(0, 0, 728, 880);
        frame.add(scrollBar);

        final JMenuItem saveAs = new JMenuItem("Save As:");
        saveAs.addActionListener((e -> {

            JFileChooser f = new JFileChooser();
            f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            f.showSaveDialog(null);

            filePath = (f.getSelectedFile());
            fileText = noteText.getText();

                try {
                    txtFile = new File (filePath, fileText);
                    if (txtFile.createNewFile()) {
                        System.out.println("File created: " + txtFile.getName());
                        txtFile.delete();
                        myWriter = new FileWriter(new File(filePath, String.valueOf(newNoteName)));
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

        final JMenuItem saveNote = new JMenuItem("Save");
        saveNote.addActionListener((e -> {

            try
            {
                Files.deleteIfExists(Paths.get((Paths.get(String.valueOf(filePath)) + "/" + newNoteName)));
            }
            catch(NoSuchFileException exceptionNoFile)
            {
                System.out.println("No such file/directory exists");
            }
            catch(DirectoryNotEmptyException exceptionNotEmpty)
            {
                System.out.println("Directory is not empty.");
            }
            catch(IOException exceptionIO)
            {
                System.out.println("Invalid permissions.");
            }

            System.out.println("Deletion successful.");

            try {
                fileText = noteText.getText();
                txtFile1 = new File (filePath, fileText);
                if (txtFile1.createNewFile()) {
                    System.out.println("File updated: " + txtFile1.getName());
                    txtFile1.delete();
                    myWriter1 = new FileWriter(new File(filePath, String.valueOf(newNoteName)));
                    myWriter1.write(fileText);
                    myWriter1.close();
                }
            } catch (IOException exept) {
                System.out.println("An error occurred.");
                exept.printStackTrace();
            }

        }));

        menu.add(saveNote);

        final JMenuItem openFile = new JMenuItem("Open File:");
        openFile.addActionListener((e -> {
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
                        String everything = sb.toString();
                        noteText.setText(everything);
                    }
                }
                catch(Exception exception)
                {
                    System.out.println("Problem found when to open the file");
                }
            }
        }));

        menu.add(openFile);

        final JMenuItem clearJTextArea = new JMenuItem("Clear:");
        clearJTextArea.addActionListener((e -> {
            noteText.setText("");
        }));

        menu.add(clearJTextArea);

        final JMenuItem exitNtpd = new JMenuItem("Exit");
        exitNtpd.addActionListener((e -> {
            System.exit(1);
        }));

        menu.add(exitNtpd);

    }

}
