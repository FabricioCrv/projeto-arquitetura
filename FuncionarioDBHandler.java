import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class FuncionarioDBHandler{
        public static FuncionarioDBHandler instance = new FuncionarioDBHandler();
        private HashMap<Integer, Funcionario> funcionarios;

        public static FuncionarioDBHandler getInstance(){
            if(instance==null){
                instance = new FuncionarioDBHandler();
            }
            return instance;
        }
        protected static String camainhoArquivo = "./funcionarios.csv";

        public FuncionarioDBHandler() {
        }

        public static void addFuncionario(Funcionario funcionario) {
            try {
                boolean arquivoExiste = (new File(camainhoArquivo)).exists();
                FileWriter fw = new FileWriter(camainhoArquivo, StandardCharsets.ISO_8859_1, true);
                if (!arquivoExiste) {
                    fw.write("Id;Nome;Cargo\n");
                }

                String var10001 = funcionario.getNome();
                fw.write(funcionario.getId() + ";" + var10001 + ";" + funcionario.getCod_cargo() +"\n");
                fw.flush();
                fw.close();
            } catch (Exception var3) {
                Exception e = var3;
                throw new RuntimeException(e);
            }
        }

        public static ArrayList<Funcionario> listarFuncionarios() {
            ArrayList<Funcionario> listaFuncionarios = new ArrayList();

            try {
                BufferedReader leitor = new BufferedReader(new FileReader(camainhoArquivo));
                boolean primeiraLinha = true;

                String linha;
                while((linha = leitor.readLine()) != null) {
                    if (primeiraLinha) {
                        primeiraLinha = false;
                    } else {
                        String[] partes = linha.split(";");
                        int id = Integer.parseInt(partes[0]);
                        String nome = partes[1];
                        int cargo = Integer.parseInt(partes[2]);

                        Funcionario funcionario = new Funcionario(id, nome, cargo) {
                        };
                        listaFuncionarios.add(funcionario);
                        PrintStream var10000 = System.out;
                        String var10001 = funcionario.getNome();
                        var10000.println("Id: "+ funcionario.getId() + "Nome: " + var10001 + ", Cargo: " + funcionario.getCod_cargo());
                    }
                }

                leitor.close();
                return listaFuncionarios;
            } catch (Exception var13) {
                Exception e = var13;
                throw new RuntimeException(e);
            }
        }

        public static void removerFuncionarioPorId(int idParaRemover) {
            Exception e;
            try {
                File arquivo = new File(camainhoArquivo);
                if (!arquivo.exists()) {
                    System.out.println("Arquivo não encontrado.");
                    return;
                }
            } catch (Exception var10) {
                e = var10;
                throw new RuntimeException("Erro ao remover vendedor: " + e.getMessage(), e);
            }

            try {
                ArrayList<String> linhasAtualizadas = new ArrayList();
                BufferedReader leitor = new BufferedReader(new FileReader(camainhoArquivo));
                boolean primeiraLinha = true;

                String linha;
                while((linha = leitor.readLine()) != null) {
                    if (primeiraLinha) {
                        linhasAtualizadas.add(linha);
                        primeiraLinha = false;
                    } else {
                        String[] partes = linha.split(";");
                        int id = Integer.parseInt(partes[0]);
                        if (id == idParaRemover) {
                            linhasAtualizadas.add(linha);
                        }
                    }
                }

                leitor.close();
                BufferedWriter escritor = new BufferedWriter(new FileWriter(camainhoArquivo, StandardCharsets.ISO_8859_1, false));
                Iterator var13 = linhasAtualizadas.iterator();

                while(var13.hasNext()) {
                    String linhaAtualizada = (String)var13.next();
                    escritor.write(linhaAtualizada);
                    escritor.newLine();
                }

                escritor.close();
            } catch (Exception var9) {
                e = var9;
                throw new RuntimeException("Erro ao remover vendedor: " + e.getMessage(), e);
            }
        }
    }

