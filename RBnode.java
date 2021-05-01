package redblack;

public class RBnode {
	RBnode parent, right, left;
	Integer value;
	private boolean isRed;
	
	public RBnode(int key) {
		this.value = key;
		isRed = true;
	}
	public RBnode addChild(RBnode child) {
		if(child.value > this.value) {//if child is bigger, it goes on the right
			if(this.right == null) {//it doesn't yet have a right child
				this.right = child;
				child.parent = this;
			}
			else {//if this already has a right child, make it that thing's kid
				this.right.addChild(child);
			}
		}
		else if (child.value < this.value) {//smaller values go on the left
			if(this.left == null) {//same logic as for adding a right child
				this.left = child;
				child.parent = this;
			}
			else {
				this.left.addChild(child);
			}
		}
		else{//meaning the values are equal
			 //do nothing, you can't have 2 nodes with the same value in the same tree
			System.err.println("tried to add duplicate values to red black tree");
		}
		return this;
	}
	public RBnode addChild(int value) {
		RBnode child = new RBnode(value);
		addChild(child);
		return this;
	}
	
	public boolean isRed() {
		return isRed;
	}
	public boolean isBlack() {
		return !isRed;
	}
	public void makeRed() {
		isRed = true;
	}
	public void makeBlack() {
		isRed = false;
	}
	public void flipColor() {
		isRed = !isRed;
	}
	
	public boolean isLeftChild() {
		if(parent.left == this) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean isRightChild() {
		if(parent.right == this) {
			return true;
		}
		else {
			return false;
		}
	}
	

	public RBnode getSibling() {
		if(parent == null) {
			return null;
		}
		else {//else redundant for clarity
			if(isLeftChild()) {
				return parent.right;
			}
			else {
				return parent.left;
			}
		}
	}
	
	public RBnode aunt() {
		if(parent == null) {
			return null;
		}
		else {
			return parent.getSibling();
		}
	}
	
	public RBnode grandparent() {
		if(parent == null) {
			return null;
		}
		else {
			return parent.parent;
		}
	}
	
}
