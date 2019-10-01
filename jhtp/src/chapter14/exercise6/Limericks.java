package chapter14.exercise6;

import java.security.SecureRandom;

public class Limericks {

    private static final int WORDS_COUNT = 5;
    private static final String SPACE = " ";
    private final String [] articles = {"the", "a", "one", "some", "any"};
    private final String [] nouns = {"boy", "girl", "dog", "town", "car"};
    private final String [] verbs = {"drove", "jumped", "ran", "walked", "skipped"};
    private final String [] prepositions = {"to", "from", "over", "under", "on"};
    private final String [] nounsRhyme0 = {"toy", "soy", "joy", "convoy", "sepoy"};
    private final String [] nounsRhyme1 = {"curl", "pearl", "schoolgirl", "churl", "knurl"};
    private final String [] nounsRhyme2 = {"bog", "frog", "job", "log", "sob"};
    private final String [] nounsRhyme3 = {"downtown", "dawn", "pawn", "lawn", "awn"};
    private final String [] nounsRhyme4 = {"calendar", "guitar", "bar", "sugar", "altar"};

    private final SecureRandom secureRandom;

    public static void main(String [] args) {
        final Limericks limericks = new Limericks();
        System.out.println(limericks.generate());
    }

    public Limericks() {
        this.secureRandom = new SecureRandom();
    }

    public String generate() {
        final int randomFinalNounOneTwoFiveVersesIndex = secureRandom.nextInt(WORDS_COUNT);
        final int randomFinalNounThreeFourVersesIndex = secureRandom.nextInt(WORDS_COUNT);
        return String.format(
            "%s%n%s%n%s%n%s%n%s.",
            generateVerse(randomFinalNounOneTwoFiveVersesIndex),
            generateVerse(randomFinalNounOneTwoFiveVersesIndex),
            generateVerse(randomFinalNounThreeFourVersesIndex),
            generateVerse(randomFinalNounThreeFourVersesIndex),
            generateVerse(randomFinalNounOneTwoFiveVersesIndex)
        );
    }

    public String generateVerse(int finalNounIndex) {
        final int randomArticle1Index = secureRandom.nextInt(WORDS_COUNT);
        final String lastNoun = getLastNoun(finalNounIndex);
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
            .concat(lastNoun);
    }

    private String getLastNoun(int randomFinalNounIndex) {
        final int randomRhymeIndex = secureRandom.nextInt(WORDS_COUNT);
        final String rhyme;
        switch (randomFinalNounIndex) {
            case 1:
                rhyme = nounsRhyme1[randomRhymeIndex];
                break;
            case 2:
                rhyme = nounsRhyme2[randomRhymeIndex];
                break;
            case 3:
                rhyme = nounsRhyme3[randomRhymeIndex];
                break;
            case 4:
                rhyme = nounsRhyme4[randomRhymeIndex];
                break;
            default:
                rhyme = nounsRhyme0[randomRhymeIndex];
                break;
        }
        return rhyme;
    }
}
