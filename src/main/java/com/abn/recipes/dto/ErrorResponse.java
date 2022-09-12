package com.abn.recipes.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse {
    private String errorCode;
    private String errorDescription;
}
