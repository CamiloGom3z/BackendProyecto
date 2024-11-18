package com.mycompany.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class PromocionTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Promocion getPromocionSample1() {
        return new Promocion()
            .id(1L)
            .nombre("nombre1")
            .descripcion("descripcion1")
            .tipoPromocion("tipoPromocion1")
            .establecimientoId(1L)
            .productoId(1L)
            .servicioId(1L);
    }

    public static Promocion getPromocionSample2() {
        return new Promocion()
            .id(2L)
            .nombre("nombre2")
            .descripcion("descripcion2")
            .tipoPromocion("tipoPromocion2")
            .establecimientoId(2L)
            .productoId(2L)
            .servicioId(2L);
    }

    public static Promocion getPromocionRandomSampleGenerator() {
        return new Promocion()
            .id(longCount.incrementAndGet())
            .nombre(UUID.randomUUID().toString())
            .descripcion(UUID.randomUUID().toString())
            .tipoPromocion(UUID.randomUUID().toString())
            .establecimientoId(longCount.incrementAndGet())
            .productoId(longCount.incrementAndGet())
            .servicioId(longCount.incrementAndGet());
    }
}
