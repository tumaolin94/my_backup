package edu.usc.maolintu;

/**
 * @author maolintu
 */
public class NewHashMap<T> {

	private Node<T>[] mapList;	/* hashmap's array including linkedlist nodes*/
	private int capacity; 		/* capacity is the initial size of hashmap */
	private int count = 0; 		/* count is recorded load condition */

	/**
	 * class Node<T>, a key-value object stored in hash map list
	 */
	@SuppressWarnings("hiding")
	public class Node<T> {
		private String key; 
		private T value;
		private Node<T> next = null; /* next node it links */

		public Node(String key, T value) {
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
		}

		public Node<T> getNext() {
			return next;
		}

		public void setNext(Node<T> next) {
			this.next = next;
		}
	}

	/**
	 * NewHashMap(int size)
	 * 
	 * @param size:
	 *            fixed size of hashmap
	 * @throws Exception:
	 *             size < 0
	 */
	@SuppressWarnings("unchecked")
	public NewHashMap(int size) throws Exception {
		if (size <= 0) {
			throw new Exception("Size of hashmap have to more than zero!");
		}
		capacity = size;
		count = 0;
		mapList = new Node[size];
	}

	/**
	 * boolean set(String key, T value)
	 * 
	 * @param key:
	 *            key of the key-value pair
	 * @param value:
	 *            value of the key-value pair
	 * @return if set key-value pair successfully
	 */
	public boolean set(String key, T value) {
		if (key == null || value == null)
			return false;
		int hash = hash(key);

		if (count < capacity) {
			/* means the map is not full */
			if (mapList[hash] == null) {
				mapList[hash] = new Node<>(key, value);
				count++;
			} else {
				Node<T> tempNode = mapList[hash];

				while (tempNode != null && !tempNode.getKey().equals(key)) {
					tempNode = tempNode.next;
				}
				if (tempNode == null) {
					/* means no same key in the hashmap */
					Node<T> newNode = new Node<>(key, value);
					newNode.setNext(mapList[hash].getNext());
					mapList[hash].setNext(newNode);
					count++;
					return true;
				} else {
					/* means exists same key, so update value */
					tempNode.setValue(value);
					return true;
				}
			}
		} else {
			/* means the map has already full, cannot save new Node */
			System.out.println("Sorry, but the map is full!");
			return false;
		}

		return true;
	}

	/**
	 * T get(String key)
	 * 
	 * @param key:
	 *            key of the key-value pair Exception key == null
	 */
	public T get(String key) throws Exception {
		if (key == null) {
			throw new Exception("This is invaild key!");
		}

		int hash = hash(key);

		if (mapList[hash] != null) {
			Node<T> tempNode = mapList[hash];
			while (tempNode != null && !tempNode.getKey().equals(key)) {
				tempNode = tempNode.next;
			}
			if (tempNode != null)
				return tempNode.getValue();
		}
		return null;
	}

	/**
	 * T delete(String key)
	 * 
	 * @param key:
	 *            key of the key-value pair Exception key == null
	 */
	public T delete(String key) throws Exception {
		if (key == null) {
			throw new Exception("This is invaild key!");
		}

		int hash = hash(key);

		if (mapList[hash] != null) {
			Node<T> tempNode = mapList[hash];
			if (mapList[hash].getKey().equals(key)) {
				T value = mapList[hash].getValue();
				mapList[hash] = tempNode.next;
				count--;
				return value;
			}
			while (tempNode.getNext() != null && !tempNode.getNext().getKey().equals(key)) {
				tempNode = tempNode.next;
			}
			if (tempNode.getNext() != null) {
				/* means exists key-value pair need to be deleted */
				T value = tempNode.getNext().getValue();
				tempNode.setNext(tempNode.getNext().getNext());
				count--;
				return value;
			}
		}
		return null;
	}

	/**
	 * float load()
	 * 
	 * @return load
	 */
	public float load() {
		return (float) (count * 1.0 / capacity);
	}

	/**
	 * int hash(String key)
	 * 
	 * @param key:
	 *            key need to be transformed to a unique code
	 * @return hash value: hashCode of input key
	 */
	public int hash(String key) {
		return Math.abs(key.hashCode() % capacity);
	}
	
	/**
	 * int getCount()
	 * @return count: 
	 * 				The number of key-value pairs
	 */
	public int getCount() {
		return this.count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
