import java.util.Stack;

public class finalproject {
    /**
     * maxIndex finds the location of the largest value in an array upto index size - 1
     */
	int [] arr = {3,10,4,6,8,9,7,2,1,5};

    static int f(int value) {
    
        if (value == 0) return 0;
        return 1 * f(value - 2);
    }

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
/*    private void swap(int[] arr1, int index1, int index2) {
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
    }*/

    public static int foo(int v) {
        if (v == 0)
                return 1;
        else if (v == 1)
                return 0;
        else if (v % 2 == 0)
                return 1+foo(v/2);
        else
                return foo(v/2);
    }

    public static int f1(int n) {
  
    if (n == 1)
        return 1;
  	
    return n + f1(n - 1);

	}
	public static int f2(int a, int b) {
    	if (b >= 1)
        	return f2(a + 1, b - 1);
    	else
        	return a;
	}

	public static int f3(int n) {
  
    	if (n == 0)
        	return 1;
  
    	else if (n < 10 && n > -10)
        	return 0;
  
    	else      
        	return f3(n / 10) + f3(n % 10);
	}

/*    public static void transpose (int [][] arr) {

        int row=arr.length;
        int col=arr[0].length;
        for (int i=0;i<row;i++){
            for (int j=i+1;j<col;j++){
                int tmp=arr[i][j];
                arr[i][j]=arr[j][i];
                arr[j][i]=tmp;
            }
        }
    }*/

/*    public static int sumOfProduct(int[] arr1, int[] arr2, int n) {
        if (n > 0) {
            return (arr1[n-1] * arr2[n-1]) + sumOfProduct(arr1,arr2,n-1);
        }
        else return 0;
    }*/

/*    public static String decipher (String cipher) {

//        kjdkti zldzae cvyrvw aaemgk
        int block = 6;

        if (cipher.length() == 0) return "no cipher"; 
        if (cipher.length() % block != 0) return "invalid cipher";

        String tmp="";
        int del=cipher.length()/block;

        for (int i=0;i<del;i++) {
            tmp += decipherChar(cipher.substring(i*block,(i*block)+block));
        }
        return tmp;
    }*/

    public static void printHourglass (int size, char symbol) {

/*        int i=0
        int spaces=size;
        if (spaces < size) {
            System.out.print(' ');
            printHourglass()
        }
        else if (size > 0) {
            System.out.print(symbol);
            if size > 1
                printHourglass(size-2,symbol);
            else
                printHourglass(size+2,symbol);
        }
        else
            System.out.println()*/
        //For upper half
        for (int i =0;i<size/2;i++) {
            for (int x=0;x<i;x++)
                System.out.print(' ');
            for (int j=0;j<(size-(2*i));j++)
                System.out.print(symbol);
            for (int y=0;y<i;y++)
                System.out.print(' ');
            System.out.println();
        }

        //For middle case
        for (int i =0;i<1;i++) {
            for (int x=i;x<(size-i)/2;x++)
                System.out.print(' ');
            for (int j=0;j<1;j++)
                System.out.print(symbol);
            for (int y=i;y<(size-i)/2;y++)
                System.out.print(' ');
            System.out.println();
        }

        //For lower half
        for (int i =1;i<=size/2;i++) {
            for (int x=i;x<(size/2);x++)
                System.out.print(' ');
            for (int j=0;j<(i*2)+1;j++)
                System.out.print(symbol);
            for (int y=i;y<(size/2);y++)
                System.out.print(' ');
            System.out.println();
        }
    }

    public static void main(String args[]) {
    	int maxPos;
    	maxPos=f3(10200);
    	//System.out.println(maxPos);
        //System.out.println(3 % 2 * 2 + 3 * - 1 / 3);
//        int x = 1;
//    int y = x++;
//    int z = ++y;
//    y = z++;
//    z = ++z;
//    System.out.println(z);
    //int i = 2.0;
//    double d = 2;
//    double d2 = 2.0f;
//    char grade = 'A';
//    int score = 99;
  
//    switch(score/10) {
  
//    case 10: grade = 'A'; 
//    case 8: grade = 'C'; 
//    case 9: grade = 'B';  
//    default: grade = 'F'; 
//    }
 //   System.out.println(grade);

 //   int x = 4;
 //   int y = 2;
 //   int z = 6;

 //   if ( x > y )
 //       if ( z > x)
 //           x = 1;
 //       else
 //           x = 3;


 //   System.out.println(x);

//int i = 0;
  
//for ( i = 0; i < 4; i++ ); {
//    i = i + 2;
//}
//System.out.println(i);
//int t=f(10);
//System.out.println(t);

//int[] numbers = {9, 2, 8, 1, 4};
//int x = 0;

//for (int i = 0; i < 5; i++) {

 //       if (numbers[i] % 2 == 0) continue;
 //       x += numbers[i];
//}
//System.out.println(x);
//int[][] a = new int[4][7];
//System.out.println(a[1][7].length);
//Stack<Integer> s = new Stack<Integer>();
//s.push(9);
//s.push(2);
//s.push(7);

//int a = s.pop();
//int b = s.pop();
//int c = s.pop();

//int x = a + b * c;
//System.out.println(x);
//int y = 1;
  
//do {
  
//        y = y + y;
  
//} while (y < 126);
//System.out.println(y);

//int i = 0;
//int j = 0;
  
//for (i = 0; i < 3; i++) {
//        for (j = 0; j < 3; j++) {
//                if (j == 1)
//                        break;
//        }
//}
//System.out.println(i);
//System.out.println(j);
//System.out.println(foo(100));

//int counter = 0;
  
//for (int i = 0; i < 10; i++)
//        for (int j = 0; j < i; j++)
 //               counter = counter + 1;

//System.out.println(counter);

//int a = 1;
//int b = 2;
//int c = 3;
  
//if ( b > a ) {
//        a = b;              
//                if ( c > a ) {
//                        a = c;
//                }
//} else if ( a == c ) {
//        a = 0;
//}
//System.out.println(a);

/*int [][] score={
    {11,12,13,14},
    {21,22,23,24},
    {31,32,33,34},
    {41,42,43,44}
};

transpose(score);

int row1=score.length;
int col1=score[0].length;
for (int i=0;i<row1;i++){
    for (int j=0;j<col1;j++){
        System.out.println(score[i][j]);
    }
    System.out.println("---------");
}*/

/*int[] arr1 = {1, 2, 3, 4, 5};
int[] arr2 = {6, 7, 8, 9, 10};

int sum;
sum=sumOfProduct(arr1,arr2,4);
System.out.println(sum);*/

//System.out.println(decipher("yjqzicdamnovvvozyrjstfad")); 
printHourglass(3, 'A');
printHourglass(7, '?');
printHourglass(5, 'Q');
printHourglass(51, '*');



    }
}
