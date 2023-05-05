package uz.atmos.weatheratmos.entity.template;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbsMainEntity {
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;
    @JoinColumn(updatable = false)

//    @ManyToOne(fetch = FetchType.LAZY)
    private Long createdBy;
//    @ManyToOne(fetch = FetchType.LAZY)
    private Long updatedBy;

}
