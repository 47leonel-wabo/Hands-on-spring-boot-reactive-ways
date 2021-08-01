package com.aiwa.reactivedemo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
public class Response {

    private Date date = new Date();
    private Integer outPut;

    public Response(Integer outPut) {
        this.outPut = outPut;
    }
}
