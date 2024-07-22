public class Queue<T> {

    public LinkedList<T> list;

    public Queue() {
        list = new LinkedList<>();
    }
    public void enqueue(T data) {
        list.add(data);
    }
    public T dequeue() {
        return list.deleteFirst();
    }
    public boolean isEmpty() {
        return list.head == null;
    }
    public void printQueue() {
        Node<T> current = list.head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty.");
        }
        return list.head.data;
    }

}
