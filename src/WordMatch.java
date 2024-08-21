public class WordMatch {
  private String answer;

  public WordMatch(String word) {
    answer = word;
  }

  public int scoreGuess(String guess) {
    // Reject guesses that are longer than the actual word
    if (guess.length() > answer.length())
      throw new IllegalArgumentException("Your guess cannot exceed the number of characters in the Secret word");

    // Reject guesses that are empty
    else if (guess.isEmpty())
      throw new IllegalArgumentException("Your guess cannot be an empty String!");

    int score = 0;

    // Count the number of occurences of the guess by looping
    // through substrings of the answer with the same length as the guess string
    for (int i = 0; i + guess.length() <= answer.length(); i++) {
      if (answer.substring(i, i + guess.length()).equals(guess)) score++;
      // Simpler alternative:
      // if (answer.startsWith(guess, i)) score++;
      // Finds if answer starts with (with respect to the index, i) the substring guess
    }

    // Multiply by the square of the length of the guess
    score *= guess.length() * guess.length();
    return score;
  }

  public String findBetterGuess(String a, String b) {
    // If the two words have the same score, return the one that is lexicographically later.
    // compareTo returns a positive value if a follows after b, and a negative value if a precedes b.
    if (scoreGuess(a) == scoreGuess(b)) return a.compareTo(b) > 0 ? a : b;

    // Otherwise return the word with the higher score.
    return scoreGuess(a) > scoreGuess(b) ? a : b;
  }
}
