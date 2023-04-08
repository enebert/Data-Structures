public class LinkedList{
    private Node head;

    private int size;

    public LinkedList(){
        head = null;
        size = 0;
    }

    public LinkedList(int value){
        head = new Node(value);
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
        Node current = head;

        while(current.getNext() != null){
            current = current.getNext();
        }

        current.setNext(new Node(value));

        this.size++;
    }

    public void insert(int index, int value){
        if(index >= size) throw new ArrayIndexOutOfBoundsException();

        if(index==0) this.insertFirst(value);

        Node previous = null;
        Node current = head;

        for(int i=0; i < index; i++){
            previous = current;
            current = current.getNext();
        }

        previous.setNext(new Node(value));
        previous.getNext().setNext(current);

        this.size++;
    }

    public void deleteFirst(){
        if(size == 1){
            head = null;
        }

        this.head = head.getNext();
        this.size--;
    }

    public void deleteLast(){
        if(size == 1){
            head = null;
            size--;
        }

        Node previous = null;
        Node current = head;

        while(current.getNext() != null){
            previous = current;
            current = current.getNext();
        }

        previous.setNext(null);

        this.size--;
    }

    public void delete(int index){
        if(index >= size) throw new ArrayIndexOutOfBoundsException();
        if(index == 0) deleteFirst();

        Node previous = null;
        Node current = head;

        for(int i=0; i < index; i++){
            previous = current;
            current = current.getNext();
        }

        previous.setNext(current.getNext());
        this.size--;
    }

    public int getSize(){
        return size;
    }
}
