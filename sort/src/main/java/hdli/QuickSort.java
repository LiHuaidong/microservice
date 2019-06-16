package hdli;

import java.util.Arrays;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 10:43 2018/11/28
 */
public class QuickSort {

	public static void main(String[] args) {
		int[] array = new int[] { 23, 33, 11, 2, 11, 3, 12, 3, 12, 11, 0, 33, 44, 12 };
		System.out.println("starting array = [" + Arrays.toString(array) + "]");

		quickSort(array, 0, array.length - 1);

		System.out.println("ending array = [" + Arrays.toString(array) + "]");
	}

	private static void quickSort(int[] array, int start, int end) {
		int left = start;
		int right = end;
		int tip = array[left];
		while (left != right) {
			while (right > left && array[right] >= tip) {
				right--;
			}
			array[left] = array[right];

			while (left < right && array[left] <= tip) {
				left++;
			}
			array[right] = array[left];
		}
		array[left] = tip;

		if (left > start) {
			quickSort(array, start, left - 1);
		}
		if (left < end) {
			quickSort(array, left + 1, end);
		}
	}

}
