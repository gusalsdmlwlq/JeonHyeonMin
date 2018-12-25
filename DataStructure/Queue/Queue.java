package Queue;

import javax.swing.JOptionPane;

public class Queue<E> extends LinkedList.LinkedList_<E>{
	public Queue()
	{
		//this.head.next = this.tail;
		//this.tail.next = this.head;
		this.head = new Node();
		this.tail = new Node();
	}
	public void enqueue(E data)
	{
		addlast(data);
	}
	
	public E dequeue()
	{
		return removefirst();
	}
	
	public E first()
	{
		//return head.next.data;
		return head.data;
	}
	
	public int size()
	{
		return size;
	}
	
	public boolean isEmpty()
	{
		return (size == 0);
	}
	
	public static void main(String args[])
	{
		boolean exit = true;
		Queue<Object> queue = new Queue<Object>();
		String menu;
		System.out.println("Queue");
		while(exit)
		{
			menu = JOptionPane.showInputDialog("(1~6�� �Է��ϼ���.)\n1.enqueue\n2.dequeue\n3.first\n4.size\n5.isEmpty\n6.exit");
			switch(menu)
			{
			case "1": //enqueue
				Object inputdata;
				try
				{
					inputdata = Integer.parseInt(JOptionPane.showInputDialog("enqueue�� �����͸� �Է��ϼ���."));
					queue.enqueue(inputdata);
					System.out.println("enqueue : "+inputdata);
				}
				catch(Exception e)
				{
					System.out.println("���ڸ� �Է��ϼ���.");
				}
				queue.print();
				break;
			case "2": //pop
				System.out.println("dequeue : "+queue.dequeue());
				queue.print();
				break;
			case "3": //top
				System.out.println("first : "+queue.first());
				queue.print();
				break;
			case "4": //size
				System.out.println("size : "+queue.size());
				queue.print();
				break;
			case "5": //isEmpty
				System.out.println(queue.isEmpty());
				queue.print();
				break;
			case "6": //exit
				exit = false;
				break;
			}
		}
	}
}
