package br.gel.casa.consultarfb.cnpj.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * Valida se um CNPJ é nulo ou possui formato inválido.
 */
@Documented
@Constraint(validatedBy = CnpjValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface CnpjValido {

    String message() default "CNPJ informado é inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
