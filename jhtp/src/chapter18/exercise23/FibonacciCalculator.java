package chapter18.exercise23;// Fig. 18.5: FibonacciCalculator.java
// Recursive fibonacci method.
import java.math.BigInteger;

public class FibonacciCalculator {
   private static BigInteger TWO = BigInteger.valueOf(2);

   // recursive declaration of method fibonacci
   public static BigInteger fibonacci(BigInteger number, BigInteger callsWrapper[]) {
      if (number.equals(BigInteger.ZERO) || number.equals(BigInteger.ONE)) { // base cases
         return number;
      }
      else { // recursion step
         callsWrapper[0] = callsWrapper[0].add(BigInteger.TWO);
         return fibonacci(number.subtract(BigInteger.ONE), callsWrapper).add(fibonacci(number.subtract(TWO), callsWrapper));
      }
   }

   public static void main(String[] args) {
      // displays the fibonacci values from 0-40
      for (int counter = 0; counter <= 40; counter++) {
         long before = System.currentTimeMillis();
         final BigInteger callsWrapper[] = {BigInteger.ONE};
         System.out.printf("Fibonacci of %d is: %d ", counter, fibonacci(BigInteger.valueOf(counter), callsWrapper));
         long after = System.currentTimeMillis();
         System.out.printf("with %s number of calls ", callsWrapper[0]);
         long difference = after - before;
         System.out.printf("that took %d milliseconds.%n", difference);
      }
   }
}


/*************************************************************************
* (C) Copyright 1992-2018 by Deitel & Associates, Inc. and               *
* Pearson Education, Inc. All Rights Reserved.                           *
*                                                                        *
* DISCLAIMER: The authors and publisher of this book have used their     *
* best efforts in preparing the book. These efforts include the          *
* development, research, and testing of the theories and programs        *
* to determine their effectiveness. The authors and publisher make       *
* no warranty of any kind, expressed or implied, with regard to these    *
* programs or to the documentation contained in these books. The authors *
* and publisher shall not be liable in any event for incidental or       *
* consequential damages in connection with, or arising out of, the       *
* furnishing, performance, or use of these programs.                     *
*************************************************************************/
