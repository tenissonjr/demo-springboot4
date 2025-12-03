package br.gel.casa.consultarfb.cpf.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * Valida se um CPF é nulo ou possui formato inválido.
 */
@Documented
@Constraint(validatedBy = CpfValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface CpfValido {

    String message() default "CPF informado é inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}