
import java.util.Arrays;

 public class ArrayList1<T> {
    private Object[] arr;
    private int size;
    private static final int originalcap = 10;

    public ArrayList1() {
        arr = new Object[originalcap];
        size = 0;
    }
    public void add(T val) {
        increaseCapacity();
        arr[size++] = val;
    }
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) arr[index];
    }
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T temp = (T) arr[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(arr, index + 1, arr, index, numMoved);
        }
        arr[--size] = null; 
        return temp;
    }
    public int size() {
        return size;
    }
    private void increaseCapacity() {
        if (size == arr.length) {
            int newCapacity = arr.length * 2;
            arr = Arrays.copyOf(arr, newCapacity);
        }
    }
}
