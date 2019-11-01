import java.io.File;
import java.util.Arrays;

public class IntegerSorter implements Sorter{

    private int[] arr;

    public static void sort_method1(int[] arr) {
        for (int i = arr.length - 1; i > 1; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void sort_method2(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

     public static void sort_method3(int arr[], int st, int end) {
        if (st == end - 1) return;

        sort_method3(arr, st, (end + st) / 2);
        sort_method3(arr, (end + st) / 2, end);

        int copiedArr1[] = Arrays.copyOfRange(arr, st, (end + st) / 2);
        int copiedArr2[] = Arrays.copyOfRange(arr, (end + st) / 2, end);

        int pt1 = 0, pt2 = 0;
        for (int i = st; i < end; i++) {
            if (copiedArr1[pt1] < copiedArr2[pt2]) {
                arr[i] = copiedArr1[pt1];
                pt1++;
            } else {
                arr[i] = copiedArr2[pt2];
                pt2++;
            }
            if (pt1 >= copiedArr1.length) {
                for (int arrIndex = ++i, j = pt2; j < copiedArr2.length; arrIndex++, j++) {
                    arr[arrIndex] = copiedArr2[j];
                }
                break;
            }

            if (pt2 >= copiedArr2.length) {
                for (int arrIndex = ++i, j = pt1; j < copiedArr1.length; arrIndex++, j++) {
                    arr[arrIndex] = copiedArr1[j];
                }
                break;
            }
        }
    }

    @Override
    public void setList(int[] list) {
        this.arr = list;
    }

    @Override
    public int[] getList() {
        return arr;
    }

    @Override
    public void sort(int type) {
        switch (type){
            case 1:
                sort_method1(arr);
                break;
            case 2:
                sort_method2(arr);
                break;
            case 3:
                sort_method3(arr, 0, arr.length);
                break;
        }
    }
}
