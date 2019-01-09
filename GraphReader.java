import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class GraphReader {

    static Optional<Graph> readGraph(String path) {
        File file = new File(path);
        BufferedReader bufferedReader;
        int nodes, edges, p, k, z;
        Graph graph;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String firstLine = bufferedReader.readLine();
            String[] firstLineValues = firstLine.split(" ");
            nodes = Integer.parseInt(firstLineValues[0]);
            edges = Integer.parseInt(firstLineValues[1]);
            p = Integer.parseInt(firstLineValues[2]);
            k = Integer.parseInt(firstLineValues[3]);
            z = Integer.parseInt(firstLineValues[4]);
            String secondLine = bufferedReader.readLine();
            String[] secondLineValues = secondLine.split(" ");
            List<Integer> costs = new ArrayList<>();
            for (String value : secondLineValues) {
                costs.add(Integer.parseInt(value));
            }
            graph = new Graph(nodes, costs, z);
            graph.setK(k);
            graph.setP(p);
            for (int i = 0; i < edges; i++) {
                int u, v;
                String line = bufferedReader.readLine();
                u = Integer.parseInt(line.split(" ")[0]);
                v = Integer.parseInt(line.split(" ")[1]);
                graph.addEdge(u, v);
            }
        } catch (IOException e) {
            return Optional.empty();
        }
        return Optional.of(graph);
    }
}
