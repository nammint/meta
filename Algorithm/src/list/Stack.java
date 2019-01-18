package list;

public class Stack extends Linkedlist{
	
	public void push(int data) {
		addTail(data);
		System.out.println(data+"이 삽입되었습니다.");
	}
	
	public int pop() {
		if(check()){
			System.out.println("스택에 데이터가 없습니다.");
		}else{
			removeTail();
			System.out.println(temp+"이 반환되었습니다.");
			return temp;
		}
		return 0;
	}
	
	public void print() {
		if(check()){
			System.out.println("스택에 데이터가 없습니다.");
		}
		else{
			System.out.println("----------스택을 출력합니다----------");
			list();
			System.out.println("-------------------------------");
		}
	}
	
	public static void main(String[] args) {
		Stack st = new Stack();
		st.pop();
		st.print();
		
		st.push(1);
		st.push(3);
		st.print();
		
		int i = st.pop();
		int j = st.pop();
		int z = st.pop();
		st.print();
		
		System.out.println(i+" "+j+" "+z);
		
	}


	
}
