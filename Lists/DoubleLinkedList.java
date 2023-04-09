public class DoubleLinkedList{
    private Node head;
    private Node tail;

    private int size;

    public DoubleLinkedList(){
        head = null;
        tail = null;
        
        size = 0;
    }

    public DoubleLinkedList(int value){
        head = new Node(value);
        tail = head;
        size = 1;
    }

    public String toString(){

        if(head==null) return "";
        Node current = head;

        String temp = String.valueOf(head.getData());

        while(current.getNext() != null){
            current=current.getNext();
            temp = temp + String.valueOf(current.getData());
        }
        return temp;
    }
    
    public String reversePrint(){
        if(head==null) return "";
        Node current = tail;
        
        String temp = String.valueOf(tail.getData());
        
        while(current.getPrevious() != null){
            current=current.getPrevious();
            
            temp = temp + String.valueOf(current.getData());
        }
        return temp;
    }
    
    public int get(int index){
        if(index >= size) throw new ArrayIndexOutOfBoundsException();
        
        Node current = head;
        int count = 0;
        
        while(index != count){
            current=current.getNext();
            count++;
        }
        
        return current.getData();
    }

    public void insertFirst(int value){
        Node temp = head;

        head = new Node(value);
        head.setNext(temp);

        this.size++;
    }

    public void insertLast(int value){
        Node temp = tail;
        tail.setNext(new Node(value));
        tail = temp.getNext();
        tail.setPrevious(temp);

        this.size++;
    }

    public void insert(int index, int value){
        if(index >= size) throw new ArrayIndexOutOfBoundsException();

        if(index==0) this.insertFirst(value);
        if(index==size-1) this.insertLast(value);

        Node previous = null;
        Node current = head;

        for(int i=0; i < index; i++){
            previous = current;
            current = current.getNext();
        }

        previous.setNext(new Node(value));
        previous.getNext().setNext(current);
        
        current.setPrevious(previous.getNext());
        previous.getNext().setPrevious(previous);

        this.size++;
    }

    public void deleteFirst(){
        if(size == 1){
            head = null;
        }

        this.head = head.getNext();
        head.setPrevious(null);
        
        this.size--;
    }

    public void deleteLast(){
        if(size == 1){
            head = null;
            tail = null;
            size--;
        }

       tail = tail.getPrevious();
       tail.setNext(null); 

        this.size--;
    }

    public void delete(int index){
        if(index >= size) throw new ArrayIndexOutOfBoundsException();
        if(index == 0) deleteFirst();
        if(index == size-1) deleteLast();

        Node previous = null;
        Node current = head;

        for(int i=0; i < index; i++){
            previous = current;
            current = current.getNext();
        }

        previous.setNext(current.getNext());
        current.getNext().setPrevious(previous);
        this.size--;
    }

    public int getSize(){
        return size;
    }
}
