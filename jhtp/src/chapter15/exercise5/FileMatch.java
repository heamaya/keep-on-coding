package chapter15.exercise5;

import chapter15.exercise4.Account;
import chapter15.exercise4.Transaction;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class FileMatch {

    private static final String PATH = "src"
        .concat(File.separator)
        .concat("chapter15")
        .concat(File.separator)
        .concat("exercise5")
        .concat(File.separator);
    private static final String OLD_MASTER = PATH.concat("old_master.txt");
    private static final String MULTIPLE_TRANSACTIONS = PATH.concat("multiple_transactions.txt");
    private static final String NEW_MASTER = PATH.concat("new_master.txt");
    private static final String LOG = PATH.concat("log.txt");
    private static final String SPACE = " ";
    private final Map<Long, Account> accountsMap;
    private final Map<Long, List<Transaction>> transactionsMap;

    public FileMatch() {
        accountsMap = new TreeMap<>();
        transactionsMap = new HashMap<>();
    }

    public static void main(String [] args) {
        final FileMatch fileMatch = new FileMatch();
        fileMatch.apply();
    }

    private void readAccounts() {
        try (Scanner oldMasterInput = new Scanner(Paths.get(OLD_MASTER))) {
            if (oldMasterInput.hasNext()) {
                oldMasterInput.nextLine();
            }
            while (oldMasterInput.hasNext()) {
                final Account account = new Account();
                account.setAccountNumber(oldMasterInput.nextLong());
                account.setFirstName(oldMasterInput.next());
                account.setLastName(oldMasterInput.next());
                account.setBalance(oldMasterInput.nextDouble());
                accountsMap.put(account.getAccountNumber(), account);
            }
        } catch (IOException ioException) {
            System.out.println("IO Exception reading old_master.txt");
        }
    }

    private void readTransactions() {
        try (Scanner transactionsInput = new Scanner(Paths.get(MULTIPLE_TRANSACTIONS))) {
            if (transactionsInput.hasNext()) {
                transactionsInput.nextLine();
            }
            while (transactionsInput.hasNext()) {
                final Transaction transaction = new Transaction();
                transaction.setAccountNumber(transactionsInput.nextLong());
                transaction.setAmount(transactionsInput.nextDouble());
                transactionsMap.compute(
                    transaction.getAccountNumber(),
                    (key, value) -> {
                        if (value == null) {
                            value = new ArrayList<>();
                        }
                        value.add(transaction);
                        return value;
                    }
                );
            }
        } catch (IOException ioException) {
            System.out.println("IO Exception reading transactionsMap.txt");
        }
    }

    public void apply() {
        readAccounts();
        readTransactions();
        try (Formatter newMasterOutput = new Formatter(NEW_MASTER);
            Formatter logOutput = new Formatter(LOG)) {
            newMasterOutput.format("%-30s%-20s%-20s%n", "Account Number", "Name", "Amount");
            transactionsMap.entrySet().stream()
                .filter(transactionEntry -> !accountsMap.containsKey(transactionEntry.getKey()))
                .forEachOrdered(transactionEntry -> {
                    logOutput.format("Unmatched record for account number: %d%n", transactionEntry.getKey());
                });
            accountsMap.entrySet().stream()
                .forEachOrdered(accountEntry -> {
                    final Account account = accountEntry.getValue();
                    if (transactionsMap.containsKey(account.getAccountNumber())) {
                        transactionsMap.get(account.getAccountNumber())
                            .stream()
                            .forEach(transaction -> {
                                account.combine(transaction);
                            });
                    }
                    newMasterOutput.format(
                        "%-30d%-20s%20.2f%n",
                        account.getAccountNumber(),
                        account.getFirstName().concat(SPACE).concat(account.getLastName()),
                        account.getBalance()
                    );
                });
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found exception reading new_master.txt");
        }
    }
}
