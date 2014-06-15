package com.bravofly.exercise.main;

import java.util.List;

public class Routes {

    private Graph g;

    public Routes(Graph g) {
        this.g = g;
    }

    public int getTravelTime(List<Airports> itinerary) {
        int sum = 0;
        for (int i = 0; i < itinerary.size() - 1; i++) {
            Edge edgeToSearch = new Edge(itinerary.get(i), itinerary.get(i + 1));
            if (!g.getEdges().contains(edgeToSearch)) return 0;
            sum += recoverTravelTime(edgeToSearch);
        }
        return sum;
    }

    private int recoverTravelTime(Edge toSearch) {
        for (Edge f : g.getEdges()) {
            if (f.equals(toSearch)) return f.getWeight();
        }
        return 0;
    }

    public int getItinerariesWithLessThenOrEqualsTo(int maxVertex, Airports source, Airports destination) {

        return 0;
    }

//    public TreeNode getPaths(Graph g, Airports source, Airports destination, int threshold) {
//
//        TreeNode startNode = new TreeNode(source, 0);
//
//        for (Edge e : g.adiacentEdges(new GraphNode(source))) {
//            int currentWeight = startNode.getWeight() + e.getWeight();
//            if (currentWeight <= threshold) {
//                TreeNode node = new TreeNode(e.getDestination(), currentWeight);
//                startNode.getChildren().add(node);
//                getPaths(g, node, threshold);
//            }
//
//        }
//        return startNode;
//    }
//
//    private void getPaths(Graph g, TreeNode treeNode, int threshold) {
//        for (Edge e : g.adiacentEdges(new GraphNode(treeNode.getNode()))) {
//            int currentWeight = treeNode.getWeight() + e.getWeight();
//            if (currentWeight <= threshold) {
//                TreeNode node = new TreeNode(e.getDestination(), currentWeight);
//                treeNode.getChildren().add(node);
//                getPaths(g, node, threshold);
//            }
//        }
//    }
}
