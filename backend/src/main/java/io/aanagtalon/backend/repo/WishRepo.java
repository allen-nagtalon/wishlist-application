package io.aanagtalon.backend.repo;

import io.aanagtalon.backend.entity.WishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishRepo extends JpaRepository<WishEntity, Long> {
    Optional<WishEntity> findByWishId(String wishId);
    List<WishEntity> findByWishlists_Id(Long id);
}
