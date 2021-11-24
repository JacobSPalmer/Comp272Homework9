

public class Node<E> // E is any object type
{
    E info;
    Node<E> prev;
    Node<E> next;
    
    public Node()
    {
       info=null;
       prev=null;
       next=null;
    }
    
    public void setNext(Node<E> n){
       next = n; 
        
    }
    
    public void setPrev(Node<E> p){
        prev=p;
    }
    
    public void setInfo(E val) {
        
        info =val;
    }
    
    public E getInfo() {
        
        return info;
        
    }
    
    public Node<E> getNext() {
        return next;
        
    }
      public Node<E> getPrev() {
        return prev;
        
    }
    

    

    
}
