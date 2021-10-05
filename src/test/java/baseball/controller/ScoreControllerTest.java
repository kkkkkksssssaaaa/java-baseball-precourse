package baseball.controller;

import baseball.model.ComputerPlayer;
import baseball.model.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreControllerTest {

    ComputerPlayer computer = new ComputerPlayer();
    ScoreController scoreController = new ScoreController();

    @Nested
    @DisplayName("낫싱 테스트")
    class NothingTest {

        @Test
        void Null을_인자로_받으면_false를_반환한다() {
            Boolean isNothing = scoreController.isNothing(null, null);

            assertFalse(isNothing);
        }

        @Test
        void 인자_중_한_개라도_Null이_포함_되어_있다면_false를_반환한다() {
            Boolean isNothing = scoreController.isNothing(null, computer);

            assertFalse(isNothing);
        }

        @Test
        void 컬렉션의_요소가_비어있어도_false를_반환한다() {
            String playerNumber = "165";

            Player player = new Player(playerNumber);


            ComputerPlayer computer = new ComputerPlayer();
            computer.setNumbersOnlyTest(new ArrayList<>());

            Boolean isNothing = scoreController.isNothing(player, computer);

            assertFalse(isNothing);
        }

        @Test
        void 두_컬렉션을_비교하여_같은_요소가_존재한다면_false를_반환한다() {
            StringBuilder getNumbers = new StringBuilder();

            for (int i = 0; i < computer.getSize(); i++) {
                getNumbers.append(computer.getNumbers().get(i));
            }

            getNumbers.reverse();

            Player player = new Player(getNumbers.toString());

            Boolean isNothing = scoreController.isNothing(player, computer);

            assertFalse(isNothing);
        }

        @ParameterizedTest
        @ValueSource(strings = { "165", "145", "154", "156", "175", "198", "197", "256", "298", "276", "245", "256", "365", "672", "265"," 372", "376" })
        void 두_컬렉션을_비교하여_한_가지_요소만_같아도_false를_반환한다() {
            ComputerPlayer computer = createNewComputer("123");

            String playerNumber = "165";

            Player player = new Player(playerNumber);

            Boolean isNothing = scoreController.isNothing(player, computer);

            assertFalse(isNothing);
        }

        @Test
        void 두_컬렉션을_비교하여_같은_요소가_존재하지_않는다면_true를_반환한다() {
            ComputerPlayer computer = createNewComputer("123");

            String playerNumber = "456";

            Player player = new Player(playerNumber);

            Boolean isNothing = scoreController.isNothing(player, computer);

            assertTrue(isNothing);
        }

    }

    @Nested
    @DisplayName("포볼 테스트")
    class FourBallTest {

        @Test
        void Null을_인자로_받으면_false를_반환한다() {
            Boolean isFourBall = scoreController.isFourBall(null, null);

            assertFalse(isFourBall);
        }

        @Test
        void 인자_중_한_개라도_Null이_포함_되어_있다면_false를_반환한다() {
            Boolean isFourBall = scoreController.isFourBall(null, computer);

            assertFalse(isFourBall);
        }

        @Test
        void 컬렉션의_요소가_비어있어도_false를_반환한다() {
            String playerNumber = "165";

            Player player = new Player(playerNumber);


            ComputerPlayer computer = new ComputerPlayer();
            computer.setNumbersOnlyTest(new ArrayList<>());

            Boolean isFourBall = scoreController.isFourBall(player, computer);

            assertFalse(isFourBall);
        }

        @ParameterizedTest
        @ValueSource(strings = { "165", "145", "154", "156", "175", "198", "197", "256", "298", "276", "245", "256", "365", "672", "265"," 372", "376" })
        void 두_컬렉션을_비교하여_한_가지_요소만_같아도_false를_반환한다() {
            ComputerPlayer computer = createNewComputer("123");

            String playerNumber = "165";

            Player player = new Player(playerNumber);

            Boolean isFourBall = scoreController.isFourBall(player, computer);

            assertFalse(isFourBall);
        }

        @Test
        void 두_컬렉션을_비교하여_같은_요소가_존재하지_않는다면_false를_반환한다() {
            ComputerPlayer computer = createNewComputer("123");

            String playerNumber = "456";

            Player player = new Player(playerNumber);

            Boolean isFourBall = scoreController.isFourBall(player, computer);

            assertFalse(isFourBall);
        }

        @ParameterizedTest
        @ValueSource(strings = { "132", "213", "321" })
        void 두_컬렉션을_비교하여_모든_요소를_포함하지만_한_자리만_같아도_false를_반환한다(String input) {
            ComputerPlayer computer = createNewComputer("123");

            Player player = new Player(input);

            Boolean isFourBall = scoreController.isFourBall(player, computer);

            assertFalse(isFourBall);
        }

        @ParameterizedTest
        @CsvSource(value = { "123:312", "123:231", "456:564", "456:645", "789:897", "789:978" },
                delimiter = ':')
        void 두_컬렉션을_비교하여_모든_요소를_포함하지만_자리가_다른_경우만_true를_반환한다(String playerNumber,
                                                            String computerNumber) {
            ComputerPlayer computer = createNewComputer(computerNumber);

            Player player = new Player(playerNumber);

            Boolean isFourBall = scoreController.isFourBall(player, computer);

            assertTrue(isFourBall);
        }

    }

    @Nested
    @DisplayName("스트라이크 테스트")
    class StrikeTest {

        @Test
        void Null을_인자로_받으면_0을_반환한다() {
            Integer getCount = scoreController.getStrikeCount(null, null);

            assertEquals(getCount, 0);
        }

        @Test
        void 인자_중_한_개라도_Null이_포함_되어_있다면_0을_반환한다() {
            Integer getCount = scoreController.getStrikeCount(null, computer);

            assertEquals(getCount, 0);
        }

        @Test
        void 컬렉션의_요소가_비어있어도_0을_반환한다() {
            String playerNumber = "165";

            Player player = new Player(playerNumber);

            ComputerPlayer computer = new ComputerPlayer();
            computer.setNumbersOnlyTest(new ArrayList<>());

            Integer getCount = scoreController.getStrikeCount(player, computer);

            assertEquals(getCount, 0);
        }

        @ParameterizedTest
        @CsvSource(value = { "123:456", "123:789", "456:123", "456:789", "789:321", "789:654" }, delimiter = ':')
        void 컬렉션의_요소_중_중복되는_요소가_없다면_0을_반환한다(String playerNumber,
                                            String computerNumber) {
            Player player = new Player(playerNumber);
            ComputerPlayer computer = createNewComputer(computerNumber);

            Integer getCount = scoreController.getStrikeCount(player, computer);

            assertEquals(getCount, 0);
        }

        @ParameterizedTest
        @CsvSource(value = { "345:123", "256:123", "356:123", "124:456", "512:456" }, delimiter = ':')
        void 컬렉션의_요소_중_중복되는_요소가_있지만_자리가_틀린_경우_0을_반환한다(String playerNumber,
                                                      String computerNumber) {
            Player player = new Player(playerNumber);
            ComputerPlayer computer = createNewComputer(computerNumber);

            Integer getCount = scoreController.getStrikeCount(player, computer);

            assertEquals(getCount, 0);
        }

    }

    private ComputerPlayer createNewComputer(String numberString) {
        ComputerPlayer computer = new ComputerPlayer();

        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < numberString.length(); i++) {
            numbers.add(Integer.parseInt(numberString.substring(i, (i + 1))));
        }

        computer.setNumbersOnlyTest(numbers);

        return computer;
    }

}