package hdli;

import java.util.Arrays;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 17:46 2018/11/21
 */
public class InsertionSort {

	public static void main(String[] args) {
		int[] array = new int[] { 12, 23, 33, 11, 2, 11, 3, 12, 3, 12, 11, 33, 44, 1 };
		System.out.println("starting array = [" + Arrays.toString(array) + "]");
		insertionSort(array);
		System.out.println("ending array = [" + Arrays.toString(array) + "]");
	}

	public static void insertionSort(int a[]) {
		int len = a.length;
		for (int i = 1; i < len; i++) {
			int tip = a[i];
			int j = i;
			while (j > 0 && tip < a[j-1]) {
				a[j] = a[j-1];
				j--;
			}
			if (j != i) {
				a[j] = tip;
			}
		}
	}

}
