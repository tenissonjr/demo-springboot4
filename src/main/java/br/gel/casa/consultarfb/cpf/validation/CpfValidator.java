package br.gel.casa.consultarfb.cpf.validation;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<CpfValido, String> {

    private static final Pattern CPF_NUMERICO = Pattern.compile("\\d{11}");
    private static final Pattern CPF_FORMATADO = Pattern.compile("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    private static final int[] PESOS_PRIMEIRO_DIGITO = {10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] PESOS_SEGUNDO_DIGITO = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        if (!CPF_NUMERICO.matcher(value).matches() && !CPF_FORMATADO.matcher(value).matches()) {
            return false;
        }

        String digits = value.replaceAll("\\D", "");
        if (digits.length() != 11) {
            return false;
        }

        if (todosDigitosIguais(digits)) {
            return false;
        }

        int primeiroDigito = calcularDigito(digits.substring(0, 9), PESOS_PRIMEIRO_DIGITO);
        int segundoDigito = calcularDigito(digits.substring(0, 10), PESOS_SEGUNDO_DIGITO);

        return digits.charAt(9) == Character.forDigit(primeiroDigito, 10)
            && digits.charAt(10) == Character.forDigit(segundoDigito, 10);
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