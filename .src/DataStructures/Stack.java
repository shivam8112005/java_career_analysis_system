public class Stack<T> {

    public LinkedList<T> list;

    public Stack() {
        list = new LinkedList<>();
    }

    // Add an element to the top of the stack
    public void push(T data) {
        list.addFirst(data);
    }

    // Remove and return the element from the top of the stack
    public T pop() {
        T data = list.deleteFirst();
        if (data == null) {
            throw new IllegalStateException("Stack is empty");
        }
        return data;
    }

    // Return the top element without removing it
    public T peek() {
        T data = list.peekFirst();
        if (data == null) {
            throw new IllegalStateException("Stack is empty");
        }
        return data;
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return list.head == null;
    }

    // Get the size of the stack
    public int size() {
        int size = 0;
        Node<T> current = list.head;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    // Print all elements in the stack
    public void printStack() {
        Node<T> current = list.head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}
