package hdli;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 17:46 2018/11/21
 */
public class InsertionSort {

	public static void main(String[] args) {
		int[] array = new int[]{12, 23, 33, 11, 2, 11, 3, 12, 3, 12, 11, 33, 44, 1};
		int length = array.length;
		int tmp = 0;
		for (int i = 1; i < length; i++) {
			tmp = array[i];
			int j = i;

//			for (; j > 0; j--) {
//				if(tmp < array[j-1]) {
//					array[j] = array[j-1];
//				} else {
//					break;
//				}
//			}
//			array[j] = tmp;


			while(j>0 && tmp < array[j-1]) {
				array[j] = array[--j];
			}
			array[j] = tmp;

			for(int data : array) {
				System.out.print(data + " ");
			}
			System.out.println();
		}
	}
}
