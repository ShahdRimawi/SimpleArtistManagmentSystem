package Link;

public class LinkedListStack<T> {

	private Node<T> top;

	public LinkedListStack() {
		top = null; // initial value
	}

	public T peek() {
		if (isEmpty()) {
			System.out.println("nothing to peek");
			return null;
		}
		return top.data; // without delete
	}

	public boolean isEmpty() {
		return top == null;
	}

	public T pop() { // take data ,and make next the top (removing)
		if (isEmpty()) {
			System.out.println("no data to pop");
		}
		T t = top.data;
		top = top.next;
		return t;

	}

//	public void push(char data) {// make the next top ,add data ,like insert of head
////		Node<T> neww = new Node<>(data);
////		neww.next = top;
////		top = neww;
//		(char)top=data;
//	}
	public void push(T data) {// make the next top ,add data ,like insert of head (adding)
		Node<T> neww = new Node<>(data);
		neww.next = top;
		top = neww;
	}

	public void clear() {
		top = null;
	}

	// balanced method
	public boolean balanced(String s) {

		LinkedListStack<String> ll = new LinkedListStack<String>();

		boolean f = false;
		for (int i = 0; i < s.length() - 1; i++) {
			switch (s.charAt(i)) {
			case '(':
				ll.push("(");
				break;
			case ')':
				String temp = ll.pop();
				String n = "(";
				if (n == temp) {
					f = true;
				} else {
					f = false;
				}
				break;
			}
		}
		if (f == true) {
			return true;

		} else {
			return false;
		}
	}

//	private char pop(char charAt) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

}