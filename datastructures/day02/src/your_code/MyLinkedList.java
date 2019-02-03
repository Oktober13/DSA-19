package your_code;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Chicken val;
        Node prev;
        Node next;

        private Node(Chicken d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }

        private Node(Chicken d) {
            this.val = d;
            prev = null;
            next = null;
        }
    }

    public MyLinkedList() {
        Node head = null;
        Node tail = null;
        int size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Chicken c) {
        addLast(c);
    }

    public Chicken pop() {
        return removeLast();
    }

    public void addLast(Chicken c) {
        if (size == 0) {
            head = new Node(c);
            tail = head;
        } else {
            tail.next = new Node(c, tail, null);
            tail = tail.next;
        }
        size += 1;
    }

    public void addFirst(Chicken c) {
        if (size == 0) {
            head = new Node(c);
            tail = head;
        } else {
            head.prev = new Node(c, null, head);
            head = head.prev;
        }
        size += 1;
    }

    public Chicken get(int index) {
        Node curr = head;
        for (int count = 0; count < index; count++) {
            curr = curr.next;
        }
        return curr.val;
    }

    public Chicken remove(int index) {
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        Node curr = head;
        for (int count = 0; count < index; count++) {
            curr = curr.next;
        }

        if (curr.prev != null) {
            curr.prev.next = curr.next;
        }
        if (curr.next != null) {
            curr.next.prev = curr.prev;
        }

        size = size -1;
        return curr.val;
    }

    public Chicken removeFirst() {
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        Node runaway = head;

        if (size == 1) {
            head = null;
            tail = null;
        } else {
            Node latter = head.next;
            latter.prev = null;
            head = latter;
        }

        size = size - 1;
        return runaway.val;
    }

    public Chicken removeLast() {
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        Node runaway = tail;

        if (size == 1) {
            head = null;
            tail = null;
        } else {
            Node former = tail.prev;
            former.next = null;
            tail = former;
        }

        size = size - 1;
        return runaway.val;
    }
}