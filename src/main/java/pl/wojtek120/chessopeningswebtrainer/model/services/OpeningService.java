package pl.wojtek120.chessopeningswebtrainer.model.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening.OpeningDto;
import pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening.UserOpeningDto;
import pl.wojtek120.chessopeningswebtrainer.model.entities.Opening;
import pl.wojtek120.chessopeningswebtrainer.model.repositories.OpeningRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OpeningService implements ServiceInterface<OpeningDto> {
    
    private final ModelMapper modelMapper;
    private final OpeningRepository openingRepository;

    public OpeningService(ModelMapper modelMapper, OpeningRepository openingRepository) {
        this.modelMapper = modelMapper;
        this.openingRepository = openingRepository;
    }

    @Override
    public List<OpeningDto> getAll() {

        List<OpeningDto> openingDtoList = new ArrayList<>();

        for(Opening opening : openingRepository.findAll()){
            openingDtoList.add(convertToDto(opening));
        }

        return openingDtoList;
    }

    @Override
    public void saveAll(List<OpeningDto> dtos) {

        List<Opening> openingList = new ArrayList<>();

        for(OpeningDto openingDto : dtos){
            openingList.add(convertToEntity(openingDto));
        }

        openingRepository.saveAll(openingList);
    }

    @Override
    public OpeningDto getOne(Long id) {

        Optional<Opening> opening = openingRepository.findById(id);

        return opening.map(this::convertToDto).orElse(null);

    }

    @Override
    public Long save(OpeningDto dto) {

        Opening opening = convertToEntity(dto);

        return openingRepository.save(opening).getId();
    }

    @Override
    public void update(OpeningDto dto) {
        openingRepository.save(convertToEntity(dto));
    }

    @Override
    public void deleteById(Long id) {
        openingRepository.deleteById(id);
    }

    private OpeningDto convertToDto(Opening opening){
        return modelMapper.map(opening, OpeningDto.class);
    }

    private Opening convertToEntity(OpeningDto openingDto){
        return modelMapper.map(openingDto, Opening.class);
    }
}
