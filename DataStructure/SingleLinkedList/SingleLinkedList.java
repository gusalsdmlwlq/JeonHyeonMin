package SingleLinkedList;

import javax.swing.JOptionPane;

public class SingleLinkedList {
	Node head = new Node();
	Node tail = new Node();
	int size=0;
	
	public static class Node
	{
		Object data;
		Node next;
		public Node() {
			this.next = null;
			this.data = null;
		}
		public Node(int data){
			this(data, null);
		}
		public Node(int data, Node next)
		{
			this.data = data;
			this.next = next;
		}
	}
	
	private void add(int input)
	{
		Node cur = head;
		Node addnode = new Node(input);
		while(true)
		{
			if(head.next == tail || (cur == head && input <= (int)cur.next.data) || 
					cur.next == tail ||
					(cur != head && input >= (int)cur.data && input <= (int)cur.next.data))
			{
				addnode.next = cur.next;
				cur.next = addnode;
				break;
			}
			else
			{
				cur = cur.next;
			}
		}
		if(addnode.next == tail)
			tail.next = addnode;
		this.size++;
	}
	
	private Object remove(int index)
	{
		Object rdata;
		Node cur = head;
		if(index < 1 || index > this.size) //����
		{
			System.out.println("������ ������ ������ϴ�.");
			return null;
		}
		for(int i=0; i<index-1; i++)
		{
			cur = cur.next;
		}
		rdata = cur.next.data;
		cur.next = cur.next.next;
		if(cur.next == tail)
			tail.next = cur;
		this.size--;
		return rdata;
	}
	
	private Object removefirst()
	{
		Object rdata;
		if(head.next == tail)
			return null;
		else
		{
			rdata = head.next.data;
			head.next = head.next.next;
			if(head.next == tail)
				tail.next = head;
			this.size--;
			return rdata;
		}
	}
	
	private Object removelast()
	{
		Node cur = head;
		Object rdata;
		if(tail.next == head)
			return null;
		else
		{
			rdata = tail.next.data;
			for(int i=0; i<size-1; i++)
				cur = cur.next;
			cur.next = tail;
			tail.next = cur;
			this.size--;
			return rdata;
		}
	}
	
	private Object get(int index)
	{
		Object gdata;
		Node cur = head;
		if(index <= 0 || index > this.size) //����
		{
			System.out.println("������ ������ ������ϴ�.");
			return null;
		}
		for(int i=0; i<index; i++)
		{
			cur = cur.next;
		}
		gdata = cur.data;
		return gdata;
	}
	
	private Object set(int index, int input)
	{
		Node cur = head;
		Object predata;
		if(index <= 0 || index > this.size) //����
		{
			System.out.println("������ ������ ������ϴ�.");
			return  null;
		}
		for(int i=0; i<index; i++)
		{
			cur = cur.next;
		}
		predata = cur.data;
		if(cur.data != null) cur.data = input; //�� �����ʹ� set �Ұ�
		return predata;
	}
	
	private void set_(int index, int input) //set �Ŀ� �������� ����
	{
		Node cur = head;
		if(index <= 0 || index > this.size) //����
		{
			System.out.println("������ ������ ������ϴ�.");
			return;
		}
		for(int i=0; i<index; i++)
		{
			cur = cur.next;
		}
		if(cur.data != null) //�� �����ʹ� set �Ұ�
		{
			this.remove(index);
			this.add(input);
		}
	}
	
	private void print()
	{
		Node cur = head;
		for(int i=0; i<this.size; i++)
		{
			cur = cur.next;
			if(cur.data != null) System.out.print(cur.data+" "); //�� �����ʹ� print x
		}
		System.out.println("");
	}
	
	public static void main(String[] args)
	{
		boolean exit = true;
		SingleLinkedList list = new SingleLinkedList();
		String menu;
		list.head.next = list.tail;
		list.tail.next = list.head;
		System.out.println("SingleLinkedList");
		while(exit)
		{
			menu = JOptionPane.showInputDialog("(1~6�� �Է��ϼ���.)\n1.add\n2.remove\n3.get\n4.set\n5.print\n6.exit");
			switch(menu)
			{
			case "1": //add
				int inputdata;
				try
				{
					inputdata = Integer.parseInt(JOptionPane.showInputDialog("add�� ���ڸ� �Է��ϼ���."));
					list.add(inputdata);
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