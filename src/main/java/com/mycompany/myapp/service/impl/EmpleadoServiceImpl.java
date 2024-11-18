package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Empleado;
import com.mycompany.myapp.repository.EmpleadoRepository;
import com.mycompany.myapp.service.EmpleadoService;
import com.mycompany.myapp.service.dto.EmpleadoDTO;
import com.mycompany.myapp.service.mapper.EmpleadoMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.mycompany.myapp.domain.Empleado}.
 */
@Service
@Transactional
public class EmpleadoServiceImpl implements EmpleadoService {

    private static final Logger LOG = LoggerFactory.getLogger(EmpleadoServiceImpl.class);

    private final EmpleadoRepository empleadoRepository;

    private final EmpleadoMapper empleadoMapper;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository, EmpleadoMapper empleadoMapper) {
        this.empleadoRepository = empleadoRepository;
        this.empleadoMapper = empleadoMapper;
    }

    @Override
    public EmpleadoDTO save(EmpleadoDTO empleadoDTO) {
        LOG.debug("Request to save Empleado : {}", empleadoDTO);
        Empleado empleado = empleadoMapper.toEntity(empleadoDTO);
        empleado = empleadoRepository.save(empleado);
        return empleadoMapper.toDto(empleado);
    }

    @Override
    public EmpleadoDTO update(EmpleadoDTO empleadoDTO) {
        LOG.debug("Request to update Empleado : {}", empleadoDTO);
        Empleado empleado = empleadoMapper.toEntity(empleadoDTO);
        empleado = empleadoRepository.save(empleado);
        return empleadoMapper.toDto(empleado);
    }

    @Override
    public Optional<EmpleadoDTO> partialUpdate(EmpleadoDTO empleadoDTO) {
        LOG.debug("Request to partially update Empleado : {}", empleadoDTO);

        return empleadoRepository
            .findById(empleadoDTO.getId())
            .map(existingEmpleado -> {
                empleadoMapper.partialUpdate(existingEmpleado, empleadoDTO);

                return existingEmpleado;
            })
            .map(empleadoRepository::save)
            .map(empleadoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmpleadoDTO> findAll() {
        LOG.debug("Request to get all Empleados");
        return empleadoRepository.findAll().stream().map(empleadoMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EmpleadoDTO> findOne(Long id) {
        LOG.debug("Request to get Empleado : {}", id);
        return empleadoRepository.findById(id).map(empleadoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Empleado : {}", id);
        empleadoRepository.deleteById(id);
    }
}
