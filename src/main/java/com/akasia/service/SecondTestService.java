package com.akasia.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akasia.dto.ResponseDataV1;
import com.akasia.models.repositories.Test2Repository;

@Service
public class SecondTestService {
    private static final Logger log = LogManager.getLogger(SecondTestService.class);

    @Autowired
    private ResponseDataV1 result;

    @Autowired
    private Test2Repository test2Repository;

    @Transactional
    public Object businessProcess(HashMap<String, Object> payload, HashMap<String, Object> headers) {

        if (payload.isEmpty()) {
            result.setRc("99");
            result.setData(null);
            return result;

        } else {
            ArrayList<HashMap<String, Object>> response = new ArrayList<HashMap<String, Object>>();

            String column = payload.get("column") == null ? "" : payload.get("column").toString().trim();
            int id = (int) payload.get("id");

            column = column.toUpperCase();
            log.info("COLUMN : " + column);

            try {
                switch (column) {
                    case "A":
                        test2Repository.invertA(id);
                        break;
                    case "B":
                        test2Repository.invertB(id);
                        break;
                    case "C":
                        test2Repository.invertC(id);
                        break;
                    case "D":
                        test2Repository.invertD(id);
                        break;
                    case "E":
                        test2Repository.invertE(id);
                        break;
                    default:
                        System.out.println("COLUMN INVALID!");
                        result.setRc("99");
                        result.setData(response);
                        return result;
                }
                
            } catch (Exception e) {
                log.info("PROCESS ERROR");
                log.info(ExceptionUtils.getStackTrace(e));
            }
            result.setRc("00");
            result.setData(response);
            return result;
        }
    }
}
