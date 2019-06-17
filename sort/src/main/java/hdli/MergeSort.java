package hdli;

import java.util.Arrays;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 10:46 2019/6/17
 */
public class MergeSort {

	public static void main(String[] args) {
//		Integer[] a = new Integer[]{23, 12, 33, 11, 2, 11, 3, 12, 3, 12, 11, 33, 44, 1};
//		Integer[] a = new Integer[]{23, 12, 33};
		Integer[] a = new Integer[]{7, 6, 9, 23, 14, 12, 21};
		System.out.println("starting a = [" + Arrays.toString(a) + "]");
		mergeSort(a);
		System.out.println("ending a = [" + Arrays.toString(a) + "]");
	}

	private static <T extends Comparable<? super T>> void mergeSort(T[] a, T[] tmpArray, int leftStart, int rightEnd) {
		if (leftStart < rightEnd) {
			int center = (leftStart + rightEnd) / 2;
			mergeSort(a, tmpArray, leftStart, center);
			mergeSort(a, tmpArray, center + 1, rightEnd);
			merge(a, tmpArray, leftStart, center + 1, rightEnd);

			System.out.println(
					"a = [" + Arrays.toString(a) + "], tmpArray = [" + Arrays.toString(tmpArray) + "], leftStart = [" + leftStart + "], rightEnd = [" + rightEnd + "]");
		}
	}

	public static <T extends Comparable<? super T>> void mergeSort(T[] a) {
		T[] tmpArray = (T[]) new Comparable[a.length];
		mergeSort(a, tmpArray, 0, a.length - 1);
	}

	private static <T extends Comparable<? super T>> void merge(T[] a, T[] tmpArray, int leftStart, int rightStart,
			int rightEnd) {
		int leftEnd = rightStart - 1;
		int tmpPos = leftStart;
		int numElements = rightEnd - leftStart + 1;

		while (leftStart <= leftEnd && rightStart <= rightEnd) {
			if (a[leftStart].compareTo(a[rightStart]) <= 0) {
				tmpArray[tmpPos++] = a[leftStart++];
			} else {
				tmpArray[tmpPos++] = a[rightStart++];
			}
		}

		while (leftStart <= leftEnd) {
			tmpArray[tmpPos++] = a[leftStart++];
		}

		while (rightStart <= rightEnd) {
			tmpArray[tmpPos++] = a[rightStart++];
		}

		for (int i = 0; i < numElements; i++, rightEnd--) {
			a[rightEnd] = tmpArray[rightEnd];
		}
	}

}
