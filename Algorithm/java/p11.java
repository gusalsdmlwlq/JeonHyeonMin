package algorithm;

public class p11 {
	public int[][] lengths = {{99,5,8,99,99,99,99,99},
	                   {99,99,4,99,4,99,99,99},
	                   {99,99,99,2,99,99,5,99},
	                   {99,99,99,99,99,99,7,7},
	                   {1,99,99,99,99,99,99,99},
	                   {99,6,99,99,2,99,99,99},
	                   {99,99,99,3,99,8,99,99},
	                   {99,99,99,99,99,5,4,99}}; //�Ÿ��� ������ ���̴� 99�� ǥ��
	pqueue visits = new pqueue();
	int minlength=99;
	public class node{
		String travel="";
		node[] child = null;
		int length;
		int bound;
		int level;
		int last;
		node parent;
		node left;
		node right;
		node next;
		node(int b,int level){
			this.bound = b;
			this.parent = null;
			this.left = null;
			this.right = null;
			this.level = level;
			this.next = null;
		}
	}
	public class pqueue{ //bound�� ���� ��� ������� �湮�ϱ� ���� �켱���� queue�� ���
		int size=0;
		node head;
		node tail;
		pqueue(){
			head = new node(0,0);
			tail = new node(0,0);
			head.next=tail;
			tail.parent=head;
		}
		void add(node n){
			if(size==0){
				head.next=n;
				n.parent=head;
				n.next=tail;
				tail.parent=n;
			}
			else{ //queue�� ��带 �߰��ϸ� ���ÿ� bound�� ���� ���� ��尡 �� ������ ������ ����
				node curnode=tail.parent;
				node cur_next;
				while(curnode.parent!=null && n.bound<curnode.bound){
					curnode=curnode.parent;
				}
				cur_next=curnode.next;
				curnode.next.parent=n;
				curnode.next=n;
				n.parent=curnode;
				n.next=cur_next;
			}
			size++;
		}
		node remove(){
			size--;
			node removenode = head.next;
			if(size==0){
				head.next=tail;
				tail.parent=head;
			}
			else{
				head.next = head.next.next;
				head.next.parent=head;
			}
			return removenode;
		}
	}
	public int bounds(node n,int level,int last){
		int bound_=0;
		int[] cities = {0,1,1,1,1,1,1,1,1}; //�湮�� city�� 0���� ǥ��
		String travel = n.travel;
		for(int i=0; i<travel.length()-1; i++){ //������ path���� ���̵��� ��� ����
			bound_ += lengths[Integer.parseInt(travel.charAt(i)+"")-1][Integer.parseInt(travel.charAt(i+1)+"")-1];
			cities[Integer.parseInt(travel.charAt(i)+"")-1] = 0;
		}
		cities[last-1] = 0;
		for(int j=0; j<8; j++){
			if(j==last-1){ //���������� �湮�� city���� �� �� �ִ� �ּ� ���̸� ����
				bound_ += minlength(j,cities);
			}
			else if(cities[j]==1){ //�湮���� ���� city���� ���̸� ����
				if(minlength(j,cities)>lengths[j][0]){ //��������� ���ư��� ���
					bound_ += lengths[j][0];
				}
				else{ //������� �ƴ� �ٸ� city�� ���� ���
					bound_ += minlength(j,cities);
				}
			}
		}
		return bound_;
	}
	public void visit(){
		node root = new node(0,1);
		root.travel="1";
		root.last=1;
		root.bound = bounds(root,1,1);
		node curnode;
		int level;
		visits.add(root);
		while(visits.size!=0){ //queue�� bound�� ���� ������� node�� �߰��ϰ� �տ������� �ϳ��� �湮�ϸ鼭 solution�� ����
			curnode = visits.remove();
			level = curnode.level;
			if(level<=7 && curnode.bound<minlength){ 
				System.out.println("travel : "+curnode.travel+" bound:"+curnode.bound+" level:"+curnode.level);
				curnode.child = new node[8]; //��帶�� 8���� child��带 ����
				for(int i=0; i<8; i++){
					if(lengths[curnode.last-1][i] < 99 && nottravel(i,curnode.travel)){ //�ش� ��忡�� �� �� �ִ� child ������ �湮��
						curnode.child[i] = new node(0,level+1);
						curnode.child[i].travel = curnode.travel+(i+1);
						curnode.child[i].last = i+1;
						curnode.child[i].bound = bounds(curnode.child[i],level+1,curnode.child[i].last);
						if(curnode.child[i].bound<99) visits.add(curnode.child[i]); //child ��尡 promising�� ��� queue�� �߰���
					}
				}
				if(level==7) minlength=curnode.bound; //������ ������ �����ϸ� minlength�� ����
			} 
		}
		System.out.println("minimun length : "+minlength); //city���� ��ȸ�ϴ� �ּ� �Ÿ��� ���
	}
	public boolean nottravel(int child,String travel){ //child��带 �湮������ �ִ��� ������ ��ȯ
		for(int i=0; i<travel.length(); i++){
			if(Integer.parseInt(travel.charAt(i)+"")==child+1) return false;
		}
		return true;
	}
	public int minlength(int start,int[] cities){ //�� city���� �ٸ� city�� ������ �ּ� �Ÿ��� ��ȯ
		int min=99;
		for(int i=0; i<8; i++){
			if(cities[i]==1){
				if(lengths[start][i]<min) min=lengths[start][i];
			}
		}
		return min;
	}
	public static void main(String[] args){
		p11 test = new p11();
		test.visit();
	}
}
