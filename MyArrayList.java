import java.util.AbstractList;

public class MyArrayList<T> extends AbstractList<T> {
    private T[] items;
    
    private int size;

    @SuppressWarnings("unchecked")
    public MyArrayList (int startLength) {
        int length = 2;
        while (length < startLength) {
            length = length * 2;
        }
        this.size = 0;
        this.items = (T[]) new Object[length];
    }

    public MyArrayList() {
        this(2);
    }

    public int size() {
        int s = 0;
        for (T item : this.items) {
            if (item != null) {
                s++;
            }
            else {
                break;
            }
        }
        return s;
    }

    public T get(int index) {
        if (index < 0 || index > this.size() - 1) {
            throw new IndexOutOfBoundsException("The index is incorrect. Please type one between 0 and " + (this.size -1) + ".");
        }
        return this.items[index];
    }

    @SuppressWarnings("Unchecked")
    private void resize() {
        T[] newArray = (T[]) new Object[2 * this.items.length];
        int i = 0;
        for (T item : this.items) {
            newArray[i] = item;
            i++;
        }
        this.items = newArray;
    }

    public void add(int index, T item) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Tried to add an item to a MyArrayList with too large of an index. Index:" + index + " but size is " + this.size);
        }
        else if (this.size == this.items.length) {
            resize();
            this.items[index] = item;
            this.size++;
        }
        else {
            for (int i = this.size - 1; i > index - 1; i--) {
                this.items[i+1] = this.items[i];
            }
            this.items[index] = item;
            this.size++;
        }
    }

    public boolean add(T item) {
        this.add(this.size, item);
        return true;
    }

    public T set(int index, T item) {
        if (index < 0 || index > this.size -1) {
            throw new IndexOutOfBoundsException("The index is incorrect. Please type one between 0 and " + (this.size -1) + ".");
        }
        T trash = this.items[index];
        this.items[index] = item;
        return trash;
    }

    public T remove(int index) {
        if (index < 0 || index > this.size - 1) {
            throw new IndexOutOfBoundsException("The index is incorrect. Please type one between 0 and " + (this.size -1) + ".");
        }
        T trash = this.items[index];
        for (int i = index + 1; i < this.size; i++) {
            this.items[i-1] = this.items[i];
        }
        this.items[this.size - 1] = null;
        this.size--;
        return trash;
    }

    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.items[i] = null;
        }
        this.size = 0;
    }
}