package it.epicode.cardcorp.repository;

import it.epicode.cardcorp.model.Carta;
import it.epicode.cardcorp.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String username);
    boolean existsByEmail(String email);
    Optional<User> findOneByEmail(String email);
    @Query("""
      SELECT c 
        FROM User u 
        JOIN u.favoriteCards c 
       WHERE u.id = :userId
      """)
    List<Carta> findFavoritesByUserId(@Param("userId") int userId);

    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO user_favorites(user_id, carta_id) VALUES (:userId, :cartaId)",
            nativeQuery = true
    )
    void addFavorite(
            @Param("userId") int userId,
            @Param("cartaId") int cartaId
    );

    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM user_favorites WHERE user_id = :userId AND carta_id = :cartaId",
            nativeQuery = true
    )
    void removeFavorite(
            @Param("userId") int userId,
            @Param("cartaId") int cartaId
    );
}
