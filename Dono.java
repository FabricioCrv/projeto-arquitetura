import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class Dono {
    FuncionarioFactory funcionarioFactory = new FuncionarioFactory();
    FuncionarioController funcionarioController;
    public Dono() {
        funcionarioController = new FuncionarioController();
    }
    Scanner sc = new Scanner(System.in);

    public Funcionario cadastrarFuncionario() {
        try {
            System.out.println("Qual cargo do Funcionario (1-vendedor, 2-gerente)");
            int cargoFunc = sc.nextInt();
            sc.nextLine();

            System.out.println("Nome do Funcionario: ");
            String nome = sc.next();
            sc.nextLine();

            System.out.println("id do Funcionario");
            int id = sc.nextInt();
            sc.nextLine();

            Funcionario funcionario = funcionarioFactory.criarFuncionario(id, nome, cargoFunc);
            funcionarioController.criarFuncionario(funcionario);
            return funcionario;

        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite o tipo de dado correto.");
            sc.nextLine(); // Limpa o buffer do scanner
            return null;
        } catch (NoSuchElementException | IllegalStateException e) {
            System.out.println("Erro na leitura dos dados: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
            return null;
        }
    }
}