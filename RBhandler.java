package redblack;

public class RBhandler {
	public static boolean isRed(RBnode node) {
		if(node == null) {
			return false;
		}
		else {
			return node.isRed();
		}
	}
	public static boolean isBlack(RBnode node) {
		if(node == null) {
			return true;
		}
		else {
			return node.isBlack();
		}
	}
	
	public static Integer fixBlackDepth(RBnode node) {
		Integer depth = 0;
		Integer rightDepth = 0;
		Integer leftDepth = 0;
		if(node == null) {
			return 1;
		}
		else {
			rightDepth = fixBlackDepth(node.right);
			leftDepth = fixBlackDepth(node.left);
			if(rightDepth == null || leftDepth == null) {
				//if they returned null there was an error
				return null;//return null to signal error
			}
			else if(rightDepth != leftDepth) {
				//there should be the same # of black nodes on each side
				fixViolation(node); 
				return null; //returning null will signal there was an error
			}
			else {
				depth = rightDepth;
				if(isBlack(node)) {
					depth++;
				}
				return depth;
			}
		}
	}
	public static boolean fixColorPattern(RBnode node) {//meaning no consecutive red nodes
		if(isRed(node) && isRed(node.parent)) {
			fixViolation(node);
			return false; //return false to show there was an issue
		}
		return true;
		
	}
	
	public static void fixViolation(RBnode problem) {
		RBnode parent, grandparent, aunt;
		
		parent 		= problem.parent;
		grandparent = problem.grandparent();
		aunt 		= problem.aunt();
		
		if(aunt.isRed()) {
			colorFlip(problem);
		}
		else {
			if(parent.isLeftChild()) {
				rightRotate(grandparent);
			}
			else {
				leftRotate(grandparent);
			}
		}
	
	}
	
	static void colorFlip(RBnode node){
		node.parent.makeBlack();
		node.aunt().makeBlack();
		node.grandparent().makeRed();
	}
	
	
	public static RBnode rightRotate(RBnode node) {
		RBnode leftChild = node.left;
		
		leftChild.parent = node.parent; //make left kid move up a generation
		if(node.isLeftChild()) { //if the original node was on the left
			node.parent.left = leftChild;
		}
		else {//that means it was on the right
			node.parent.right = leftChild;
		}
		node.left = leftChild.right;
		
		
		//make what used to be the left child the new parent
		leftChild.right = node;
		node.parent = leftChild;
		return node;
	}
	
	public static RBnode leftRotate(RBnode node) {
		RBnode rightChild = node.right;
		
		//make the right child move up a generation
		rightChild.parent = node.parent;
		if(node.isLeftChild()) { //if the original node was on the left
			node.parent.left = rightChild;
		}
		else {//that means it was on the right
			node.parent.right = rightChild;
		}
		
		//rearrange the grandkids
		node.right = rightChild.left;
		rightChild.left = node;
		node.parent = rightChild;
		return node;
	}

	
	
	//make sure there are no consecutive reds
}
