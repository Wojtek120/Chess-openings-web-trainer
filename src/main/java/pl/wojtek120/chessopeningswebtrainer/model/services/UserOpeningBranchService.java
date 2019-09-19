package pl.wojtek120.chessopeningswebtrainer.model.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening.branch.UserOpeningBranchDto;
import pl.wojtek120.chessopeningswebtrainer.model.entities.UserOpening;
import pl.wojtek120.chessopeningswebtrainer.model.entities.UserOpeningBranch;
import pl.wojtek120.chessopeningswebtrainer.model.entities.UserOpeningMove;
import pl.wojtek120.chessopeningswebtrainer.model.repositories.UserOpeningBranchRepository;
import pl.wojtek120.chessopeningswebtrainer.model.repositories.UserOpeningMoveRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserOpeningBranchService implements ServiceInterface<UserOpeningBranchDto> {

    private final ModelMapper modelMapper;
    private final UserOpeningBranchRepository userOpeningBranchRepository;
    private final UserOpeningMoveRepository userOpeningMoveRepository;

    public UserOpeningBranchService(ModelMapper modelMapper, UserOpeningBranchRepository userOpeningBranchRepository, UserOpeningMoveRepository userOpeningMoveRepository) {
        this.modelMapper = modelMapper;
        this.userOpeningBranchRepository = userOpeningBranchRepository;
        this.userOpeningMoveRepository = userOpeningMoveRepository;
    }

    @Override
    public List<UserOpeningBranchDto> getAll() {

        List<UserOpeningBranchDto> userOpeningBranchDtoList = new ArrayList<>();

        for (UserOpeningBranch userOpeningBranch : userOpeningBranchRepository.findAll()) {
            userOpeningBranchDtoList.add(convertToDto(userOpeningBranch));
        }

        return userOpeningBranchDtoList;
    }

    @Override
    public void saveAll(List<UserOpeningBranchDto> dtos) {

        List<UserOpeningBranch> userOpeningBranchList = new ArrayList<>();

        for (UserOpeningBranchDto userOpeningBranchDto : dtos) {
            userOpeningBranchList.add(convertToEntity(userOpeningBranchDto));
        }

        userOpeningBranchRepository.saveAll(userOpeningBranchList);
    }

    @Override
    public UserOpeningBranchDto getOne(Long id) {

        Optional<UserOpeningBranch> userOpeningBranch = userOpeningBranchRepository.findById(id);

        return userOpeningBranch.map(this::convertToDto).orElse(null);

    }

    @Override
    public Long save(UserOpeningBranchDto dto) {
        UserOpeningBranch userOpeningBranch = convertToEntity(dto);

        UserOpening userOpening = new UserOpening();
        userOpening.setId(dto.getUserOpeningId());
        userOpeningBranch.setUserOpening(userOpening);

        userOpeningBranch.getUserOpeningMoves().addAll(dto.getUserOpeningMovesIds().stream().map(id -> {
            UserOpeningMove userOpeningMove = new UserOpeningMove();
            userOpeningMove.setId(id);
            return userOpeningMove;
        }).collect(Collectors.toList()));

        return userOpeningBranchRepository.save(userOpeningBranch).getId();
    }

    @Override
    public void update(UserOpeningBranchDto dto) {
        userOpeningBranchRepository.save(convertToEntity(dto));
    }

    @Override
    public void deleteById(Long id) {
        userOpeningBranchRepository.deleteById(id);
    }

    private UserOpeningBranchDto convertToDto(UserOpeningBranch userOpeningBranch) {
        return modelMapper.map(userOpeningBranch, UserOpeningBranchDto.class);
    }

    private UserOpeningBranch convertToEntity(UserOpeningBranchDto userOpeningBranchDto) {
        return modelMapper.map(userOpeningBranchDto, UserOpeningBranch.class);
    }


    public void deleteAllByOpeningIdWithAllMoves(Long openingId) {
        List<UserOpeningBranch> allByUserOpeningId = userOpeningBranchRepository.getAllByUserOpeningId(openingId);

        allByUserOpeningId.forEach(e -> userOpeningMoveRepository.deleteAllByUserOpeningBranchId(e.getId()));
        userOpeningBranchRepository.deleteAllByUserOpeningId(openingId);
    }

    public String loadOpeningBranchesInfo(Long openingId) {

        StringBuilder string = new StringBuilder();
        List<UserOpeningBranch> allByOpeningIdWithMoves = userOpeningBranchRepository.getAllByOpeningIdWithMoves(openingId);

        string.append("{");
        for (UserOpeningBranch userOpeningBranch : allByOpeningIdWithMoves) {

            for (UserOpeningMove userOpeningMove : userOpeningBranch.getUserOpeningMoves()) {
                string.append("\"branch.").append(userOpeningBranch.getBranchNumber()).append(".")
                        .append(userOpeningBranch.getParentBranch()).append(".")
                        .append(userOpeningMove.getMoveNumber()).append("\" : \"")
                        .append(userOpeningMove.getPgn()).append("\",");
            }
        }

        string.setLength(Math.max(string.length() - 1, 0));
        string.append("}");


        System.out.println(string);

        return string.toString();
    }
}