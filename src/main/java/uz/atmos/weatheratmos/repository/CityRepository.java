package uz.atmos.weatheratmos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import uz.atmos.weatheratmos.entity.City;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
public interface CityRepository extends JpaRepository<City , Long> {
    @Query(value = " select * from City ", nativeQuery = true)
    Optional<List<City>> getAll();
    @Query("from City where id = :id")
    Optional<City> get(Long id);

    @Query(value = " select * from City where state=1 ", nativeQuery = true)
    Optional<List<City>> showAll();
}
