package sort;

import java.util.Arrays;

public class Bubblesort {
	int[] arr = {5,88,3,91,1,33,45,6};
	int temp=0;
	int index=0;
	
	public void sort(){
		for(int i=arr.length-1;i>=2;i--){
			System.out.println("---------------------------------------------");
			for(int j=0;j<i;j++){
				if(arr[j]>arr[j+1]){
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
				System.out.print(j+"-"+(j+1)+" 비교:"+Arrays.toString(arr)+"\n");
			}
		}
		System.out.println("결과: "+Arrays.toString(arr));
	}

	public static void main(String[] args) {
		Bubblesort bs = new Bubblesort();
		bs.sort();

	}

}
