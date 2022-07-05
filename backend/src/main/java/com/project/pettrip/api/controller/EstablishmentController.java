package com.project.pettrip.api.controller;

import com.project.pettrip.api.dto.EstablishmentCompleteDTO;
import com.project.pettrip.api.dto.EstablishmentInputDTO;
import com.project.pettrip.api.dto.EstablishmentSummaryDTO;
import com.project.pettrip.domain.model.Establishment;
import com.project.pettrip.domain.service.ICityService;
import com.project.pettrip.domain.service.IEstablishmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;


/**
 * Classe controller responsável pelo gerenciamento dos endpoints de estabelecimentos.
 */
@RestController
@RequestMapping("/establishment")
@Api("Pet Trip Application")
public class EstablishmentController {

    private final IEstablishmentService establishmentService;
    private final ICityService cityService;


    /**
     * Construtor de instanciação de EstablishmentController.
     *
     * @param establishmentService referência à interface IEstablishmentService.
     * @param cityService          referência à interface ICityService.
     */
    public EstablishmentController(IEstablishmentService establishmentService, ICityService cityService) {
        this.establishmentService = establishmentService;
        this.cityService = cityService;
    }

    /**
     * Endpoint para obter uma lista de estabelecimentos através dos filtros informados.
     *
     * @param establishmentInputDTO EstablishmentInputDTO
     * @param pageRequest           Pageable
     * @return uma lista de estabelecimentos.
     */
    @ApiOperation("Obtains a establishment list.")
    @ApiResponses({@ApiResponse(code = 200, message = "Ok"),
                   @ApiResponse(code = 400, message = "\"Oops... Sinto muito. Não foi possível encontrar hotéis com as " +
                                                        "buscas que você deseja, tente novamente.\"")})
    @GetMapping
    public Page<EstablishmentSummaryDTO> findWithFilters(EstablishmentInputDTO establishmentInputDTO,
                                                         @PageableDefault(direction = Sort.Direction.ASC, size = 6) Pageable pageRequest){

        if (establishmentInputDTO.getCityId() == null){
            establishmentInputDTO.setCityId(cityService.getDefaultCityId());
        }
        return establishmentService.findByFilters(establishmentInputDTO, pageRequest);
    }

    /**
     * Endpoint responsável pela criação de um estabelecimento.
     *
     * @param establishmentCompleteInputDTO EstablishmentCompleteDTO.
     * @return um EstablishmentCompleteDTO.
     */
    @ApiOperation("Create a establishment.")
    @ApiResponses({@ApiResponse(code = 201, message = "Created"),
                   @ApiResponse(code = 400, message = "\"Já existe um estabelecimento cadastrado com esse CNPJ")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstablishmentCompleteDTO createEstablishment(@Valid @RequestBody EstablishmentCompleteDTO establishmentCompleteInputDTO){
        return establishmentService.create(establishmentCompleteInputDTO);
    }

    /**
     * Endpoint responsável pela inativação do status de um estabelecimento.
     *
     * @param establishmentId o id de um estabelecimento.
     */
    @ApiOperation("Alter a establishment status to inactivate.")
    @ApiResponses({@ApiResponse(code = 204, message = "No content"),
                   @ApiResponse(code = 400, message = "\"Estabelecimento não pode ser inativado!"),
                   @ApiResponse(code = 404, message = "\"Estabelecimento não encontrado!")})
    @PutMapping("{establishmentId}/inactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inactivate(@PathVariable Long establishmentId){
        establishmentService.inactivate(establishmentId);
    }

    /**
     * Endpoint responsável pela ativação do status de um estabelecimento.
     *
     * @param establishmentId o id de um estabelecimento.
     */
    @ApiOperation("Alter a establishment status to activate.")
    @ApiResponses({@ApiResponse(code = 204, message = "No content"),
                   @ApiResponse(code = 400, message = "\"Estabelecimento não pode ser ativado!"),
                   @ApiResponse(code = 404, message = "\"Estabelecimento não encontrado!")})
    @PutMapping("{establishmentId}/activate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@PathVariable Long establishmentId){
        establishmentService.activate(establishmentId);
    }

    /**
     * Endpoint responsável por deletar um estabelecimento.
     *
     * @param establishmentId o id de um estabelecimento.
     */
    @ApiOperation("Delete a establishment")
    @ApiResponses({@ApiResponse(code = 204, message = "No content"),
                   @ApiResponse(code = 400, message = "\"O Id do estabelecimento não pode ser nulo."),
                   @ApiResponse(code = 404, message = "\"Estabelecimento não encontrado!")})
    @DeleteMapping("{establishmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long establishmentId){
        Establishment establishment = establishmentService.findEstablishementById(establishmentId);
        establishmentService.delete(establishment);
    }

}
