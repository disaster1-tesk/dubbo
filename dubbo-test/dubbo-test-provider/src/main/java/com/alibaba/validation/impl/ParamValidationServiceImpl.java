package com.alibaba.validation.impl;

import com.alibaba.validation.ParamValidationService;
import com.alibaba.validation.ValidationParameter;

public class ParamValidationServiceImpl implements ParamValidationService {
    @Override
    public void save(ValidationParameter parameter) {
        System.out.println("parameter = " + parameter);
    }

    @Override
    public void delete(int id) {
        System.out.println("id = " + id);
    }
}
