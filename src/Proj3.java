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

    // Sorting Method declarations
    // Merge Sort, sorts the given arraylist with bounds as lt and rt.
    // Sorting Method declarations
    //Merge Sort sorts the given arraylist with the bounds being the left and right parameters.
    public static <T extends Comparable> void mergeSort(ArrayList<T> a, int left, int right) {
        // Finish Me
        //Sort with lengths >= 2
        if (left + 1 < right) {
            //Get the midpoint of the Arraylist
            int mid = (left + right) / 2;//left + (right - left) /2;


            //Sort the first and second halves of the list.
            mergeSort(a, left, mid);
            mergeSort(a, mid, right); //HAD +1 ON MID
            //Merge the halves after they have been sorted.
            merge(a, left, mid, right);
        }
    }


    //This method is called from mergeSort(). It merges two different arraylists with bounds.
    public static <T extends Comparable> void merge(ArrayList<T> a, int left, int mid, int right) {
        // Finish Me
        //The sizes of the two arrays being merged.
        //System.out.println(left + " " + mid + " " + right);


        //CHECK WHICH SIDE GETS MORE WHEN ODD
        //int n1 = mid-1;// - left + 1;
        //int n2 = right - mid;


        //Temp arrays:
        //lTemp is the array list that has the values of a from the left index to mid
        //rTemp is the array list that has the values of a from mid to the right index
        ArrayList<T> lTemp = new ArrayList<T>(a.subList(left, mid));
        ArrayList<T> rTemp = new ArrayList<T>(a.subList(mid, right));


        //Copy the data into the temp arrays.
       /*for (int i = 0; i < n1; i++) {
           System.out.println("in");
           lTemp.set(i, a.get(i+left));
       }
       System.out.println(lTemp);
       for (int j = 0; j < n2; j++)
           rTemp.set(j, a.get(mid+j));
       */


        int i = 0, j = 0;


        // Initial index of merged subarray array
        int k = left;
        while (i < lTemp.size() && j < rTemp.size()) {
            if (lTemp.get(i).compareTo(rTemp.get(j)) <= 0) {
                a.set(k, lTemp.get(i));
                i++;
            } else {
                a.set(k, rTemp.get(j));
                j++;
            }
            k++;
        }
        // Copy remaining elements of lTemp
        while (i < lTemp.size()) {
            a.set(k, lTemp.get(i));
            i++;
            k++;
        }
        // Copy remaining elements of rTemp
        while (j < rTemp.size()) {
            a.set(k, rTemp.get(j));
            j++;
            k++;
        }


    }


    // Quick Sort
    public static <T extends Comparable> void quickSort(ArrayList<T> a, int left, int right) {
        // Finish Me
        //Base case. ArrayList is sorted.
        /*
        if (left >= right) {
            return;
        }
        System.out.println("quick1");
        System.out.println(a);

        //Partition the data in the ArrayList. index is the index of the low
        //partition's last element.
        int index = partition(a, left, right);
        //Recursively sort the low partition and high partition, starting at index+1.
        quickSort(a, left, index);
        quickSort(a, index + 1, right);
        System.out.println("quick2");
        System.out.println(a);

         */

        if (left >= right) {
            return;
        }

        //Partition the data in the ArrayList.
        //The index is the index of the low partition's last element.
        int lowEndIndex = partition(a, left, right);
        //Recursively sort the low partition and the high partition (start at the index + 1)
        quickSort(a, left, lowEndIndex);
        quickSort(a, lowEndIndex + 1, right);

        /*
        if (left >= right) {
            return;
        }


        //Partition the data in the ArrayList. index is the index of the low
        //partition's last element.
        int index = partition(a, left, right);
        //Recursively sort the low partition and high partition, starting at index+1.
        System.out.println(left);
        System.out.println(index);
        quickSort(a, left, index);
        quickSort(a, index + 1, right);


/*
        if (left < right) {
            // Partition the list and get the pivot index
            int pi = partition(a, left, right);

            // Recursively apply QuickSort on the left and right of the pivot
            quickSort(a, left, pi - 1);  // Left side
            quickSort(a, pi + 1, right); // Right side
        }

 */
    }


    public static <T extends Comparable> int partition(ArrayList<T> a, int left, int right) {
        // Finish Me
        //c is used to compare Volcano values.
        //Comparator<Volcano> c = new StarTemp();
        //Pick middle element as pivot.


        int center = (left + right - 1) / 2;

        //System.out.println(a);
        //Compare the first, last, and middle elements and order them correctly.
        System.out.println(a.get(center));
        System.out.println(a.get(left));

        if (a.get(center).compareTo(a.get(left)) < 0) {
            swap(a, left, center);
        }
        if (a.get(right - 1).compareTo(a.get(left)) < 0) {
            swap(a, left, right - 1);
        }
        if (a.get(right - 1).compareTo(a.get(center)) < 0) {
            swap(a, center, right - 1);
        }


        //The median is the pivot. Place it at the last index.
        swap(a, center, right - 1);


        //Set pivot.
        T pivot = a.get(right - 1);


        //Assign the range to i and j.
        int i = left;
        int j = right - 2;

        //System.out.println("quick3");
        //System.out.println(a);
        //Loop until i is less than j.
        while (i < j) {
            // Increment the left index while a.get(i) < pivot
            while ((i >= 0 && i < 100) && (a.get(i).compareTo(pivot) <= 0)) {
                i += 1;
            }
            //System.out.println(j);
            //System.out.println(a.size());
            // Decrement right index while pivot < a.get(j).
            while ((j >= 0) && (pivot.compareTo(a.get(j)) <= 0)) {
                j -= 1;
            }

            //If zero or one elements remain, then all numbers are partitioned.
            if (i < j) {
                // Swap elements at i and j.
                swap(a, i, j);
            } else {
                break;
            }
        }
        //System.out.println(a);
        //System.out.println("quick4");
        //Restore pivot.
        if(i < 100 && i >= 0) {
            swap(a, i, right - 1);
        }
        return i;
    }


    static <T> void swap(ArrayList<T> a, int i, int j) {
        T temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }


    // Heap Sort
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
            //swap max element with the last element
            swap(a, left, i);
            //heapify the heap
            //heapify(a, left, i);
            heapify(a, left, i);
        }
    }


    public static <T extends Comparable> void heapify(ArrayList<T> a, int left, int right) {
        // Finish Me
        //Root node
        int maxNode = left;
        int leftChild = (2 * left) + 1;
        int rightChild = (2 * left) + 2;


        if (leftChild < right && a.get(leftChild).compareTo(a.get(maxNode)) > 0) {
            maxNode = leftChild;
        }


        //Check if right child is larger than the maxNode
        if (rightChild < right && a.get(rightChild).compareTo(a.get(maxNode)) > 0) {
            maxNode = rightChild;
        }


        //If the maxNode is not the root, swap and heapify.
        if (maxNode != left) {
            swap(a, left, maxNode);
            //Heapify the subtree with the maxNode as the root.
            heapify(a, maxNode, right);
        }
    }


    // Bubble Sort
    public static <T extends Comparable> int bubbleSort(ArrayList<T> a, int size) {
        // Finish Me
        //Used to compare Volcano values.
        //Comparator<Volcano> c = new StarTemp();
        int count = 0; //counter for num comparisons
        boolean swapped = true; //flag for swapped
        //End loop when all elements sorted.
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
    public static <T extends Comparable> int transpositionSort(ArrayList<T> a, int size) {
        // Finish Me
        //Used to compare Volcano values.


        //Comparator<Volcano> c = new StarTemp();


        int count = 0; //count is number of comparisons.
        boolean isSorted = false; //Checks if list is sorted.
        //While list is not sorted, continue.
        while (!isSorted) {
            isSorted = true;
            //Increment by 2 to compare only even odd-even pairs.
            for (int i = 0; i < size - 1; i += 2) {
                //For the even-odd pair if the current el is greater than the next el
                //swap them.
                //if(c.compare(a.get(i), a.get(i + 1)) > 0) {
                if (a.get(i).compareTo(a.get(i + 1)) > 0) {
                    swap(a, i, i + 1);
                    isSorted = false;
                }
                count++;
            }
            //If not sorted yet, increment the number of swaps
           /*if(isSorted == false) {
               count++;
           }*/
            isSorted = true;
            //Increment by 2 to compare only even even-odd pairs.
            for (int i = 1; i < size - 1; i += 2) {
                //for the odd-even pair if the current el is greater than the next el
                //swap them


                //if(c.compare(a.get(i), a.get(i + 1)) > 0) {
                if (a.get(i).compareTo(a.get(i + 1)) > 0) {
                    swap(a, i, i + 1);
                    isSorted = false;
                }
                count++;
            }
            //Increment if there was at least one swap.
           /*if(isSorted == false) {
               count++;
           }*/
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
        String filename = ""; //The file name.
        String sortAlg = ""; //The sorting algorithm.
        int numLines = 0; //The number of lines to store and sort.
        try {
            //Assign values.
            filename = args[0];
            sortAlg = args[1];
            numLines = Integer.parseInt(args[2]);
        } catch (Exception e) {
            System.err.println("Invalid number of command line arguments");
            System.exit(1);
        }


        ArrayList<RemoteWork> origList = new ArrayList<RemoteWork>();   // original list
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


            //Data is stored in the Volcano Arraylist. We iterate over each line of the file.
            int count = 0; //count keeps track of the line count.
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
                origList.add(r);
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
            for (int i = 0; i < origList.size(); i++) {
                quickList.add(origList.get(i));
                bubbleList.add(origList.get(i));
                transpositionList.add(origList.get(i));
                heapList.add(origList.get(i));
                mergeList.add(origList.get(i));
            }
            //System.out.println(mergeList);


            FileWriter out = null;
            FileWriter out2 = null;
            try {
                //Create or open the file to write to it.
                out = new FileWriter("analysis.txt", true);
                out2 = new FileWriter("sorted.txt");


                //To determine execution time, declare start and time.
                long start;
                long time;
                //printList(origList);
                //System.out.println();
                //Check for which sorting algorithm to perform. Call the appropriate method with the correct range.
                //Output the execution time and/or number of swaps.
                //out.write("Sort Method,Num Lines,Time(ns),Num Comparisons\n");
                //System.out.println("Num Lines   Time(ns)    Num Comparisons(Optional)");
                System.out.println(quickList.size());
                System.out.println(numLines);
                System.out.println(quickList);
                if (sortAlg.equals("quick")) {
                    //Collections.sort(quickList, Collections.reverseOrder())
                    Collections.sort(quickList);
                    start = System.nanoTime();
                    quickSort(quickList, 0, numLines);

                    time = System.nanoTime();
                    System.out.println("Quick Sort: Number of Lines=" + numLines + ", Time(ns)=" + (time - start));
                    out.write("Quick Sort," + numLines + "," + (time - start) + ",\n");
                    out2.write(quickList.toString());
                }
                sortAlg = "bubble";
                if (sortAlg.equals("bubble")) {
                    //Collections.sort(bubbleList, Collections.reverseOrder());
                    Collections.sort(bubbleList);
                    start = System.nanoTime();
                    int bubbleCount = bubbleSort(bubbleList, numLines);
                    //printList(bubbleList);
                    time = System.nanoTime();
                    System.out.println("Bubble Sort: Number of Lines=" + numLines + ", Time(ns)=" + (time - start) + ", Number of Comparisons=" + bubbleCount);
                    out.write("Bubble Sort," + numLines + "," + (time - start) + "," + bubbleCount + "\n");
                    out2.write(bubbleList.toString());
                }
                sortAlg = "transposition";
                if (sortAlg.equals("transposition")) {
                    //Collections.sort(transpositionList, Collections.reverseOrder());
                    Collections.sort(transpositionList);
                    int transpositionCount = transpositionSort(transpositionList, numLines);
                    //printList(transpositionList);
                    System.out.println("Transposition Sort: Number of Lines=" + numLines + ", Number of Comparisons=" + transpositionCount);
                    out.write("Transposition Sort," + numLines + ",," + transpositionCount + "\n");
                    out2.write(transpositionList.toString());
                }
                sortAlg = "heap";
                if (sortAlg.equals("heap")) {
                    //Collections.sort(heapList, Collections.reverseOrder());
                    Collections.sort(heapList);
                    start = System.nanoTime();
                    heapSort(heapList, 0, numLines);
                    time = System.nanoTime();
                    System.out.println("Heap Sort: Number of Lines=" + numLines + ", Time(ns)=" + (time - start));
                    //printList(heapList);
                    out.write("Heap Sort," + numLines + "," + (time - start) + ",\n");
                    out2.write(heapList.toString());
                }
                sortAlg = "merge";
                if (sortAlg.equals("merge")) {
                    //Collections.sort(mergeList, Collections.reverseOrder());
                    Collections.sort(mergeList);
                    start = System.nanoTime();
                    mergeSort(mergeList, 0, numLines);
                    time = System.nanoTime();
                    System.out.println("Merge Sort: Number of Lines=" + numLines + ", Time(ns)=" + (time - start));
                    //printList(mergeList);
                    out.write("Merge Sort," + numLines + "," + (time - start) + ",\n");
                    out2.write(mergeList.toString());
                } /*else {
                    System.err.println("Invalid sorting algorithm");
                    System.exit(1);
                }*/
            }
            /*} catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }

             */
            finally {

                out.close();
                out2.close();
            }
        }


        public static void printList (ArrayList <RemoteWork> a) {
            for (int i = 0; i < a.size(); i++) {
                System.out.print(a.get(i).getHours_Worked_Per_Week() + " ");
            }
        }
    }

