package studyjava;

public class ThrowingErrors
{
   public static void main(String[] args)
   {
	   int j = 0;
	   for (int i = 0; i < 100; i++) {
	   j = j++;
	   }
	   System.out.println(j);
   }

   public static void throwError()
   {
      throw new Error("Error occurred!");
   }
}
