
import java.util.*;
import java.lang.*;
public class MyLinkedList<E>
{
    Node<E> first;
    Node<E> last;
    int size;
    Node<E> iter;
    
    public MyLinkedList()
    {
       first = null;
       last = null;
       iter =first;
       size=0;
    }
    
    public Node<E> getFirst(){
        return first;
    }
    
    public boolean isEmpty() {
        
        return (size==0);
        
    }
    
    public int size(){
        
        return size;
    }
    public void appendList(MyLinkedList<E> other) {
        if (!other.isEmpty()) {
        last.setNext(other.first);
        other.first.setPrev(last);
        last=other.last;
    }
    size+=other.size;
        
    }
    
   
    
    public void addFirst(E info) {
        
        Node<E> n =new Node<>();
        n.setInfo(info);
        
       if (isEmpty())  
           last=n;
           
    
       else {
        n.setNext(first);
        first.setPrev(n);
        
    }
    first=n;
    size++;
        
    }
    
    public E removeFirst() {
        
    if (!isEmpty()) {
        
    E val = first.getInfo();
        if (size>1) {
       
        first.getNext().setPrev(null);
        first=first.getNext();
        size--;
        
    }
    else if (size==1) {
       
         first=null;
         last=null;
         size--;
         
        
    }
      return val;  
    }
    else 
    throw new NoSuchElementException();

    }
    
    
    public E removeLast() {
        
    if (!isEmpty()) {
        
   
        E val = last.getInfo();
        if (size>1) {
        last.getPrev().setNext(null);
        last=last.getPrev();
        size--;
    }
       else if (size==1) {
           
         first=null;
         last=null;
         size--;
        }
        return val;
    }
    else 
    
        throw new NoSuchElementException();
}   
   
    public E remove(int k) {
        
        if (!isEmpty()) {
        Node<E> temp = first;
        
        if ((k>=0) && (k<size)) {
            if (k==0) return removeFirst();
            else if (k==size-1) return removeLast();
            else {
                // get to k
               // int i=0;
                for (int i=0;i<k;i++) 
                temp = temp.getNext();
                E val=temp.getInfo();
              temp.getPrev().setNext(temp.getNext());
              temp.getNext().setPrev(temp.getPrev());
              size--;
              return val;
                
            }
        }
        else throw new IndexOutOfBoundsException();
    }
            else {
                System.out.println("list empty ..");
            
                throw new NoSuchElementException();
        }
    }
    
    
    // adds an item to the end of the list with info field set to val
    public void addLast(E val) {
        Node<E> n =new Node<>();
        n.setInfo(val);
       if (isEmpty())  
           first=n;
       else {
        n.setPrev(last);
        last.setNext(n);
        }
    last=n;
    size++;
    }
    
    // prints all items in the list from first to last taking care of situations when the list is empty
    // use exception handling
    public void printListForward() {
        if (!isEmpty()){
            Node<E> temp = first;
            do {
            System.out.print(temp.getInfo()+"  ");  
            temp = temp.getNext();  
        } while (temp!=null);
        
    }
        else {
               System.out.println("empty list ...");
            throw new NoSuchElementException();
        }
        
    }
    
    // prints all items in the list from last to first taking care of situations when the list is em
    // use exception handling
    public void printListBackward() {
        
        if (!isEmpty()){
            Node<E> temp = last;
            do {
            System.out.println(temp.getInfo());
            temp = temp.getPrev(); 
            } while (temp!=null);
         }
    else throw new NoSuchElementException();
    }
    //returns true if and only if the list contains at least one element e such that 
    //Objects.equals(o,e)
    //return false if the list is empty
    public boolean contains(Object o) {
        if (!isEmpty()){
            Node<E> temp = first;
            
            do {
            if (Objects.equals(temp.getInfo(),(E)o))
            return true; 
            temp=temp.getNext();
            } while (temp!=null);
         return false;
        }
        else throw new NoSuchElementException();
    }
    
    // brings the current list back to an empty list
    public void clear() {
        first=null;
        last=null;
        size=0;
        
    }
    // returns the info value stored at node i 
    // throw IndexOutOfBounds exception of i is out of bounds or the list is empty
    public E get(int i) {
        int j=0;
        if (i>=0 && i<size) {
        if (!isEmpty()){
            Node<E> temp = first;
            do {
            if (i==j) return temp.getInfo();  
            temp = temp.getNext();  
            j++;
        } while (temp!=null);
    }
     throw new NoSuchElementException();
    }
    else  throw new IndexOutOfBoundsException();
}
    
    // compares this MyLinkedList with the parameter otherList 
    // and returns true if the linkedlists have identical values from beginning to end
    // same size and this.get(i).equals(otherList.get(i)) should be true for all i
    // lists can be empty in which case return true
    //should run in O(n) time where n is the size of each list.
    public boolean equals(Object otherList) {
        if (otherList instanceof MyLinkedList) {
             MyLinkedList<E> p =(MyLinkedList<E>)otherList;
             if (size!=p.size) return false;
             else if (isEmpty()&&p.isEmpty()) return true;
             else {
                    Node<E> t1= this.first;
                    Node<E> t2 = p.first;
                    do { 
                        E e1 = t1.getInfo();
                        E e2 = t2.getInfo();
                        if (!e1.equals(e2)) return false;
                        t1=t1.getNext();
                        t2=t2.getNext();
                        }  while (t1!=null && t2!=null);
                        }
                        return true;
 
         }
 else return false;
}


public MyLinkedList<E> uniqueList() {
    HashSet<E> hs = new HashSet<>();
    MyLinkedList<E> unique = new MyLinkedList<>();
    iter=first;
    while (iter!=null) {
       hs.add(iter.getInfo());
       iter=iter.getNext();
    }
   
   for (E val: hs)
      unique.addFirst(val);
    
  return unique;
    
}

public MyLinkedList<E> commonValues (MyLinkedList<E> other) {
    MyLinkedList<E> otherUnique = other.uniqueList();
    MyLinkedList<E> result=new MyLinkedList<>();
    
    HashSet<E> hs = new HashSet<>();
     iter=this.first;
    while (iter!=null) {
       hs.add(iter.getInfo());
       iter=iter.getNext();
    }
    iter=otherUnique.first;
    while (iter!=null) {
        E inf=iter.getInfo();
        if (hs.contains(inf))
          result.addFirst(inf);
          iter=iter.getNext();
        }
        
    return result;
}

public MyLinkedList<E> clone(){
     MyLinkedList<E> temp = new MyLinkedList<>();
     iter=this.first;
     while (iter!=null) {
     temp.addFirst(iter.getInfo());
     iter=iter.getNext();
    }
    return temp;
    
    
}

public MyLinkedList<E> union (MyLinkedList<E> other) {
    MyLinkedList<E> combine = clone();
    iter=other.first;
     while (iter!=null) {
     combine.addFirst(iter.getInfo());
     iter=iter.getNext();
    // other.next();
    }
    return combine.uniqueList();
    
}
    
public static void main(String[] args) {
    MyLinkedList<Integer> m1 = new MyLinkedList<>();
    m1.addFirst(1);m1.addFirst(2);
     m1.addFirst(1);m1.addFirst(5);
      m1.addFirst(3);m1.addFirst(3);
       m1.addFirst(5);m1.addFirst(4);
       //System.out.println(m1.contains(4));
       
      // ml.uniqueList();
      MyLinkedList<Integer> m2 = new MyLinkedList<>();
       m2.addFirst(1);m2.addFirst(2);
        m2.addFirst(5);m2.addFirst(5);
         m2.addFirst(10);m2.addFirst(10);
          m2.addFirst(11);m2.addFirst(11);
          
          
         m1.appendList(m2);
        // m1.printListForward();
        while (!m1.isEmpty())
         System.out.println(m1.removeFirst());
        //  m1.commonValues(m2).printListForward();
          
       
    
    
    
    
    
}
}
    


    
    

    
    