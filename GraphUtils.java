import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

class GraphUtils {

    static Optional<State> findPath(Graph graph) {
        int p = graph.getP();
        int k = graph.getK();
        Deque<State> deque = new ArrayDeque<>();
        Graph.Node[] nodes = graph.getNodes();
        nodes[p].setCostAtArrival(nodes[p].getCost());
        deque.add(new State(nodes[p], nodes[p].getCostAtArrival()));
        nodes[p].visit(new State(nodes[p], nodes[p].getCostAtArrival()));
        while (!deque.isEmpty()) {
            State tmpState = deque.removeFirst();
            Graph.Node tmpNode = tmpState.getNode();
            tmpNode.setCostAtArrival(tmpState.getState());
            if (tmpNode == nodes[k] && tmpNode.getCostAtArrival() == graph.getZ())
                return Optional.of(new State(tmpNode, tmpNode.getCostAtArrival()));
            for (Graph.Node neighbor : tmpNode.getNeighbors()) {
                if (tmpNode.getCostAtArrival() + neighbor.getCost() <= graph.getZ()) {
                    neighbor.setCostAtArrival(tmpNode.getCostAtArrival() + neighbor.getCost());
                    if (!neighbor.isVisited(neighbor.getCostAtArrival())) {
                        neighbor.visit(new State(tmpNode, tmpNode.getCostAtArrival()));
                        deque.add(new State(neighbor, neighbor.getCostAtArrival()));
                    }
                }
            }
        }
        return Optional.empty();
    }

    static Deque<Graph.Node> retracePath(State state) {
        Deque<Graph.Node> stack = new ArrayDeque<>();
        State currentState = state;
        State previousState = currentState.getNode().getStates()[currentState.getState()];
        while (currentState != previousState) {
            stack.addFirst(currentState.getNode());
            currentState = previousState;
            previousState = currentState.getNode().getStates()[currentState.getState()];
        }
        return stack;
    }
}
