package hdli.search;

/**
 * 二分查找
 * 场景：查找有序数组里是否有目标值
 *
 * 二分法的思想很简单，因为整个数组是有序的，数组默认是递增的。
 *
 * 首先选择数组中间的数字和需要查找的目标值比较
 * 如果相等最好，就可以直接返回答案了
 * 如果不相等
 * 如果中间的数字大于目标值，则中间数字向右的所有数字都大于目标值，全部排除
 * 如果中间的数字小于目标值，则中间数字向左的所有数字都小于目标值，全部排除
 */
public class BinarySearch {

    public static void main(String[] args) {
        // case1
        int[] array = new int[]{-1, 0, 1, 2, 3, 4};

        if (array == null || array.length == 0) {
            System.out.print("array is empty");
        }

        int result = search(array, 0, array.length - 1, -2);
        System.out.println("search result is " + result);

        result = search2(array, 0, array.length - 1, -2);
        System.out.println("search2 result is " + result);
    }

    public static int search(int[] array, int left, int right, int target) {
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        int midValue = array[mid];
        if (midValue == target) {
            return mid;
        } else if (midValue > target) {
            return search(array, left, mid - 1, target);
        } else if (midValue < target) {
            return search(array, mid + 1, right, target);
        }

        return -1;
    }

    public static int search2(int[] array, int left, int right, int target) {
        while (left <= right) {
            int mid = (left + right) / 2;
            int midValue = array[mid];
            if (midValue == target) {
                return mid;
            } else if (midValue > target) {
                right = mid - 1;
            } else if (midValue < target) {
                right = mid + 1;
            }
        }

        return -1;
    }


}
