package sort;

import java.util.Arrays;

public class Insertionsort {
	int[] arr = {5,88,3,91,1,33,45,6};
	int temp=0;
	int index=0;
	
	public void sort(){
		for(int i=1;i<arr.length;i++){
			temp = arr[i];
			index=i;
			System.out.println("---------------------------------------------");
			for(int j=i-1;j>=0;j--){
				if(arr[i]<arr[j]){
					index = j;
				}
				System.out.print(i+"-"+j+" 비교: "+Arrays.toString(arr)+"\t");
				System.out.println("index : "+index);		
			}
			if(index!=i){
				for(int z=i;z>index;z--){
					arr[z] = arr[z-1];
					System.out.println("이동: "+Arrays.toString(arr));
				}
				arr[index] = temp;
			}
			System.out.println(i+"번째 루프결과: "+Arrays.toString(arr));
		}
		System.out.println("결과 : "+Arrays.toString(arr));
	}

	public static void main(String[] args) {
		Insertionsort t = new Insertionsort();		
		t.sort();
	}

}
