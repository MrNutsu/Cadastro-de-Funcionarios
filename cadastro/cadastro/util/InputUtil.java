package util;

import java.util.Scanner;

public class InputUtil {
    private static Scanner scanner = new Scanner(System.in);

    public static String lerTexto(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public static String lerTextoObrigatorio(String prompt) {
        String texto;
        do {
            texto = lerTexto(prompt);
            if (texto.isEmpty()) {
                System.out.println("Este campo é obrigatório!");
            }
        } while (texto.isEmpty());
        return texto;
    }

    public static int lerInteiro(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido!");
            }
        }
    }

    public static int lerInteiroPositivo(String prompt) {
        int numero;
        do {
            numero = lerInteiro(prompt);
            if (numero < 0) {
                System.out.println("O número deve ser positivo!");
            }
        } while (numero < 0);
        return numero;
    }

    public static boolean lerConfirmacao(String prompt) {
        String resposta = lerTexto(prompt + " (s/n): ").toLowerCase();
        return resposta.equals("s") || resposta.equals("sim");
    }

    public static int lerOpcaoMenu(String prompt, int min, int max) {
        int opcao;
        do {
            opcao = lerInteiro(prompt);
            if (opcao < min || opcao > max) {
                System.out.printf("Opção inválida! Digite um número entre %d e %d.\n", min, max);
            }
        } while (opcao < min || opcao > max);
        return opcao;
    }
}