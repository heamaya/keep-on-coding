package chapter14.exercise5;

import java.security.SecureRandom;
import java.util.stream.IntStream;

public class RandomSentences {

    private static final int WORDS_COUNT = 5;
    private static final String SPACE = " ";
    private static final String DOT = ".";
    private final String [] articles = {"the", "a", "one", "some", "any"};
    private final String [] nouns = {"boy", "girl", "dog", "town", "car"};
    private final String [] verbs = {"drove", "jumped", "ran", "walked", "skipped"};
    private final String [] prepositions = {"to", "from", "over", "under", "on"};
    private final SecureRandom secureRandom;

    public static void main(String [] args) {
        final RandomSentences randomSentences = new RandomSentences();
        IntStream.rangeClosed(1, 20)
            .forEach(i -> System.out.println(randomSentences.generate()));
    }

    public RandomSentences() {
        this.secureRandom = new SecureRandom();
    }

    public String generate() {
        final int randomArticle1Index = secureRandom.nextInt(WORDS_COUNT);
        return articles[randomArticle1Index].substring(0, 1).toUpperCase()
            .concat(articles[randomArticle1Index].substring(1))
            .concat(SPACE)
            .concat(nouns[secureRandom.nextInt(WORDS_COUNT)])
            .concat(SPACE)
            .concat(verbs[secureRandom.nextInt(WORDS_COUNT)])
            .concat(SPACE)
            .concat(prepositions[secureRandom.nextInt(WORDS_COUNT)])
            .concat(SPACE)
            .concat(articles[secureRandom.nextInt(WORDS_COUNT)])
            .concat(SPACE)
            .concat(nouns[secureRandom.nextInt(WORDS_COUNT)])
            .concat(DOT);
    }
}
