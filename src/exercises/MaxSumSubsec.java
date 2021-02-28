package exercises;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class MaxSumSubsec {
	
	public static int max3(int a, int b, int c) {
		return Arrays.asList(a,b,c).stream().mapToInt(i -> Integer.valueOf(i)).max().getAsInt();
	}
	
	public static int calculate(List<Integer> array, int left, int right) throws InterruptedException, ExecutionException {
		int maxLeftBorderSum = 0, maxRightBorderSum = 0;
		int leftBorderSum = 0, rightBorderSum = 0;
		int center = (left + right)/2;
		
		if(left == right) {
			return array.get(left) > 0 ? array.get(left) : 0 ;
		}
		
		int maxLeftSum = MaxSumSubsec.calculate(array, left, center);
		int maxRightSum = MaxSumSubsec.calculate(array, center + 1, right);
		
		Supplier<Integer> getTheMaxLeftBorderSum = () -> calculateMaxLeftBorderSum(array, left, maxLeftBorderSum, leftBorderSum, center); 
		Supplier<Integer> getTheMaxRightBorderSum = () -> calculateMaxRightBorderSum(array, right, maxRightBorderSum, rightBorderSum, center);
		
		CompletableFuture<Integer> getMaxLeftBorderSum = CompletableFuture.supplyAsync(getTheMaxLeftBorderSum);
		CompletableFuture<Integer> getMaxRightBorderSum = CompletableFuture.supplyAsync(getTheMaxRightBorderSum);
		
		return MaxSumSubsec.max3(maxRightSum,maxLeftSum, getMaxLeftBorderSum.get()+ getMaxRightBorderSum.get());
	}

	private static int calculateMaxRightBorderSum(List<Integer> array, int right, int maxRightBorderSum, int rightBorderSum,
			int center) {
		for(int i = center + 1; i <= right; i++) {
			rightBorderSum += array.get(i);
			if(rightBorderSum > maxRightBorderSum) {
				maxRightBorderSum = rightBorderSum;
			}
		}
		return maxRightBorderSum;
	}

	private static int calculateMaxLeftBorderSum(List<Integer> array, int left, int maxLeftBorderSum, int leftBorderSum, int center) {
		for (int i = center; i >= left; i--) {
			leftBorderSum += array.get(i);
			if(leftBorderSum > maxLeftBorderSum) {
				maxLeftBorderSum = leftBorderSum;
			}
		}
		return maxLeftBorderSum;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		var array = Arrays.asList(4,-3,5,-2,-1,2,6,-2);
		System.out.println(array.size() > 0 ? MaxSumSubsec.calculate(array, 0, array.size()-1) : 0);
	}
}
