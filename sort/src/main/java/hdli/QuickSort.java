package hdli;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 10:43 2018/11/28
 */
public class QuickSort {

	public static void main(String[] args) {
		int[] array = new int[] { 23, 33, 11, 2, 11, 3, 12, 3, 12, 11, 0, 33, 44, 12 };

		for(int data : array) {
			System.out.print(data + " ");
		}
		System.out.println();

		partitionSort2(array, 0, array.length - 1);
	}

	private static void partitionSort1(int[] array, int start, int end) {
		int left = start;
		int right = end;
		int tip = array[right];
		while (left != right) {
			for (; left < right && array[left] <= tip; left++);
			swapByIndex(array, left, right);

			for (; right > left && array[right] >= tip; right--);
			swapByIndex(array, left, right);
		}
		if(left > start) partitionSort1(array, start, left - 1);
		if(left < end) partitionSort1(array, left + 1, end);
	}

	private static void partitionSort2(int[] array, int start, int end) {
		int left = start;
		int right = end;
		int tip = array[right];
		while (left != right) {
			for (; left < right && array[left] <= tip; left++);
			for (; right > left && array[right] >= tip; right--);
			swapByIndex(array, left, right);
		}
		swap(array, left, tip);

		if(left > start) partitionSort2(array, start, left - 1);
		if(left < end) partitionSort2(array, left + 1, end);
	}

	public static void swapByIndex(int[] array, int leftIndex, int rightIndex) {
		int tmp = array[leftIndex];
		array[leftIndex] = array[rightIndex];
		array[rightIndex] = tmp;

		for(int data : array) {
			System.out.print(data + " ");
		}
		System.out.println();
	}

	public static void swap(int[] array, int leftIndex, int tip) {
		array[leftIndex] = tip;
		for(int data : array) {
			System.out.print(data + " ");
		}
		System.out.println();
	}
}
