package Controll;


public class ValidationController {
    public static boolean isValidUsername(String username) {
        // Implement your username validation logic here
        // For example, check if it starts with 'C' and has 3 digits
        return username.matches("C\\d{2}");
    }

    public static boolean isValidNumber(String number) {
        // Implement your number validation logic here
        // For example, check if it contains 12 digits
        return number.matches("\\d{12}");
    }

    public static boolean isValidFullName(String fullName) {
    // Implement your full name validation logic here
    // For example, check if it contains only letters, spaces, and has a maximum length of 30
    return fullName.matches("[a-zA-Z\\s]+") && fullName.length() <= 30;
}


    public static boolean isValidCountry(String country) {
    
    String regex = "^[^0-9]{1,20}$";
    return country.matches(regex);
}


    public static boolean isValidPhone(String phone) {
        // Implement your phone validation logic here
        // For example, check if it starts with '07' and has 10 digits
        return phone.matches("07\\d{8}");
    }

    public static boolean isValidEmail(String email) {
        // Implement your email validation logic here
        // For example, check if it matches a basic email pattern
        return email.matches("\\S+@\\S+\\.\\S+");
    }
    
    public static boolean isValidNumberOfPersons(String numberOfPersons) {
    if (numberOfPersons == null) {
        return false;
    }
    
    try {
        int num = Integer.parseInt(numberOfPersons);
        return num >= 1 && num <= 20;
    } catch (NumberFormatException e) {
        return false;
    }
}


}
