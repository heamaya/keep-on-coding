package chapter16.exercise11;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordTypeCount {
   public static void main(String[] args) {
      final Map<Character, Integer> myMap = new HashMap<>();
      createMap(myMap);
      displayMap(myMap);
   }

   private static void createMap(Map<Character, Integer> map) {
      final Scanner scanner = new Scanner(System.in);
      System.out.println("Enter a string:");
      final String input = scanner.nextLine();
      final Pattern letterPattern = Pattern.compile("[A-Za-z]");
      final Matcher matcher = letterPattern.matcher(input);
      final Character[] letters = matcher.results()
          .map(matchResult -> matchResult.group().toLowerCase().charAt(0))
          .toArray(Character[]::new);
      for (Character letter : letters) {
         if (map.containsKey(letter)) {
            int count = map.get(letter);
            map.put(letter, count + 1);
         }
         else {
            map.put(letter, 1);
         }
      }
   }

   private static void displayMap(Map<Character, Integer> map) {
      final Set<Character> keys = map.keySet(); // get keys
      final TreeSet<Character> sortedKeys = new TreeSet<>(keys);
      System.out.printf("%nMap contains:%nKey\t\tValue%n");
      for (Character key : sortedKeys) {
         System.out.printf("%-10c%10d%n", key, map.get(key));
      }
      System.out.printf("%nsize: %d%nisEmpty: %b%n", map.size(), map.isEmpty());
   }
}
