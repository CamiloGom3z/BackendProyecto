package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Promocion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Promocion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PromocionRepository extends JpaRepository<Promocion, Long> {}
