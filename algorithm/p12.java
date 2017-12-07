package algorithm;
import java.util.Collections;
import java.util.Vector;

public class p12 { //9���� city���� �湮�ϴ� �ּ� ���̸� branch and bound �˰���� dynamic �˰������� ����ؼ� �ɸ��� �ð��� ��
					//dynamic �˰����� ����ؼ� �迭�� �����ذ��� �ּ� ���̸� �����ϱ� ������ city�� ������ ���������� �ɸ��� �ð��� ���� Ŀ��
					//branch and bound �˰����� bound�� ����Ͽ� bound�� ���� ������� �湮�ϸ� ���ʿ��� ������ ���ֱ� ������ city�� ������ �������� dynamic �˰����� ��캸�� �ɸ��� �ð��� ����
public class dynamic{
  public int N = 9;
  public int index;
  public Vector<Integer> V = new Vector<Integer>() ;
  private int cost; //�̵��� �Ÿ�
  private int cities; //��ȸ�� city���� ��
  private int[] visited = new int[N]; //�湮�� city���� �迭
  int [][]M = {{999,1,7,4,5,9,2,3,6},
		  {5,999,4,6,2,7,3,9,8},
		  {8,2,999,3,6,1,5,9,4},
		  {8,1,6,999,4,3,2,5,7},
		  {9,7,5,6,999,2,1,3,8},
		  {6,8,5,4,2,999,3,7,1},
		  {3,5,8,6,9,4,999,7,2},
		  {3,2,9,1,4,6,7,999,5},
		  {3,1,2,4,6,9,5,8,999}
		  };
  public dynamic() {
   cities = 1; 
         cost = 0;
         index = 0;
  }
  public void tsp(int cur) {   //cur : ���� city�� index
   int i;
   if(cities == N && M[cur][0] != 0){  //������ city�� ���� N���̰� ������ġ���� 0��° index�� city�� ���� ���� ���� ��
    V.addElement(cost + M[cur][0]);
    return;
   }
   for(i = 1; i<N ; ++i) {
    if(i==cur) {
     continue;
    }
    if(M[cur][i] != 999 && visited[i] == 0){ // cur��° index�� city���� i��° city�� �����ְ� �湮������ ���� ��
     visited[i] = 1;  //i��° index�� city�� �湮��
     cities++;   //�湮�� city�� �� ����
     cost+=M[cur][i]; //�̵��� �Ÿ� ����
     tsp(i);    //i��° index�� city�� �̵�
     cities--;
     visited[i] = 0;
     cost -=M[cur][i];    
    }   
   } 
  }
  public void getvalue() {
   Collections.sort(V); //vector�� ������������ ������
   System.out.println("minimum length :"+ V.elementAt(0)); //city���� ��ȸ�ϴ� �ּ� �Ÿ��� ���
  }
}
class bound_branch {
	public int[][] lengths = {{999,1,7,4,5,9,2,3,6},
			  {5,999,4,6,2,7,3,9,8},
			  {8,2,999,3,6,1,5,9,4},
			  {8,1,6,999,4,3,2,5,7},
			  {9,7,5,6,999,2,1,3,8},
			  {6,8,5,4,2,999,3,7,1},
			  {3,5,8,6,9,4,999,7,2},
			  {3,2,9,1,4,6,7,999,5},
			  {3,1,2,4,6,9,5,8,999} //city������ ���� ���� ��� 999�� ǥ��
			  };
	pqueue visits = new pqueue();
	int minimumlength=999;
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
		int[] cities = {0,1,1,1,1,1,1,1,1,1}; //�湮�� city�� 0���� ǥ��
		String travel = n.travel;
		for(int i=0; i<travel.length()-1; i++){ //������ path���� ���̵��� ��� ����
			bound_ += lengths[Integer.parseInt(travel.charAt(i)+"")-1][Integer.parseInt(travel.charAt(i+1)+"")-1];
			cities[Integer.parseInt(travel.charAt(i)+"")-1] = 0;
		}
		cities[last-1] = 0;
		for(int j=0; j<9; j++){
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
			if(level<=8 && curnode.bound<minimumlength){
				//System.out.println("travel : "+curnode.travel+" bound:"+curnode.bound+" level:"+curnode.level);
				//���� �ð��� ���ϱ� ���� ������ ����� ���� ����
				curnode.child = new node[9]; //��帶�� 9���� child��带 ����
				for(int i=0; i<9; i++){
					if(lengths[curnode.last-1][i] < 999 && nottravel(i,curnode.travel)){ //�ش� ��忡�� �� �� �ִ� child ������ �湮��
						curnode.child[i] = new node(0,level+1);
						curnode.child[i].travel = curnode.travel+(i+1);
						curnode.child[i].last = i+1;
						curnode.child[i].bound = bounds(curnode.child[i],level+1,curnode.child[i].last);
						if(curnode.child[i].bound<999) visits.add(curnode.child[i]); //child ��尡 promising�� ��� queue�� �߰���
					}
				}
				if(level==8) minimumlength=curnode.bound; //������ ������ �����ϸ� minlength�� ����
			} 
		}
		System.out.println("minimun length : "+minimumlength); //city���� ��ȸ�ϴ� �ּ� �Ÿ��� ���
	}
	public boolean nottravel(int child,String travel){ //child��带 �湮������ �ִ��� ������ ��ȯ
		for(int i=0; i<travel.length(); i++){
			if(Integer.parseInt(travel.charAt(i)+"")==child+1) return false;
		}
		return true;
	}
	public int minlength(int start,int[] cities){ //�� city���� �ٸ� city�� ������ �ּ� �Ÿ��� ��ȯ
		int min=999;
		for(int i=0; i<9; i++){
			if(cities[i]==1){
				if(lengths[start][i]<min) min=lengths[start][i];
			}
		}
		return min;
	}
}
 public static void main(String[] argc){
	 p12 test = new p12();
	 System.out.println("Dynamic");
	 long starts = System.currentTimeMillis();
	 dynamic a = test.new dynamic();
	 a.tsp(0);
	 a.getvalue();
	 long ends = System.currentTimeMillis();
	 System.out.println("Time : "+(ends-starts));
	 System.out.println("Branch and Bound");
	 starts = System.currentTimeMillis();
	 bound_branch b = test.new bound_branch();
	 b.visit();
	 ends = System.currentTimeMillis();
	 System.out.println("Time : "+(ends-starts));
 }
}