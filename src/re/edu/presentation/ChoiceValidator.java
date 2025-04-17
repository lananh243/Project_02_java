package re.edu.presentation;

import re.edu.validate.Validator;

import java.util.Scanner;

public class ChoiceValidator {
    public static int validateChoice(Scanner scanner) {
        System.out.println("\u001B[34m Lựa chọn của bạn: \u001b[0m");
        while (true) {
            String choice = scanner.nextLine();
            if (Validator.isValidDataType(choice, Integer.class)) {
                return Integer.parseInt(choice);
            }
        }
    }
}
