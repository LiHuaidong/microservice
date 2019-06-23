package hdli;

import java.util.Arrays;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 1:46 2019/6/18
 */
public class RadisSort {

	public static void main(String[] args) {
		int[] array = new int[] { 12, 23, 33, 11, 2, 11, 3, 12, 3, 12, 11, 33, 44, 1 };
		System.out.println("starting array = [" + Arrays.toString(array) + "]");
		radisSort(array);
		System.out.println("ending array = [" + Arrays.toString(array) + "]");
	}

	public static void radisSort(int[] a) {
		// 获取最大值
		int max = a[0];
		for (int value : a) {
			if (value > max) {
				max = value;
			}
		}

		// 获取最大值的数值位数
		int digitCount = getMaxDigit(max);
		int mod = 10;
		int dev = 1;
		for (int i = 0; i < digitCount; i++, dev *= 10, mod *= 10) {
			// 考虑负数的情况，这里扩展一倍队列数，其中 [0-9]对应负数，[10-19]对应正数 (bucket + 10)
			int[][] counter = new int[mod * 2][0];

			for (int j = 0; j < a.length; j++) {
				int bucket = ((a[j] % mod) / dev) + mod;
				counter[bucket] = append(counter[bucket], a[j]);
			}

			int pos = 0;
			for (int[] bucket : counter) {
				for (int value : bucket) {
					a[pos++] = value;
				}
			}
		}

	}

	public static int getMaxDigit(int a) {
		int digit = a == 0 ? 1 : 0;
		while (a != 0) {
			a /= 10;
			digit++;
		}
		return digit;
	}

	public static int[] append(int[] target, int value) {
		target = Arrays.copyOf(target, target.length + 1);
		target[target.length - 1] = value;
		return target;
	}

}
