package ir.mapsa.java.java17spring.models.entities;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@MappedSuperclass
@Data
public abstract class BaseEntity {
    @CreatedDate
    private Date insertDate;
    @LastModifiedDate
    private Date lastModifiedDate;
}
