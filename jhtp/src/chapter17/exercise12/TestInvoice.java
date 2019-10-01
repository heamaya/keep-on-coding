package chapter17.exercise12;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class TestInvoice {

	public static void main(String[] args) {
		Invoice[] invoices = new Invoice[8];
		invoices[0] = new Invoice("83", "Electric Sander", 7, 57.98d);
		invoices[1] = new Invoice("24", "Power saw", 18, 99.99d);
		invoices[2] = new Invoice("7", "Sledge hammer", 11, 21.50d);
		invoices[3] = new Invoice("77", "Hammer", 76, 11.99d);
		invoices[4] = new Invoice("39", "Lawn Mower", 3, 79.50d);
		invoices[5] = new Invoice("68", "Screwdriver", 106, 6.99d);
		invoices[6] = new Invoice("56", "Jig saw", 21, 11.00d);
		invoices[7] = new Invoice("3", "wrench", 34, 7.50d);
		
		// a)
		System.out.println("a)");
		System.out.println();
		
		Function<Invoice, Integer> toPartNumber = i -> Integer.valueOf(i.getPartNumber());
		Comparator<Invoice> byPartNumber = Comparator.comparing(toPartNumber);
		
		Arrays.stream(invoices)
			.sorted(byPartNumber)
			.forEach(System.out::println);
		
		// b)
		System.out.println();
		System.out.println("b)");
		System.out.println();
		
		Function<Invoice, Double> toPricePerItem = Invoice::getPricePerItem;
		Comparator<Invoice> byPricePerItem = Comparator.comparing(toPricePerItem);
		
		Arrays.stream(invoices)
			.sorted(byPricePerItem)
			.forEach(System.out::println);
		
		// c)
		System.out.println();
		System.out.println("c)");
		System.out.println();
		
		Function<Invoice, InvoiceQuantity> toInvoiceQuantity = InvoiceQuantity::new;
		Comparator<InvoiceQuantity> byQuantity = Comparator.comparing(InvoiceQuantity::getQuantity);
		
		Arrays.stream(invoices)
			.map(toInvoiceQuantity)
			.sorted(byQuantity)
			.forEach(System.out::println);
		
		// d)
		System.out.println();
		System.out.println("d)");
		System.out.println();
		
		Function<Invoice, InvoiceValue> toInvoiceValue = InvoiceValue::new;
		Comparator<InvoiceValue> byValue = Comparator.comparing(InvoiceValue::getValue);
		
		Arrays.stream(invoices)
			.map(toInvoiceValue)
			.sorted(byValue)
			.forEach(System.out::println);
		
		// e)
		System.out.println();
		System.out.println("e)");
		System.out.println();
		
		Predicate<InvoiceValue> greaterThanTwoHundred = iv -> iv.getValue() > 200d;
		Predicate<InvoiceValue> lessThanFiveHundred = iv -> iv.getValue() < 500d;
		
		Arrays.stream(invoices)
			.map(toInvoiceValue)
			.filter(greaterThanTwoHundred.and(lessThanFiveHundred))
			.sorted(byValue)
			.forEach(System.out::println);
		
		// f)
		System.out.println();
		System.out.println("f)");
		System.out.println();
		
		Predicate<Invoice> containsSaw = i -> i.getPartDescription().contains("saw");
		
		Optional<Invoice> optionalInvoice = Arrays.stream(invoices)
			.filter(containsSaw)
			.findAny();
		
		if(optionalInvoice.isPresent()) {
			System.out.println(optionalInvoice.get());
		}
				

	}

}
