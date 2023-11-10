package ir.mapsa.java.java17spring.models.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDocument {
    private Object response;
    @Indexed
    private Date responseDate;
    private Boolean error;
    @Indexed(unique = true)
    private String trackId;
}
