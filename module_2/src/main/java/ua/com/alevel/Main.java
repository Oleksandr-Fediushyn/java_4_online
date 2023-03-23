package ua.com.alevel;

public class Main {

    public static void main(String[] args) {
        String inputFile = "module_2\\input.txt";
        String outputFile = "module_2\\output.txt";
        ShortWayForPrice lowCostWay = new ShortWayForPrice(inputFile, outputFile);
        lowCostWay.createGraph();
        lowCostWay.calculateShortestPath();
        lowCostWay.writeInFileShortestPath();
    }
}