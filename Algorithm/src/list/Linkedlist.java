package list;

public class Linkedlist {
	 Node head;
	 Node tail;
	 Node node;
	 boolean flag;
	 int temp;
	
	public void addHead(int data) {
		node = new Node(data);
		if(check()) { 
			head = node;
			tail = node;
		}else {
			node.address = head;
			head = node;
		}
	}
	
	public void addTail(int data) {
		node = new Node(data);
		if(check()) { 
			head = node;
			tail = node;
		}else {
			tail.address = node;
			tail = node;
		}
	}
	
	public void removeHead(){
		temp = head.data;
		if(head==tail){
			head=null;
			tail=null;
		}else{
			head = head.address;
		}	
	}
	
	public void removeTail(){
		temp = tail.data;
		if(head==tail){
			head=null;
			tail=null;
		}else{
			node=head;
			while(true){
				if(node.address==tail){
					tail=node;
					node.address=null;
					break;
				}
				node=node.address;
			}
		}
	}
	
	public void list() {
		node=head;
		while(true){
			System.out.print(node.data+"\t");
			if(node.address==null){
				break;
			}
			node=node.address;
		}
		System.out.println();
	}
	
	public boolean check() {
		flag = head == null && tail == null;
		return flag;
	}
	
	public static void main(String[] args) {
		Linkedlist ll = new Linkedlist();
		ll.addHead(1);
		ll.addHead(2);
		ll.addHead(3);
		ll.list();
		
		ll.removeHead();
		ll.addTail(4);
		ll.list();
		
		ll.removeTail();
		ll.list();
	}
}
