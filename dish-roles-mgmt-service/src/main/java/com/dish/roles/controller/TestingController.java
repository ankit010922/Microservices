package com.dish.roles.controller;

import com.dish.roles.entity.LoadingEntity;
import com.dish.roles.respository.LoadingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestingController {

   @Value("${service.property.name}")
   private String appnameproperties;

    @GetMapping("")
    public ResponseEntity<String>
                getEagerLoadingEntity(){
        return new ResponseEntity<>
                (appnameproperties, HttpStatus.OK);
    }
}