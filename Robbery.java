// You have a heist getaway sack with a capacity, and n items in front of you
// with sizes and worths. Figure out the maximum value you could
// get with the items.

// You are encouraged to make helper functions!

public class Robbery {

	// Using DP: Get the maximum value with capacity C and n items
	public int max(
		int a,
		int b
	) {
		if(a>b){
			return a;
		}
		return b;
	}
	
	public int maximizeRobWorthRecur(
		int capacity,
		int[] sizes,
		int[] worths,
		int numOfItems
	) {
		// fill in here, change the return
	
		if(numOfItems==0||capacity==0){
			return 0;
		}
		if(sizes[numOfItems-1]>capacity){
			return maximizeRobWorthRecur(capacity,sizes,worths,numOfItems-1);
		}
		else return max(worths[numOfItems-1]+maximizeRobWorthRecur(capacity-sizes[numOfItems-1],sizes,worths,numOfItems-1),
				maximizeRobWorthRecur(capacity,sizes,worths,numOfItems-1));
	}

	public int maximizeRobWorthBottomUp(
		int capacity,
		int[] sizes,
		int[] worths
	) {
		// fill in here, change the return
		int i,j;
		int numOfItems=worths.length;
		int s[][]=new int [numOfItems+1][capacity+1];
		
		for(i=0;i<=numOfItems;i++){
			for(j=0;j<=capacity;j++){
			if(i==0||capacity==0){
				s[i][j]=0;
			}
			else if (sizes[i-1]<=j){
				s[i][j]=max(worths[i-1]+s[i-1][j-sizes[i-1]],s[i-1][j]);
			}
			else
				s[i][j]=s[i-1][j];
				
			}
		}
		return s[numOfItems][capacity];
	}

/**
* Bonus: figure out which items exactly
* Takes in a DP DPTable
* Returns an int array of the individual worths of the items you took
*/
	public int[] takeRobInventory(int[][] DPTable) {
		// fill in here, change the return
		return new int[DPTable.length];
	}

	public static void main(String[] args) {
		Robbery r = new Robbery();
		int bagCapacity = 40;
		int[] itemSizes = {2, 25, 6, 13, 1, 15, 8, 5, 17, 4};
		int[] itemWorths = {35, 120, 900, 344, 29, 64, 67, 95, 33, 10};

		int maxWorthRecur = r.maximizeRobWorthRecur(bagCapacity, itemSizes, itemWorths,itemWorths.length);
		System.out.println("Max worth of the bag: " + maxWorthRecur);
		int maxWorthBottomUp = r.maximizeRobWorthBottomUp(bagCapacity, itemSizes, itemWorths);
		System.out.println("Max worth of the bag: " + maxWorthBottomUp);

		// Bonus: Fill in the helper method takeRobInventory that could help you
		//figure out which items go into the bag that make it max worth. Feel free
		//to change up the parameters and returned types + values of the helper
		// methods above.
		// int[] itemsPicked = r.takeRobInventory();
		// System.out.println("The items we take are worth:")
		// for (int i = 0; i < results.length; i++) {
		// 	System.out.print(results[i] + " ");
	}
}
