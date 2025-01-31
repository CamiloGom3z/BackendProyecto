package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.AssertUtils.bigDecimalCompareTo;
import static org.assertj.core.api.Assertions.assertThat;

public class PromocionAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPromocionAllPropertiesEquals(Promocion expected, Promocion actual) {
        assertPromocionAutoGeneratedPropertiesEquals(expected, actual);
        assertPromocionAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPromocionAllUpdatablePropertiesEquals(Promocion expected, Promocion actual) {
        assertPromocionUpdatableFieldsEquals(expected, actual);
        assertPromocionUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPromocionAutoGeneratedPropertiesEquals(Promocion expected, Promocion actual) {
        assertThat(expected)
            .as("Verify Promocion auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPromocionUpdatableFieldsEquals(Promocion expected, Promocion actual) {
        assertThat(expected)
            .as("Verify Promocion relevant properties")
            .satisfies(e -> assertThat(e.getNombre()).as("check nombre").isEqualTo(actual.getNombre()))
            .satisfies(e -> assertThat(e.getDescripcion()).as("check descripcion").isEqualTo(actual.getDescripcion()))
            .satisfies(e ->
                assertThat(e.getPorcentajeDescuento())
                    .as("check porcentajeDescuento")
                    .usingComparator(bigDecimalCompareTo)
                    .isEqualTo(actual.getPorcentajeDescuento())
            )
            .satisfies(e -> assertThat(e.getFechaInicio()).as("check fechaInicio").isEqualTo(actual.getFechaInicio()))
            .satisfies(e -> assertThat(e.getFechaFin()).as("check fechaFin").isEqualTo(actual.getFechaFin()))
            .satisfies(e -> assertThat(e.getTipoPromocion()).as("check tipoPromocion").isEqualTo(actual.getTipoPromocion()))
            .satisfies(e -> assertThat(e.getEstablecimientoId()).as("check establecimientoId").isEqualTo(actual.getEstablecimientoId()))
            .satisfies(e -> assertThat(e.getProductoId()).as("check productoId").isEqualTo(actual.getProductoId()))
            .satisfies(e -> assertThat(e.getServicioId()).as("check servicioId").isEqualTo(actual.getServicioId()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPromocionUpdatableRelationshipsEquals(Promocion expected, Promocion actual) {
        // empty method
    }
}
