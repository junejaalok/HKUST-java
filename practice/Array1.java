import java.util.Random;
public class Array1 {
	private static int size = 4;
	private static int [][] arr = new int [3][4];
	public static void main(String args[]) {
        Random rand = new Random();
        int min=-1;
        int max=1;

        int row = arr.length;
		int col = arr[0].length;
		//Goal temp;
		for (int x = 0; x<row; x++){
    		for (int y = 0; y<col; y++){
		        System.out.println(arr[x][y]);

		        System.out.println(x + "-" + y + "   i+j%2:::" + ((x + y) % 2));
    		}
    	}
    	System.out.println("------");

        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
            	while (true) {
	            	int r1 = rand.nextInt((max - min) + 1) + min;
    	        	int r2 = rand.nextInt((max - min) + 1) + min;
    	        	int t1=i+r1;
    	        	int t2=j+r2;
    	        	//System.out.println(i + " r1:" + r1 + " t1:" + t1);
    	        	//System.out.println(j + " r2:" + r2 + " t2:" + t2);
    	        	
    	        	if (i == t1 && j == t2) continue;

    	        	if (t1 >= 0 && t2 >= 0 && t1 < row && t2 < col) {//&& (i != t1 && j != t2)) {//}  (i != r1 && j != r2)) {//&& i == r1 && j == r2) {//&& i + r1 > row && j + r2 > col && i == r1 && j == r2) {
    	        		System.out.println(i + "accept-t1:" + t1);
    	        		System.out.println(j + "accdpt-t2:" + t2);
    	        		System.out.println("========");
    	        		break;
    	        	}
    	        	else {
    	        		//System.out.println(i + "reject-t1:" + t1);
    	        		//System.out.println(j + "reject-t2:" + t2);
    	        		//System.out.println("========");
    	        		continue;
    	        	}

    	        }	
            }
        }
 
//		for (int i = 0; i < 100; i++) {
//	        int r1 = rand.nextInt((max - min) + 1) + min;
//    		for (int j = 0; j < size; j++) {
//       		arr[i][j] = size * j + i;
//        		System.out.println(i + ":" + r1);
//    		}
		
    }
}
