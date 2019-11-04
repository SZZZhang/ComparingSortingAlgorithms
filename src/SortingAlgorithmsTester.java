import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class SortingAlgorithmsTester {
    static int[] arr;
    static int[] nExponents = new int[]{4, 7, 10, 13, 16, 19};
    static int nBase = 2;
    static double nanosecToSec = 1E9;

    static int[] readArray(String fileName, int arrSize) {
        try {
            File file = new File(fileName);
            Scanner scan = new Scanner(file);

            int list[] = new int[arrSize], index = 0;

            while (scan.hasNextInt()) {
                list[index] = scan.nextInt();
                index++;
            }

            return list;

        } catch (FileNotFoundException e) {
            System.out.println("File was not found :( : " + fileName);
        }
        return new int[]{};
    }

    static void checkSort(int[] defaultSortedArray, int[] customsortedArray) {
        //checks if sorting algorithm worked
        Arrays.sort(defaultSortedArray);
        for (int i = 0; i < customsortedArray.length; i++) {
            if (customsortedArray[i] != defaultSortedArray[i]) {
                for(int j = 0; j < customsortedArray.length; j++) {
                    System.out.println(customsortedArray[j] + " " + defaultSortedArray[j]);
                }

                throw new RuntimeException("This array is NOT sorted. What the heck.");
            }
        }
    }

    static void threeTrials(PrintWriter writer, int type) {

        IntegerSorter sorter = new IntegerSorter();

        for (int test = 0; test < 3; test++) {
            for (int i = 0; i < nExponents.length; i++) {
                sorter.setList(
                        readArray(
                                "/Users/shirleyzhang/Desktop/ics4u/ComparingSortingAlgorithms/src/TestFiles/2power"
                                        + nExponents[i] + ".txt", (int) Math.pow(nBase, nExponents[i]))
                );

                int defaultSortedArray[] = Arrays.copyOf(sorter.getList(), sorter.getList().length);
                Arrays.sort(defaultSortedArray);

                long startTime = System.nanoTime();
                sorter.sort(type);
                long time = System.nanoTime() - startTime;

                checkSort(defaultSortedArray, sorter.getList());

                System.out.println((double) time/nanosecToSec + ",");

                writer.write((double) time / nanosecToSec + ", ");
            }
            writer.write("\n");
        }
    }

    static void sortOutput() {

        try {
            File file = new File("output.csv");
            PrintWriter writer = new PrintWriter(file);

            threeTrials(writer, 1);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("IOEXCEPTION! :<");
        }

    }

    static void testToString() {
        IntegerSorter sorter = new IntegerSorter();
        sorter.setList(new int[] {2, 3, 4});
        System.out.println(sorter.toString());
    }

    public static void main(String[] args) {
        sortOutput();
    }
}
