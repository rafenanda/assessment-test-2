package com.akasia.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akasia.dto.ResponseBuilder;
import com.akasia.dto.ResponseDataV1;
import com.akasia.service.SecondTestService;
import com.akasia.utils.Util;

@RestController
@RequestMapping
@PropertySource(value = { "classpath:application.properties" })
public class MainController {
    private static final Logger log = LogManager.getLogger(MainController.class);

    @Autowired
    private Util util;

    @Autowired
    private ResponseDataV1 response;

    @Autowired
    private ResponseBuilder rb;

    @Autowired
    private SecondTestService secondTest;

    @PostMapping(path = "${api.path.second.test}")
    public ResponseEntity<Object> SecondTestService(HttpServletRequest request,
            @RequestBody HashMap<String, Object> payload, @RequestHeader HashMap<String, Object> headers) {
        try {
            log.info("==================== SECOND TEST, INVERT VALUE ====================");

            util.writeMessageForLogging(payload, "REQUEST");
            util.writeMessageForLogging(headers, "HEADERS");

            ResponseDataV1 mapDataBp = (ResponseDataV1) secondTest.businessProcess(payload, headers);
            if (!mapDataBp.getRc().equals("00")) {
                response.setRc(mapDataBp.getRc());
                response.setData(mapDataBp.getData());
            } else {
                response.setRc(mapDataBp.getRc());
                response.setData(mapDataBp.getData());
            }

            rb.setResponseEntity(response, response.getRc());
            util.loggingResp(rb.responseEntity, log);
            log.info("==================== END OF FLOW ====================");
            return rb.responseEntity;
        } catch (Exception e) {
            log.info(ExceptionUtils.getStackTrace(e));
        }

        log.info("==================== END OF FLOW ====================\n");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null);
    }
}
