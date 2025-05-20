import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SequenceApp extends JFrame {
    private JComboBox<String> sequenceType;
    private JTextField firstTermField, secondParamField;
    private JTextArea resultArea;
    private JButton generateButton;

    public SequenceApp() {
        setTitle("Arithmetic & Geometric Expressions");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 500);
        setLayout(new GridLayout(6, 2, 5, 5));

        add(new JLabel("Select Sequence Type:"));
        sequenceType = new JComboBox<>(new String[]{"Arithmetic", "Geometric"});
        add(sequenceType);

        add(new JLabel("First Term (a):"));
        firstTermField = new JTextField();
        add(firstTermField);

        add(new JLabel("Second Parameter (d or r):"));
        secondParamField = new JTextField();
        add(secondParamField);

        generateButton = new JButton("Generate Expression");
        add(generateButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea));

        // Span text area across last row
        add(new JLabel("Result:"));
        add(new JLabel("")); // empty cell

        generateButton.addActionListener(e -> generateExpression());

        setVisible(true);
    }

    private void generateExpression() {
        try {
            double a = Double.parseDouble(firstTermField.getText());
            double p = Double.parseDouble(secondParamField.getText());
            StringBuilder result = new StringBuilder();

            if (sequenceType.getSelectedItem().equals("Arithmetic")) {
                result.append("Arithmetic Sequence:\n");
                for (int i = 0; i < 5; i++) {
                    result.append(a + i * p).append(" ");
                }
            } else {
                result.append("Geometric Sequence:\n");
                for (int i = 0; i < 5; i++) {
                    result.append(a * Math.pow(p, i)).append(" ");
                }
            }

            resultArea.setText(result.toString());

        } catch (NumberFormatException ex) {
            resultArea.setText("Please enter valid numbers.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SequenceApp::new);
    }
}
