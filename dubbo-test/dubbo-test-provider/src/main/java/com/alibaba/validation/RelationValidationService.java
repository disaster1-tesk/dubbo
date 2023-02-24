package com.alibaba.validation;

import javax.validation.GroupSequence;

public interface RelationValidationService {
    @GroupSequence(Update.class) // 同时验证Update组规则
    @interface Save {
    }
    void save(ValidationParameter parameter);

    @interface Update {
    }
    void update(ValidationParameter parameter);
}
