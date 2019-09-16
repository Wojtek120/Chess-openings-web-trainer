package pl.wojtek120.chessopeningswebtrainer.model.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening.branch.UserOpeningBranchDto;
import pl.wojtek120.chessopeningswebtrainer.model.entities.UserOpeningBranch;
import pl.wojtek120.chessopeningswebtrainer.model.repositories.UserOpeningBranchRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserOpeningBranchService implements ServiceInterface<UserOpeningBranchDto> {

    private final ModelMapper modelMapper;
    private final UserOpeningBranchRepository userOpeningBranchRepository;

    public UserOpeningBranchService(ModelMapper modelMapper, UserOpeningBranchRepository userOpeningBranchRepository) {
        this.modelMapper = modelMapper;
        this.userOpeningBranchRepository = userOpeningBranchRepository;
    }

    @Override
    public List<UserOpeningBranchDto> getAll() {

        List<UserOpeningBranchDto> userOpeningBranchDtoList = new ArrayList<>();

        for(UserOpeningBranch userOpeningBranch : userOpeningBranchRepository.findAll()){
            userOpeningBranchDtoList.add(convertToDto(userOpeningBranch));
        }

        return userOpeningBranchDtoList;
    }

    @Override
    public void saveAll(List<UserOpeningBranchDto> dtos) {

        List<UserOpeningBranch> userOpeningBranchList = new ArrayList<>();

        for(UserOpeningBranchDto userOpeningBranchDto : dtos){
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
    public void save(UserOpeningBranchDto dto) {
        userOpeningBranchRepository.save(convertToEntity(dto));
    }

    @Override
    public void update(UserOpeningBranchDto dto) {
        userOpeningBranchRepository.save(convertToEntity(dto));
    }

    @Override
    public void deleteById(Long id) {
        userOpeningBranchRepository.deleteById(id);
    }

    private UserOpeningBranchDto convertToDto(UserOpeningBranch userOpeningBranch){
        return modelMapper.map(userOpeningBranch, UserOpeningBranchDto.class);
    }

    private UserOpeningBranch convertToEntity(UserOpeningBranchDto userOpeningBranchDto){
        return modelMapper.map(userOpeningBranchDto, UserOpeningBranch.class);
    }
}