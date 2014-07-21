public class Fact2{

public static int fact2(int n1, int n2) {  

    int t = 1; // initialize t to 1       
    for (int counter = n1; counter <= n2; counter++) {
        t = t * counter;
    }
    return t;
}

    public static void main(String args[]) {
	int result = fact2(-2,2);
	System.out.println(result);
}
}
/*
public class Incr {
    public static void main(String args[]) {
int i = 10;
int j = 25;
i += 5;
j /= i;
int result = i + j;
	System.out.println(result);
    }
}
*/
