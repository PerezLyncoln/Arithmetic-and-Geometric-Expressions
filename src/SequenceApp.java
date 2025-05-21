import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SequenceApp extends JFrame {
    private JComboBox<String> sequenceType;
    private JTextField firstTermField, secondParamField, numTermsField;
    private JTextArea resultArea;
    private JButton generateButton;

    public SequenceApp() {
        setTitle("Arithmetic & Geometric Expressions");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 500);
        setLayout(new BorderLayout(10, 10)); 

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Font poppins;
        try {
            poppins = Font.createFont(Font.TRUETYPE_FONT, 
                new java.io.File("Poppins-Regular.ttf")).deriveFont(14f);
        } catch (Exception e) {
            poppins = new Font("Sans-Serif", Font.PLAIN, 14);
        }

        UIManager.put("Label.font", poppins);
        UIManager.put("ComboBox.font", poppins);
        UIManager.put("TextField.font", poppins);
        UIManager.put("Button.font", poppins);
        UIManager.put("TextArea.font", poppins);

        addLabelAndField(inputPanel, "Select Sequence Type:");
        sequenceType = new JComboBox<>(new String[]{"Arithmetic", "Geometric"});
        inputPanel.add(sequenceType);

        addLabelAndField(inputPanel, "First Term (a):");
        firstTermField = new JTextField();
        inputPanel.add(firstTermField);

        addLabelAndField(inputPanel, "Second Parameter (d or r):");
        secondParamField = new JTextField();
        inputPanel.add(secondParamField);

        addLabelAndField(inputPanel, "Number of Terms (n):");
        numTermsField = new JTextField("5");  // Default to 5 terms
        inputPanel.add(numTermsField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        generateButton = new JButton("Generate Expression");
        generateButton.setBackground(new Color(70, 130, 180));
        generateButton.setForeground(Color.WHITE);
        generateButton.setFocusPainted(false);
        generateButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        generateButton.setPreferredSize(new Dimension(100, 20));
        buttonPanel.add(generateButton);
        add(buttonPanel, BorderLayout.CENTER);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Result"));

        add(inputPanel, BorderLayout.NORTH);
        add(generateButton, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        generateButton.addActionListener(e -> {
        generateExpression();
            SwingUtilities.invokeLater(() -> {
            revalidate();
            repaint();     
        });
    });

        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void addLabelAndField(JPanel panel, String labelText) {
        JLabel label = new JLabel(labelText);
        label.setFont(label.getFont().deriveFont(Font.BOLD));
        panel.add(label);
    }

    private void generateExpression() {
        try {
            double a = Double.parseDouble(firstTermField.getText());
            double p = Double.parseDouble(secondParamField.getText());
            int n = Integer.parseInt(numTermsField.getText());
         
            if (n <= 0) {
                resultArea.setText("Number of terms must be positive.");
                return;
            }
            
            StringBuilder result = new StringBuilder();

            if (sequenceType.getSelectedItem().equals("Arithmetic")) {
                result.append("Arithmetic Sequence:\n");
                result.append("General form: aₙ = ").append(a).append(" + (").append(n).append("-1)*").append(p).append("\n");
                double sum = 0;
                for (int i = 0; i < n; i++) {
                    double term = a + i * p;
                    result.append(term).append(" ");
                    sum += term;
                }
                result.append("\nSum of first ").append(n).append(" terms: ").append(sum);
            } 
            else {
                result.append("Geometric Sequence:\n");
                result.append("General form: aₙ = ").append(a).append(" * ").append(p).append("^(").append(n).append("-1)\n");
                double sum = 0;
                for (int i = 0; i < n; i++) {
                    double term = a * Math.pow(p, i);
                    result.append(term).append(" ");
                    sum += term;
                }
                result.append("\nSum of first ").append(n).append(" terms: ").append(sum);
            }

            resultArea.setText(result.toString());

        } catch (NumberFormatException ex) {
            resultArea.setText("Please enter valid numbers.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new SequenceApp();
        });
    }
}
