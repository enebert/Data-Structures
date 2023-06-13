import java.util.*;
import java.util.function.*;

public class Utilities {

    public static <T> SparseGraph<T> buildSPT(WeightedGraph<T> graph, T start, T end){
        PriorityQueue<T> list = new PriorityQueue<>();
        SparseGraph<T> nodeTree = new SparseGraph<>();
        Hashtable<T, Integer> costToVertex = new Hashtable<>();

        return nodeTree;
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