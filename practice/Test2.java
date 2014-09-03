public class Test2 {

        private static int x = 10;

        public static int f() {

                int result = 1;
                int[] array = {2, 3, 4};

                for (int x = 0; x < array.length - 1; x++) {
                    result *= array[x];
                }

                result *= x;
                return result;
        }
        public static void main(String args[]) {
            System.out.println(f());
        }
}
