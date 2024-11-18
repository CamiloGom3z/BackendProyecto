package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.domain.PromocionAsserts.*;
import static com.mycompany.myapp.web.rest.TestUtil.createUpdateProxyForBean;
import static com.mycompany.myapp.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Promocion;
import com.mycompany.myapp.repository.PromocionRepository;
import com.mycompany.myapp.service.dto.PromocionDTO;
import com.mycompany.myapp.service.mapper.PromocionMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link PromocionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PromocionResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PORCENTAJE_DESCUENTO = new BigDecimal(1);
    private static final BigDecimal UPDATED_PORCENTAJE_DESCUENTO = new BigDecimal(2);

    private static final Instant DEFAULT_FECHA_INICIO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FECHA_INICIO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_FECHA_FIN = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FECHA_FIN = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_TIPO_PROMOCION = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_PROMOCION = "BBBBBBBBBB";

    private static final Long DEFAULT_ESTABLECIMIENTO_ID = 1L;
    private static final Long UPDATED_ESTABLECIMIENTO_ID = 2L;

    private static final Long DEFAULT_PRODUCTO_ID = 1L;
    private static final Long UPDATED_PRODUCTO_ID = 2L;

    private static final Long DEFAULT_SERVICIO_ID = 1L;
    private static final Long UPDATED_SERVICIO_ID = 2L;

    private static final String ENTITY_API_URL = "/api/promocions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PromocionRepository promocionRepository;

    @Autowired
    private PromocionMapper promocionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPromocionMockMvc;

    private Promocion promocion;

    private Promocion insertedPromocion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Promocion createEntity() {
        return new Promocion()
            .nombre(DEFAULT_NOMBRE)
            .descripcion(DEFAULT_DESCRIPCION)
            .porcentajeDescuento(DEFAULT_PORCENTAJE_DESCUENTO)
            .fechaInicio(DEFAULT_FECHA_INICIO)
            .fechaFin(DEFAULT_FECHA_FIN)
            .tipoPromocion(DEFAULT_TIPO_PROMOCION)
            .establecimientoId(DEFAULT_ESTABLECIMIENTO_ID)
            .productoId(DEFAULT_PRODUCTO_ID)
            .servicioId(DEFAULT_SERVICIO_ID);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Promocion createUpdatedEntity() {
        return new Promocion()
            .nombre(UPDATED_NOMBRE)
            .descripcion(UPDATED_DESCRIPCION)
            .porcentajeDescuento(UPDATED_PORCENTAJE_DESCUENTO)
            .fechaInicio(UPDATED_FECHA_INICIO)
            .fechaFin(UPDATED_FECHA_FIN)
            .tipoPromocion(UPDATED_TIPO_PROMOCION)
            .establecimientoId(UPDATED_ESTABLECIMIENTO_ID)
            .productoId(UPDATED_PRODUCTO_ID)
            .servicioId(UPDATED_SERVICIO_ID);
    }

    @BeforeEach
    public void initTest() {
        promocion = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedPromocion != null) {
            promocionRepository.delete(insertedPromocion);
            insertedPromocion = null;
        }
    }

    @Test
    @Transactional
    void createPromocion() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Promocion
        PromocionDTO promocionDTO = promocionMapper.toDto(promocion);
        var returnedPromocionDTO = om.readValue(
            restPromocionMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(promocionDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            PromocionDTO.class
        );

        // Validate the Promocion in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedPromocion = promocionMapper.toEntity(returnedPromocionDTO);
        assertPromocionUpdatableFieldsEquals(returnedPromocion, getPersistedPromocion(returnedPromocion));

        insertedPromocion = returnedPromocion;
    }

    @Test
    @Transactional
    void createPromocionWithExistingId() throws Exception {
        // Create the Promocion with an existing ID
        promocion.setId(1L);
        PromocionDTO promocionDTO = promocionMapper.toDto(promocion);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPromocionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(promocionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Promocion in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPromocions() throws Exception {
        // Initialize the database
        insertedPromocion = promocionRepository.saveAndFlush(promocion);

        // Get all the promocionList
        restPromocionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(promocion.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].porcentajeDescuento").value(hasItem(sameNumber(DEFAULT_PORCENTAJE_DESCUENTO))))
            .andExpect(jsonPath("$.[*].fechaInicio").value(hasItem(DEFAULT_FECHA_INICIO.toString())))
            .andExpect(jsonPath("$.[*].fechaFin").value(hasItem(DEFAULT_FECHA_FIN.toString())))
            .andExpect(jsonPath("$.[*].tipoPromocion").value(hasItem(DEFAULT_TIPO_PROMOCION)))
            .andExpect(jsonPath("$.[*].establecimientoId").value(hasItem(DEFAULT_ESTABLECIMIENTO_ID.intValue())))
            .andExpect(jsonPath("$.[*].productoId").value(hasItem(DEFAULT_PRODUCTO_ID.intValue())))
            .andExpect(jsonPath("$.[*].servicioId").value(hasItem(DEFAULT_SERVICIO_ID.intValue())));
    }

    @Test
    @Transactional
    void getPromocion() throws Exception {
        // Initialize the database
        insertedPromocion = promocionRepository.saveAndFlush(promocion);

        // Get the promocion
        restPromocionMockMvc
            .perform(get(ENTITY_API_URL_ID, promocion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(promocion.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION))
            .andExpect(jsonPath("$.porcentajeDescuento").value(sameNumber(DEFAULT_PORCENTAJE_DESCUENTO)))
            .andExpect(jsonPath("$.fechaInicio").value(DEFAULT_FECHA_INICIO.toString()))
            .andExpect(jsonPath("$.fechaFin").value(DEFAULT_FECHA_FIN.toString()))
            .andExpect(jsonPath("$.tipoPromocion").value(DEFAULT_TIPO_PROMOCION))
            .andExpect(jsonPath("$.establecimientoId").value(DEFAULT_ESTABLECIMIENTO_ID.intValue()))
            .andExpect(jsonPath("$.productoId").value(DEFAULT_PRODUCTO_ID.intValue()))
            .andExpect(jsonPath("$.servicioId").value(DEFAULT_SERVICIO_ID.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingPromocion() throws Exception {
        // Get the promocion
        restPromocionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPromocion() throws Exception {
        // Initialize the database
        insertedPromocion = promocionRepository.saveAndFlush(promocion);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the promocion
        Promocion updatedPromocion = promocionRepository.findById(promocion.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedPromocion are not directly saved in db
        em.detach(updatedPromocion);
        updatedPromocion
            .nombre(UPDATED_NOMBRE)
            .descripcion(UPDATED_DESCRIPCION)
            .porcentajeDescuento(UPDATED_PORCENTAJE_DESCUENTO)
            .fechaInicio(UPDATED_FECHA_INICIO)
            .fechaFin(UPDATED_FECHA_FIN)
            .tipoPromocion(UPDATED_TIPO_PROMOCION)
            .establecimientoId(UPDATED_ESTABLECIMIENTO_ID)
            .productoId(UPDATED_PRODUCTO_ID)
            .servicioId(UPDATED_SERVICIO_ID);
        PromocionDTO promocionDTO = promocionMapper.toDto(updatedPromocion);

        restPromocionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, promocionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(promocionDTO))
            )
            .andExpect(status().isOk());

        // Validate the Promocion in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedPromocionToMatchAllProperties(updatedPromocion);
    }

    @Test
    @Transactional
    void putNonExistingPromocion() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        promocion.setId(longCount.incrementAndGet());

        // Create the Promocion
        PromocionDTO promocionDTO = promocionMapper.toDto(promocion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPromocionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, promocionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(promocionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Promocion in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPromocion() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        promocion.setId(longCount.incrementAndGet());

        // Create the Promocion
        PromocionDTO promocionDTO = promocionMapper.toDto(promocion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPromocionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(promocionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Promocion in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPromocion() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        promocion.setId(longCount.incrementAndGet());

        // Create the Promocion
        PromocionDTO promocionDTO = promocionMapper.toDto(promocion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPromocionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(promocionDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Promocion in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePromocionWithPatch() throws Exception {
        // Initialize the database
        insertedPromocion = promocionRepository.saveAndFlush(promocion);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the promocion using partial update
        Promocion partialUpdatedPromocion = new Promocion();
        partialUpdatedPromocion.setId(promocion.getId());

        partialUpdatedPromocion
            .descripcion(UPDATED_DESCRIPCION)
            .tipoPromocion(UPDATED_TIPO_PROMOCION)
            .establecimientoId(UPDATED_ESTABLECIMIENTO_ID);

        restPromocionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPromocion.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPromocion))
            )
            .andExpect(status().isOk());

        // Validate the Promocion in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPromocionUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedPromocion, promocion),
            getPersistedPromocion(promocion)
        );
    }

    @Test
    @Transactional
    void fullUpdatePromocionWithPatch() throws Exception {
        // Initialize the database
        insertedPromocion = promocionRepository.saveAndFlush(promocion);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the promocion using partial update
        Promocion partialUpdatedPromocion = new Promocion();
        partialUpdatedPromocion.setId(promocion.getId());

        partialUpdatedPromocion
            .nombre(UPDATED_NOMBRE)
            .descripcion(UPDATED_DESCRIPCION)
            .porcentajeDescuento(UPDATED_PORCENTAJE_DESCUENTO)
            .fechaInicio(UPDATED_FECHA_INICIO)
            .fechaFin(UPDATED_FECHA_FIN)
            .tipoPromocion(UPDATED_TIPO_PROMOCION)
            .establecimientoId(UPDATED_ESTABLECIMIENTO_ID)
            .productoId(UPDATED_PRODUCTO_ID)
            .servicioId(UPDATED_SERVICIO_ID);

        restPromocionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPromocion.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPromocion))
            )
            .andExpect(status().isOk());

        // Validate the Promocion in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPromocionUpdatableFieldsEquals(partialUpdatedPromocion, getPersistedPromocion(partialUpdatedPromocion));
    }

    @Test
    @Transactional
    void patchNonExistingPromocion() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        promocion.setId(longCount.incrementAndGet());

        // Create the Promocion
        PromocionDTO promocionDTO = promocionMapper.toDto(promocion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPromocionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, promocionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(promocionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Promocion in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPromocion() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        promocion.setId(longCount.incrementAndGet());

        // Create the Promocion
        PromocionDTO promocionDTO = promocionMapper.toDto(promocion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPromocionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(promocionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Promocion in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPromocion() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        promocion.setId(longCount.incrementAndGet());

        // Create the Promocion
        PromocionDTO promocionDTO = promocionMapper.toDto(promocion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPromocionMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(promocionDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Promocion in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePromocion() throws Exception {
        // Initialize the database
        insertedPromocion = promocionRepository.saveAndFlush(promocion);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the promocion
        restPromocionMockMvc
            .perform(delete(ENTITY_API_URL_ID, promocion.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return promocionRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected Promocion getPersistedPromocion(Promocion promocion) {
        return promocionRepository.findById(promocion.getId()).orElseThrow();
    }

    protected void assertPersistedPromocionToMatchAllProperties(Promocion expectedPromocion) {
        assertPromocionAllPropertiesEquals(expectedPromocion, getPersistedPromocion(expectedPromocion));
    }

    protected void assertPersistedPromocionToMatchUpdatableProperties(Promocion expectedPromocion) {
        assertPromocionAllUpdatablePropertiesEquals(expectedPromocion, getPersistedPromocion(expectedPromocion));
    }
}
