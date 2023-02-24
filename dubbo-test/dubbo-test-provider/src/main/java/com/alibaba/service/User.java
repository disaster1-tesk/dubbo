package com.alibaba.service;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 7158911668568000392L;

    String name;
    int age;
}
