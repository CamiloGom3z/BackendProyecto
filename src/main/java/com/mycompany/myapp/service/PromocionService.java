package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.PromocionDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Promocion}.
 */
public interface PromocionService {
    /**
     * Save a promocion.
     *
     * @param promocionDTO the entity to save.
     * @return the persisted entity.
     */
    PromocionDTO save(PromocionDTO promocionDTO);

    /**
     * Updates a promocion.
     *
     * @param promocionDTO the entity to update.
     * @return the persisted entity.
     */
    PromocionDTO update(PromocionDTO promocionDTO);

    /**
     * Partially updates a promocion.
     *
     * @param promocionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PromocionDTO> partialUpdate(PromocionDTO promocionDTO);

    /**
     * Get all the promocions.
     *
     * @return the list of entities.
     */
    List<PromocionDTO> findAll();

    /**
     * Get the "id" promocion.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PromocionDTO> findOne(Long id);

    /**
     * Delete the "id" promocion.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
