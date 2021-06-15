package pl.simpleNotes.simpleNotesUI;

import pl.simpleNotes.simpleNotesEngine.FontDemo;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static pl.simpleNotes.simpleNotesEngine.changeColor.*;
import static pl.simpleNotes.simpleNotesEngine.simpleNotesUtils.replaceWith;
import static pl.simpleNotes.simpleNotesUI.mainUI.isNoteOpened;
import static pl.simpleNotes.simpleNotesUI.mainUI.runEverything;
import static pl.simpleNotes.simpleNotesEngine.makeNewNote.newNoteName;

public class noteGUI {

    public static String fileText;
    public static File filePath;
    public static File txtFile;
    public static FileWriter myWriter;
    public static File txtFile1;
    public static FileWriter myWriter1;
    public static String currentTextGetter;
    public static JTextArea noteText;

    public static void note() {
        JFrame framex = new JFrame("| Simple Notes |");

        Image icon = Toolkit.getDefaultToolkit().getImage(Objects.requireNonNull(mainUI.class.getClassLoader().getResource("Graphics/notepadIcon.png")));
        framex.setIconImage(icon);

        framex.pack();
        framex.setVisible(true);
        framex.setLayout(null);
        framex.setLocationRelativeTo(null);
        framex.setResizable(false);
        framex.setTitle("| Simple Notes, Note: " + "'" + newNoteName + "'" + " |");
        framex.setSize(748, 880);
        framex.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar utilsMenu = new JMenuBar();
        utilsMenu.setVisible(true);
        framex.add(utilsMenu);
        framex.setJMenuBar(utilsMenu);
        JMenu menu = new JMenu("File");
        utilsMenu.add(menu);

        noteText = new JTextArea();
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
        framex.add(scrollBar);

        final JMenuItem saveAs = new JMenuItem("Save As:");
        saveAs.addActionListener((e -> {

            JFileChooser f = new JFileChooser();
            f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            f.showSaveDialog(null);
            f.setCurrentDirectory(new File(System.getProperty("user.home")));

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
                String string = newNoteName.toString();
                String[] parts = string.split("\\.");
                String part1 = parts[0];
                fileText = noteText.getText();
                txtFile1 = new File (filePath, part1);

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
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text File", "txt"));
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            int a=fileChooser.showOpenDialog(null);
            if(a==JFileChooser.APPROVE_OPTION)
            {
                File fileToOpen = fileChooser.getSelectedFile();
                filePath = (fileChooser.getSelectedFile());

                try
                {
                    newNoteName = fileToOpen;
                    framex.setTitle("| Simple Notes, Note: " + "'" + newNoteName.toString() + "'" + " |");

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

        JMenu edit = new JMenu("Edit");
        utilsMenu.add(edit);

        final JMenuItem replaceTextWith = new JMenuItem("Replace Text With:");
        replaceTextWith.addActionListener((e -> {

            currentTextGetter = noteText.getText();
            replaceWith();

        }));

        final JMenuItem date = new JMenuItem("Print Date:");
        date.addActionListener((e -> {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            noteText.setText(noteText.getText() + " " + dtf.format(now));

        }));

        final JMenuItem copy = new JMenuItem("Copy:");
        copy.addActionListener((e -> {

            if (noteText.getSelectedText() != null) {
                String currentText = noteText.getSelectedText();
                StringSelection selectText = new StringSelection(currentText);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selectText, null);
            }

        }));

        final JMenuItem openPhraseInBrowser = new JMenuItem("Search Selected Text In Browser: ");
        openPhraseInBrowser.addActionListener((e -> {

            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                String createSearchURL = noteText.getSelectedText();
                CharSequence oldChar1 = " ";
                CharSequence newChar1 = "+";
                String initializeSearchURL = createSearchURL.replace(oldChar1, newChar1);
                if (!initializeSearchURL.equals("")) {
                    initializeSearchURL = "https://www.google.pl/search?q=" + initializeSearchURL;
                    try {
                        Desktop.getDesktop().browse(new URI(initializeSearchURL));
                    } catch (IOException | URISyntaxException ioException) {
                        ioException.printStackTrace();
                    }

                }

            }

        }));

        final JMenuItem cutText = new JMenuItem("Cut: ");
        cutText.addActionListener((e -> {

            String textToCut = noteText.getText();
            CharSequence oldChar1 = noteText.getSelectedText();
            CharSequence newChar1 = "";
            String cuttedText = textToCut.replace(oldChar1, newChar1);
            noteText.setText(cuttedText);

        }));

        final JMenuItem pasteText = new JMenuItem("Paste: ");
        pasteText.addActionListener((e -> {

            try {

                Robot r = new Robot();
                r.keyPress(KeyEvent.VK_CONTROL);
                r.keyPress(KeyEvent.VK_V);
                r.keyRelease(KeyEvent.VK_CONTROL);
                r.keyRelease(KeyEvent.VK_V);

            } catch(Exception ex) {
                ex.printStackTrace();
            }

        }));

        final JMenuItem changeFont = new JMenuItem("Change Font: ");
        changeFont.addActionListener((e -> {

            new FontDemo();

        }));

        final JMenuItem changeTextColor = new JMenuItem("Change Text Color: ");
        changeTextColor.addActionListener((e -> {

            startTextColorPicker();

        }));

        final JMenuItem changeBackgroundColor = new JMenuItem("Change Background Color: ");
        changeBackgroundColor.addActionListener((e -> {

            startBackgroundColorPicker();

        }));

        final JMenuItem undo = new JMenuItem("Undo: ");
        undo.addActionListener((e -> {

            try {

                Robot r = new Robot();
                r.keyPress(KeyEvent.VK_CONTROL);
                r.keyPress(KeyEvent.VK_Z);
                r.keyRelease(KeyEvent.VK_CONTROL);
                r.keyRelease(KeyEvent.VK_Z);

            } catch(Exception ex) {
                ex.printStackTrace();
            }

        }));

        edit.add(undo);
        edit.add(copy);
        edit.add(pasteText);
        edit.add(cutText);
        edit.add(changeTextColor);
        edit.add(changeBackgroundColor);
        edit.add(replaceTextWith);
        edit.add(openPhraseInBrowser);
        edit.add(date);
    }
}

