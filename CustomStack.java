import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class CustomStack {
    private static final int INITIAL_CAPACITY = 4;

    private int[] data;
    private int size;
    private int capacity;

    public CustomStack(int[] data, int size, int capacity) {
        this.data = new int[INITIAL_CAPACITY];
        this.capacity = INITIAL_CAPACITY;
    }

    public void push(int element) {
        if (this.size == this.capacity) {
            resize();
        }

        this.data[this.size] = element;
        this.size++;
    }

    private void resize() {
        this.capacity *= 2;
        int[] copyArr = new int[this.capacity];

        for (int i = 0; i < this.data.length; i++) {
            copyArr[i] = this.data[i];
        }

        this.data = copyArr;
    }


    public int pop() {
        checkEmpty();
        int element = this.data[this.size - 1];
        this.data[this.size - 1] = 0;
        this.size--;

        if (this.size <= this.capacity / 4) {
            shrink();
        }

        return element;
    }


    private void checkEmpty() {
        if (this.size == 0) {
            throw new NoSuchElementException("Stack is empty");
        }

    }

    private void shrink() {
        this.capacity /= 2;
        int[] copyArr = new int[this.capacity];
        for (int i = 0; i < this.size; i++) {
            copyArr[i] = this.data[i];
        }
        this.data = copyArr;
    }

    public int peek() {
        checkEmpty();
        return this.data[this.size - 1];
    }

    public void forEach(Consumer<Integer> consumer) {
        for (int i = 0; i < this.size; i++) {
            consumer.accept(this.data[i]);
        }
    }
}
