public class TreeNode {
    private TreeNode left;
    private TreeNode right;
    
    private int data;
    
    public TreeNode(){
        left = null;
        right = null;
        
        data = 0;
    }
    
    public TreeNode(int value){
        left = null;
        right = null;

        data = value;
    }
    
    public TreeNode getLeft(){
        return left;
    }
    
    public TreeNode getRight(){
        return right;
    }
    
    public int getData(){
        return data;
    }
    
    public void setLeft(TreeNode node){
        left=node;
    }
    
    public void setRight(TreeNode node){
        right=node;
    }
    
    public void setData(int value){
        data = value;
    }
}