package com.carsmoviesinventory.app.Entities;

import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GASEOSAS_ENTITY")
public class GaseosasEntities {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("GaseosaName")
    @NotBlank(message = "Gaseosa name is required")
    @Size(min = 3, max = 100, message = "Gaseosa name must be between 3 and 100 characters")
    private String gaseosaName;

    @JsonProperty("GaseosaSabor")
    @NotBlank(message = "Sabor is required")
    @Size(min = 3, max = 100, message = "Gaseosa sabor must be between 3 and 100 characters")
    private String gaseosaSabor;

    @JsonProperty("Empresa")
    @NotBlank(message = "Empresa is required")
    @Size(min = 3, max = 100, message = "Empresa must be between 3 and 100 characters")
    private String empresa;

    @PrePersist
    public void generateUUID() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    @Override
    public String toString() {
        return "GaseosasEntities{" +
                "id=" + id +
                ", gaseosaName='" + gaseosaName + '\'' +
                ", gaseosaSabor='" + gaseosaSabor + '\'' +
                ", empresa='" + empresa + '\'' +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public String getGaseosaName() {
        return gaseosaName;
    }

    public void setGaseosaName(String gaseosaName) {
        this.gaseosaName = gaseosaName;
    }

    public String getGaseosaSabor() {
        return gaseosaSabor;
    }

    public void setGaseosaSabor(String gaseosaSabor) {
        this.gaseosaSabor = gaseosaSabor;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

}
