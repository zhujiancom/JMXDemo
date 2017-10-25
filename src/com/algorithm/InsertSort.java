package com.algorithm;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = new int[]{9,1,7,4,8,2};
//        insertionSort(arr);
        selectionSort(arr);
    }

    public static void insertionSort(int[] arr){
        for(int i=0;i<arr.length;i++){
            for(int j = i+1;j > 0 && j<arr.length;j--){
                if(arr[j-1] <= arr[j]){
                    break;
                }
                int temp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = temp;
                System.out.println("sroting: "+ Arrays.toString(arr));
            }
        }
    }

    public static void selectionSort(int[] arr){
        for(int i=0;i < arr.length-1; i++){
            int min = i;
            for(int j=i+1; j < arr.length; j++){//选出之后待排序中值最小的位置
                if(arr[j] < arr[min]){
                    min = j;
                }
            }
            if(min != i){
                System.out.println("before sorting i="+i+", min="+min+": "+Arrays.toString(arr));
                int temp = arr[min];
                arr[min] = arr[i];
                arr[i] = temp;
                System.out.println("sorting: "+Arrays.toString(arr));
            }
        }
    }
}
