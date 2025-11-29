package com.example.demospringboot4.bcnpj.validation;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CnpjValidator implements ConstraintValidator<CnpjValido, String> {

    private static final Pattern CNPJ_NUMERICO = Pattern.compile("\\d{14}");
    private static final Pattern CNPJ_FORMATADO = Pattern.compile("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}");
    private static final int[] PESOS_PRIMEIRO_DIGITO = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] PESOS_SEGUNDO_DIGITO = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        if (!CNPJ_NUMERICO.matcher(value).matches() && !CNPJ_FORMATADO.matcher(value).matches()) {
            return false;
        }

        String digits = value.replaceAll("\\D", "");
        if (digits.length() != 14) {
            return false;
        }

        if (todosDigitosIguais(digits)) {
            return false;
        }

        int primeiroDigito = calcularDigito(digits.substring(0, 12), PESOS_PRIMEIRO_DIGITO);
        int segundoDigito = calcularDigito(digits.substring(0, 13), PESOS_SEGUNDO_DIGITO);

        return digits.charAt(12) == Character.forDigit(primeiroDigito, 10)
            && digits.charAt(13) == Character.forDigit(segundoDigito, 10);
    }

    private boolean todosDigitosIguais(String digits) {
        char primeiro = digits.charAt(0);
        for (int i = 1; i < digits.length(); i++) {
            if (digits.charAt(i) != primeiro) {
                return false;
            }
        }
        return true;
    }

    private int calcularDigito(String base, int[] pesos) {
        int soma = 0;
        for (int i = 0; i < pesos.length; i++) {
            int valor = Character.getNumericValue(base.charAt(i));
            soma += valor * pesos[i];
        }
        int resto = soma % 11;
        return resto < 2 ? 0 : 11 - resto;
    }
}
