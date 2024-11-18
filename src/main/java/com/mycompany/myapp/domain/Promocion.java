package com.mycompany.myapp.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Promocion.
 */
@Entity
@Table(name = "promocion")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Promocion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "porcentaje_descuento", precision = 21, scale = 2)
    private BigDecimal porcentajeDescuento;

    @Column(name = "fecha_inicio")
    private Instant fechaInicio;

    @Column(name = "fecha_fin")
    private Instant fechaFin;

    @Column(name = "tipo_promocion")
    private String tipoPromocion;

    @Column(name = "establecimiento_id")
    private Long establecimientoId;

    @Column(name = "producto_id")
    private Long productoId;

    @Column(name = "servicio_id")
    private Long servicioId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Promocion id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Promocion nombre(String nombre) {
        this.setNombre(nombre);
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public Promocion descripcion(String descripcion) {
        this.setDescripcion(descripcion);
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPorcentajeDescuento() {
        return this.porcentajeDescuento;
    }

    public Promocion porcentajeDescuento(BigDecimal porcentajeDescuento) {
        this.setPorcentajeDescuento(porcentajeDescuento);
        return this;
    }

    public void setPorcentajeDescuento(BigDecimal porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public Instant getFechaInicio() {
        return this.fechaInicio;
    }

    public Promocion fechaInicio(Instant fechaInicio) {
        this.setFechaInicio(fechaInicio);
        return this;
    }

    public void setFechaInicio(Instant fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Instant getFechaFin() {
        return this.fechaFin;
    }

    public Promocion fechaFin(Instant fechaFin) {
        this.setFechaFin(fechaFin);
        return this;
    }

    public void setFechaFin(Instant fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getTipoPromocion() {
        return this.tipoPromocion;
    }

    public Promocion tipoPromocion(String tipoPromocion) {
        this.setTipoPromocion(tipoPromocion);
        return this;
    }

    public void setTipoPromocion(String tipoPromocion) {
        this.tipoPromocion = tipoPromocion;
    }

    public Long getEstablecimientoId() {
        return this.establecimientoId;
    }

    public Promocion establecimientoId(Long establecimientoId) {
        this.setEstablecimientoId(establecimientoId);
        return this;
    }

    public void setEstablecimientoId(Long establecimientoId) {
        this.establecimientoId = establecimientoId;
    }

    public Long getProductoId() {
        return this.productoId;
    }

    public Promocion productoId(Long productoId) {
        this.setProductoId(productoId);
        return this;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Long getServicioId() {
        return this.servicioId;
    }

    public Promocion servicioId(Long servicioId) {
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
        if (!(o instanceof Promocion)) {
            return false;
        }
        return getId() != null && getId().equals(((Promocion) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Promocion{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", porcentajeDescuento=" + getPorcentajeDescuento() +
            ", fechaInicio='" + getFechaInicio() + "'" +
            ", fechaFin='" + getFechaFin() + "'" +
            ", tipoPromocion='" + getTipoPromocion() + "'" +
            ", establecimientoId=" + getEstablecimientoId() +
            ", productoId=" + getProductoId() +
            ", servicioId=" + getServicioId() +
            "}";
    }
}
