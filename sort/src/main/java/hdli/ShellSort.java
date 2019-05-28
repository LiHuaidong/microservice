package hdli;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 11:40 2018/11/29
 */
public class ShellSort {

	public static void main(String[] args) {
		int[] array = new int[] { 12, 23, 33, 11, 2, 11, 3, 12, 3, 12, 11, 33, 44, 1 };
		int length = array.length;

		int incrementNum = length / 2;
		while(incrementNum >= 1) {
			for(int i=0; i<length; i++) {
				for(int j=i; j<length-incrementNum; j+=incrementNum) {
					if(array[j] > array[j + incrementNum]) {
						int temp = array[j];
						array[j] = array[j + incrementNum];
						array[j + incrementNum] = temp;
					}
				}
			}
			incrementNum = incrementNum/2;
			printArray(array);
		}
	}

	private static void printArray(int[] array) {
		for (int data : array) {
			System.out.print(data + " ");
		}
		System.out.println();
	}
}