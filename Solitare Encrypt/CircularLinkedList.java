/* Author: Jonah Malriat
 * Date: 09/25/2020
 * Course: CIS 2168
 * Project: Creates the data structure for a circular linked list. Allows for manipulation of this
 * data through methods such as get(), set(), add(), remove(), size(). and toString(). 
 * */

package circularlinkedlist;
import java.util.Iterator;

public class CircularLinkedList<E> implements Iterable<E> {
	Node<E> head;
	Node<E> tail;
	int size;

	public CircularLinkedList() {
		size = 0;
	}

	public int size() {
		return size;
	}

	private Node<E> getNode(int index) {
		Node<E> current = head;
		if ((index < 0) || (index > size)) {
			throw new IndexOutOfBoundsException("Index Out Of Bounds.");
		}
		else {
			for (int i = 0; i < index; i++) {
				current = current.next;
			}
			return current;
		}
	}

	public E get(int index) {
		if ((index < 0) || (index > size)) {
			throw new IndexOutOfBoundsException("Index Out Of Bounds.");
		}
		return getNode(index).item;
	}

	public E set(int index, E item) {
		if ((index < 0) || (index > size)) {
			throw new IndexOutOfBoundsException("Index Out Of Bounds.");
		}
		Node<E> target = getNode(index);
		E oldData = target.item;
		target.item = item;
		return oldData;
	}

	public boolean add(E item) {
		this.add(size, item);
		return true;
	}

	public void add(int index, E item){
		Node<E> addnode = new Node(item);
		if ((index < 0) || (index > size)) {
			throw new IndexOutOfBoundsException("Index Out Of Bounds.");
		}
		else if (size == 0) {
			this.tail = addnode;
			this.head = addnode;
		}
		else if (index == 0) {
			addnode.next = head;
			head = addnode;
			tail.next = head;
		}
		else if (index == size) {
			tail.next = addnode;
			tail = addnode;
			tail.next = head;
		}
		else {
			Node<E> before = getNode(index - 1);
			addnode.next = before.next;
			before.next = addnode;
		}
		size++;
	}

	public E remove(int index) {
		E toReturn = null;
		if ((index < 0) || (index > size)) {
			throw new IndexOutOfBoundsException("Index Out Of Bounds.");
		}
		else if (size == 1) {
			toReturn = head.item;
			head = null;
			tail = null;
		}
		else if (index == 0) {
			toReturn = head.item;
			head = head.next;
			tail.next = head;
		}
		else if (index == size) {
			Node<E> before = getNode(index - 1);
			toReturn = tail.item;
			before.next = tail.next;
			tail = before;
		}
		else {
			Node<E> before = getNode(index - 1);
			toReturn = before.next.item;
			before.next = before.next.next;
		}
		size--;
		return toReturn;
	}
	
	public String toString(){
		Node<E> current =  head;
		StringBuilder result = new StringBuilder();
		if (size == 0){
			return "";
		}
		if (size == 1) {
			return head.item.toString();	
		}
		else {
			do {
				result.append(current.item);
				result.append(" ==> ");
				current = current.next;
			} while(current != head);
		}
		return result.toString();
	}
	
	public Iterator<E> iterator() {
		return new ListIterator<E>();
	}
	
	private class ListIterator<E> implements Iterator<E>{
		
		Node<E> nextItem;
		Node<E> prev;
		int index;
		
		@SuppressWarnings("unchecked")
		public ListIterator(){
			nextItem = (Node<E>) head;
			index = 0;
		}

		public boolean hasNext() {
			return size != 0;
		}
		
		public E next() {
			prev =  nextItem;
			nextItem = nextItem.next;
			index =  (index + 1) % size;
			return prev.item;
	
		}
		
		public void remove() {
			int target;
			if(nextItem == head) {
				target = size - 1;
			} else{ 
				target = index - 1;
				index--;
			}
			CircularLinkedList.this.remove(target); 
		}
	}
	
	private static class Node<E>{
		E item;
		Node<E> next;
		public Node(E item) {
			this.item = item;
		}
	}

	public static void main(String[] args){
//		CircularLinkedList<Integer> list = new CircularLinkedList<>();
//		int[] attempt = new int[]{4, 6, 26, 23, 15, 19, 9, 22, 27, 1, 3, 12, 24, 18, 2, 14, 11, 7, 13, 5, 28, 8, 10, 16, 17, 20, 21, 25};
//		for(int i = 0; i < attempt.length; i++) {
//			list.add(attempt[i]);
//		}
//
//		System.out.println(list);
	}
}
