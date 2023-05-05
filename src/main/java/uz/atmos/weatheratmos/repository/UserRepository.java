package uz.atmos.weatheratmos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import uz.atmos.weatheratmos.entity.AuthUser;
import uz.atmos.weatheratmos.entity.City;


import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<AuthUser, Long> {
    Optional<AuthUser> findByEmail(String email);

    Optional<List<City>>findAllByCities(Long id);

    boolean existsByEmail(String email);

}
