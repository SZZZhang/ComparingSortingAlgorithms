import java.io.File;
import java.util.Arrays;

public class IntegerSorter implements Sorter {

    private int[] list;

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < list.length; i++) {
            str += (list[i] + ", ");
        }
        return str;
    }

    //sorts array
    public void sort_method1() {
        for (int i = list.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (list[j] > list[j + 1]) {
                    int temp = list[j + 1];
                    list[j + 1] = list[j];
                    list[j] = temp;
                }
            }
        }
    }

    public void sort_method2() {
        for (int i = 0; i < list.length - 1; i++) {
            for (int j = i + 1; j < list.length; j++) {
                if (list[i] > list[j]) {
                    int temp = list[i];
                    list[i] = list[j];
                    list[j] = temp;
                }
            }
        }
    }

    //sorts the list, from the start index(inclusive) to the end index(exclusive)
    public void sort_method3(int st, int end) {
        if (st == end - 1) return;

        sort_method3(st, (end + st) / 2);
        sort_method3((end + st) / 2, end);

        int[] arr1 = Arrays.copyOfRange(list, st, (end + st) / 2);
        int[] arr2 = Arrays.copyOfRange(list, (end + st) / 2, end);

        int pt1 = 0, pt2 = 0, index = st;
        while (pt1 < arr1.length && pt2 < arr2.length) {
            if (arr1[pt1] < arr2[pt2]) {
                list[index] = arr1[pt1];
                pt1++;
            } else {
                list[index] = arr2[pt2];
                pt2++;
            }
            index++;
        }
        if (pt1 >= arr1.length) {
            for (int copyIndex = index, j = pt2; j < arr2.length; copyIndex++, j++) {
                list[copyIndex] = arr2[j];
            }
        }
        if (pt2 >= arr2.length) {
            for (int copyIndex = index, j = pt1; j < arr1.length; copyIndex++, j++) {
                list[copyIndex] = arr1[j];
            }
        }
    }


    @Override
    public void setList(int[] list) {
        this.list = list;
    }

    @Override
    public int[] getList() {
        return list;
    }

    @Override
    public void sort(int type) {
        switch (type) {
            case 1:
                sort_method1();
                break;
            case 2:
                sort_method2();
                break;
            case 3:
                sort_method3(0, list.length);
                break;
        }
    }
}
