package com.carsmoviesinventory.app.Entities;

import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class GaseosasEntities {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("GaseosaName")
    @NotBlank(message = "Gaseosa name is required")
    @Size(min = 3, max = 100, message = "Gaseosa name must be between 3 and 100 characters")
    private String GaseosaName;

    @JsonProperty("GaseosaSabor")
    @NotBlank(message = "Sabor is required")
    @Size(min = 3, max = 100, message = "Gaseosa name must be between 3 and 100 characters")
    private String GaseosaSabor;

    @JsonProperty("Empresa")
    @NotNull(message = "Empresa is required")
    @Size(min = 3, max = 100, message= "Gaseosa name must be between 3 and 100 characters")
    private String Empresa;

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
                ", GaseosaName='" + GaseosaName + '\'' +
                ", GaseosaSabor='" + GaseosaSabor + '\'' +
                ", Empresa=" + Empresa +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public String getGaseosaName() {
        return GaseosaName;
    }

    public void setGaseosaName(String GaseosaName) {
        this.GaseosaName = GaseosaName;
    }

    public String getGaseosaSabor() {
        return GaseosaSabor;
    }

    public void setGaseosaSabor(String GaseosaSabor) {
        this.GaseosaSabor = GaseosaSabor;
    }

    public String getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(String Empresa) {
        this.Empresa = Empresa;
    }

}
