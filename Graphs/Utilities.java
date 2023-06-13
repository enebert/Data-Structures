import java.util.*;
import java.util.function.*;

public class Utilities {
    public static <T> boolean dfSearch(SparseGraph<T> graph, T key){
        ArrayList<T> searched = new ArrayList<>();
        Stack<T> unsearched = new Stack<>();
        
        T start = graph.getVertices().iterator().next();
        unsearched.push(start);
        
        while(!unsearched.empty()){
            T current = unsearched.pop();
            if(current.equals(key)) return true;
            
            searched.add(current);
            for(T item : graph.getAdjacent(current)){
                if(!searched.contains(item)) unsearched.push(item);
            }
        }
        
        return false;
    }
    
    public static <T> void dfTraverse(SparseGraph<T> graph, Consumer<T> method){
        ArrayList<T> processed = new ArrayList<>();
        Stack<T> unprocessed = new Stack<>();

        T start = graph.getVertices().iterator().next();
        unprocessed.push(start);

        while(!unprocessed.empty()){
            T current = unprocessed.pop();
            method.accept(current);

            processed.add(current);
            for(T item : graph.getAdjacent(current)){
                if(!processed.contains(item)) unprocessed.push(item);
            }
        }
    }

    public static <T> boolean bfSearch(SparseGraph<T> graph, T key){
        ArrayList<T> searched = new ArrayList<>();
        Deque<T> unsearched = new LinkedList<>();

        T start = graph.getVertices().iterator().next();
        unsearched.addLast(start);

        while(!(unsearched.size()==0)){
            T current = unsearched.removeFirst();
            if(current.equals(key)) return true;

            searched.add(current);

            for(T item : graph.getAdjacent(current)){
                if(!searched.contains(item)) unsearched.addLast(item);
            }
        }

        return false;
    }

    public static <T> void bfTraverse(SparseGraph<T> graph, Consumer<T> method){
        ArrayList<T> processed = new ArrayList<>();
        Deque<T> unprocessed = new LinkedList<>();

        T start = graph.getVertices().iterator().next();
        unprocessed.addLast(start);

        while(!(unprocessed.size()==0)){
            T current = unprocessed.removeFirst();
            method.accept(current);

            processed.add(current);

            for(T item : graph.getAdjacent(current)){
                if(!processed.contains(item)) unprocessed.addLast(item);
            }
        }
    }

    public static <T> Path<T> shortestPath(SparseGraph<T> graph, T start, T end){
        ArrayList<T> searched = new ArrayList<>();
        Deque<T> unsearched = new LinkedList<>();
        HashMap<T, T> predecessors = new HashMap<>();

        Path<T> path = new Path<>();
        unsearched.add(start);

        while(!(unsearched.size()==0)){
            T current = unsearched.removeFirst();
            if(current.equals(end)) break;

            searched.add(current);


            for(T item : graph.getAdjacent(current)){
                if(!searched.contains(item)){
                    unsearched.addLast(item);
                    predecessors.putIfAbsent(item, current);
                }
            }
        }

        T p = end;
        while(!p.equals(start)){
            if(!predecessors.containsKey(p)) return new Path<T>();
            path.add(new Edge<T>(p, predecessors.get(p)));
            p = predecessors.get(p);
        }
        return path;
    }

    public static SparseGraph<Integer> random(int maxVertices){
        Random gen = new Random();
        int t;
        int h;
        Edge<Integer> e; Edge<Integer> f;
        SparseGraph<Integer> randGraph = new SparseGraph<>();
        ArrayList<Edge<Integer>> edgeList = new ArrayList<>();

        int numEdges = gen.nextInt(maxVertices*(maxVertices-1)/2) + 1;

        for(int i=0; i < numEdges; i++){
            do{
                t = gen.nextInt(maxVertices);
                h = gen.nextInt(maxVertices);
                e = new Edge<Integer>(t,h);
                f = new Edge<Integer>(h,t);
            }while(edgeList.contains(e) || edgeList.contains(f) || t==h);
            edgeList.add(e);
        }

        randGraph.addAllEdges(edgeList, false);

        return randGraph;
    }

    public static <T> SparseGraph<T> buildSPT(WeightedGraph<T> graph, T start, T end){
        PriorityQueue<T> list = new PriorityQueue<>();
        SparseGraph<T> nodeTree = new SparseGraph<>();
        Hashtable<T, Integer> costToVertex = new Hashtable<>();

        return nodeTree;
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
        Pair<T> current = new Pair<>(start, Double.MAX_VALUE);
        LinkedList<Edge<T>> edges = graph.getAdjEdges(start);

        for(Edge<T> e : edges){
           if(e.getWeight() < current.getCost()){
                current = new Pair<T>(e.getHead(), e.getWeight());
            }
        }
        return current.getComponent();
    }

    private static class Pair<T>{
        private T component;
        private double cost;

        public Pair(){
            component = null;
            cost = 0.0;
        }

        public Pair(T v, double c){
            component = v;
            cost = c;
        }

        public String toString(){
            return "(Component: " + component + ", cost: " + cost + ")";
        }

        public boolean equals(Object o){
            if(o == this) return true;
            if(!(o instanceof Pair)) return false;

            Pair p = (Pair) o;
            return (component.equals(p.component)) && (Double.compare(cost, p.cost)==0);
        }

        public int hashCode(){
            final int prime = 22859;
            int result = 7691;
            result = prime*result + ((component==null) ? 0 : component.hashCode());

            return result;
        }

        public int compareTo(Object o){
            Pair p = (Pair) o;

            if(this.equals(0)) return 0;

            return Double.compare(this.cost, p.cost);
        }

        public double getCost(){
            return cost;
        }

        public T getComponent(){
            return component;
        }
    }
}