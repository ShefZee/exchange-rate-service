package com.shefzee.exchangerateservice.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse  {

    private List<String> errorMessages;
    private Integer httpStatus;
}
