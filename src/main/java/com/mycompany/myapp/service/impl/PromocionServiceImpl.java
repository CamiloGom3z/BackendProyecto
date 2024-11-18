package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Promocion;
import com.mycompany.myapp.repository.PromocionRepository;
import com.mycompany.myapp.service.PromocionService;
import com.mycompany.myapp.service.dto.PromocionDTO;
import com.mycompany.myapp.service.mapper.PromocionMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.mycompany.myapp.domain.Promocion}.
 */
@Service
@Transactional
public class PromocionServiceImpl implements PromocionService {

    private static final Logger LOG = LoggerFactory.getLogger(PromocionServiceImpl.class);

    private final PromocionRepository promocionRepository;

    private final PromocionMapper promocionMapper;

    public PromocionServiceImpl(PromocionRepository promocionRepository, PromocionMapper promocionMapper) {
        this.promocionRepository = promocionRepository;
        this.promocionMapper = promocionMapper;
    }

    @Override
    public PromocionDTO save(PromocionDTO promocionDTO) {
        LOG.debug("Request to save Promocion : {}", promocionDTO);
        Promocion promocion = promocionMapper.toEntity(promocionDTO);
        promocion = promocionRepository.save(promocion);
        return promocionMapper.toDto(promocion);
    }

    @Override
    public PromocionDTO update(PromocionDTO promocionDTO) {
        LOG.debug("Request to update Promocion : {}", promocionDTO);
        Promocion promocion = promocionMapper.toEntity(promocionDTO);
        promocion = promocionRepository.save(promocion);
        return promocionMapper.toDto(promocion);
    }

    @Override
    public Optional<PromocionDTO> partialUpdate(PromocionDTO promocionDTO) {
        LOG.debug("Request to partially update Promocion : {}", promocionDTO);

        return promocionRepository
            .findById(promocionDTO.getId())
            .map(existingPromocion -> {
                promocionMapper.partialUpdate(existingPromocion, promocionDTO);

                return existingPromocion;
            })
            .map(promocionRepository::save)
            .map(promocionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PromocionDTO> findAll() {
        LOG.debug("Request to get all Promocions");
        return promocionRepository.findAll().stream().map(promocionMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PromocionDTO> findOne(Long id) {
        LOG.debug("Request to get Promocion : {}", id);
        return promocionRepository.findById(id).map(promocionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Promocion : {}", id);
        promocionRepository.deleteById(id);
    }
}
