package hdli;

/**
 * Hello world!
 *
 */
public class BubbleSort
{
    public static void main(String[] args) {
        int[] array = new int[] { 12, 23, 33, 11, 2, 11, 3, 12, 3, 12, 11, 33, 44, 1 };
        int length = array.length;
        for (int i = 0; i < length - 1; i++) { // 此处减1是因为两两比较只需要n-1次比较即可判断出大小
            for (int j = 0; j < length - i - 1; j++) { // lenght - i -1 再减1的原因是因为当循环至倒数第2个既可以比较结束
                int a = array[j];
                int b = array[j + 1];
                if (a > b) {
                    array[j + 1] = a;
                    array[j] = b;
                }
            }
            privateArray(array);
        }

        int i = 5;
        System.out.println(--i);
        System.out.println(i);
    }

    private static void privateArray(int[] array) {
        for(int data : array) {
            System.out.print(data + " ");
        }
        System.out.println();
    }
}
