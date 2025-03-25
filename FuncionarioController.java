public class FuncionarioController {
    public FuncionarioDBHandler dbHandler;

    public FuncionarioController(){
        dbHandler = FuncionarioDBHandler.getInstance();
    }

    public void criarFuncionario(Funcionario funcionario){
        dbHandler.addFuncionario(funcionario);
    }
}
