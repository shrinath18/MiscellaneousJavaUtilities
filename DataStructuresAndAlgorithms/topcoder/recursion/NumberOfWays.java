package recursion;

/*
 * Given n, find the number of different ways to write n as the sum of 1,3,4
 * */
public class NumberOfWays {
	public static void main(String args[]){
		int arr[] = {1,3,4};
		NumberOfWays numberOfWays = new NumberOfWays();
	
		System.out.print("Number of ways:"+numberOfWays.numOfWays(arr, 0, 5));
	}
	public int numOfWays(int arr[],int curSum,int reqSum){
		int ways = 0;
		if(curSum == reqSum){
			return 1;
		}
		if(curSum > reqSum)
			return 0;
		
		for(int i=0; i<arr.length;i++){
			ways += numOfWays(arr, curSum+arr[i], reqSum);
		}
		
		return ways;
	}
}
