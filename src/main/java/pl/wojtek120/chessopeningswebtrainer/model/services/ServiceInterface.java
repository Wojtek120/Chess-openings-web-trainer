package pl.wojtek120.chessopeningswebtrainer.model.services;

import java.util.List;

public interface ServiceInterface<D> {

    List<D> getAll();
    void saveAll(List<D> dtos);
    D getOne(Long id);
    Long save(D dto);
    void update(D dto);
    void deleteById(Long id);

}
