package hdli;

import java.util.Arrays;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 11:40 2018/11/29
 */
public class ShellSort {

	public static void main(String[] args) {
		int[] array = new int[] { 12, 23, 33, 11, 2, 11, 3, 12, 3, 12, 11, 33, 44, 1 };

		System.out.println("starting array = [" + Arrays.toString(array) + "]");

		shellSort(array);

		System.out.println("ending array = [" + Arrays.toString(array) + "]");
	}

	public static void shellSort(int[] a) {
		int len = a.length;
		int gap = 0;
		while(gap < len) {
			gap = 3*gap + 1;
		}

		while(gap > 0) {
			for(int i=gap; i<len; i++) {
				int tmp = a[i];
				int j = i;
				while(j>=gap && tmp < a[j-gap]) {
					a[j] = a[j-gap];
					j-=gap;
				}
				if(j != i) {
					a[j] = tmp;
				}
			}
			gap = (gap - 1) / 3;
		}
	}

}