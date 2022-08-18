
import java.util.Arrays;
import java.util.NoSuchElementException;

public class HeapPriorityQueue<E extends Comparable<? super E>> implements PriorityQueueInterface<E> {

	// Properties =========================================================
	private E[] heap;
	private int size;
	
	// Constructor ========================================================
	public HeapPriorityQueue() {
		clear();
	}
	
	// Methods ============================================================
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean isFull() {
		return size == heap.length - 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		heap = (E[]) new Comparable[10];
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}
	
	public void add(E newEntry) {
		resize();
		heap[size + 1] = newEntry;
		reheapUp(size + 1);
		size++;
	}
	
	private void resize() {
		if (size == heap.length - 1) {
			heap = Arrays.copyOf(heap, 2 * size + 1);
		}
	}
	
	private void reheapUp(int index) {
		if (index < 2) return;
		int parentIndex = index / 2;
		while (parentIndex > 0 && heap[parentIndex].compareTo(heap[index]) > 0) {
			E tmp = heap[index];
			heap[index] = heap[parentIndex];
			heap[parentIndex] = tmp;
			index = parentIndex;
			parentIndex = index / 2;
		}
	}
	
	@Override
	public E peek() {
		if (isEmpty()) return null;
		return heap[1];
	}

	@Override
	public E remove() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		E ret = heap[1];
		heap[1] = heap[size];
		reheapDown(1);
		size--;
		return ret;
	}
	
	private void reheapDown(int index) {
		if (size == 1) return;
		int parentIndex = index;
		int leftChild = index * 2;
		int rightChild = index * 2 + 1;
		while (leftChild < size) {
			int maxChildIndex = getMaxChildIndex(leftChild, rightChild);
			swap(parentIndex, maxChildIndex);		
			parentIndex = maxChildIndex;
			leftChild = 2 * parentIndex;
			rightChild = 2 * parentIndex + 1;
		}
	}
	
	public String toString() {
		return Arrays.toString(heap);
	}
	
	// Private Helper Methods =============================================================
	private void swap(int i1, int i2) {
		E tmp = heap[i1];
		heap[i1] = heap[i2];
		heap[i2] = tmp;	
	}
	
	private int getMaxChildIndex(int leftIndex, int rightIndex) {
		if (heap[rightIndex] == null) {
			return leftIndex;
		}
		if(heap[rightIndex].compareTo(heap[leftIndex]) > 0) {
			return leftIndex;
		}
		return rightIndex;
	}
	
}
