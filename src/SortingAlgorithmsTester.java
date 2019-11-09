import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class SortingAlgorithmsTester {
    static int[] arr;
    static int[] nExponents1 = new int[]{4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
    static int[] nExponents = new int[]{4, 5, 6, 7, 8, 9, 10, 11, 12};
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
            scan.close();
            return list;

        } catch (FileNotFoundException e) {
            System.out.println("File was not found :( : " + fileName);
        }
        return new int[]{};
    }

    static void checkSort(int[] defaultSortedArray, int[] customSortedArray) {
        //checks if sorting algorithm worked
        Arrays.sort(defaultSortedArray);
        for (int i = 0; i < customSortedArray.length; i++) {
            if (customSortedArray[i] != defaultSortedArray[i]) {
                for (int j = 0; j < customSortedArray.length; j++) {
                    System.out.println(customSortedArray[j] + " " + defaultSortedArray[j]);
                }

                throw new RuntimeException("This array is NOT sorted. What the heck.");
            }
        }
    }

    static void threeTrials(PrintWriter writer, int type) {

        IntegerSorter sorter = new IntegerSorter();

        for (int test = 0; test < 7; test++) {
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

                System.out.print((double) time / nanosecToSec + ",");

                writer.write((double) time / nanosecToSec + ", ");
            }
            System.out.println();
            writer.write("\n");
        }
    }

    static void sortOutput() {

        try {
            File file = new File("output.csv");
            PrintWriter writer = new PrintWriter(file);

            threeTrials(writer, 3);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("IOEXCEPTION! :<");
        }

    }

    static void testOneMethod() {
        IntegerSorter sorter = new IntegerSorter();

        sorter.setList(
                readArray(
                        "/Users/shirleyzhang/Desktop/ics4u/ComparingSortingAlgorithms/src/TestFiles/2power"
                                + 19 + ".txt", (int) Math.pow(nBase, 19)
                ));

        int defaultSortedArray[] = Arrays.copyOf(sorter.getList(), sorter.getList().length);
        Arrays.sort(defaultSortedArray);

        long startTime = System.nanoTime();
        sorter.sort(2);
        long time = System.nanoTime() - startTime;

        checkSort(defaultSortedArray, sorter.getList());

        System.out.println((double) time / nanosecToSec + ",");
    }

    static void testToString() {
        IntegerSorter sorter = new IntegerSorter();
        sorter.setList(readArray("/Users/shirleyzhang/Desktop/ics4u/ComparingSortingAlgorithms/src/TestFiles/2power"
                + 19 + ".txt", (int) Math.pow(nBase, 19)));
        sorter.sort_method3(0, sorter.getList().length);
        System.out.println(sorter.toString());
    }

    static void testTimer() {
        long prevtime = System.nanoTime();
        for (int i = 0; i < 8; i++) {
            long curtime = System.nanoTime() - prevtime;

            prevtime = System.nanoTime();
            System.out.println(curtime);
        }
    }

    public static void main(String[] args) {
        sortOutput();
    }
}
