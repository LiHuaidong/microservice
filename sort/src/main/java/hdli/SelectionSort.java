package hdli;

import java.util.Arrays;

public class SelectionSort {

    public static void main(String[] args) {
        int[] array = new int[] { 12, 23, 33, 11, 2, 11, 3, 12, 3, 12, 11, 33, 44, 1 };
        System.out.println("starting array = [" + Arrays.toString(array) + "]");
        selectionSort(array);
        System.out.println("ending array = [" + Arrays.toString(array) + "]");
    }

    private static void selectionSort(int[] array) {
        int length = array.length;
        int min = 0;
        for (int i = 0; i < length - 1; i++) {
            min = i;
            for (int j = i + 1; j < length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            if(i != min) {
                swap(array, i, min);
            }
        }
    }

    public static void swap(int[] array, int before, int after) {
        int tmp = array[before];
        array[before] = array[after];
        array[after] = tmp;
    }
}
