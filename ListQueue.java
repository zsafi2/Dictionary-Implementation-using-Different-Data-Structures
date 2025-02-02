//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class ListQueue<E> implements Queue<E> {
    private ListQueue<E>.Node front = null;
    private ListQueue<E>.Node back = null;
    private int size = 0;

    public ListQueue() {
    }

    public int getSize() {
        return this.size;
    }

    public void enqueue(E toAdd) {
        if (this.front == null) {
            this.front = new Node(toAdd);
            this.back = this.front;
            this.size = 1;
        } else {
            this.back.next = new Node(toAdd);
            this.back = this.back.next;
            ++this.size;
        }
    }

    public E dequeue() {
        if (this.front == null) {
            return null;
        } else {
            E toRet = this.front.value;
            this.front = this.front.next;
            --this.size;
            return toRet;
        }
    }

    public E front() {
        return this.front == null ? null : this.front.value;
    }

    private class Node {
        E value;
        ListQueue<E>.Node next;

        Node(E newValue) {
            this.value = newValue;
        }
    }
}

