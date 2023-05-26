import java.util.*;
import java.util.function.*;

public class GTree<T extends Comparable<T>> {
    private GTreeNode<T> root;
    private Random gen = new Random();
    
    public GTree(){
        root = null;
    }
    
    public GTree(T value){
        root = new GTreeNode<>(value);
    }
    
    public GTree(ArrayList<T> values){
        for(T item : values){
            this.insert(item);
        }
    }
    
    public void preOrder(Consumer<T> method){
        preOrder(root, method);
    }
    
    private void preOrder(GTreeNode<T> node, Consumer<T> method){
        method.accept(node.getData());

        if(node.getLeft() != null){
            preOrder(node.getLeft(), method);
        }

        if(node.getRight() != null){
            preOrder(node.getRight(), method);
        }
    }
    
    public void inOrder(Consumer<T> method){
        inOrder(root, method);
    }
    
    private void inOrder(GTreeNode<T> node, Consumer<T> method){
        if (node == null) return;
        
        inOrder(node.getLeft(), method);
        method.accept(node.getData());
        inOrder(node.getRight(), method);
    }
    
    private void insertLeaf(T value){
        insert(root, value);
    }
    
    private void insert(GTreeNode<T> node, T value){
        if(root == null){
            root = new GTreeNode<>(value);
        }else if(node.getLeft() != null && node.getData().compareTo(value) > 0){
            insert(node.getLeft(), value);
        }else if(node.getRight() != null && node.getData().compareTo(value) < 0){
            insert(node.getRight(), value);
        }else{
            if(node.getData().compareTo(value) > 0){
              node.setLeft(new GTreeNode<>(value));
          }else{
              node.setRight(new GTreeNode<>(value));
          }
      }
    }
    
    private void insertRoot(T value){
        root = insertRoot(root, value);
    }
    
    private GTreeNode<T> insertRoot(GTreeNode<T> node, T value){
        if(node == null) return new GTreeNode<>(value);

        if(node.getData().compareTo(value) > 0){
            node.setLeft(insertRoot(node.getLeft(), value));
            node = rotateRight(node);
        }else{
            node.setRight(insertRoot(node.getRight(), value));
            node = rotateLeft(node);
        }
        
        return node;
    }
    
    public void insert(T value){
        boolean choice = gen.nextBoolean();
        
        if(choice){
            insertRoot(value);
        }else{
            insertLeaf(value);
        }
    }
    
    public void delete(T value){
        root = delete(root, value);
    }
    
    private GTreeNode<T> delete(GTreeNode<T> node, T value){
        if(node == null) return node;
        if(node.getData().compareTo(value) < 0){
            node.setRight(delete(node.getRight(), value));
        }else if(node.getData().compareTo(value) > 0){
            node.setLeft(delete(node.getLeft(), value));
        }else{
            if(node.getLeft() == null && node.getRight() == null){
                node = null;
            } else if(node.getRight() != null){
                node.setData(successor.apply(node));
                node.setRight(delete(node.getRight(), node.getData()));
            } else{
                node.setData(predecessor.apply(node));
                node.setLeft(delete(node.getLeft(), node.getData()));
            }
        }
        return node;
    }
    
    public void join(GTree<T> other){
        root = join(this.root, other.root);
    }
    
    private GTreeNode<T> join(GTreeNode<T> nodeA, GTreeNode<T> nodeB){
        if ( nodeA == null) return nodeB;
        if ( nodeB == null) return nodeA;
        
        nodeB = insertRoot(nodeB, nodeA.getData());
        
        nodeB.setLeft(join(nodeA.getLeft(), nodeB.getLeft()));
        nodeB.setRight(join(nodeA.getRight(), nodeB.getRight()));
        
        return nodeB;
    }
    
    public boolean search(T value){
        if (this.root == null) return false;
        return search(root, value);
    }
    
    private boolean search(GTreeNode<T> node, T value){
        if(node.getData() == value) return true;

        if(node.getLeft() != null && node.getData().compareTo(value) > 0){
            return search(node.getLeft(), value);
        }else if (node.getRight() != null && node.getData().compareTo(value) < 0){
            return search(node.getRight(), value);
        }
        
        return false;
    }
    
    public GTreeNode<T> select(T value){
        return select(root, value);
    }
    
    private GTreeNode<T> select(GTreeNode<T> node, T value){
        if (node.getData() == value) return node;

        if(node.getLeft() != null && node.getData().compareTo(value) > 0){
            return select(node.getLeft(), value);
        }else if (node.getRight() != null && node.getData().compareTo(value) < 0){
            return select(node.getRight(), value);
        }
        
        return null;
    }
    
    private GTreeNode<T> rotateLeft(GTreeNode<T> node){
        GTreeNode<T> temp = node.getRight();
        node.setRight(temp.getLeft());
        temp.setLeft(node);
        
        return temp;
    }
    
    private GTreeNode<T> rotateRight(GTreeNode<T> node){
        GTreeNode<T> temp = node.getLeft();
        node.setLeft(temp.getRight());
        temp.setRight(node);

        return temp;
    }
    
    public T minimum(){
        return minimum(root);
    }
    
    private T minimum(GTreeNode<T> node){
        if(node.getLeft() == null){
            return node.getData();
        }
        return minimum(node.getLeft());
    }
    
    
    public T maximum(){
        return maximum(root);
    }
    
    private T maximum(GTreeNode<T> node){
        if(node.getRight() == null){
            return node.getData();
        }
        return maximum(node.getRight());
    }
    
    Function<GTreeNode<T>, T> predecessor = node -> maximum(node.getLeft());
    Function<GTreeNode<T>, T> successor = node -> minimum(node.getRight());
}
