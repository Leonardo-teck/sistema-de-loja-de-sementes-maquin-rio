import java.io.*;
import java.util.ArrayList;

public class ProdutoRepository {

        private ArrayList<Produto> produtos;
        private final String ARQUIVO = "produto.dat";

        public ProdutoRepository(){
                produtos = new ArrayList<>();
                carregar();
            }

            private void salvarArquivo(){

            try(ObjectOutputStream out =
                        new ObjectOutputStream(
                                new FileOutputStream(ARQUIVO))){

                out.writeObject(produtos);

            }catch(IOException e){

                System.out.println("Erro ao salvar arquivo: "
                        + e.getMessage());
            }
        }

        public void salvar(Produto produto){

        produtos.add(produto);

        salvarArquivo();

        }

        public ArrayList<Produto> listarTodosProdutos(){
            return produtos;
        }

        public Produto buscarPorId(int id){

            for(Produto produto : produtos){

                if(produto.getId() == id){
                    return produto;
                }
            }
            throw new ProdutoNaoEncontradoException("Produto: " + id + " não foi encontrado");
        }

        public boolean remover(int id){

            Produto produto = buscarPorId(id);

            produtos.remove(produto);

            salvarArquivo();

            return true;
        }

        @SuppressWarnings("unchecked")
        public void carregar(){

            try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARQUIVO))){
                produtos = (ArrayList<Produto>) in.readObject();
            }

            catch (FileNotFoundException e){
                System.out.println("Arquivo ainda não existe!");
            }

            catch (IOException | ClassNotFoundException e){
                System.out.println("Erro ao carregar arquivo!");
            }

        }

        public int buscarMaiorId(){

            int maiorId = 1;

            for( Produto produto: produtos){

                if(produto.getId() > maiorId){
                    maiorId = produto.getId();
                }

            }
            return maiorId;
        }
    }