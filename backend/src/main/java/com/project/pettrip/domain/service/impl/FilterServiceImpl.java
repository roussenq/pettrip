package com.project.pettrip.domain.service.impl;

import com.project.pettrip.api.dto.FilterDTO;
import com.project.pettrip.domain.model.Filters;
import com.project.pettrip.domain.model.FiltersEnum;
import com.project.pettrip.domain.repository.FilterRepository;
import com.project.pettrip.domain.service.IFiltrerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe da camada Service com a lógica de implementação referênte aos filtros.
 */
@Service
public class FilterServiceImpl implements IFiltrerService {

    private final FilterRepository filterRepository;
    private final ModelMapper modelMapper;

    /**
     * Construtor de instanciação de FilterServiceImpl.
     *
     * @param filterRepository referência à Interface FilterRepository.
     * @param modelMapper      referência à ModelMapper.
     */
    public FilterServiceImpl(FilterRepository filterRepository, ModelMapper modelMapper) {
        this.filterRepository = filterRepository;
        this.modelMapper = modelMapper;
    }


    /**
     * Cria uma lista de filtros observando se cada filtro já existe no banco de dados.
     * Caso exista: utiliza o filtro existente.
     * Caso não exista: cria um novo filtro no banco de dados.
     *
     * @param filters uma lista de FilterDTO.
     * @return uma lista de entidades Filters.
     * @override interface IFilterservice
     */
    @Override
    public List<Filters> create(List<FilterDTO> filters) {
        List<Filters> filters2 = new ArrayList<>();
        for (FilterDTO filter : filters) {
            Filters filterFinded = filterRepository.findByTypeAndWeightAndCastratedAndGender(filter.getType(), filter.getWeight(), filter.getCastrated(), filter.getGender());
            if (filterFinded != null) {
                filters2.add(filterFinded);
            }else{
                filters2.add(filterRepository.save(toFilterEntity(filter)));
            }
        }
        return filters2;
    }

    /**
     * Método para conversão de FilterDTO para entidade Filters
     *
     * @param dto FilterDTO.
     * @return entidade Filters.
     */
    private Filters toFilterEntity(FilterDTO dto){
        return modelMapper.map(dto, Filters.class);
    }

}
