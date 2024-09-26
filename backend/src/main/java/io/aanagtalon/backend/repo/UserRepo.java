package io.aanagtalon.backend.repo;

import io.aanagtalon.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmailIgnoreCase(String email);

    @Query("SELECT u FROM UserEntity u LEFT JOIN u.following f")
    List<UserEntity> getAllUsersJoinedWithFollowStatus(Long id);
}
