package io.aanagtalon.backend.repo;

import io.aanagtalon.backend.entity.WishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishRepo extends JpaRepository<WishEntity, Long> {
    Optional<WishEntity> findById(Long id);
    Optional<WishEntity> findByWishlists_Id(Long id);
}
