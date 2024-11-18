package com.mycompany.myapp.domain;

import com.mycompany.myapp.domain.enumeration.EstadoCitaEnum;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Cita.
 */
@Entity
@Table(name = "cita")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "fecha_cita")
    private Instant fechaCita;

    @Column(name = "fecha_fin_cita")
    private Instant fechaFinCita;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoCitaEnum estado;

    @Column(name = "persona_id")
    private Long personaId;

    @Column(name = "establecimiento_id")
    private Long establecimientoId;

    @Column(name = "servicio_id")
    private Long servicioId;

    @Column(name = "empleado_id")
    private  Long empleadoId;

    // jhipster-needle-entity-add-field - JHipster will add fields here


    public Long getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Long empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Long getId() {
        return this.id;
    }

    public Cita id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getFechaCita() {
        return this.fechaCita;
    }

    public Cita fechaCita(Instant fechaCita) {
        this.setFechaCita(fechaCita);
        return this;
    }

    public void setFechaCita(Instant fechaCita) {
        this.fechaCita = fechaCita;
    }

    public Instant getFechaFinCita() {
        return this.fechaFinCita;
    }

    public Cita fechaFinCita(Instant fechaFinCita) {
        this.setFechaFinCita(fechaFinCita);
        return this;
    }

    public void setFechaFinCita(Instant fechaFinCita) {
        this.fechaFinCita = fechaFinCita;
    }

    public EstadoCitaEnum getEstado() {
        return this.estado;
    }

    public Cita estado(EstadoCitaEnum estado) {
        this.setEstado(estado);
        return this;
    }

    public void setEstado(EstadoCitaEnum estado) {
        this.estado = estado;
    }

    public Long getPersonaId() {
        return this.personaId;
    }

    public Cita personaId(Long personaId) {
        this.setPersonaId(personaId);
        return this;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }

    public Long getEstablecimientoId() {
        return this.establecimientoId;
    }

    public Cita establecimientoId(Long establecimientoId) {
        this.setEstablecimientoId(establecimientoId);
        return this;
    }

    public void setEstablecimientoId(Long establecimientoId) {
        this.establecimientoId = establecimientoId;
    }

    public Long getServicioId() {
        return this.servicioId;
    }

    public Cita servicioId(Long servicioId) {
        this.setServicioId(servicioId);
        return this;
    }

    public void setServicioId(Long servicioId) {
        this.servicioId = servicioId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cita)) {
            return false;
        }
        return getId() != null && getId().equals(((Cita) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Cita{" +
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
