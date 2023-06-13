import java.util.*;
import java.util.function.*;

public class SparseGraph<T> extends Graph<T,T>{
    
    public SparseGraph(){
        numVertices = 0;
        numEdges = 0;
        
        adjacencyList = new Hashtable<>();
    }
    
    public void addAllEdges(ArrayList<Edge<T>> edgeList, boolean directed){
        for(Edge<T> edge : edgeList){
            if(!adjacencyList.contains(edge.getHead())) insertVertex(edge.getHead());
            if(!adjacencyList.containsKey(edge.getTail())) insertVertex(edge.getTail());

            insertEdge(edge, directed);
        }
    }

    public boolean deleteVertex(T key, boolean directed){
        
        if(!adjacencyList.containsKey(key)) return false;
        
        if(directed){
            adjacencyList.remove(key);
            Set<T> temp = adjacencyList.keySet();
            for(T item : temp){
                if(adjacencyList.get(item).contains(key)) adjacencyList.get(item).remove(key);    
            }
        }else{
            LinkedList<T> temp=adjacencyList.get(key);
            adjacencyList.remove(key);
            for(T item : temp){
                adjacencyList.get(item).remove(key);
            }
        }
        numVertices--;
        return true;
    }
    
    public boolean insertEdge(Edge<T> e, boolean directed){
        if(adjacencyList.containsKey(e.getHead()) && adjacencyList.containsKey(e.getTail())){
            if(directed){
                adjacencyList.get(e.getTail()).add(e.getHead());
            }else{
                adjacencyList.get(e.getTail()).add(e.getHead());
                adjacencyList.get(e.getHead()).add(e.getTail());
            }
            numEdges++;
            return true;
        }
        return false;
    }

    public boolean deleteEdge(Edge<T> e, boolean directed){
        if(adjacencyList.containsKey(e.getHead()) && adjacencyList.containsKey(e.getTail())){
            if(directed){
                adjacencyList.get(e.getTail()).remove(e.getHead());
            }else{
                adjacencyList.get(e.getTail()).remove(e.getHead());
                adjacencyList.get(e.getHead()).remove(e.getTail());
            }
            numEdges--;
            return true;
        }
        return false;
    }
    
    public boolean containsEdge(Edge<T> e){
        return adjacencyList.get(e.getTail()).contains(e.getHead());
    }
    
    public Graph<T,T> subgraph(ArrayList<T> vertices){
        SparseGraph<T> sub = new SparseGraph<>();
        sub.addAllVertices(vertices);

        for(T item : vertices){
            for(T v : adjacencyList.get(item)){
                if(vertices.contains(v)) sub.insertEdge(new Edge<T>(item,v), true);
            }
        }
        return sub;
    }

    public LinkedList<T> getAdjacent(T vertex){
        return adjacencyList.get(vertex);
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
}