        import java.util.ArrayList;

        public class EstoqueService {

            private ProdutoRepository produtoRepository;


            public EstoqueService(){

                produtoRepository = new ProdutoRepository();

            }


            public void cadastrarProduto(Produto produto){

                produtoRepository.salvar(produto);

            }


            public ArrayList<Produto> listarProdutos(){

                return produtoRepository.listarTodosProdutos();

            }


            public Produto buscarPorId(int id){

                return produtoRepository.buscarPorId(id);

            }


            public boolean removerProduto(int id){

                return produtoRepository.remover(id);

            }

            public int buscarMaiorId(){
                return produtoRepository.buscarMaiorId();
            }

        }