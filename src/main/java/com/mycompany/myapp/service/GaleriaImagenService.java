package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.GaleriaImagenDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.GaleriaImagen}.
 */
public interface GaleriaImagenService {
    /**
     * Save a galeriaImagen.
     *
     * @param galeriaImagenDTO the entity to save.
     * @return the persisted entity.
     */
    GaleriaImagenDTO save(GaleriaImagenDTO galeriaImagenDTO);

    /**
     * Updates a galeriaImagen.
     *
     * @param galeriaImagenDTO the entity to update.
     * @return the persisted entity.
     */
    GaleriaImagenDTO update(GaleriaImagenDTO galeriaImagenDTO);

    /**
     * Partially updates a galeriaImagen.
     *
     * @param galeriaImagenDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<GaleriaImagenDTO> partialUpdate(GaleriaImagenDTO galeriaImagenDTO);

    /**
     * Get all the galeriaImagens.
     *
     * @return the list of entities.
     */
    List<GaleriaImagenDTO> findAll();

    /**
     * Get the "id" galeriaImagen.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GaleriaImagenDTO> findOne(Long id);

    /**
     * Delete the "id" galeriaImagen.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
