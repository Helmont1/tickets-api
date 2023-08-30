package com.upx.ticketsapi.util;

import org.springframework.beans.BeanUtils;

public class DTOConverterFactory {
    private DTOConverterFactory() {
        throw new IllegalAccessError("utility class");
    }

    public static <T> T fromDTO(T dto, T toConvert){
        BeanUtils.copyProperties(dto, toConvert);
        
        return toConvert;
    }
}
