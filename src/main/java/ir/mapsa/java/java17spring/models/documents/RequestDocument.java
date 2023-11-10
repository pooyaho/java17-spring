package ir.mapsa.java.java17spring.models.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestDocument {
    private String url;
    private String className;
    private String methodName;
    @Indexed
    private Date insertDate;
    @Id
    private String id;
    private Object[] input;
    @Indexed(unique = true)
    private String trackId;

}
