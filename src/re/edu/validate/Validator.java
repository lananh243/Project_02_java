package re.edu.validate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Validator {
    private static final String PHONE_VIETTEL_PREFIXES = "086|096|097|098|039|038|037|036|035|034|033|032";
    private static final String PHONE_VINAPHONE_PREFIXES = "091|094|088|083|084|085|081|082";
    private static final String PHONE_MOBIFONE_PREFIXES = "070|079|077|076|078|089|090|093";

    public static boolean isValidPhoneNumberVN(String inputValuePhone) {
        String phoneRegex = "(" + PHONE_VIETTEL_PREFIXES + "|" + PHONE_VINAPHONE_PREFIXES + "|" + PHONE_MOBIFONE_PREFIXES + ")\\d{7}";
        return inputValuePhone != null && Pattern.matches(phoneRegex, inputValuePhone.trim());
    }

    public static boolean isValidEmail(String inputValueEmail) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return inputValueEmail != null && Pattern.matches(emailRegex, inputValueEmail.trim());
    }

    public static Integer validateInputInteger(Scanner scanner, String message) {
        System.out.println(message);
        while (true) {
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.err.println("Dữ liệu không được để trống. Vui lòng nhập lại.");
                continue;
            }

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.err.println("Dữ liệu nhập vào không phải là kiểu số nguyên, vui lòng nhập lại");
            }

        }
    }

    public static boolean validateInputBoolean(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);
            try {
                String inputString = scanner.nextLine();
                if (inputString.equalsIgnoreCase("true") || inputString.equalsIgnoreCase("false")) {
                    return Boolean.parseBoolean(inputString);
                }

                throw new IllegalArgumentException("Dữ liệu nhập vào không phải true | false , vui lòng nhập lại");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static <T extends Enum<T>> T validateEnumInput(Scanner scanner, String message, Class<T> enumClass) {
        System.out.println(message);
        while (true) {
            String input = scanner.nextLine().trim();
            for (T value : enumClass.getEnumConstants()) {
                if (value.name().equalsIgnoreCase(input)) {
                    return value;
                }
            }
            System.err.println("Giá trị nhập vào không hợp lệ, vui lòng nhập lại");
        }
    }

    public static LocalDate validateInputDate(Scanner scanner, String message) {
        System.out.println(message);
        do {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try {
                return LocalDate.parse(scanner.nextLine(), dtf);
            } catch (DateTimeParseException e) {
                System.err.println("Không đúng định dạng ngày tháng năm, vui lòng nhập lại");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (true);
    }

    public static LocalDate validateInputMonthYear(Scanner scanner, String message) {
        System.out.println(message);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/yyyy");
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return LocalDate.parse("01/" + input, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeParseException e) {
                System.err.println("Không đúng định dạng tháng năm, vui lòng nhập lại (MM/yyyy):");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    public static String validateInputString(Scanner scanner, String message) {
        System.out.println(message);
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.err.println("Dữ liệu không được để trống, vui lòng nhập lại");
                continue;
            }
            return input;
        }
    }
}
