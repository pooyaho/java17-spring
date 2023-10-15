package ir.mapsa.java.java17spring.controllers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDto {
    private Date timestamp;
    private String message;
    private Integer errorCode;
}
