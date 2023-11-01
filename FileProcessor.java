package Experiment2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor {
    private String inputFilePath;
    private String result;

    public void readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();
        result = content.toString();
    }

    public String getResult() {
        return result;
    }

    public void setInputFilePath(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }
}