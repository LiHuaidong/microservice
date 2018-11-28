package hdli;

public class SelectionSort {

    public static void main(String[] args) {
        int[] array = new int[] { 12, 23, 33, 11, 2, 11, 3, 12, 3, 12, 11, 33, 44, 1 };
        int length = array.length;
        privateArray(array);
        int min = 0;
        for (int i = 0; i < length - 1; i++) {
            min = i;
            for (int j = i + 1; j < length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            swapByIndex(array, i, min);
            privateArray(array);
        }
    }

    private static void privateArray(int[] array) {
        for(int data : array) {
            System.out.print(data + " ");
        }
        System.out.println();
    }

    public static void swapByIndex(int[] array, int leftIndex, int rightIndex) {
        int tmp = array[leftIndex];
        array[leftIndex] = array[rightIndex];
        array[rightIndex] = tmp;
    }
}
