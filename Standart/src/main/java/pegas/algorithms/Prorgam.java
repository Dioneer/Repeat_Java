package pegas.algorithms;

import java.util.Arrays;

public class Prorgam {
    public static void main(String[] args) {
        int[] array = new int[] {1,11,55,66,77,12,33,55,45,98,0};
        System.out.println(less(array));
        pusyr(array);
        vybor(array);
    }

    public static int less(int[] arr){
        int min = arr[0];
        for (int i: arr){
            if(min>i){
                min = i;
            }
        }
        return min;
    }
    public static void pusyr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if(arr[i]<arr[j]){
                    int tempo = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tempo;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
    public static void vybor(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int index = i;
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j]<min){
                    min = arr[j];
                    index = j;
                }
            }
            int tempo = arr[i];
            arr[i] = min;
            arr[index] = tempo;
        }
        System.out.println(Arrays.toString(arr));
    }
}
