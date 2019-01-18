package search;

import java.util.Scanner;

public class Binarysearch {
	int[] arr = {1,3,5,6,33,45,88,91};
	int left=0;
	int right=0;
	int index;

	public void search(int data){
		right=arr.length;
		while(true){
			index=(left+right)/2;
			System.out.println("index : "+index+"\tleft : "+left+"\tright : "+right);
			if(arr[index]>data){
				right=index-1;
			}else if(arr[index]<data){
				left=index+1;
			}else if(arr[index]==data){
				System.out.println("현재 "+data+"의 위치는 arr["+index+"]입니다.");
				break;
			}
			if(left>right || left==arr.length){
				System.out.println("데이터가 없습니다.");
				break;
			}
		}
	}
	
	public static void main(String[] args) {	
			Scanner sc = new Scanner(System.in);
			System.out.println("검색할 번호를 입력해주세요.");
			int data = sc.nextInt();
			Binarysearch bs = new Binarysearch();
			bs.search(data);
	}

}
