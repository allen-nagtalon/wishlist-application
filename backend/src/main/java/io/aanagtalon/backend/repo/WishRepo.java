package io.aanagtalon.backend.repo;

import io.aanagtalon.backend.domain.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishRepo extends JpaRepository<Wish, String> {
    Optional<Wish> findById(String id);
}
