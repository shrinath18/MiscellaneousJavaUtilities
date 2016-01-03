/*
 * Nick likes to play the lottery. The cost of a single lottery ticket is price. Nick has exactly four banknotes with values b1, b2, b3 and b4 (some of the values may be equal).
 * He wants to know if it's possible to buy a single lottery ticket without getting any change back. In other words, he wants to pay the exact price of a ticket using any subset of his banknotes.
 * Return "POSSIBLE" if it is possible or "IMPOSSIBLE" if it is not (all quotes for clarity).
 * */
public class LotteryTicket {
	public static void main(String args[]){
		LotteryTicket lotteryTicket = new LotteryTicket();
		int [] arr = {1,5,10,50};
		System.out.print("Possible"+lotteryTicket.isPossible(arr, 0, 14, 0));
	}
	public boolean isPossible(int arr[],int startIndex,int reqValue,int curValue){
		boolean possible = false;
		if(curValue == reqValue)
			return true;
		if(curValue > reqValue)
			return false;
		for(int i= startIndex;i<arr.length;i++){
			possible = possible || isPossible(arr, i+1, reqValue, curValue+arr[i]);
		}
		return possible;
	}
}
