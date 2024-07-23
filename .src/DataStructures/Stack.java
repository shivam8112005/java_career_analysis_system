public class Stack<T> {

    public LinkedList<T> list;

    public Stack() {
        list = new LinkedList<>();
    }
    public void push(T data) {
        list.addFirst(data);
    }
    public T pop() {
        T data = list.deleteFirst();
        if (data == null) {
            throw new IllegalStateException("Stack is empty");
        }
        return data;
    }
    public T peek() {
        T data = list.peekFirst();
        if (data == null) {
            throw new IllegalStateException("Stack is empty");
        }
        return data;
    }
    public boolean isEmpty() {
        return list.head == null;
    }
    public int size() {
        int size = 0;
        Node<T> current = list.head;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }
    public void printStack() {
        Node<T> current = list.head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}
