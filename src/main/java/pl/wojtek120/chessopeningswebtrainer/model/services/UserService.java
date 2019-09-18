package pl.wojtek120.chessopeningswebtrainer.model.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.wojtek120.chessopeningswebtrainer.model.dto.user.UserDto;
import pl.wojtek120.chessopeningswebtrainer.model.entities.User;
import pl.wojtek120.chessopeningswebtrainer.model.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements ServiceInterface<UserDto> {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public UserService(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAll() {

        List<UserDto> userDtoList = new ArrayList<>();

        for(User user : userRepository.findAll()){
            userDtoList.add(convertToDto(user));
        }

        return userDtoList;
    }

    @Override
    public void saveAll(List<UserDto> dtos) {

        List<User> userList = new ArrayList<>();

        for(UserDto userDto : dtos){
            userList.add(convertToEntity(userDto));
        }

        userRepository.saveAll(userList);
    }

    @Override
    public UserDto getOne(Long id) {

        Optional<User> user = userRepository.findById(id);

        return user.map(this::convertToDto).orElse(null);

    }

    @Override
    public Long save(UserDto dto) {

        User user = convertToEntity(dto);

        return userRepository.save(user).getId();
    }

    @Override
    public void update(UserDto dto) {
        userRepository.save(convertToEntity(dto));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    private UserDto convertToDto(User user){
        return modelMapper.map(user, UserDto.class);
    }

    private User convertToEntity(UserDto userDto){
        return modelMapper.map(userDto, User.class);
    }
}