public class Main {

    public static void main(String[] args) {
        GraphReader.readGraph(args[0]).ifPresent((graph) -> {
            GraphUtils.findPath(graph).ifPresent((state) -> {
                    GraphUtils.retracePath(state).forEach((node) -> System.out.println(node.toString()));
            });
        });
    }
}
