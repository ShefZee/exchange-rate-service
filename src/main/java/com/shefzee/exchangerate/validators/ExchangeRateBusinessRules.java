package com.shefzee.exchangerate.validators;

import com.shefzee.exchangerate.error.BusinessException;
import com.shefzee.exchangerate.helper.ResourceBundleUtil;
import com.shefzee.exchangerate.request.ExchangeRateRequest;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.shefzee.exchangerate.constants.ValidationMessageConstants.*;

@Component
@AllArgsConstructor
public class ExchangeRateBusinessRules {

    private ResourceBundleUtil resourceBundleUtil;

    public void validate(ExchangeRateRequest request){
        List<String> errorMessages = new ArrayList<>();

        //validate each fields
        checkSourceCurrency(request,errorMessages);
        checkTargetCurrency(request,errorMessages);
        checkValue(request,errorMessages);

        if(!CollectionUtils.isEmpty(errorMessages)){
            BusinessException businessException = BusinessException.builder()
                    .errorMessages(errorMessages)
                    .httpStatus(HttpStatus.BAD_REQUEST.value())
                    .build();
            throw businessException;
        }
    }

    private void checkSourceCurrency(ExchangeRateRequest request, List<String> errorMessages){
        if(StringUtils.isEmpty(request.getSourceCurrency())){
            String errorMessage = resourceBundleUtil.getMessageFromResourceBundle(SOURCE_CURRENCY_MANDATORY);
            errorMessages.add(errorMessage);
        }else if(request.getSourceCurrency().length() != 3){
            String errorMessage = resourceBundleUtil.getMessageFromResourceBundle(SOURCE_CURRENCY_CODE_LENGTH);
            errorMessages.add(errorMessage);
        }
    }

    private void checkTargetCurrency(ExchangeRateRequest request, List<String> errorMessages){
        if(StringUtils.isEmpty(request.getTargetCurrency())){
            String errorMessage = resourceBundleUtil.getMessageFromResourceBundle(TARGET_CURRENCY_MANDATORY);
            errorMessages.add(errorMessage);
        } else if(request.getSourceCurrency().length() != 3){
            String errorMessage = resourceBundleUtil.getMessageFromResourceBundle(TARGET_CURRENCY_CODE_LENGTH);
            errorMessages.add(errorMessage);
        }
    }

    private void checkValue(ExchangeRateRequest request,List<String> errorMessages ){
        if(StringUtils.isEmpty(request.getValue())){
            String errorMessage = resourceBundleUtil.getMessageFromResourceBundle(EXCHANGE_RATE_MANDATORY);
            errorMessages.add(errorMessage);
        }else {
            try {
                Double.parseDouble(request.getValue());
            } catch (NumberFormatException e) {
                String errorMessage = resourceBundleUtil.getMessageFromResourceBundle(INVALID_RATE);
                errorMessages.add(errorMessage);
            }

        }
    }
}
