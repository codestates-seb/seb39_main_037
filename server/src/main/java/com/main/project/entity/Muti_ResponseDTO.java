package com.main.project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;


@AllArgsConstructor
@Getter
public class Muti_ResponseDTO<T> {

    private List<T> data;


}
