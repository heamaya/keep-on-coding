package chapter17.exercise12;
// Invoice.java
public class InvoiceValue {
   private final String partDescription;
   private double value;
   
   // constructor
   public InvoiceValue(String partDescription, double value) {

      if (value < 0.0) { // validate value
         throw new IllegalArgumentException("Value must be >= 0");
      }

      this.partDescription = partDescription;
      this.value = value;
   } 
   
   public InvoiceValue(Invoice invoice) {
	  this(invoice.getPartDescription(), invoice.getInvoiceAmount());
   }

   // get description
   public String getPartDescription() {return partDescription;} 

   // set value
   public void setValue(double value) {
      
	  if (value < 0.0) {// validate value
         throw new IllegalArgumentException("Value must be >= 0");
      }

      this.value = value;
   } 

   // get value
   public double getValue() {return value;} 

   // return String representation of InvoiceValue object
   public String toString() {
	      return String.format("%s: %n%s: %s%n%s: $%,.2f", 
	         "invoice value", "part description", getPartDescription(), 
	         "value", getValue());
   }  
}
