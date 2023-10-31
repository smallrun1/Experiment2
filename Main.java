package Experiment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArchitectureExample {
    public static void main(String[] args) {
        // 从输入文件加载数据
        List<String> inputData = loadInputData("input.txt");

        // 使用四种不同的软件体系结构风格处理数据
        processWithMainSubroutine(inputData);
        processWithObjectOriented(inputData);
        processWithEventSystem(inputData);
        processWithPipeFilter(inputData);
    }

    // 从输入文件加载数据
    private static List<String> loadInputData(String filename) {
        List<String> inputData = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                inputData.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputData;
    }

    // 处理数据的主程序-子程序风格
    private static void processWithMainSubroutine(List<String> data) {
        List<String> result = new ArrayList<>();
        for (String item : data) {
            String processedItem = mainSubroutineProcess(item);
            result.add(processedItem);
        }
        saveOutputData("main_subroutine_output.txt", result);
    }

    // 主程序-子程序风格的子程序
    private static String mainSubroutineProcess(String data) {
        // 进行数据处理的逻辑
        return data.toUpperCase();
    }

    // 处理数据的面向对象风格
    private static void processWithObjectOriented(List<String> data) {
        DataProcessor processor = new DataProcessor();
        List<String> result = processor.processData(data);
        saveOutputData("object_oriented_output.txt", result);
    }

    // 处理数据的事件系统风格
    private static void processWithEventSystem(List<String> data) {
        EventProcessor processor = new EventProcessor();
        List<String> result = processor.processData(data);
        saveOutputData("event_system_output.txt", result);
    }

    // 处理数据的管道-过滤器风格
    private static void processWithPipeFilter(List<String> data) {
        PipeFilterProcessor processor = new PipeFilterProcessor();
        List<String> result = processor.processData(data);
        saveOutputData("pipe_filter_output.txt", result);
    }

    // 保存处理后的数据到输出文件
    private static void saveOutputData(String filename, List<String> data) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (String item : data) {
                writer.write(item);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
