package pl.simpleNotes.simpleNotesEngine;

import javax.swing.*;
import java.awt.*;

import static pl.simpleNotes.simpleNotesUI.noteGUI.noteText;

public class changeColor {

    public static JFrame colorFrame;
    private static String operationType;
    public static Color textColor;

    public static void startColorPicker(String typePicker) {

        openColorMenu();
        changeTextColor(typePicker);
        colorFrame.setTitle("Change " + operationType + " Color");

    }

    public static void openColorMenu() {

        colorFrame = new JFrame("Change " + operationType + " Color");
        colorFrame.pack();
        colorFrame.setVisible(true);
        colorFrame.setLayout(null);
        colorFrame.setLocationRelativeTo(null);
        colorFrame.setResizable(false);
        colorFrame.setTitle("Change " + operationType + " Color");
        colorFrame.setSize(500, 300);
        colorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void changeTextColor(String type) {

        operationType = type;
        final int[] redOfText = {0};
        final int[] greenOfText = {0};
        final int[] blueOfText = {0};

        Color buttonBg = new Color(238, 238, 238);

        JLabel previewText = new JLabel("Preview: ");
        colorFrame.add(previewText);
        previewText.setBounds(20, 1, 100, 20);
        previewText.setVisible(true);
        previewText.setText("Preview:");

        JTextArea preview = new JTextArea();
        colorFrame.add(preview);
        preview.setVisible(true);
        preview.setBounds(20, 30, 100, 90);
        if (type.equals("Text")) {
            preview.setText("\nHi users of\nSimple Notepad!");
        } else {
            preview.setText("");
        }

        JPanel panel = new JPanel();
        colorFrame.add(panel);
        panel.setBounds(200, 20, 180, 40);

        JPanel panel1 = new JPanel();
        colorFrame.add(panel1);
        panel1.setBounds(200, 70, 180, 40);

        JPanel panel2 = new JPanel();
        colorFrame.add(panel2);
        panel2.setBounds(200, 120, 180, 40);

        JSlider rSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        panel.add(rSlider);
        rSlider.setVisible(true);
        rSlider.setMajorTickSpacing(50);
        rSlider.setPaintLabels(true);
        rSlider.setPreferredSize(new Dimension(180, 40));

        JSlider gSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        panel1.add(gSlider);
        gSlider.setVisible(true);
        gSlider.setMajorTickSpacing(50);
        gSlider.setPaintLabels(true);
        gSlider.setPreferredSize(new Dimension(180, 40));

        JSlider bSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        panel2.add(bSlider);
        bSlider.setVisible(true);
        bSlider.setMajorTickSpacing(50);
        bSlider.setPaintLabels(true);
        bSlider.setPreferredSize(new Dimension(180, 40));

        JLabel red = new JLabel("Red:");
        JLabel green = new JLabel("Green:");
        JLabel blue = new JLabel("Blue:");
        colorFrame.add(red);
        colorFrame.add(green);
        colorFrame.add(blue);
        red.setVisible(true);
        green.setVisible(true);
        blue.setVisible(true);
        red.setBounds(140, 20, 50, 40);
        green.setBounds(140, 70, 50, 40);
        blue.setBounds(140, 120, 50, 40);

        JButton submit = new JButton("Submit");
        colorFrame.add(submit);
        submit.setVisible(true);
        submit.setBackground(buttonBg);
        submit.setBounds(20, 130, 100, 100);
        submit.setText("Submit");

        JButton showPreview = new JButton("Show Preview");
        colorFrame.add(showPreview);
        showPreview.setVisible(true);
        showPreview.setBackground(buttonBg);
        showPreview.setBounds(130, 170, 130, 60);
        showPreview.setText("Show Preview");

        showPreview.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                redOfText[0] = rSlider.getValue();
                greenOfText[0] = gSlider.getValue();
                blueOfText[0] = bSlider.getValue();
                textColor = new Color(redOfText[0], greenOfText[0], blueOfText[0]);

                if (type.equals("Text")) {
                    preview.setForeground(textColor);
                } else {
                    preview.setBackground(textColor);
                }

        }});

        submit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                if (type.equals("Text")) {
                    noteText.setForeground(textColor);
                } else {
                    noteText.setBackground(textColor);
                }

                colorFrame.setVisible(false);

            }});

        JButton cancel = new JButton("Cancel");
        colorFrame.add(cancel);
        cancel.setVisible(true);
        cancel.setBackground(buttonBg);
        cancel.setBounds(270, 170, 160, 60);
        cancel.setText("Cancel");

        cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                colorFrame.setVisible(false);

            }});

    }

}

