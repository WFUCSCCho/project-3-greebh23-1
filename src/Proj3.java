/**********************************************************************
 * @file Proj3.java
 * @brief This program sorts data using different sorting methods:
 * Quick Sort, Bubble Sort, Merge Sort, Heap Sort, and Odd-Even
 * Transposition Sort. The data is output to a txt and the sorted list
 * to a different txt file.
 * @author Blythe Greene
 * @date: November 14, 2024
 ***********************************************************************/

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Collections;

public class Proj3 {

    // Merge Sort, sorts the given arraylist with bounds as lt and rt.
    // Sorting Method declarations
    //Merge Sort sorts the given arraylist with the bounds being the left and right parameters.
    public static <T extends Comparable> void mergeSort(ArrayList<T> a, int left, int right) {
        // Finish Me
        //lengths >= 2
        if (left + 1 < right) {
            //Get the mid of the Arraylist
            int mid = (left + right) / 2;
            //Sort the first and second halves of the list.
            mergeSort(a, left, mid);
            mergeSort(a, mid, right);
            //Merge the halves after they have been sorted.
            merge(a, left, mid, right);
        }
    }

    //This method is called from mergeSort(). It merges two different arraylists with bounds.
    public static <T extends Comparable> void merge(ArrayList<T> a, int left, int mid, int right) {
        // Finish Me
        //Temp arrays:
        //leftTemp is the array list that has the values of a from the left index to mid
        //rightTemp is the array list that has the values of a from mid to the right index
        ArrayList<T> leftTemp = new ArrayList<T>(a.subList(left, mid));
        ArrayList<T> rightTemp = new ArrayList<T>(a.subList(mid, right));

        //Copy the data into the temp arrays.
        int i = 0;
        int j = 0;
        // Initial index of merged subarray array
        int k = left;
        while (i < leftTemp.size() && j < rightTemp.size()) {
            if (leftTemp.get(i).compareTo(rightTemp.get(j)) <= 0) {
                a.set(k, leftTemp.get(i));
                i++;
            } else {
                a.set(k, rightTemp.get(j));
                j++;
            }
            k++;
        }
        // Copy the leftover elements of leftTemp
        while (i < leftTemp.size()) {
            a.set(k, leftTemp.get(i));
            i++;
            k++;
        }
        // Copy the leftover elements of rightTemp
        while (j < rightTemp.size()) {
            a.set(k, rightTemp.get(j));
            j++;
            k++;
        }
    }


    // Quick Sort
    //Sorts the arraylist by dividing and conquer method
    //Repeatedly partitioning the list into smaller lists around a pivot
    //then recursively sort those sublists
    public static <T extends Comparable> void quickSort(ArrayList<T> a, int left, int right) {
        // Finish Me
        //Base case. ArrayList is sorted.
        //Base case
        if (left >= right) {
            return;
        }

        //Partition the data in the ArrayList.
        //The index is the index of the low partition's last element.
        int lowEndIndex = partition(a, left, right);
        //Recursively sort the low partition and the high partition (start at the index + 1)
        quickSort(a, left, lowEndIndex);
        quickSort(a, lowEndIndex + 1, right);

    }


    //Partition method is called by quick sort method
    //All elements <= pivot are moved to the left
    //All elements greater than the pivot are moved to the right
    //The pivot is in the correct position after, index of pivot can be used
    //to sort the sublists of left and right.
    public static <T extends Comparable> int partition(ArrayList<T> a, int left, int right) {
        // Finish Me
        //Pick middle element as pivot.
        int middle = (left + right) / 2;


        //Compare the first, last, and middle elements and order them correctly.
        if (a.get(middle).compareTo(a.get(left)) < 0) {
            swap(a, left, middle);
        }
        if (a.get(right - 1).compareTo(a.get(left)) < 0) {
            swap(a, left, right - 1);
        }
        if (a.get(right - 1).compareTo(a.get(middle)) < 0) {
            swap(a, middle, right - 1);
        }

        //The median is the pivot. Place it at the last index.
        swap(a, middle, right - 1);

        //Set pivot.
        T pivot = a.get(right - 1);

        //the range is set to i and j.
        int i = left;
        int j = right - 2;

        //System.out.println(a);
        //Loop until i is less than j.
        while (i < j) {
            // Increment the left index while a.get(i) is less than the pivot
            while ((i >= 0 && i < 100) && (a.get(i).compareTo(pivot) <= 0)) {
                i += 1;
            }
            //Decrement right index while pivot is less than a.get(j).
            while ((j >= 0) && (pivot.compareTo(a.get(j)) <= 0)) {
                j -= 1;
            }

            //If zero or one elements remain
            // numbers are partitioned.
            if (i < j) {
                // Swap elements at i and j.
                swap(a, i, j);
            } else {
                break;
            }
        }

        //Restore the pivot.
        if(i < 100 && i >= 0) {
            swap(a, i, right - 1);
        }
        return i;
    }

   //Swap method swaps the elements of parameters
    static <T> void swap(ArrayList<T> a, int i, int j) {
        T temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    // Heap Sort
    //Sorts and arraylist by building a max binary heap
    //and it also deletes the nodes.
    public static <T extends Comparable> void heapSort(ArrayList<T> a, int left, int right) {
        // Finish Me
        //Build a max heap.
        //Index of the last non-leaf node
        int start = (right / 2) - 1;

        for (int i = start; i >= left; i--) {
            //heapify(a, left, right);
            heapify(a, i, right);
        }

        for (int i = right - 1; i >= left; i--) {
            swap(a, left, i);//swap max element with the last element
            //heapify the heap
            //heapify(a, left, i);
            heapify(a, left, i);
        }
    }

    //heapify is called by heapSort method
    //Makes the binary tree a max binary heap
    public static <T extends Comparable> void heapify(ArrayList<T> a, int left, int right) {
        // Finish Me
        //Root node
        int max = left;//max node
        int leftChild = (2 * left) + 1;
        int rightChild = (2 * left) + 2;


        if (leftChild < right && a.get(leftChild).compareTo(a.get(max)) > 0) {
            max = leftChild;
        }

        //Check if right child is larger than the maxNode
        if (rightChild < right && a.get(rightChild).compareTo(a.get(max)) > 0) {
            max = rightChild;
        }

        //If the maxNode is not the root, swap and heapify.
        if (max != left) {
            swap(a, left, max);
            //Heapify the subtree with the maxNode as the root.
            heapify(a, max, right);
        }
    }

    // Bubble Sort
    //counts numeber of comparisons
    //Stops when no swaps are needed
    //Compares adjacent elements
    public static <T extends Comparable> int bubbleSort(ArrayList<T> a, int size) {
        // Finish Me
        int count = 0; //counter for the num of comparisons
        boolean swapped = true; //flag for swapped
        //End the loop when all the elements have been sorted.
        while (swapped) {
            swapped = false; //initialize swap to false
            //Loop through list
            for (int i = 0; i < size - 1; i++) {
                //If current el greater than next el, swap them.
                if (a.get(i).compareTo(a.get(i + 1)) > 0) {
                    swap(a, i, i + 1);
                    swapped = true;
                }
                count++; //Increment number of comparisons.
            }
        }
        return count; //return number of swaps.
    }


    // Odd-Even Transposition Sort
    //Performs odd and even sorting, returns comparrisons
    public static <T extends Comparable> int transpositionSort(ArrayList<T> a, int size) {
        // Finish Me
        int count = 0; //count is number of comparisons.
        boolean isSorted = false; //Checks if list is sorted.
        //While list is not sorted, continue.
        while (!isSorted) {
            isSorted = true;
            //Increment by 2 to compare only even odd-even pairs.
            for (int i = 0; i < size - 1; i += 2) {
                //For the even-odd pair if the current el is greater than the next el
                //swap them.
                if (a.get(i).compareTo(a.get(i + 1)) > 0) {
                    swap(a, i, i + 1);
                    isSorted = false;
                }
            }
            count++;
            //If not sorted yet, increment the number of comparisons
            isSorted = true;
            //Increment by 2 to compare only even even-odd pairs.
            for (int i = 1; i < size - 1; i += 2) {
                //for the odd-even pair if the current el is greater than the next el
                //swap them
                if (a.get(i).compareTo(a.get(i + 1)) > 0) {
                    swap(a, i, i + 1);
                    isSorted = false;
                }
            }
            //Increment if there was at least one comparison.
            count++;
        }
        return count; //Return the total swaps.
    }


    public static void main(String[] args) throws IOException {
        //...
        // Finish Me
        //...
      /*Your program reads in three command line arguments corresponding to the dataset,
      the type of sorting algorithm to be executed (bubble, quick, transposition), and
      an integer corresponding to the number of lines of data to be stored in your
      ArrayList. If there is more or less than three command line argument, the program
      exits and outputs an error message.*/

        String filename = ""; //file name.
        String sortAlg = ""; //sorting algorithm.
        int numLines = 0; //The number of lines to store and sort.

        try {
            //Assign the values.
            filename = args[0];
            sortAlg = args[1];
            numLines = Integer.parseInt(args[2]);
        } catch (Exception e) {
            System.err.println("Invalid number of command line arguments");
            System.exit(1);
        }

        ArrayList<RemoteWork> orgList = new ArrayList<RemoteWork>();   // original list
        ArrayList<RemoteWork> quickList = new ArrayList<RemoteWork>();   // list to be sorted via quick sort
        ArrayList<RemoteWork> bubbleList = new ArrayList<RemoteWork>();   // list to be sorted via bubble sort
        ArrayList<RemoteWork> transpositionList = new ArrayList<RemoteWork>();   // list to be sorted via transposition sort
        ArrayList<RemoteWork> heapList = new ArrayList<RemoteWork>();   // list to be sorted via transposition sort
        ArrayList<RemoteWork> mergeList = new ArrayList<RemoteWork>();   // list to be sorted via transposition sort

        //The file is opened for reading. A Scanner object is created for reading.
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        //The try block attempts to open the file for reading. The catch block is for a file error.
        //An error will be printed to the user.
        try {
            //Open the input file.
            inputFileNameStream = new FileInputStream(filename);
            inputFileNameScanner = new Scanner(inputFileNameStream);

            //The first line is ignored.
            inputFileNameScanner.nextLine();

            //Data is stored in the RemoteWork Arraylist. 
            //iterate over each line of the file.
            int count = 0; //keeps track of the line count.
            //Traverse numLines.
            while (count < numLines) {//inputFileNameScanner.hasNext()) {
                //Scan in the line.
                //Scan the line
                String line = inputFileNameScanner.nextLine();
                String[] parts = line.split(","); // split the string into multiple parts

                RemoteWork r;//Data stored in r
                r = new RemoteWork(Integer.parseInt(parts[0]), parts[1],
                        Integer.parseInt(parts[2]), Integer.parseInt(parts[3]),
                        Integer.parseInt(parts[4]));
                orgList.add(r);
                count++;
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return;
        }
        //This block guarantees the file is closed.
        finally{
            if (inputFileNameStream != null) {
                //The input file is closed.
                inputFileNameStream.close();
            }
        }
        // Fill lists.
        for (int i = 0; i < orgList.size(); i++) {
            quickList.add(orgList.get(i));
            bubbleList.add(orgList.get(i));
            transpositionList.add(orgList.get(i));
            heapList.add(orgList.get(i));
            mergeList.add(orgList.get(i));
        }

        FileWriter output1 = null;
        FileWriter output2 = null;
        try {
            //Create or open the file to write to it.
            output1 = new FileWriter("analysis.txt", true);
            output2 = new FileWriter("sorted.txt");

            //To determine execution time, declare start and time.
            long start;
            long time;
            //Check for which sorting algorithm to perform. Call the appropriate method with the correct range.
            //Output the execution time and/or number of swaps.
            if (sortAlg.equals("quick")) {
                //Collections.sort(quickList, Collections.reverseOrder())
                Collections.shuffle(quickList);
                start = System.nanoTime();
                quickSort(quickList, 0, numLines);

                time = System.nanoTime();
                System.out.println("Quick Sort: Number of Lines = " + numLines + ", Time = " + (time - start));
                output1.write("Quick Sort," + numLines + "," + (time - start) + ",\n");
                output2.write(quickList.toString());
            }
            //out.write("Quick Sort," + numLines + "," + ",\n");
            //sortAlg = "bubble";
            else if (sortAlg.equals("bubble")) {
                //Collections.sort(bubbleList, Collections.reverseOrder());
                Collections.shuffle(bubbleList);
                start = System.nanoTime();
                int bubbleCount = bubbleSort(bubbleList, numLines);
                //printList(bubbleList);
                time = System.nanoTime();
                System.out.println("Bubble Sort: Number of Lines = " + numLines + ", Time = " + (time - start) + ", Number of Comparisons = " + bubbleCount);
                output1.write("Bubble Sort," + numLines + "," + (time - start) + "," + bubbleCount + "\n");
                output2.write(bubbleList.toString());
            }
            //sortAlg = "transposition";
            else if (sortAlg.equals("transposition")) {
                //Collections.sort(transpositionList, Collections.reverseOrder());
                Collections.shuffle(transpositionList);
                int transpositionCount = transpositionSort(transpositionList, numLines);
                //printList(transpositionList);
                System.out.println("Transposition Sort: Number of Lines = " + numLines + ", Number of Comparisons = " + transpositionCount);
                output1.write("Transposition Sort," + numLines + ",," + transpositionCount + "\n");
                output2.write(transpositionList.toString());
            }
            //sortAlg = "heap";
            else if (sortAlg.equals("heap")) {
                //Collections.sort(heapList, Collections.reverseOrder());
                Collections.shuffle(heapList);
                start = System.nanoTime();
                heapSort(heapList, 0, numLines);
                time = System.nanoTime();
                System.out.println("Heap Sort: Number of Lines = " + numLines + ", Time = " + (time - start));
                //printList(heapList);
                output1.write("Heap Sort," + numLines + "," + (time - start) + ",\n");
                output2.write(heapList.toString());
            }
            //sortAlg = "merge";
            else if (sortAlg.equals("merge")) {
                //Collections.sort(mergeList, Collections.reverseOrder());
                Collections.shuffle(mergeList);
                start = System.nanoTime();
                mergeSort(mergeList, 0, numLines);
                time = System.nanoTime();
                System.out.println("Merge Sort: Number of Lines = " + numLines + ", Time = " + (time - start));
                //printList(mergeList);
                output1.write("Merge Sort," + numLines + "," + (time - start) + ",\n");
                output2.write(mergeList.toString());
            }
            else {
                   System.err.println("Invalid sorting algorithm");
                   System.exit(1);
               }
        }
            catch (Exception e) {
               System.out.println(e.getMessage());
               return;
           }

        finally {
            output1.close();
            output2.close();
        }
    }

    public static void printList (ArrayList <RemoteWork> a) {
        for (int i = 0; i < a.size(); i++) {
            System.out.print(a.get(i).getHours_Worked_Per_Week() + " ");
        }
    }
}
