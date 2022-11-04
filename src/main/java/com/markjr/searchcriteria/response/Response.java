package com.markjr.searchcriteria.response;

import lombok.Data;

@Data
public class Response {

    private Integer code;

    private String message;

    private String status;

    private Object result;
}
