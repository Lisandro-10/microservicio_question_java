package com.lisandro.microservicioQuestions.utils.gson;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
public @interface SkipSerialization {

}