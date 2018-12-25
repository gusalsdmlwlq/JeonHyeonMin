package BinarySearchTree;

import Stack.Stack;

public class BinarySearchTree<E> {
	Node root = null;
	BinarySearchTree()
	{
		this.root = null;
	}
	public class Node{
		public Node parent;
		public Node left;
		public Node right;
		public int key;
		public E value;
		Node(int key,E value)
		{
			this.parent = null;
			this.left = null;
			this.right = null;
			this.key = key;
			this.value = value;
		}
		public boolean isexternal(Node node)
		{
			return (node.left == null && node.right == null);
		}
	}
	int height(Node root)
	{
		if(root == null) return 0;
		else if(root.left == null && root.right == null) return 0;
		else
		{
			if(height(root.left) >= height(root.right)) return height(root.left)+1;
			else return height(root.right)+1;
		}
	}
	int depth(Node root)
	{
		if(root.parent == null) return 0;
		else return depth(root.parent)+1;
	}
	Node search(Node root, int key)
	{
		if(root.key == key) return root;
		else if(root.key > key)
		{
			if(root.left == null) return root;
			else return search(root.left,key);
		}
		else
		{
			if(root.right == null) return root;
			else return search(root.right,key);
		}
	}
	Node leftmost(Node root)
	{
		if(root.left == null) return root;
		else return leftmost(root.left);
	}
	Node rightmost(Node root)
	{
		if(root.right == null) return root;
		else return rightmost(root.right);
	}
	E get(int key)
	{
		Node getnode = search(this.root,key);
		if(getnode.key == key) return getnode.value;
		else return null;
	}
	void put(int key,E value)
	{
		Node newnode = new Node(key,value);
		if(root == null) root = new Node(key,value); //ó������ input�� �޴� ���
		else
		{
			Node curnode = search(this.root,key); //key ���� ���� ��带 ã��
			if(curnode.key == newnode.key) //�̹� key ���� ��尡 ����
				curnode.value = value; //value�� ��ü
			else if(curnode.key > newnode.key) //key ���� ���� ��尡 ���� -> left�� ���ο� ��带 ����
			{
				newnode.parent = curnode;
				curnode.left = newnode;
			}
			else if(curnode.key < newnode.key) //right�� ���ο� ��带 ����
			{
				newnode.parent = curnode;
				curnode.right = newnode;
			}
		}
	}
	E remove(int key)
	{
		Node removenode = search(root,key);
		if(removenode.key == key) //remove�Ϸ��� key�� ����
		{
			if(removenode.left == null && removenode.right == null) //removenode�� child�� ���� ���
			{
				if(removenode == root)
					root = null;
				else if(removenode.parent.key > removenode.key) //removenode�� left child�� ���
					removenode.parent.left = null;
				else //removenode�� right child�� ���
					removenode.parent.right = null;
				return removenode.value;
			}
			else if(removenode.left == null & removenode.right != null) //left child�� ���� ���
			{
				if(removenode == root)
					root = removenode.right;
				if(removenode.parent.key > removenode.key) //removenode�� left child�� ���
				{
					removenode.parent.left = removenode.right;
					removenode.right.parent = removenode.parent;
				}
				else //removenode�� right child�� ���
				{
					removenode.parent.right = removenode.right;
					removenode.right.parent = removenode.parent;
				}
				return removenode.value;
			}
			else if(removenode.left != null && removenode.right == null) //right child�� ���� ���
			{
				if(removenode == root)
					root = removenode.left;
				if(removenode.parent.key > removenode.key) //removenode�� left child�� ���
				{
					removenode.parent.left = removenode.left;
					removenode.left.parent = removenode.parent;
				}
				else //removenode�� right child�� ���
				{
					removenode.parent.right = removenode.left;
					removenode.left.parent = removenode.parent;
				}
				return removenode.value;
			}
			else //child�� �� �� �ִ� ���
			{
				E removevalue = removenode.value;
				Node leftmostnode = leftmost(removenode.right);
				Node rightmostnode = rightmost(removenode.left);
				if(depth(leftmostnode) > depth(rightmostnode)) //������� ����� �ٷ� �� ���� �ٷ� ���� ����� depth�� ū ��带 ã��
				{
					removenode.key = leftmostnode.key;
					removenode.value = leftmostnode.value;
					if(leftmostnode.right != null) //leftmostnode�� child�� ���� ���
					{
						if(leftmostnode.key < leftmostnode.parent.key) //leftmostnode�� leftchild
						{
							leftmostnode.parent.left = leftmostnode.right;
							leftmostnode.right.parent = leftmostnode.parent;
						}
						else //leftmostnode�� rightchild
						{
							leftmostnode.parent.right = leftmostnode.right;
							leftmostnode.right.parent = leftmostnode.parent;
						}
					}
					leftmostnode = null;
				}
				else
				{
					removenode.key = rightmostnode.key;
					removenode.value = rightmostnode.value;
					if(rightmostnode.left != null) //rightmostnode�� child�� ���� ���
					{
						if(rightmostnode.key > rightmostnode.parent.key) //rightmostnode�� rightchild
						{
							rightmostnode.parent.right = rightmostnode.left;
							rightmostnode.left.parent = rightmostnode.parent;
						}
						else //rightmostnode�� leftchild
						{
							rightmostnode.parent.left = rightmostnode.left;
							rightmostnode.left.parent = rightmostnode.parent;
						}
					}
					rightmostnode = null;
				}
				return removevalue;
			}
		}
		else return null;
	}
	Node firstentry()
	{
		return leftmost(root);
	}
	Node lastentry()
	{
		return rightmost(root);
	}
	Node floorentry(int key)
	{
		Node curnode = search(root,key);
		if(leftmost(root).key > key) return null;
		if(curnode.key <= key) return curnode;
		while(true)
		{
			if(curnode.key > curnode.parent.key) return curnode.parent;
			curnode = curnode.parent;
		}
	}
	Node seilingentry(int key)
	{
		Node curnode = search(root,key);
		if(rightmost(root).key < key) return null;
		if(curnode.key >= key) return curnode;
		while(true)
		{
			if(curnode.key < curnode.parent.key) return curnode.parent;
			curnode = curnode.parent;
		}
	}
	void printtree()
	{
		print(this.root);
	}
	void print(Node root)
	{
		if(root == null) return;
		else
		{
			print(root.left);
			System.out.println(root.value+" "+depth(root));
			print(root.right);
		}
	}
	void inordertraversal(Node root)
	{
		Stack<Node> s = new Stack<Node>(); //��带 ������ ����
		Node curnode = root;
		if(curnode == null) //�� Ʈ���� ���
			return;
		while(!s.isEmpty() || curnode != null) //��� ��带 �ѹ��� üũ
		{
			if(curnode != null) //leftmost ������ �������鼭 ���ÿ� ��带 ���ʴ�� ����
			{
				s.push(curnode);
				curnode = curnode.left;
			}
			else //leftmost ��忡 �����ϸ� leftmost ��带 ���ÿ��� ���鼭 üũ�ϰ� right�� ���ÿ� ����
			{
				Node n = s.pop();
				System.out.println(n.value);
				curnode = n.right;
			}
		}
	}
	public static void main(String[] args)
	{
		BinarySearchTree<Object> tree = new BinarySearchTree<Object>();
		tree.put(5,"five");
		tree.put(4,"four");
		tree.put(2,"two");
		tree.put(1,"one");
		tree.put(3,"three");
		tree.put(6,"six");
		tree.put(7,"seven");
		tree.put(0,"zero");
		tree.remove(5);
		tree.printtree();
	}
}
