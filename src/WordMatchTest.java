import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordMatchTest {
  private WordMatch game1;
  private WordMatch game2;
  private WordMatch game3;

  @BeforeEach
  void setUp() {
    game1 = new WordMatch("mississippi");
    game2 = new WordMatch("aaaabb");
    game3 = new WordMatch("concatenation");
  }

  @AfterEach
  void tearDown() {
    game1 = null;
    game2 = null;
    game3 = null;
  }

  @Test
  void guessIsEmptyString() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> game1.scoreGuess(""));

    String expectedMessage = "Your guess cannot be an empty String!";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void guessLengthExceedsSecretLength() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> game1.scoreGuess("mississippi river"));

    String expectedMessage = "Your guess cannot exceed the number of characters in the Secret word";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void scoreGuessOne() {
    assertEquals(4, game1.scoreGuess("i"));
    assertEquals(18, game1.scoreGuess("iss"));
    assertEquals(36, game1.scoreGuess("issipp"));
    assertEquals(121, game1.scoreGuess("mississippi"));
  }

  @Test
  void scoreGuessTwo() {
    assertEquals(4, game2.scoreGuess("a"));
    assertEquals(12, game2.scoreGuess("aa"));
    assertEquals(18, game2.scoreGuess("aaa"));
    assertEquals(16, game2.scoreGuess("aabb"));
    assertEquals(0, game2.scoreGuess("c"));
  }

  @Test
  void scoreGuessThree() {
    assertEquals(9, game3.scoreGuess("ten"));
    assertEquals(36, game3.scoreGuess("nation"));
    assertEquals(9, game3.scoreGuess("con"));
    assertEquals(9, game3.scoreGuess("cat"));
  }

  @Test
  void findBetterGuess() {
    assertEquals("nation", game3.findBetterGuess("ten", "nation"));
    assertEquals("con", game3.findBetterGuess("con", "cat"));
  }
}