package com.alibaba.validation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public interface ParamValidationService {
    void save(@NotNull ValidationParameter parameter); // 验证参数不为空

    void delete(@Min(1) int id); // 直接对基本类型参数验证
}
