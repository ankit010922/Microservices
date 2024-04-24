package com.dish.test.mgmt.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dish.test.mgmt.constants.TestManagementURLsConstant;

@CrossOrigin
@RestController
@RequestMapping(TestManagementURLsConstant.API_V1 + TestManagementURLsConstant.DEVICES)
public class DevicesController {}