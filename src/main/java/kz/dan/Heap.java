package kz.dan;

import java.util.ArrayList;
import java.util.List;

public class Heap {

    private final List<Item> items = new ArrayList<>();
    private int size;

    public void add(Item item) {
        items.add(item);
        size++;
        siftUp(size);
    }

    public Item get() {
        if (size == 0) {
            return null;
        }
        Item result = items.get(0);
        items.set(0, items.get(--size));
        siftDown(0);
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void siftUp(int index) {
        if (index == 1) {
            return;
        }
        int parentIndex = index / 2;
        if (items.get(parentIndex - 1).compareTo(items.get(index - 1)) > 0) {
            Item temp = items.get(parentIndex - 1);
            items.set(parentIndex - 1, items.get(index - 1));
            items.set(index - 1, temp);
            siftUp(parentIndex);
        }
    }

    public void siftDown(int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        if (left >= size) {
            return;
        }
        int indexLargest = left;
        if (right < size && items.get(left).compareTo(items.get(right)) > 0) {
            indexLargest = right;
        }
        if (items.get(index).compareTo(items.get(indexLargest)) > 0) {
            Item temp = items.get(index);
            items.set(index, items.get(indexLargest));
            items.set(indexLargest, temp);
            siftDown(indexLargest);
        }
    }
}


