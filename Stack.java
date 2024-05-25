
public class Stack {
	private Object[] objects;
	private int topOfStack;
	public Stack(int capacity) {
		objects = new Object[capacity];
		topOfStack = - 1;
	}
	public boolean isEmpty() {
		if(topOfStack == -1) {
			return true;
		}
		return false;
	}
	public boolean isFull() {
		if(topOfStack + 1 == objects.length) {
			return true;
		}
		return false;
	}
	public void push(Object data) {
		if(isFull()) {
			System.out.println("Stack overflow");
		}
		else {
			topOfStack++;
			objects[topOfStack] = data;
		}
	}
	public Object pop() {
		if(isEmpty()) {
			System.out.println("Stack is empty.");
			return null;
		}
		else {
			Object retData = objects[topOfStack];
			topOfStack--;
			return retData;
		}
	}
	public Object peek() {
		if(isEmpty()) {
			System.out.println("Stack is empty.");
			return null;
		}
		else {
			return objects[topOfStack];
		}
	}
	public int size() {
		return topOfStack + 1;
	}
}
