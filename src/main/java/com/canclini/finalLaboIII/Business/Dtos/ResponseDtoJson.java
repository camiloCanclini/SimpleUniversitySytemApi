package com.canclini.finalLaboIII.Business.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDtoJson {
    public HttpStatus status;
    public String message;
    public Object data;
}
