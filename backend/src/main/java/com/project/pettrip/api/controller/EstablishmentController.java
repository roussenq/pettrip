package com.project.pettrip.api.controller;

import com.project.pettrip.api.dto.EstablishmentInputDTO;
import com.project.pettrip.api.dto.EstablishmentSummaryDTO;
import com.project.pettrip.domain.service.CityService;
import com.project.pettrip.domain.service.EstablishmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/establishment")
@Api("Pet Trip Application")
public class EstablishmentController {

    private final EstablishmentService establishmentService;
    private final CityService cityService;


    public EstablishmentController(EstablishmentService establishmentService, CityService cityService) {
        this.establishmentService = establishmentService;
        this.cityService = cityService;
    }

    @ApiOperation("Obtains a establishment list.")
    @ApiResponses({@ApiResponse(code = 200, message = "ok"),
                    @ApiResponse(code = 400, message = "\"Oops... Sinto muito. Não foi possível encontrar resultados com " +
                    "as informações que você deseja, tente novamente.\"")})
    @GetMapping
    public Page<EstablishmentSummaryDTO> findWithFilters(EstablishmentInputDTO establishmentInputDTO,
                                                         @PageableDefault(direction = Sort.Direction.ASC, size = 6) Pageable pageRequest){

        if (establishmentInputDTO.getCityId() == null){
            establishmentInputDTO = createDefaultInputDTO(establishmentInputDTO);
        }
        return establishmentService.findByFilters(establishmentInputDTO, pageRequest);
    }

    private EstablishmentInputDTO createDefaultInputDTO(EstablishmentInputDTO establishmentInputDTO) {
        EstablishmentInputDTO defaultInputDTO = new EstablishmentInputDTO(
                cityService.getDefaultCityId(),
                establishmentInputDTO.getType(),
                establishmentInputDTO.getWeight(),
                establishmentInputDTO.getCastrated(),
                establishmentInputDTO.getGender());
        return defaultInputDTO;
    }

}
