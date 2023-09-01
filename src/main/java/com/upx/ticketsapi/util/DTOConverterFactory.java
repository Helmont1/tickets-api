package com.upx.ticketsapi.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.BeanUtils;

import com.upx.ticketsapi.exception.BusinessException;

public class DTOConverterFactory {

    private DTOConverterFactory() {
        throw new IllegalAccessError("utility class");
    }

    public static <D,E> E fromDTO(D dto, Class<E> entityClass){
        try {
            Constructor<E> constructor = entityClass.getDeclaredConstructor();
            E entity = constructor.newInstance();
            BeanUtils.copyProperties(dto, entity);
            return entity;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new BusinessException("Error creating entity instance");
        }
    }
}
