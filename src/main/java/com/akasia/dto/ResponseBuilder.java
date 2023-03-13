package com.akasia.dto;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResponseBuilder {

  public ResponseEntity<Object> responseEntity;

  public void setResponseEntity(Object clz, String rc) {
    ResponseEntity<Object> result;
    if (rc.equalsIgnoreCase("00")) {
      result = ResponseEntity.ok().headers(setHeader()).body(clz);
    } else {
      if (clz != null) {
        result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .headers(setHeader())
            .body(clz);
      } else {
        result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .headers(setHeader())
        .body(null);
      }
    }
    this.responseEntity = result;
  }

  private HttpHeaders setHeader() {
    HttpHeaders responseHeaders = new HttpHeaders();

    responseHeaders.set("Encoding", "UTF-8");
    // responseHeaders.set("Test", "Test");

    return responseHeaders;
  }
}
