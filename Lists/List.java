public class List<T> {
    private GNode<T> head;
    private GNode<T> tail;

    private int size;

    public List(){
        head = null;
        tail = null;

        size = 0;
    }

    public List(T value){
        head = new GNode<>(value);
        tail = head;
        size = 1;
    }

    public String toString(){

        if(head==null) return "";
        GNode<T> current = head;

        String temp = head.getData().toString();

        while(current.getNext() != null){
            current=current.getNext();
            temp = temp + current.getData().toString();
        }
        return temp;
    }

    public T get(int index){
        if(index >= size) throw new ArrayIndexOutOfBoundsException();

        GNode<T> current = head;
        int count = 0;

        while(index != count){
            current=current.getNext();
            count++;
        }

        return current.getData();
    }

    public void insertFirst(T value){
        GNode<T> temp = head;

        head = new GNode<>(value);
        head.setNext(temp);

        this.size++;
    }

    public void insertLast(T value){
        GNode<T> temp = tail;
        tail.setNext(new GNode<>(value));
        tail = temp.getNext();
        tail.setPrevious(temp);

        this.size++;
    }

    public void insert(int index, T value){
        if(index >= size) throw new ArrayIndexOutOfBoundsException();

        if(index==0) this.insertFirst(value);
        if(index==size-1) this.insertLast(value);

        GNode<T> previous = null;
        GNode<T> current = head;

        for(int i=0; i < index; i++){
            previous = current;
            current = current.getNext();
        }

        previous.setNext(new GNode<>(value));
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

        GNode<T> previous = null;
        GNode<T> current = head;

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