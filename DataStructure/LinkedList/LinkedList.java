package LinkedList;

import javax.swing.JOptionPane;
//head->(1)->(2)->...()<->tail
public class LinkedList<E> {
	public int size;
	public Node head = new Node();
	public Node tail = new Node();
	public LinkedList()
	{
		this.head.next = this.tail;
		this.tail.next = this.head;
	}
	public class Node
	{
		public E data;
		public Node next;
		Node()
		{
			this.data = null;
			this.next = null;
		}
		Node(E data)
		{
			this(data,null);
		}
		Node(E data, Node next)
		{
			this.data = data;
			this.next = next;
		}
	}
	public void add(int index, E data)
	{
		Node cur = head;
		Node addnode = new Node(data);
		if(index > this.size)
		{
			for(int i=0; i<size; i++)
				cur = cur.next;
			addnode.next = cur.next;
			cur.next = addnode;
		}
		else if(index <= 0)
		{
			addnode.next = cur.next;
			cur.next = addnode;
		}
		else
		{
			for(int i=0; i<index-1; i++)
				cur = cur.next;
			addnode.next = cur.next;
			cur.next = addnode;
		}
		if(addnode.next == tail)
			tail.next = addnode;
		this.size++;
	}
	public void addfirst(E data)
	{
		Node addnode = new Node(data);
		addnode.next = head.next;
		head.next = addnode;
		if(addnode.next == tail)
			tail.next = addnode;
		this.size++;
	}
	public void addlast(E data)
	{
		Node addnode = new Node(data);
		tail.next.next = addnode;
		addnode.next = tail;
		tail.next = addnode;
		this.size++;
	}
	public E remove(int index)
	{
		Node cur = head;
		E removedata;
		if(index <= 0 || index > size)
		{
			System.out.println("������ ������ ������ϴ�.");
			return null;
		}
		else
		{
			for(int i=0; i<index-1; i++)
				cur = cur.next;
			removedata = cur.next.data;
			cur.next = cur.next.next;
			this.size--;
			return removedata;
		}
	}
	public E removefirst()
	{
		E removedata;
		if(head.next == tail)
			return null;
		else
		{
			removedata = head.next.data;
			head.next = head.next.next;
			if(head.next == tail)
				tail.next = head;
			this.size--;
			return removedata;
		}
	}
	public E removelast()
	{
		Node cur = head;
		E removedata;
		if(tail.next == head)
			return null;
		else
		{
			removedata = tail.next.data;
			for(int i=0; i<size-1; i++)
				cur = cur.next;
			cur.next = tail;
			tail.next = cur;
			this.size--;
			return removedata;
		}
	}
	public E get(int index)
	{
		Node cur = head;
		if(index <= 0 || index > size)
		{
			System.out.println("������ ������ ������ϴ�.");
			return null;
		}
		else
		{
			for(int i=0; i<index; i++)
				cur = cur.next;
			return cur.data;
		}
	}
	public E set(int index, E data)
	{
		Node cur = head;
		E predata = null;
		if(index <= 0 || index > size)
		{
			System.out.println("������ ������ ������ϴ�.");
			return null;
		}
		else
		{
			for(int i=0; i<index-1; i++)
				cur = cur.next;
			predata = cur.next.data;
			cur.next.data = data;
			return predata;
		}
	}
	public void print()
	{
		Node cur = head;
		System.out.print("( ");
		for(int i=0; i<this.size; i++)
		{
			cur = cur.next;
			System.out.print(cur.data+" ");
		}
		System.out.println(")");
	}
	public static void main(String[] args)
	{
		boolean exit = true;
		LinkedList<Object> list = new LinkedList<Object>();
		String menu;
		System.out.println("LinkedList");
		while(exit)
		{
			menu = JOptionPane.showInputDialog("(1~6�� �Է��ϼ���.)\n1.add\n2.remove\n3.get\n4.set\n5.print\n6.exit");
			switch(menu)
			{
			case "1": //add
				int inputindex;
				Object inputdata;
				try
				{
					inputindex = Integer.parseInt(JOptionPane.showInputDialog("add�� ��ġ�� �Է��ϼ���."));
					inputdata = Integer.parseInt(JOptionPane.showInputDialog("add�� �����͸� �Է��ϼ���."));
					list.add(inputindex,inputdata);
					System.out.println("add : "+inputdata);
				}
				catch(Exception e)
				{
					System.out.println("���ڸ� �Է��ϼ���.");
				}
				break;
			case "2": //remove
				int removeindex;
				Object removedata;
				try
				{
					removeindex = Integer.parseInt(JOptionPane.showInputDialog("remove�� index�� �Է��ϼ���."));
					removedata = list.remove(removeindex);
					System.out.println("remove : "+removedata);
				}
				catch(Exception e)
				{
					System.out.println("���ڸ� �Է��ϼ���.");
				}
				break;
			case "3": //get
				int getindex; 
				Object getdata;
				try
				{
					getindex = Integer.parseInt(JOptionPane.showInputDialog("get�� index�� �Է��ϼ���."));
					getdata = list.get(getindex);
					System.out.println("get : "+getdata);
				}
				catch(Exception e)
				{
					System.out.println("���ڸ� �Է��ϼ���.");
				}
				break;
			case "4": //set
				int setindex, setdata;
				Object predata;
				try
				{
					setindex = Integer.parseInt(JOptionPane.showInputDialog("set�� index�� �Է��ϼ���."));
					setdata = Integer.parseInt(JOptionPane.showInputDialog("set�� data�� �Է��ϼ���."));
					predata = list.set(setindex,setdata);
					if(predata != null) System.out.println("set : "+predata+" -> "+setdata);
				}
				catch(Exception e)
				{
					System.out.println("���ڸ� �Է��ϼ���.");
				}
				break;
			case "5": //print
				list.print();
				break;
			case "6": //exit
				exit = false;
				break;
			}
		}
	}
}
