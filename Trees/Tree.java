import java.util.*;

public class Tree {
    private TreeNode root;
    private Random gen = new Random();
    
    public Tree(){
        root = null;
    }
    
    public Tree(int value){
        root = new TreeNode(value);
    }
    
    public Tree(int[] values){
        for(int item : values){
            this.insert(item);
        }
    }
    
    public void prePrint(){
        prePrint(root);
    }
    private void prePrint(TreeNode node){
        System.out.println(node.getData());
        
        if(node.getLeft() != null){
            prePrint(node.getLeft());
        }
        
        if(node.getRight() != null){
            prePrint(node.getRight());
        }
    }
    public void inPrint(){
        inPrint(root);
    }
    
    private void inPrint(TreeNode node){
        if (node == null) return;
        
        inPrint(node.getLeft());
        System.out.println(node.getData());
        inPrint(node.getRight());
    }
    
    private void insertLeaf(int value){
        insert(root, value);
    }
    
    private void insert(TreeNode node, int value){
      if(root == null){
          root = new TreeNode(value);
      }else if(node.getLeft() != null && value < node.getData()){
          insert(node.getLeft(), value);
      }else if(node.getRight() != null && value > node.getData()){
          insert(node.getRight(), value);
      }else{
          if(value < node.getData()){
              node.setLeft(new TreeNode(value));
          }else{
              node.setRight(new TreeNode(value));
          }
      }
    }
    
    private void insertRoot(int value){
        root = insertRoot(root, value);
    }
    
    private TreeNode insertRoot(TreeNode node, int value){
        if(node == null) return new TreeNode(value);
        
        if(value < node.getData()){
            node.setLeft(insertRoot(node.getLeft(), value));
            node = rotateRight(node);
        }else{
            node.setRight(insertRoot(node.getRight(), value));
            node = rotateLeft(node);
        }
        
        return node;
    }
    
    public void insert(int value){
        boolean choice = gen.nextBoolean();
        
        if(choice){
            insertRoot(value);
        }else{
            insertLeaf(value);
        }
    }
    
    public void delete(int value){
        root = delete(root, value);
    }
    
    private TreeNode delete(TreeNode node, int value){
        if(node == null) return node;
        if(value > node.getData()){
            node.setRight(delete(node.getRight(), value));
        }else if(value < node.getData()){
            node.setLeft(delete(node.getLeft(), value));
        }else{
            if(node.getLeft() == null && node.getRight() == null){
                node = null;
            } else if(node.getRight() != null){
                node.setData(successor(node));
                node.setRight(delete(node.getRight(), node.getData()));
            } else{
                node.setData(predecessor(node));
                node.setLeft(delete(node.getLeft(), node.getData()));
            }
        }
        return node;
    }
    
    public void join(Tree other){
        root = join(this.root, other.root);
    }
    
    private TreeNode join(TreeNode nodeA, TreeNode nodeB){
        if ( nodeA == null) return nodeB;
        if ( nodeB == null) return nodeA;
        
        nodeB = insertRoot(nodeB, nodeA.getData());
        
        nodeB.setLeft(join(nodeA.getLeft(), nodeB.getLeft()));
        nodeB.setRight(join(nodeA.getRight(), nodeB.getRight()));
        
        return nodeB;
    }
    
    public boolean search(int value){
        if (this.root == null) return false;
        return search(root, value);
    }
    
    private boolean search(TreeNode node, int value){
        if(node.getData() == value) return true;
        
        if(node.getLeft() != null && value < node.getData()){
            return search(node.getLeft(), value);
        }else if (node.getRight() != null && value > node.getData()){
            return search(node.getRight(), value);
        }
        
        return false;
    }
    
    public TreeNode select(int value){
        return select(root, value);
    }
    
    private TreeNode select(TreeNode node, int value){
        if (node.getData() == value) return node;
        
        if(node.getLeft() != null && value < node.getData()){
            return select(node.getLeft(), value);
        }else if (node.getRight() != null && value > node.getData()){
            return select(node.getRight(), value);
        }
        
        return null;
    }
    
    private TreeNode rotateLeft(TreeNode node){
        TreeNode temp = node.getRight();
        node.setRight(temp.getLeft());
        temp.setLeft(node);
        
        return temp;
    }
    
    private TreeNode rotateRight(TreeNode node){
        TreeNode temp = node.getLeft();
        node.setLeft(temp.getRight());
        temp.setRight(node);

        return temp;
    }
    
    public int minimum(){
        return minimum(root);
    }
    
    private int minimum(TreeNode node){
        if(node.getLeft() == null){
            return node.getData();
        }
        return minimum(node.getLeft());
    }
    
    private int successor(TreeNode node){
        return minimum(node.getRight());
    }
    
    public int maximum(){
        return maximum(root);
    }
    
    private int maximum(TreeNode node){
        if(node.getRight() == null){
            return node.getData();
        }
        return maximum(node.getRight());
    }
    
    private int predecessor(TreeNode node){
        return maximum(node.getLeft());
    }
}
