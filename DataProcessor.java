package Experiment;

class DataProcessor {
    private String data;

    void loadData() {
        // 从文件或数据库加载数据
        data = "Sample Data";
    }

    void processData() {
        // 数据处理逻辑
        data = data.toUpperCase();
    }

    void displayResult() {
        // 显示处理结果
        System.out.println("Result: " + data);
    }
}