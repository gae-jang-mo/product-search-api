package com.gaejangmo.productsearchapi.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductResponseDtos {
    @JsonProperty(value = "items")
    List<ProductResponseDto> productDtos;
}
