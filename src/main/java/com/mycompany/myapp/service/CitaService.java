package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.CitaDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Cita}.
 */
public interface CitaService {
    /**
     * Save a cita.
     *
     * @param citaDTO the entity to save.
     * @return the persisted entity.
     */
    CitaDTO save(CitaDTO citaDTO);

    /**
     * Updates a cita.
     *
     * @param citaDTO the entity to update.
     * @return the persisted entity.
     */
    CitaDTO update(CitaDTO citaDTO);

    /**
     * Partially updates a cita.
     *
     * @param citaDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CitaDTO> partialUpdate(CitaDTO citaDTO);

    /**
     * Get all the citas.
     *
     * @return the list of entities.
     */
    List<CitaDTO> findAll();

    /**
     * Get the "id" cita.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CitaDTO> findOne(Long id);

    /**
     * Delete the "id" cita.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
