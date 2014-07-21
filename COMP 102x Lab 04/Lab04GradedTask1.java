import comp102x.IO;

public class Lab04GradedTask1
{
   public static void testCase1()
   {
       Choice choice1 = new Choice(0); // Rock_0,Scissor_2
       Choice choice2 = new Choice(2); // Paper_1
       int result = choice1.compareWith(choice2); // -1
       IO.outputln(result);
   }   
}
