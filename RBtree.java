package redblack;

public class RBtree {
	RBnode root;
	
	public RBtree(RBnode node) {
		root = node;
	}
	public RBtree(Integer value) {
		root = new RBnode(value);
	}
	public void addNode(RBnode node) {
		if(root != null) {
			root.addChild(node);
		}
		else {
			root = node;
		}
		root.makeBlack();
	}
	void checkViolations() {
		while(RBhandler.fixBlackDepth(root) == null) {
			//wait for it to return not null so you know it's done
		}
		while(RBhandler.fixColorPattern(root) == false) {
			//wait for it to return not false so you know it's done
		}
	}
}
