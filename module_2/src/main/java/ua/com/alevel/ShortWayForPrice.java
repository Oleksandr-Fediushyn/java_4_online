package ua.com.alevel;

import java.io.*;
import java.util.*;

public class ShortWayForPrice {
    private final String pathToInputFile;
    private final String pathToOutputFile;

    private int countRequests;
    private final List<String> sources = new ArrayList<>();
    private final List<String> destinations = new ArrayList<>();
    private final List<Integer> results = new ArrayList<>();
    private final Map<String, Map<String, Integer>> graph = new HashMap<>();
    private final Map<String, String> towns = new HashMap<>();
    private final Map<String, Integer> distances = new HashMap<>();
    private final Map<String, String> previous = new HashMap<>();

    public ShortWayForPrice(String pathInput, String pathOutput) {
        this.pathToInputFile = pathInput;
        this.pathToOutputFile = pathOutput;
    }

    public void createGraph() {
        File file = new File(pathToInputFile);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int n = Integer.parseInt(reader.readLine());
            if (n <= 10000) {
                int count = 0;
                for (int i = 0; i < n; i++) {
                    String townName = reader.readLine().trim();
                    count++;
                    towns.put(townName, String.valueOf(count));
                    int m = Integer.parseInt(reader.readLine());
                    Map<String, Integer> neighbors = new HashMap<>();
                    for (int j = 0; j < m; j++) {
                        String[] neighborLine = reader.readLine().split(" ");
                        String neighbor = neighborLine[0];
                        int weight = Integer.parseInt(neighborLine[1]);
                        neighbors.put(neighbor, weight);
                    }
                    graph.put(towns.get(townName), neighbors);
                }
                countRequests = Integer.parseInt(reader.readLine());
                if (countRequests <= 100) {
                    for (int k = 0; k < countRequests; k++) {
                        String[] queryLine = reader.readLine().split(" ");
                        sources.add(towns.get(queryLine[0]));
                        destinations.add(towns.get(queryLine[1]));
                    }
                } else {
                    countRequests = 0;
                    System.out.println(" The number path which need find greater limit -> 100");
                }
            } else {
                System.out.println(" The number towns greater limit -> 10000");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void calculateDistances(String source) {
        Set<String> unvisited = new HashSet<>(graph.keySet());
        for (String v : graph.keySet()) {
            distances.put(v, Integer.MAX_VALUE);
            previous.put(v, null);
        }
        distances.put(source, 0);
        while (!unvisited.isEmpty()) {
            String minVertex = null;
            int minDistance = Integer.MAX_VALUE;
            for (String v : unvisited) {
                int d = distances.get(v);
                if (d < minDistance) {
                    minVertex = v;
                    minDistance = d;
                }
            }
            unvisited.remove(minVertex);
            for (String neighbor : graph.get(minVertex).keySet()) {
                if (unvisited.contains(neighbor)) {
                    int edgeWeight = graph.get(minVertex).get(neighbor);
                    int newDistance = distances.get(minVertex) + edgeWeight;
                    if (newDistance < distances.get(neighbor)) {
                        distances.put(neighbor, newDistance);
                        previous.put(neighbor, minVertex);
                    }
                }
            }
        }
    }

    public void calculateShortestPath() {

        for (int i = 0; i < countRequests; i++) {
            String source = sources.get(i);
            this.calculateDistances(source);
            String target = destinations.get(i);
            int minCost = distances.get(target);
            results.add(minCost);
            List<String> path = new ArrayList<>();
            path.add(getKey(towns, target));
            while (!target.equals(source)) {
                target = previous.get(target);
                path.add(getKey(towns, target));
            }
            Collections.reverse(path);
            System.out.println(path);
        }
    }

    public void writeInFileShortestPath() {
        File file = new File(pathToOutputFile);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Integer s : results) {
                if (s > 20000) {
                    writer.write("The minimum cost of the route exceeds 200000 " + System.lineSeparator());
                } else {
                    writer.write(s + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static <K, V> K getKey(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
