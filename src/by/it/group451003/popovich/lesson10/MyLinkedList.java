package by.it.group451003.popovich.lesson10;

import java.util.Deque;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements Deque<E> {

    private static class Node<E> {
        E data;
        Node<E> prev;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    private Node<E> first;
    private Node<E> last;
    private int size;

    public MyLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        Node<E> current = first;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean add(E element) {
        addLast(element);
        return true;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<E> toRemove;
        if (index == 0) {
            toRemove = first;
            first = first.next;
            if (first != null) {
                first.prev = null;
            } else {
                last = null;
            }
        } else if (index == size - 1) {
            toRemove = last;
            last = last.prev;
            last.next = null;
        } else {
            toRemove = getNode(index);
            toRemove.prev.next = toRemove.next;
            toRemove.next.prev = toRemove.prev;
        }

        E removedData = toRemove.data;
        toRemove.data = null;
        toRemove.prev = null;
        toRemove.next = null;
        size--;
        return removedData;
    }

    @Override
    public boolean remove(Object element) {
        if (element == null) {
            return false;
        }

        Node<E> current = first;
        int index = 0;
        while (current != null) {
            if (element.equals(current.data)) {
                remove(index);
                return true;
            }
            current = current.next;
            index++;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(E element) {
        if (element == null) {
            throw new NullPointerException();
        }

        Node<E> newNode = new Node<>(element);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        }
        size++;
    }

    @Override
    public void addLast(E element) {
        if (element == null) {
            throw new NullPointerException();
        }

        Node<E> newNode = new Node<>(element);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            newNode.prev = last;
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return first.data;
    }

    @Override
    public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return last.data;
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }

        E data = first.data;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        size--;
        return data;
    }

    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }

        E data = last.data;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        size--;
        return data;
    }

    private Node<E> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<E> current;
        if (index < size / 2) {
            current = first;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = last;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Остальные методы интерфейса Deque - необязательные для этого задания

    @Override
    public boolean offerFirst(E e) { return false; }

    @Override
    public boolean offerLast(E e) { return false; }

    @Override
    public E removeFirst() { return null; }

    @Override
    public E removeLast() { return null; }

    @Override
    public E peekFirst() { return null; }

    @Override
    public E peekLast() { return null; }

    @Override
    public boolean removeFirstOccurrence(Object o) { return false; }

    @Override
    public boolean removeLastOccurrence(Object o) { return false; }

    @Override
    public boolean offer(E e) { return false; }

    @Override
    public E remove() { return null; }

    @Override
    public E peek() { return null; }

    @Override
    public boolean addAll(java.util.Collection<? extends E> c) { return false; }

    @Override
    public boolean removeAll(java.util.Collection<?> c) { return false; }

    @Override
    public boolean retainAll(java.util.Collection<?> c) { return false; }

    @Override
    public void clear() {}

    @Override
    public void push(E e) {}

    @Override
    public E pop() { return null; }

    @Override
    public boolean contains(Object o) { return false; }

    @Override
    public boolean containsAll(java.util.Collection<?> c) { return false; }

    @Override
    public java.util.Iterator<E> iterator() { return null; }

    @Override
    public java.util.Iterator<E> descendingIterator() { return null; }

    @Override
    public Object[] toArray() { return new Object[0]; }

    @Override
    public <T> T[] toArray(T[] a) { return null; }
}