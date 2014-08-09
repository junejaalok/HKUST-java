public class Test {
    /**
     * maxIndex finds the location of the largest value in an array upto index size - 1
     */
	int [] arr = {3,10,4,6,8,9,7,2,1,5};

    private int maxIndex(int size) {
        int mIndex = 0;
        for (int i = 0; i < size; i++) {
            if (arr[i] > arr[mIndex]) mIndex=i; 
        }

        return mIndex;
    }
    
    /**
     * Swaps two elements of the array indexed by index1 and index2.
     */
    private void swap(int[] arr1, int index1, int index2) {
        int temp = arr1[index1];
        arr1[index1] = arr1[index2];
        arr1[index2] = temp;
    }


    public void main(String args[]) {
    	int maxPos;
        for (int i=arr.length-1;i>0;i--){
            maxPos = maxIndex(i + 1);
            swap(arr,maxPos, i);
        }
    }
}
