package io.aanagtalon.backend.repo;

import io.aanagtalon.backend.entity.WishlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepo extends JpaRepository<WishlistEntity, String> {
    Optional<WishlistEntity> findByOwner_Id(Long id);
}
