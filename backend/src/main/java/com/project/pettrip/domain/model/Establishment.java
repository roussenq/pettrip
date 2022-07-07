package com.project.pettrip.domain.model;

import com.project.pettrip.domain.exception.BusinessException;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Classe para objetos do tipo Establishment.
 */
@Entity
@Table(name = "tb_establishment")
public class Establishment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    @Size(max = 18)
    private String cnpj;

    @NotBlank
    private String name;

    private String description;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Column(name = "number_phone")
    private String numberPhone;

    @Enumerated(EnumType.STRING)
    private StatusEstablishment status;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Valid
    @NotNull
    @OneToOne(cascade = CascadeType.REMOVE)
    private Address address;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="tb_establishment_filters", joinColumns=
            {@JoinColumn(name="establishment_id")}, inverseJoinColumns=
            {@JoinColumn(name="filters_id")})
    private List<Filters> filters;

    private String image;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj.replaceAll("[./-]", "").trim();;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNumberPhone() {
        return numberPhone;
    }
    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }
    public StatusEstablishment getStatus() {
        return status;
    }
    public void setStatus(StatusEstablishment status) {
        this.status = status;
    }
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public List<Filters> getFilters() {
        return filters;
    }
    public void setFilters(List<Filters> filters) {
        this.filters = filters;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Método que altera o status do estabelecimento para inativo.
     * @throws BusinessException
     */
    public void inactivate() {
        if (canInactivate()){
            this.setStatus(StatusEstablishment.INACTIVE);
            this.setUpdatedAt(OffsetDateTime.now());
        }else{
            throw new BusinessException("Estabelecimento não pode ser inativado!");
        }
    }

    /**
     * Método que verifica se um estabelecimento pode ter o status inativado.
     * @return 'true' se o status for ativo.
     */
    private boolean canInactivate() {
        return StatusEstablishment.ACTIVE.equals(getStatus());
    }

    /**
     * Método que altera o status do estabelecimento para ativo.
     * @throws BusinessException
     */
    public void activate() {
        if (canActivate()){
            this.setStatus(StatusEstablishment.ACTIVE);
            this.setUpdatedAt(OffsetDateTime.now());
        }else{
            throw new BusinessException("Estabelecimento não pode ser ativado!");
        }
    }

    /**
     * Método que verifica se um estabelecimento pode ter o status ativado.
     * @return 'true' se o status for inativo.
     */
    private boolean canActivate() {
        return StatusEstablishment.INACTIVE.equals(getStatus());
    }

}
