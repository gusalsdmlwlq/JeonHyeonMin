package algorithm;

public class p2 {
	int[] profit = {0,20,30,35,12,3}; //element���� profit
	int[] weight = {0,2,5,7,3,1}; //element���� weight
	int[] pw = {0,10,6,5,4,3}; //element���� profit/weight
	int max_profit=0;
	int total_profit=0;
	int total_weight=0;
	int w=13;
	queue visits = new queue();
	
	public class node{
		int profit;
		int weight;
		int bound;
		int level;
		node parent;
		node left;
		node right;
		node next;
		node(int p,int w,int b,int level){
			this.profit = p;
			this.weight = w;
			this.bound = b;
			this.parent = null;
			this.left = null;
			this.right = null;
			this.level = level;
			this.next = null;
		}
	}
	public class queue{
		int size=0;
		node head;
		node tail;
		queue(){
			head = new node(0,0,0,0);
			tail = new node(0,0,0,0);
		}
		void add(node n){
			if(size==0){
				head=n;
				tail=n;
			}
			else{
				tail.next = n;
				n.parent = tail;
				tail = n;
			}
			size++;
		}
		node remove(){
			size--;
			node removenode = head;
			if(size==0){
				head = new node(0,0,0,0);
				tail = new node(0,0,0,0);
			}
			else{
				head = head.next;
				head.parent = null;
			}
			return removenode;
		}
	}
	p2(){
		visit();
	}
	void visit(){
		node root = new node(0,0,0,0);
		root.bound = bounds(root,0);
		node curnode;
		int level;
		visits.add(root);
		while(visits.size!=0){ //queue�� �� level�� ������� node�� �߰��ϰ� �տ������� �ϳ��� �湮�ϸ鼭 solution�� ����
			curnode = visits.remove();
			level = curnode.level;
			if(curnode.bound>max_profit && curnode.weight<=w){ //�ش� ��尡 promising�� ��쿡 �Ʒ� �ڵ带 ����
				if(curnode.profit>=max_profit) max_profit = curnode.profit; //max_profit�� ����
				if(level<=4){ //��带 �湮�ϸ� �� ���� element�� ���� ���� ���� �ʴ� 2���� ����� ��带 queue�� �߰���
					curnode.left = new node(curnode.profit+profit[level+1],curnode.weight+weight[level+1],0,level+1);
					curnode.left.bound = bounds(curnode.left,level+1);
					visits.add(curnode.left);
					curnode.right = new node(curnode.profit,curnode.weight,0,level+1);
					curnode.right.bound = bounds(curnode.right,level+1);
					visits.add(curnode.right);
				}
			}
			System.out.println("profit:"+curnode.profit+" weight:"+curnode.weight+" bound:"+curnode.bound+" max_profit:"+max_profit+" level:"+curnode.level);
			//�湮�ϴ� ������ ���
		}
		System.out.println("max_profit : "+max_profit); //�������� ���� �� �ִ� profit�� ���
	}
	
	public int bounds(node n,int level){ //������ bound�� ����
		int bound_=n.profit;
		int weight_=n.weight;
		if(level==5){
			return n.profit;
		}
		if(weight_>w){
			return 0;
		}
		for(int i=level+1; i<=5; i++){ //�� weight�� ��� ������ w�� ���� �ʵ��� ��带 �湮�ϸ� bound�� ����
			if(weight_+weight[i] <= w){
				bound_ += profit[i];
				weight_ += weight[i];
			}
			else{
				bound_ += (w-weight_)*pw[i];
				break;
			}
		}
		return bound_;
	}
	public static void main(String[] args){
		p2 test = new p2();
	}
}
