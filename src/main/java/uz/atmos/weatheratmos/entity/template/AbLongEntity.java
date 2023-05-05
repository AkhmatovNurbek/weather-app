package uz.atmos.weatheratmos.entity.template;



import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;


@Data
@MappedSuperclass
public abstract class AbLongEntity extends AbsMainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}