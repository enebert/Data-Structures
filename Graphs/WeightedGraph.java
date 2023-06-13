import java.util.*;
import java.util.function.*;

public class WeightedGraph<T> extends Graph<T,Edge<T>>{

    public WeightedGraph(){
        adjacencyList = new Hashtable<>();
        numVertices = 0;
        numEdges = 0;

    }

    public boolean deleteVertex(T vertex, boolean directed){
        if(!adjacencyList.containsKey(vertex)) return false;

        if(directed){
            adjacencyList.remove(vertex);
            Set<T> temp = adjacencyList.keySet();
            for(T item : temp){
                if(adjacencyList.get(item).contains(new Edge<T>(item, vertex))){
                    adjacencyList.get(item).remove(new Edge<T>(item, vertex));
                }     
            }
        }else{
            LinkedList<Edge<T>> temp=adjacencyList.get(vertex);
            adjacencyList.remove(vertex);
            for(Edge<T> item : temp){
                if(adjacencyList.containsKey(item.getHead())){
                    adjacencyList.get(item.getHead()).remove(new Edge<T>(vertex, item.getHead()));
                }else{
                    adjacencyList.get(item.getTail()).remove(new Edge<T>(item.getTail(), vertex));
                }
            }
        }
        numVertices--;
        return true;
    }

    public LinkedList<T> getAdjacent(T vertex){
        LinkedList<Edge<T>> temp = adjacencyList.get(vertex);
        LinkedList<T> v = new LinkedList<>();

        for(Edge<T> t : temp){
            if(t.getTail().equals(vertex)){
                v.add(t.getHead());
            }else{
                v.add(t.getTail());
            }
        }
        return v;
    }

    public LinkedList<Edge<T>> getAdjEdges(T vertex){
        return adjacencyList.get(vertex);
    }

    public void addAllEdges(ArrayList<Edge<T>> edgeList, boolean directed){
        for(Edge<T> edge : edgeList){
            if(!adjacencyList.contains(edge.getHead())) insertVertex(edge.getHead());
            if(!adjacencyList.containsKey(edge.getTail())) insertVertex(edge.getTail());

            insertEdge(edge, directed);
        }
    }

    public boolean insertEdge(Edge<T> e, boolean directed){

        if(adjacencyList.containsKey(e.getHead()) && adjacencyList.containsKey(e.getTail())){
            if(directed){
                adjacencyList.get(e.getTail()).add(e);
            }else{
                adjacencyList.get(e.getTail()).add(e);
                adjacencyList.get(e.getHead()).add(e);
            }
            numEdges++;
            return true;
        }
        return false;
    }

    public boolean deleteEdge(Edge<T> e, boolean directed){
        if(!(adjacencyList.size()==0) || adjacencyList.containsKey(e.getHead()) && adjacencyList.containsKey(e.getTail())){
            if(directed){
                adjacencyList.get(e.getTail()).remove(e);
            }else{
                Edge<T> t = new Edge<T> (e.getHead(), e.getTail());
                adjacencyList.get(e.getTail()).remove(e);
                adjacencyList.get(e.getHead()).remove(e);

                adjacencyList.get(e.getTail()).remove(t);
                adjacencyList.get(e.getHead()).remove(t);
            }
            numEdges--;
            return true;
        }
        return false;
    }

    public boolean containsEdge(Edge<T> e){
        if(adjacencyList.size()==0 || !adjacencyList.containsKey(e.getTail())) return false;
        return adjacencyList.get(e.getTail()).contains(e);
    }

    public WeightedGraph<T> subgraph(ArrayList<T> vertices){
        WeightedGraph<T> sub = new WeightedGraph<>();
        sub.addAllVertices(vertices);

        for(T item : vertices){
            for(Edge<T> e : adjacencyList.get(item)){
                if(e.getTail().equals(item) || e.getHead().equals(item)) sub.insertEdge(e, true);
            }
        }
        return sub;
    }

    public static <T> Path<T> dijkstra(WeightedGraph<T> graph, T start, T end){
        Path<T> path = new Path<>();
        Hashtable<T,T> predecessors = new Hashtable<>();
        Hashtable<T, Double> costs = new Hashtable<>();
        ArrayList<T> processed = new ArrayList<>();

        Supplier<T> getLeastCost = () ->{
            Double leastCost = Double.MAX_VALUE;
            T currentVertex = null;

            for(T n : costs.keySet()){
                Double cost = costs.get(n);
                if(cost < leastCost && !processed.contains(n)){
                    leastCost = cost;
                    currentVertex = n;
                }
            }
            return currentVertex;
        };

        Set<T> v = graph.getVertices();
        for(T item : v){
            costs.put(item, Double.MAX_VALUE);
        }
        for(Edge<T> e : graph.getAdjEdges(start)){
            costs.put(e.getHead(), e.getWeight());
            predecessors.put(e.getHead(), start);
        }
        processed.add(start);

        T node = getLeastVertex(graph, start);

        while(!processed.contains(node)){
            double cost = costs.get(node);
            for(Edge<T> e : graph.getAdjEdges(node)){
                double newCost = cost + e.getWeight();
                if(costs.get(e.getHead()) > newCost){
                    costs.put(e.getHead(), newCost);
                    predecessors.put(e.getHead(), node);
                }
            }
            processed.add(node);
            if(node.equals(end)) break;
            node = getLeastCost.get();
        }

        T current = end;

        while(!current.equals(start)){
            path.add(new Edge<T> (current, predecessors.get(current)));
            current = predecessors.get(current);
        }
        return path;
    }

    private static <T> T getLeastVertex(WeightedGraph<T> graph, T start){
        Graph.Pair<T> current = new Pair<>(start, Double.MAX_VALUE);
        LinkedList<Edge<T>> edges = graph.getAdjEdges(start);

        for(Edge<T> e : edges){
            if(e.getWeight() < current.getCost()){
                current = new Pair<T>(e.getHead(), e.getWeight());
            }
        }
        return current.getComponent();
    }
}