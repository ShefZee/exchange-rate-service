package com.shefzee.exchangerateservice.controller;

import com.shefzee.exchangerateservice.constants.ApiConstants;
import com.shefzee.exchangerateservice.request.ExchangeRateRequest;
import com.shefzee.exchangerateservice.response.ExchangeRateResponse;
import com.shefzee.exchangerateservice.service.ExchangeRateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(ApiConstants.API_ROOT + "/exchange-rate")
@CrossOrigin(origins = "*")
public class ExchangeRateController {

    private ExchangeRateService exchangeRateService;

    @PostMapping("/save")
    public ResponseEntity<ExchangeRateResponse> create(@RequestBody ExchangeRateRequest request){
        return ResponseEntity.ok(exchangeRateService.save(request));
    }


    @GetMapping("/all")
    public ResponseEntity<List<ExchangeRateResponse>> getAll( )  {
        return ResponseEntity.ok(exchangeRateService.findAll());
    }

    @PostMapping("/converter")
    public ResponseEntity<ExchangeRateResponse> getExchangeRate(@RequestBody ExchangeRateRequest request )  {
        return ResponseEntity.ok(exchangeRateService.findBySourceAndTarget(request.getSourceCurrency(),request.getTargetCurrency()));
    }

}
