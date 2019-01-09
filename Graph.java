import java.util.ArrayList;
import java.util.List;

class Graph {

    private Node[] nodes;
    private int z;
    private int p;
    private int k;

    Graph(int n, List<Integer> s, int z) {
        this.z = z;
        nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(s.get(i), i + 1);
        }
    }

    Node[] getNodes() {
        return nodes;
    }

    int getZ() {
        return z;
    }

    void addEdge(int u, int v) {
        nodes[u - 1].neighbors.add(nodes[v - 1]);
        nodes[v - 1].neighbors.add(nodes[u - 1]);
    }

    int getP() {
        return p;
    }

    void setP(int p) {
        this.p = p;
    }

    int getK() {
        return k;
    }

    void setK(int k) {
        this.k = k;
    }

    class Node {

        private int index;
        private int cost;
        private int costAtArrival;
        private List<Node> neighbors = new ArrayList<>();
        private State[] states;

        Node(int cost, int index) {
            this.index = index;
            this.cost = cost;
            states = new State[z + 1];
        }

        State[] getStates() {
            return states;
        }

        List<Node> getNeighbors() {
            return neighbors;
        }

        int getCost() {
            return cost;
        }

        int getCostAtArrival() {
            return costAtArrival;
        }

        void setCostAtArrival(int costAtArrival) {
            this.costAtArrival = costAtArrival;
        }

        boolean isVisited(int s) {
            return states[s] != null;
        }

        void visit(State visitor) {
            states[costAtArrival] = visitor;
        }

        @Override
        public String toString() {
            return Integer.toString(index);
        }
    }
}

class State {
    private Graph.Node node;
    private int state;

    State(Graph.Node node, int state) {
        this.node = node;
        this.state = state;
    }

    Graph.Node getNode() {
        return node;
    }

    int getState() {
        return state;
    }
}

