package com.shefzee.exchangerate.controller;

import com.shefzee.exchangerate.constants.ApiConstants;
import com.shefzee.exchangerate.request.ExchangeRateRequest;
import com.shefzee.exchangerate.response.ExchangeRateResponse;
import com.shefzee.exchangerate.service.ExchangeRateService;
import com.shefzee.exchangerate.test.MyEventPublisher;
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
    private MyEventPublisher myEventPublisher;

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

    @PostMapping("/test")
    public ResponseEntity publish(){
        myEventPublisher.publish();
        return null;
    }

}
