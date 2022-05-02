package com.project.pettrip.domain.model;

import com.project.pettrip.api.dto.EstablishmentSummaryDTO;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

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

    public Establishment(){

    }

    public Establishment(String cnpj, String name, String description, String email, String numberPhone, Address address, List<Filters> filters, String image) {
        this.cnpj = cnpj;
        this.name = name;
        this.description = description;
        this.email = email;
        this.numberPhone = numberPhone;
        this.address = address;
        this.filters = filters;
        this.image = image;
    }

    public Establishment(City city, List<Filters> filters) {
        this.address = new Address(city);
        this.filters = filters;
    }

    public Establishment(Address address, List<Filters> filters) {
        this.address = address;
        this.filters = filters;
    }

    public EstablishmentSummaryDTO toEstablishmentSummayDTO(){
        return new EstablishmentSummaryDTO(id, cnpj, name, description, email, numberPhone, image, address.toAddressDTO());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusEstablishment getStatus() {
        return status;
    }

    public Address getAddress() {
        return address;
    }

    public String getImage() {
        return image;
    }

    public List<Filters> getFilters() {
        return filters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Establishment)) return false;
        Establishment that = (Establishment) o;
        return Objects.equals(id, that.id) && Objects.equals(cnpj, that.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cnpj);
    }

    public void setStatus(StatusEstablishment status) {
        this.status = status;
    }

}
