import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utilitarios {

    public static String lerProduto(Scanner scanner) {

        while (true) {

            String produto = scanner.nextLine().trim();

            if (produto.isEmpty()) {
                System.out.println("Campo vazio.");
                continue;
            }

            if (!produto.matches("[a-zA-ZÀ-ÿ0-9 ]+")) {
                System.out.println("Use apenas letras.");
                continue;
            }

            return produto;
        }
    }

    public static String lerString(Scanner scanner) {

        String texto = scanner.nextLine().trim();

        while (texto.isEmpty()) {

            System.out.println("Campo vazio.");
            texto = scanner.nextLine().trim();

        }

        return texto;
    }

    public static String lerFornecedor(Scanner scanner) {

        while (true) {

            String fornecedor = scanner.nextLine().trim();


            if (fornecedor.isEmpty()) {
                System.out.println("Campo vazio.");
                continue;
            }


            if (!fornecedor.matches("[a-zA-ZÀ-ÿ0-9 ]+")) {
                System.out.println("Use apenas letras.");
                continue;
            }


            return fornecedor;
        }
    }

    public static int lerInt(Scanner scanner) {

        while (true) {

            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }

            System.out.println("Valor inválido!");
            scanner.next();
        }
    }

    public static double lerDouble(Scanner scanner) {

        while (true) {

            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            }

            System.out.println("Valor inválido!");
            scanner.next();
        }
    }

    public static LocalDate lerData(Scanner scanner) {

        while (true) {

            try {

                String data = scanner.nextLine();

                return LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            } catch (Exception e) {

                System.out.println("Data inválida. Use dd/MM/yyyy");

            }
        }
    }
}