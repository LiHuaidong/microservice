package hdli;

import java.util.Arrays;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 0:38 2019/6/18
 */
public class BucketSort {

	public static void main(String[] args) {
		int[] array = new int[] { 12, 23, 33, 11, 2, 11, 3, 12, 3, 12, 11, 33, 44, 1 };
		System.out.println("starting array = [" + Arrays.toString(array) + "]");
		bucketSort(array, 3);
		System.out.println("ending array = [" + Arrays.toString(array) + "]");
	}

	public static void bucketSort(int[] a, int bucketSize) {
		if (a == null || a.length == 0) {
			return;
		}

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

		int bucketCount = (max - min + 1) / bucketSize + 1;
		int[][] buckets = new int[bucketCount][0];

		int len = a.length;
		for (int i = 0; i < len; i++) {
			int index = (a[i] - min + 1) / bucketSize; // 映射至桶
			buckets[index] = append(buckets[index], a[i]);
		}

		int arrIndex = 0;
		for (int[] bucket : buckets) {
			if (bucket.length <= 0) {
				continue;
			}

			InsertionSort.insertionSort(bucket);
			for (int value : bucket) {
				a[arrIndex++] = value;
			}
		}
	}

	public static int[] append(int[] target, int value) {
		target = Arrays.copyOf(target, target.length + 1);
		target[target.length - 1] = value;
		return target;
	}
}
