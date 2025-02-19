package com.lisandro.microservicioQuestions.utils.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Validador que valida tamaño mínimo de una cadena de caracteres en una propiedad de una clase
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MinLen {
	int value();
}
