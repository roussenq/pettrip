package com.project.pettrip.domain.service.impl;

import com.project.pettrip.api.dto.FilterDTO;
import com.project.pettrip.domain.model.Filters;
import com.project.pettrip.domain.repository.FilterRepository;
import com.project.pettrip.domain.service.IFiltrerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class FilterServiceImpl implements IFiltrerService {

    private final FilterRepository filterRepository;

    private final ModelMapper modelMapper;

    public FilterServiceImpl(FilterRepository filterRepository, ModelMapper modelMapper) {
        this.filterRepository = filterRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * @param filters
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
    private Filters toFilterEntity(FilterDTO dto){
        return modelMapper.map(dto, Filters.class);
    }

}
