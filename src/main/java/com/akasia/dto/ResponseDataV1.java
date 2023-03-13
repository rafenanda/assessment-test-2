package com.akasia.dto;

import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akasia.models.entities.RcMapping;
import com.akasia.models.repositories.RcMappingRepository;

@Service
public class ResponseDataV1 {
  private static final Logger log = LoggerFactory.getLogger(ResponseDataV1.class);
  private Object data;
  private String rc;
  private String rd;
  // private boolean status;

  @Autowired
  private RcMappingRepository rcMappingRepository;

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public String getRc() {
    return rc;
  }

  public void setRc(String rc) {
    this.rc = rc;
    setRd();
  }

  // public boolean isStatus() {
  //   return status;
  // }

  // public void setStatus(boolean status) {
  //   this.status = status;
  // }

  public String getRd() {
    return rd;
  }

  private void setRd() {
    this.rd = getRespDescription(this.rc);
  }

  public String getRespDescription(String rc) {
    String result = "";
    try {
      Optional<RcMapping> rcMappingIsExist = rcMappingRepository.findById(rc);
      if (rcMappingIsExist.isPresent()) {
        result = rcMappingIsExist.get().getRc();
        result = rcMappingIsExist.get().getRd();
      } else {
        result = "General Error";
      }
      return result;
    } catch (Exception e) {
      log.info("FAILED TO GET RESPONSE CODE AND DESCRIPTION, CAUSED BY " + ExceptionUtils.getStackTrace(e));
      return null;
    }

  }
}