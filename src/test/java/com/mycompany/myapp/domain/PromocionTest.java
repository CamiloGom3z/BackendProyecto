package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.PromocionTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PromocionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Promocion.class);
        Promocion promocion1 = getPromocionSample1();
        Promocion promocion2 = new Promocion();
        assertThat(promocion1).isNotEqualTo(promocion2);

        promocion2.setId(promocion1.getId());
        assertThat(promocion1).isEqualTo(promocion2);

        promocion2 = getPromocionSample2();
        assertThat(promocion1).isNotEqualTo(promocion2);
    }
}
