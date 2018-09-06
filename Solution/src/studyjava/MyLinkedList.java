package studyjava;

import java.util.NoSuchElementException;

public class MyLinkedList<E> {

	class Node<E>{
		private E item;
		private Node<E> preNode;
		private Node<E> nextNode;
		public Node(E item, Node<E> preNode, Node<E> nextNode){
			this.item = item;
			this.preNode = preNode;
			this.nextNode = nextNode;
		}
		
	}
	
	private int size;
	private Node<E> headNode;
	private Node<E> tailNode;
	
	public MyLinkedList() {
		size = 0;
		this.headNode = new Node(null, null, null);
		this.tailNode = new Node(null, null, null);
		headNode.nextNode = tailNode;
		tailNode.preNode = headNode;
	}
	
	/**
	 * 
	 * */
	private Node<E> getNode(int index) {
		if(index > size >> 1) {
			Node<E> result = tailNode;  
			for(int i = size - 1;i >= index; i--) {
				result = result.preNode;
			}
			return result;
			}else {
			Node<E> result = headNode;  
			for(int i = 0;i <= index;i++) {
				result = result.nextNode;
			}
			return result;
		}
	}
	
    private boolean checkElementIndex(int index) {  
        if(index <0 || index >=size) {  
            return false;  
        }  
        return true;  
    }  

    /** 
     * add element at last
     * @param e 
     * @return 
     */  
    public boolean add(E e) {  
    	linkedLast(e);  
        return true;  
    }  
    /** 
     * add element at first 
     * @param e 
     * @return 
     */  
    public boolean addFirst(E e) {  
        linedFirst(e);  
        return true;  
    }  
    /** 
     * add element at head
     * @param e 
     */  
    private void linedFirst(E e) {  
        Node<E> newNode = new Node<>(e, headNode, headNode.nextNode);  
        headNode.nextNode.preNode = newNode;
        headNode.nextNode = newNode;
        size ++;  
    }
    /** 
     * add element at tail
     * @param e 
     */  
    private void linkedLast(E e) {  
        Node<E> newNode = new Node<>(e, tailNode.preNode, tailNode);  
        tailNode.preNode.nextNode = newNode;
        tailNode.preNode = newNode;
        size ++;  
    }
    /** 
     * Get item by index
     * @param index 
     * @return item
     */  
	public E get(int index) {
		if(!checkElementIndex(index)) {
			throw new IndexOutOfBoundsException();  
		}else {
			return getNode(index).item;
		}
	}
	
    /** 
     * add element at position index
     * @param index 
     * @param e 
     * @return 
     */  
    public boolean add(int index,E e) {  
        if(index==size) {  
            linkedLast(e);  
        }else {  
            addBefore(index, e);  
        }  
        return true;  
    }  
    /** 
     * insert element before a node
     * @param index 
     * @param e 
     */  
    void addBefore(int index, E e) {  
        if(!checkElementIndex(index)) {  
            throw new IndexOutOfBoundsException();  
        }  
        Node<E> oldNode = getNode(index);  
        Node<E> preNode = oldNode.preNode;  
        Node<E> newNode = new Node<>(e, preNode, oldNode);  
        preNode.nextNode = newNode;
        oldNode.preNode = newNode;
        size ++;  
    }  
    /** 
     * remove element by index
     * @param index 
     * @return 
     */  
    public E remove(int index) {  
        return unlink(getNode(index));  
    }  
      
    /** 
     * remove the first element
     * @return 
     */  
    public E removeFirst() {  
        return unlinkFirst();  
    }  
    /** 
     * remove the last element
     * @return 
     */        
    public E removeLast() {  
        return unlinkLast();  
    }  
	
    E unlinkLast() {  
        Node<E> l = tailNode.preNode;  
        if(l == headNode) {  
            throw new NoSuchElementException();  
        }  
        l.preNode.nextNode = l.nextNode;
        l.nextNode.preNode = l.preNode;
        E element = l.item;
        l.item = null;//help gc  
        l.preNode = null;//help gc  
        l.nextNode = null;
        
        size --;  
        return element;  
    }  

    E unlinkFirst() {  
        Node<E> f = headNode.nextNode;  
        if(f == tailNode) {  
            throw new NoSuchElementException();  
        }  
        E element = f.item;  
        f.preNode.nextNode = f.nextNode;
        f.nextNode.preNode = f.preNode; 
        f.item = null;  
        f.nextNode = null;  
        f.preNode = null;
        size --;  
        return element;  
    } 
    
    private E unlink(Node<E> e) {  
        E element = e.item;  
        Node<E> preNode = e.preNode;  
        Node<E> nextNode = e.nextNode;  
        preNode.nextNode = nextNode;
        nextNode.preNode = preNode;
        size --;  
        return element;  
    }  
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 MyLinkedList<String> list = new MyLinkedList<String>();  
		list.add("1");
		list.add("2");
		list.addFirst("3");
		list.add(0,"4");
		list.add(2,"5");
		for(int i=0;i<list.size;i++) {  
            System.out.println(list.get(i));  
        }
		list.remove(1);
		System.out.println("remove 0");
		for(int i=0;i<list.size;i++) {  
            System.out.println(list.get(i));  
        }
		
		list.removeFirst();
		list.removeLast();
		list.removeFirst();
		list.removeLast();
		
		System.out.println("remove first,last");
		for(int i=0;i<list.size;i++) {  
            System.out.println(list.get(i));  
        }		
	}

}
