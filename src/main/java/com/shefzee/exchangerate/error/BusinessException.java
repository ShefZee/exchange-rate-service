package com.shefzee.exchangerate.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private List<String> errorMessages;
    private Integer httpStatus;
}
