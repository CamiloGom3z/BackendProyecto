package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.enumeration.EstadoCitaEnum;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Cita} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CitaDTO implements Serializable {

    private Long id;

    private Instant fechaCita;

    private Instant fechaFinCita;

    private EstadoCitaEnum estado;

    private Long personaId;

    private Long establecimientoId;

    private Long servicioId;

    private Long empleadoId;

    public Long getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Long empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Instant fechaCita) {
        this.fechaCita = fechaCita;
    }

    public Instant getFechaFinCita() {
        return fechaFinCita;
    }

    public void setFechaFinCita(Instant fechaFinCita) {
        this.fechaFinCita = fechaFinCita;
    }

    public EstadoCitaEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoCitaEnum estado) {
        this.estado = estado;
    }

    public Long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }

    public Long getEstablecimientoId() {
        return establecimientoId;
    }

    public void setEstablecimientoId(Long establecimientoId) {
        this.establecimientoId = establecimientoId;
    }

    public Long getServicioId() {
        return servicioId;
    }

    public void setServicioId(Long servicioId) {
        this.servicioId = servicioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CitaDTO)) {
            return false;
        }

        CitaDTO citaDTO = (CitaDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, citaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CitaDTO{" +
            "id=" + getId() +
            ", fechaCita='" + getFechaCita() + "'" +
            ", fechaFinCita='" + getFechaFinCita() + "'" +
            ", estado='" + getEstado() + "'" +
            ", personaId=" + getPersonaId() +
            ", establecimientoId=" + getEstablecimientoId() +
            ", servicioId=" + getServicioId() +
            "}";
    }
}
