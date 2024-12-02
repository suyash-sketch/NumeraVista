package com.company.codes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TransparentComboBox<E> extends JComboBox<E> {
    public TransparentComboBox(E[] items) {
        super(items);
        //setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(new Color(0, 0, 0, 0)); // Transparent background
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRect(0, 0, getWidth(), getHeight());
    }
}

class RoundedButton extends JButton {
    public RoundedButton(String label) {
        super(label);
        setContentAreaFilled(false);
        setOpaque(false);
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.LIGHT_GRAY);
        } else {
            g.setColor(getBackground());
        }
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
    }
}

public class ConverterGraph extends JFrame {
    private JTextField inputField;
    private JTextField outputField;
    private TransparentComboBox<String> inputType;
    private TransparentComboBox<String> outputType;

    public ConverterGraph() {
        setTitle("Number Converter");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Custom JPanel for background image
        JPanel backgroundPanel = new JPanel() {
            ImageIcon icon = new ImageIcon("D:\\New folder\\Suyash\\Images\\558309-solid_color.jpg");
            Image img = icon.getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);

        JLabel inputLabel = new JLabel("Input:");
        inputLabel.setBounds(20, 20, 80, 25);
        inputLabel.setFont(new Font("Arial", Font.BOLD, 14));
        inputLabel.setForeground(Color.BLACK);
        add(inputLabel);

        inputField = new JTextField();
        inputField.setBounds(100, 20, 200, 25);
        inputField.setOpaque(false);
        inputField.setBackground(new Color(0, 0, 0, 0));
        inputField.setFont(new Font("AIDGT",Font.PLAIN,15));
        inputField.setForeground(Color.WHITE);
        inputField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        add(inputField);

        JLabel outputLabel = new JLabel("Output:");
        outputLabel.setBounds(20, 60, 80, 25);
        outputLabel.setFont(new Font("Arial", Font.BOLD, 14));
        outputLabel.setForeground(Color.BLACK);
        add(outputLabel);

        outputField = new JTextField();
        outputField.setBounds(100, 60, 200, 25);
        outputField.setOpaque(false);
        outputField.setBackground(new Color(0, 0, 0, 0));
        outputField.setFont(new Font("AIDGT",Font.PLAIN,15));
        outputField.setForeground(Color.WHITE);
        outputField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        outputField.setEditable(false);
        add(outputField);

        String[] types = {"Binary", "Decimal", "Hexadecimal", "Octal"};
        inputType = new TransparentComboBox<>(types);
        DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        inputType.setRenderer(listRenderer);
        inputType.setBounds(80, 100, 120, 25);
        inputType.setForeground(Color.gray);
        add(inputType);

        outputType = new TransparentComboBox<>(types);
        DefaultListCellRenderer listCellRenderer = new DefaultListCellRenderer();
        outputType.setRenderer(listRenderer);
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        outputType.setBounds(220, 100, 120, 25);
        outputType.setForeground(Color.GRAY);
        add(outputType);

        RoundedButton convertButton = new RoundedButton("Convert");
        convertButton.setBounds(150, 150, 100, 25);
        convertButton.setBackground(new Color(70, 130, 180));
        convertButton.setForeground(Color.WHITE);
        add(convertButton);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                String inputFormat = (String) inputType.getSelectedItem();
                String outputFormat = (String) outputType.getSelectedItem();
                String output = "";

                try {
                    int decimalValue = 0;

                    // Convert input to decimal
                    switch (inputFormat) {
                        case "Binary":
                            decimalValue = Integer.parseInt(input, 2);
                            break;
                        case "Decimal":
                            decimalValue = Integer.parseInt(input);
                            break;
                        case "Hexadecimal":
                            decimalValue = Integer.parseInt(input, 16);
                            break;
                        case "Octal":
                            decimalValue = Integer.parseInt(input, 8);
                            break;
                    }

                    // Convert decimal to output format
                    switch (outputFormat) {
                        case "Binary":
                            output = Integer.toBinaryString(decimalValue);
                            break;
                        case "Decimal":
                            output = String.valueOf(decimalValue);
                            break;
                        case "Hexadecimal":
                            output = Integer.toHexString(decimalValue).toUpperCase();
                            break;
                        case "Octal":
                            output = Integer.toOctalString(decimalValue);
                            break;
                    }
                } catch (NumberFormatException ex) {
                    output = "Invalid input";
                }

                outputField.setText(output);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ConverterGraph().setVisible(true);
            }
        });
    }
}
