package homework.xiangmu4;


import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

    public class Calculator {
        private JFrame frame;
        private JTextField display;

        private char operation = ' ';
        private double operand1 = 0.0;
        private double operand2 = 0.0;

        public static void main(String[] args) {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        Calculator window = new Calculator();
                        window.frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        public Calculator() {
            initialize();
        }

        private void initialize() {
            //窗口名字
            frame = new JFrame("简易计算器");
            //窗口大小
            frame.setBounds(100, 100, 300, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().setLayout(new GridBagLayout());

            display = new JTextField("");
            display.setEditable(false);
            display.setFont(new Font("Arial", Font.PLAIN, 20));
            GridBagConstraints gbcDisplay = new GridBagConstraints();
            gbcDisplay.fill = GridBagConstraints.HORIZONTAL;
            gbcDisplay.gridwidth = 4;
            gbcDisplay.insets = new Insets(10, 10, 10, 10);
            gbcDisplay.gridx = 0;
            gbcDisplay.gridy = 0;
            frame.getContentPane().add(display, gbcDisplay);
            display.setColumns(10);

            String[] buttonLabels = {"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "0", ".", "=", "/"};
            GridBagConstraints[] gbcButtons = new GridBagConstraints[buttonLabels.length];

            for (int i = 0; i < buttonLabels.length; i++) {
                JButton button = new JButton(buttonLabels[i]);
                button.addActionListener(e -> handleButtonClick(button));
                GridBagConstraints gbcButton = new GridBagConstraints();
                gbcButton.fill = GridBagConstraints.HORIZONTAL;
                gbcButton.insets = new Insets(8, 8, 8, 8);

                if (i == buttonLabels.length - 1) {
                    gbcButton.gridheight = 2;
                    gbcButton.gridy = 4;
                } else if (i >= 12) {
                    gbcButton.gridy = i - 11;
                    gbcButton.gridx = 3;
                } else if (i >= 8) {
                    gbcButton.gridy = i - 7;
                    gbcButton.gridx = 2;
                } else if (i >= 4) {
                    gbcButton.gridy = i - 3;
                    gbcButton.gridx = 1;
                } else {
                    gbcButton.gridy = i + 1;
                    gbcButton.gridx = 0;
                }

                gbcButtons[i] = gbcButton;
                frame.getContentPane().add(button, gbcButton);
            }
        }

        private void handleButtonClick(JButton button) {
            String buttonText = button.getText();

            if (Character.isDigit(buttonText.charAt(0))) {
                if (operation != ' ') {
                    operand2 = operand2 * 10 + Double.parseDouble(buttonText);
                } else {
                    operand1 = operand1 * 10 + Double.parseDouble(buttonText);
                }
                display.setText(display.getText() + buttonText);
            } else if (buttonText.equals(".")) {
                if (operation != ' ') {
                    operand2 = Double.parseDouble(display.getText() + buttonText);
                } else {
                    operand1 = Double.parseDouble(display.getText() + buttonText);
                }
                display.setText(display.getText() + buttonText);
            } else if (buttonText.equals("+") || buttonText.equals("-") || buttonText.equals("*") || buttonText.equals("/")) {
                operation = buttonText.charAt(0);
                display.setText(display.getText() + buttonText);
            } else if (buttonText.equals("=")) {
                if (operation == '+') {
                    display.setText(Double.toString(operand1 + operand2));
                } else if (operation == '-') {
                    display.setText(Double.toString(operand1 - operand2));
                } else if (operation == '*') {
                    display.setText(Double.toString(operand1 * operand2));
                } else if (operation == '/') {
                    if (operand2 == 0) {
                        display.setText("错误：不能除以零");
                    } else {
                        display.setText(Double.toString(operand1 / operand2));
                    }
                }

                operand1 = Double.parseDouble(display.getText());
                operand2 = 0.0;
                operation = ' ';
            } else {
                operand1 = 0.0;
                operand2 = 0.0;
                operation = ' ';
                display.setText("");
            }
        }
    }

