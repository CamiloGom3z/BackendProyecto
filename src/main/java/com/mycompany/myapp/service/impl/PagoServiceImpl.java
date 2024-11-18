package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Pago;
import com.mycompany.myapp.repository.PagoRepository;
import com.mycompany.myapp.service.PagoService;
import com.mycompany.myapp.service.dto.PagoDTO;
import com.mycompany.myapp.service.mapper.PagoMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.mycompany.myapp.domain.Pago}.
 */
@Service
@Transactional
public class PagoServiceImpl implements PagoService {

    private static final Logger LOG = LoggerFactory.getLogger(PagoServiceImpl.class);

    private final PagoRepository pagoRepository;

    private final PagoMapper pagoMapper;

    public PagoServiceImpl(PagoRepository pagoRepository, PagoMapper pagoMapper) {
        this.pagoRepository = pagoRepository;
        this.pagoMapper = pagoMapper;
    }

    @Override
    public PagoDTO save(PagoDTO pagoDTO) {
        LOG.debug("Request to save Pago : {}", pagoDTO);
        Pago pago = pagoMapper.toEntity(pagoDTO);
        pago = pagoRepository.save(pago);
        return pagoMapper.toDto(pago);
    }

    @Override
    public PagoDTO update(PagoDTO pagoDTO) {
        LOG.debug("Request to update Pago : {}", pagoDTO);
        Pago pago = pagoMapper.toEntity(pagoDTO);
        pago = pagoRepository.save(pago);
        return pagoMapper.toDto(pago);
    }

    @Override
    public Optional<PagoDTO> partialUpdate(PagoDTO pagoDTO) {
        LOG.debug("Request to partially update Pago : {}", pagoDTO);

        return pagoRepository
            .findById(pagoDTO.getId())
            .map(existingPago -> {
                pagoMapper.partialUpdate(existingPago, pagoDTO);

                return existingPago;
            })
            .map(pagoRepository::save)
            .map(pagoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PagoDTO> findAll() {
        LOG.debug("Request to get all Pagos");
        return pagoRepository.findAll().stream().map(pagoMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PagoDTO> findOne(Long id) {
        LOG.debug("Request to get Pago : {}", id);
        return pagoRepository.findById(id).map(pagoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Pago : {}", id);
        pagoRepository.deleteById(id);
    }
}
