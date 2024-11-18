package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Promocion;
import com.mycompany.myapp.service.dto.PromocionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Promocion} and its DTO {@link PromocionDTO}.
 */
@Mapper(componentModel = "spring")
public interface PromocionMapper extends EntityMapper<PromocionDTO, Promocion> {}
