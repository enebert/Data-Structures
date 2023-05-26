public class GTreeNode<T extends Comparable<T>>{
    private GTreeNode<T> left;
    private GTreeNode<T> right;
    
    private T data;
    
    public GTreeNode(){
        left = null;
        right = null;
        
        data = null;
    }
    
    public GTreeNode(T value){
        left = null;
        right = null;

        data = value;
    }
    
    public GTreeNode<T> getLeft(){
        return left;
    }
    
    public GTreeNode<T> getRight(){
        return right;
    }
    
    public String toString(){
        return data.toString();
    }
    
    public T getData(){
        return data;
    }
    
    public void setData(T value){
        data = value;
    }
    
    public void setLeft(GTreeNode<T> node){
        left=node;
    }
    
    public void setRight(GTreeNode<T> node){
        right=node;
    }
}