package pl.wojtek120.chessopeningswebtrainer.model.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening.move.UserOpeningMoveCreationDto;
import pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening.move.UserOpeningMoveDto;
import pl.wojtek120.chessopeningswebtrainer.model.entities.UserOpeningMove;
import pl.wojtek120.chessopeningswebtrainer.model.repositories.UserOpeningBranchRepository;
import pl.wojtek120.chessopeningswebtrainer.model.repositories.UserOpeningMoveRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserOpeningMoveService implements ServiceInterface<UserOpeningMoveDto> {

    private final ModelMapper modelMapper;
    private final UserOpeningMoveRepository userOpeningMoveRepository;
    private final UserOpeningBranchRepository userOpeningBranchRepository;

    public UserOpeningMoveService(ModelMapper modelMapper, UserOpeningMoveRepository userOpeningMoveRepository, UserOpeningBranchRepository userOpeningBranchRepository) {
        this.modelMapper = modelMapper;
        this.userOpeningMoveRepository = userOpeningMoveRepository;
        this.userOpeningBranchRepository = userOpeningBranchRepository;
    }

    @Override
    public List<UserOpeningMoveDto> getAll() {

        List<UserOpeningMoveDto> userOpeningMoveDtoList = new ArrayList<>();

        for(UserOpeningMove userOpeningMove : userOpeningMoveRepository.findAll()){
            userOpeningMoveDtoList.add(convertToDto(userOpeningMove));
        }

        return userOpeningMoveDtoList;
    }

    @Override
    public void saveAll(List<UserOpeningMoveDto> dtos) {

        List<UserOpeningMove> userOpeningMoveList = new ArrayList<>();

        for(UserOpeningMoveDto userOpeningMoveDto : dtos){
            userOpeningMoveList.add(convertToEntity(userOpeningMoveDto));
        }

        userOpeningMoveRepository.saveAll(userOpeningMoveList);
    }

    @Override
    public UserOpeningMoveDto getOne(Long id) {

        Optional<UserOpeningMove> userOpeningMove = userOpeningMoveRepository.findById(id);

        return userOpeningMove.map(this::convertToDto).orElse(null);

    }

    @Override
    public Long save(UserOpeningMoveDto dto) {

        UserOpeningMove userOpeningMove = convertToEntity(dto);
        userOpeningMove.setUserOpeningBranch(userOpeningBranchRepository.getOne(dto.getUserOpeningBranchId()));

        return userOpeningMoveRepository.save(userOpeningMove).getId();
    }

    public Long save(UserOpeningMoveCreationDto dto) {

        UserOpeningMove userOpeningMove = convertToEntity(dto);
        userOpeningMove.setUserOpeningBranch(userOpeningBranchRepository.getOne(dto.getUserOpeningBranchId()));

        return userOpeningMoveRepository.save(userOpeningMove).getId();
    }

    @Override
    public void update(UserOpeningMoveDto dto) {
        userOpeningMoveRepository.save(convertToEntity(dto));
    }

    @Override
    public void deleteById(Long id) {
        userOpeningMoveRepository.deleteById(id);
    }

    private UserOpeningMoveDto convertToDto(UserOpeningMove userOpeningMove){
        return modelMapper.map(userOpeningMove, UserOpeningMoveDto.class);
    }

    private UserOpeningMove convertToEntity(UserOpeningMoveDto userOpeningMoveDto){
        return modelMapper.map(userOpeningMoveDto, UserOpeningMove.class);
    }
}
