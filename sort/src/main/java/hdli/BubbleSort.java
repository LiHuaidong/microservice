package hdli;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] array = new int[] { 12, 23, 33, 11, 2, 11, 3, 12, 3, 12, 11, 33, 44, 1 };

        bubbleSort(array);
    }

    public static int[] bubbleSort(int[] array) {
        if (array == null) {
            return null;
        }
        int length = array.length;
        System.out.println("length = [" + length + "]");
        for (int i = 0; i < length - 1; i++) {
            boolean isSwap = false;
            for (int j = 0; j < length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    isSwap = true;
                }
            }
            if (!isSwap) {
                break;
            }
            System.out.println(i + " array = [" + Arrays.toString(array) + "]");
        }
        return array;
    }
}
