package pl.wojtek120.chessopeningswebtrainer.model.services;

import org.junit.Test;
import org.modelmapper.ModelMapper;
import pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening.move.UserOpeningMoveDto;
import pl.wojtek120.chessopeningswebtrainer.model.entities.UserOpeningMove;

import static org.junit.Assert.*;

public class UserOpeningMoveServiceTest {

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void mapToEntity() {
        UserOpeningMove userOpeningMove = new UserOpeningMove();
        UserOpeningMoveDto userOpeningMoveDto = new UserOpeningMoveDto();

        userOpeningMoveDto.setMoveNumber(1);
        userOpeningMoveDto.setComment("comment");

        userOpeningMove = modelMapper.map(userOpeningMoveDto, UserOpeningMove.class);

        assertEquals(userOpeningMove.getMoveNumber(), userOpeningMoveDto.getMoveNumber());
        assertEquals(userOpeningMove.getComment(), userOpeningMoveDto.getComment());
    }
}