package chapter17.exercise12;
// Invoice.java
public class InvoiceQuantity {
   private final String partDescription;
   private int quantity;

   // constructor
   public InvoiceQuantity(String partDescription, int quantity) {
      if (quantity < 0) { // validate quantity
         throw new IllegalArgumentException("Quantity must be >= 0");
      }

      this.quantity = quantity;
      this.partDescription = partDescription;
   }
   
   public InvoiceQuantity(Invoice invoice) {
	   this(invoice.getPartDescription(), invoice.getQuantity());
   }

   // get description
   public String getPartDescription() {return partDescription;} 

   // set quantity
   public void setQuantity(int quantity) {
      if (quantity < 0) { // validate quantity
         throw new IllegalArgumentException("Quantity must be >= 0");
      }

      this.quantity = quantity;
   } 

   // get quantity
   public int getQuantity() {return quantity;}

   public String toString() {
      return String.format("%s: %n%s: %s%n%s: %d", 
         "invoice quantity", "part description", getPartDescription(), 
         "quantity", getQuantity());
   } 
}