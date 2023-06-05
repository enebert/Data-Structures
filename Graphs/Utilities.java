import java.util.*;

public class Utilities {
    public static <T> boolean dfSearch(Graph<T> graph, T key){
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
    
    public static <T> boolean bfSearch(Graph<T> graph, T key){
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
}