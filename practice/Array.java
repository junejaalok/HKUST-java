public class Array {
	private static int size = 4;
	private static int [][] arr = new int [4][4];
	public static void main(String args[]) {
		for (int i = 0; i < size; i++) {
    		for (int j = 0; j < size; j++) {
        		arr[i][j] = size * j + i;
        		System.out.println(i + "-" + j + ":" + arr[i][j]);
    		}
		}
    }
}
