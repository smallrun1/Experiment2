import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UI {
    private JFrame frame;
    private JButton chooseFileButton;
    private JButton clearButton;
    private JButton processButton;
    private JComboBox<String> processingMethods;
    private JTextArea resultTextArea;
    private JLabel selectedFileLabel;

    public UI() {
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("File Processor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        chooseFileButton = new JButton("Choose File");
        clearButton = new JButton("Clear");
        processButton = new JButton("Process File");

        resultTextArea = new JTextArea();
        resultTextArea.setEditable(true);

        selectedFileLabel = new JLabel("Selected File: ");

        // 添加文件选择按钮的事件处理
        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    updateSelectedFileLabel(fileChooser.getSelectedFile().getName());
                    try {
                        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
                        resultTextArea.setText(fileContent);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        resultTextArea.setText("Error reading file.");
                    }
                }
            }
        });

        // 添加清除按钮的事件处理
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultTextArea.setText("");
                selectedFileLabel.setText("Selected File: ");
            }
        });

        // 创建处理方法选择下拉框
        String[] methods = {"主程序-子程序", "面向对象", "事件系统", "管道-过滤器"};
        processingMethods = new JComboBox<>(methods);

        // 添加处理按钮的事件处理
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedMethod = processingMethods.getSelectedItem().toString();
                String fileContent = resultTextArea.getText();
                String result = processFile(selectedMethod, fileContent);
                resultTextArea.setText(result);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(chooseFileButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(processingMethods);
        buttonPanel.add(processButton);

        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(selectedFileLabel, BorderLayout.CENTER);
        panel.add(new JScrollPane(resultTextArea), BorderLayout.SOUTH);

        frame.add(panel);
    }

    private void updateSelectedFileLabel(String fileName) {
        selectedFileLabel.setText("Selected File: " + fileName);
    }

    private String processFile(String method, String fileContent) {
        // 根据不同的处理方法执行相应的操作，这里只是示例
        if (method.equals("Method 1")) {
            return "Result from Method 1: " + fileContent.toUpperCase();
        } else if (method.equals("Method 2")) {
            return "Result from Method 2: " + fileContent.toLowerCase();
        } else if (method.equals("Method 3")) {
            return "Result from Method 3: " + fileContent.replaceAll("[aeiouAEIOU]", "*");
        } else {
            return "Result from Method 4: " + reverseString(fileContent);
        }
    }

    private String reverseString(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    public void displayUI() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        UI ui = new UI();
        ui.displayUI();
    }
}
