package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.PromocionRepository;
import com.mycompany.myapp.service.PromocionService;
import com.mycompany.myapp.service.dto.PromocionDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Promocion}.
 */
@RestController
@RequestMapping("/api/promocions")
public class PromocionResource {

    private static final Logger LOG = LoggerFactory.getLogger(PromocionResource.class);

    private static final String ENTITY_NAME = "promocion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PromocionService promocionService;

    private final PromocionRepository promocionRepository;

    public PromocionResource(PromocionService promocionService, PromocionRepository promocionRepository) {
        this.promocionService = promocionService;
        this.promocionRepository = promocionRepository;
    }

    /**
     * {@code POST  /promocions} : Create a new promocion.
     *
     * @param promocionDTO the promocionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new promocionDTO, or with status {@code 400 (Bad Request)} if the promocion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<PromocionDTO> createPromocion(@RequestBody PromocionDTO promocionDTO) throws URISyntaxException {
        LOG.debug("REST request to save Promocion : {}", promocionDTO);
        if (promocionDTO.getId() != null) {
            throw new BadRequestAlertException("A new promocion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        promocionDTO = promocionService.save(promocionDTO);
        return ResponseEntity.created(new URI("/api/promocions/" + promocionDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, promocionDTO.getId().toString()))
            .body(promocionDTO);
    }

    /**
     * {@code PUT  /promocions/:id} : Updates an existing promocion.
     *
     * @param id the id of the promocionDTO to save.
     * @param promocionDTO the promocionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated promocionDTO,
     * or with status {@code 400 (Bad Request)} if the promocionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the promocionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PromocionDTO> updatePromocion(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PromocionDTO promocionDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update Promocion : {}, {}", id, promocionDTO);
        if (promocionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, promocionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!promocionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        promocionDTO = promocionService.update(promocionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, promocionDTO.getId().toString()))
            .body(promocionDTO);
    }

    /**
     * {@code PATCH  /promocions/:id} : Partial updates given fields of an existing promocion, field will ignore if it is null
     *
     * @param id the id of the promocionDTO to save.
     * @param promocionDTO the promocionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated promocionDTO,
     * or with status {@code 400 (Bad Request)} if the promocionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the promocionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the promocionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PromocionDTO> partialUpdatePromocion(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PromocionDTO promocionDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Promocion partially : {}, {}", id, promocionDTO);
        if (promocionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, promocionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!promocionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PromocionDTO> result = promocionService.partialUpdate(promocionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, promocionDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /promocions} : get all the promocions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of promocions in body.
     */
    @GetMapping("")
    public List<PromocionDTO> getAllPromocions() {
        LOG.debug("REST request to get all Promocions");
        return promocionService.findAll();
    }

    /**
     * {@code GET  /promocions/:id} : get the "id" promocion.
     *
     * @param id the id of the promocionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the promocionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PromocionDTO> getPromocion(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Promocion : {}", id);
        Optional<PromocionDTO> promocionDTO = promocionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(promocionDTO);
    }

    /**
     * {@code DELETE  /promocions/:id} : delete the "id" promocion.
     *
     * @param id the id of the promocionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePromocion(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Promocion : {}", id);
        promocionService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
