import comp102x.IO;

public class PowerCalculator
{
    /**
     * Calculate the non-negative power of an integer number. If a negative power is input, the method returns 1.
     * 
     * @param number The number to take power.
     * @param power The power factor to be taken to.
     * @return The calculation result after taking power of the integer number.
     */
    public static long powerN(int number, int power) {
        
        // write your code after this line
        
        int t = 1;
        int cnt = 1;
        
        if (power <= 0){
            return 1;
        }
        else
            while (cnt <= power){
                
                t = t * number;
                cnt = cnt + 1;
            }
        
        return t;
    }
}
