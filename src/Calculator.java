public class Calculator {
	/* EXAMPLE
	Coin Fraction	Coin Price		Cost/Sold
	0.011192		4513.1946		50.51167396
	0.011756		4300			50.5508
	-0.02294		4500			103.23
	0.011709		4250			49.76325
	*/
	public static void main (String [] args) {
		double [][] arr = {
						   {0.011192, 4513.1946, 50.51167396}, 
						   {0.011756, 4300, 50.5508},
						   {-0.02294, 4500, 103.23},
						   {0.011709, 4250, 49.76325},
						   {0.01199,  4150,	49.7585}
						  };
		calculate(arr);
	}
	
	public static void calculate(double [][] arr){
		double coins = 0;
		double costBasis = 0;
		for(int i = 0; i < arr.length; i++) {
			// negative means sell
			if(arr[i][0] < 0) {
				coins += arr[i][0];
			}
			else {
				double totalCoins = coins + arr[i][0];
				double newCostBasis = (coins/totalCoins * costBasis) + 
									  (arr[i][0]/totalCoins * arr[i][1]);
				coins = totalCoins;
				costBasis = newCostBasis;
			}
		}
		System.out.println("Coins: " + coins);		
		System.out.println("Cost Basis: " + costBasis);

	}
}
