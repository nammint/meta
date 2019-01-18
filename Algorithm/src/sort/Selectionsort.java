package sort;

import java.util.Arrays;

public class Selectionsort {
	int[] arr = {5,88,3,91,1,33,45,6};
	int temp=0;
	int index=0;
	
	public void sort(){
		for(int i=0;i<=arr.length-2;i++){
			System.out.println("---------------------------------------------");
			index=i;
			for(int j=i+1;j<=arr.length-1;j++){
				if(arr[index]>arr[j]){
					temp = arr[j];
					index = j;
				}
				System.out.print(i+"-"+j+" 비교: "+Arrays.toString(arr)+"\t");
				System.out.println("index : "+index);
			}
			if(index!=i){
				arr[index] = arr[i];
				arr[i] = temp;
			}
			System.out.println((i+1)+"번째 루프결과: "+Arrays.toString(arr));
		}
		System.out.println("결과: "+Arrays.toString(arr));
	}
	
	public static void main(String[] args) {
		Selectionsort st = new Selectionsort();
		st.sort();
	}
}
