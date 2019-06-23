package hdli;

import java.util.Arrays;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 0:23 2019/6/18
 */
public class CountingSort {

	public static void main(String[] args) {
		int[] array = new int[] { 12, 23, 33, 11, 2, 11, 3, 12, 3, 12, 11, 33, 44, 1 };
		System.out.println("starting array = [" + Arrays.toString(array) + "]");
		countingSort(array);
		System.out.println("ending array = [" + Arrays.toString(array) + "]");
	}

	public static void countingSort(int[] a) {
		int min = a[0];
		int max = a[0];
		for (int value : a) {
			if (value < min) {
				min = value;
			}

			if (value > max) {
				max = value;
			}
		}

		int len = max - min + 1;
		int[] c = new int[len];

		for (int value : a) {
			c[value - min]++;
		}

		int sortedIndex = 0;
		for (int j = 0; j < len; j++) {
			while (c[j] > 0) {
				a[sortedIndex++] = j + min;
				c[j]--;
			}
		}
	}
}
