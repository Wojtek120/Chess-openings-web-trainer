package pl.wojtek120.chessopeningswebtrainer.model.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening.UserOpeningDto;
import pl.wojtek120.chessopeningswebtrainer.model.entities.UserOpening;
import pl.wojtek120.chessopeningswebtrainer.model.entities.UserOpeningBranch;
import pl.wojtek120.chessopeningswebtrainer.model.entities.UserOpeningMove;
import pl.wojtek120.chessopeningswebtrainer.model.repositories.UserOpeningRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserOpeningService implements ServiceInterface<UserOpeningDto> {

    private final ModelMapper modelMapper;
    private final UserOpeningRepository userOpeningRepository;

    public UserOpeningService(ModelMapper modelMapper, UserOpeningRepository userOpeningRepository) {
        this.modelMapper = modelMapper;
        this.userOpeningRepository = userOpeningRepository;
    }

    @Override
    public List<UserOpeningDto> getAll() {

        List<UserOpeningDto> userOpeningDtoList = new ArrayList<>();

        for(UserOpening userOpening : userOpeningRepository.findAll()){
            userOpeningDtoList.add(convertToDto(userOpening));
        }

        return userOpeningDtoList;
    }

    @Override
    public void saveAll(List<UserOpeningDto> dtos) {

        List<UserOpening> userOpeningList = new ArrayList<>();

        for(UserOpeningDto userOpeningDto : dtos){
            userOpeningList.add(convertToEntity(userOpeningDto));
        }

        userOpeningRepository.saveAll(userOpeningList);
    }

    @Override
    public UserOpeningDto getOne(Long id) {

        Optional<UserOpening> userOpening = userOpeningRepository.findById(id);

        return userOpening.map(this::convertToDto).orElse(null);

    }

    @Override
    public Long save(UserOpeningDto dto) {

        UserOpening userOpening = convertToEntity(dto);

        userOpening.getUserOpeningBranches().addAll(dto.getUserOpeningBranchIds().stream().map(id -> {
            UserOpeningBranch userOpeningBranch = new UserOpeningBranch();
            userOpeningBranch.setId(id);
            return userOpeningBranch;
        }).collect(Collectors.toList()));

        return userOpeningRepository.save(userOpening).getId();
    }

    @Override
    public void update(UserOpeningDto dto) {
        userOpeningRepository.save(convertToEntity(dto));
    }

    @Override
    public void deleteById(Long id) {
        userOpeningRepository.deleteById(id);
    }

    private UserOpeningDto convertToDto(UserOpening userOpening){
        return modelMapper.map(userOpening, UserOpeningDto.class);
    }

    private UserOpening convertToEntity(UserOpeningDto userOpeningDto){
        return modelMapper.map(userOpeningDto, UserOpening.class);
    }
}