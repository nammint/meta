package list;

public class Queue extends Linkedlist{
	
	public void push(int data) {
		addTail(data);
		System.out.println(data+"이 삽입되었습니다.");
	}
	
	public int pop(){
		if(check()){
			System.out.println("큐에 데이터가 없습니다.");
		}else{
			removeHead();
			System.out.println(temp+"이 반환되었습니다.");
			return temp;
		}
		return 0;
	}
	
	public void print(){
		if(check()){
			System.out.println("큐에 데이터가 없습니다.");
		}
		else{
			System.out.println("----------큐를 출력합니다----------");
			list();
			System.out.println("------------------------------");
		}
	}

	public static void main(String[] args) {
		Queue qu = new Queue();
		qu.pop();
		qu.print();
		
		qu.push(1);
		qu.push(3);
		qu.print();
		
		int i = qu.pop();
		int j = qu.pop();
		int z = qu.pop();
		qu.print();
		
		System.out.println(i+" "+j+" "+z);
	}


}
