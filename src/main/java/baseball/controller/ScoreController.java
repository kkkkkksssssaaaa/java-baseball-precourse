package baseball.controller;

import baseball.model.ComputerPlayer;
import baseball.model.Player;

import java.util.List;
import java.util.Optional;

public class ScoreController extends AbstractScoreController {

    @Override
    public Boolean isNothing(Player player, ComputerPlayer computer) {
        if (!super.checkValid(player, computer)) {
            return false;
        }

        if (!super.checkNotContainsAll(player, computer)) {
            return false;
        }

        return true;
    }

    @Override
    public Boolean isFourBall(Player player, ComputerPlayer computer) {
        if (!super.checkValid(player, computer)) {
            return false;
        }

        if (!super.checkContainsAll(player, computer)) {
            return false;
        }

        if (!super.checkNotSamePlace(player, computer)) {
            return false;
        }

        return true;
    }


}
