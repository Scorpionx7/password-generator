import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;

public class Main {
    public static void main(String[] args) {
        // Pergunta ao usuário quantos caracteres a senha deve ter
        String lengthInput = JOptionPane.showInputDialog("Enter the length of your password: ");
        int length = Integer.parseInt(lengthInput);

        // Pergunta ao usuário se deve incluir caracteres especiais
        int includeSpecialChars = JOptionPane.showConfirmDialog(null, "Enter special characters including special characters: ");

        // Gera a senha
        String password = generatePassword(length, includeSpecialChars == JOptionPane.YES_OPTION);

        // Exibe a senha gerada
        JOptionPane.showMessageDialog(null,"The generated password is:" +  password);

        // Salva a senha em um arquivo .txt
        savePasswordToFile(password);
    }

    public static String generatePassword(int length, boolean includeSpecialChars) {
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String specialChars = "!@#$%^&*()-_=+[]{}|;:,.<>?/";

        String allowedChar = upperCaseLetters + lowerCaseLetters + numbers;
        if (includeSpecialChars) {
            allowedChar += specialChars;
        }

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        // Garantir pelo menos um de cada tipo necessário
        password.append(upperCaseLetters.charAt(random.nextInt(upperCaseLetters.length())));
        password.append(lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length())));
        password.append(numbers.charAt(random.nextInt(numbers.length())));
        if (includeSpecialChars) {
            password.append(specialChars.charAt(random.nextInt(specialChars.length())));
        }

        for (int i = password.length(); i < length; i++) {
            int index = random.nextInt(allowedChar.length());
            password.append(allowedChar.charAt(index));
        }

        return password.toString();
    }

    public static void savePasswordToFile(String password) {
        try (FileWriter writer = new FileWriter("password.txt")){
            writer.write(password);
            JOptionPane.showMessageDialog(null,"password saved in password.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving password to file" + e.getMessage());
        }
    }

}