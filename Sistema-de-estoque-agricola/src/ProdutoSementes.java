import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProdutoSementes extends Produto{
    private double quilos;
    private int lote ;
    private LocalDate validade;
    private String safra;

    public ProdutoSementes(int id, String nome, String categoria, double preco,
                           String fornecedor, int lote, LocalDate validade, String safra, double quilos) {

        super(id, nome, categoria, preco, fornecedor);

        if (quilos <= 0) {
            quilos = 0;
        }

        this.quilos = quilos;
        this.lote = lote;
        this.validade = validade;
        this.safra = safra;
    }

    public double getQuilos() {
        return quilos;
    }

    public double calcularValorTotal(){
        return getQuilos() * getPreco();
    }

    public String getSafra(){
        return safra;
    }

    public int getLote(){
        return lote;
    }

    public LocalDate getValidade() {
        return validade;
    }
    @Override
    public void adicionarEstoque(double valorEstoque){

        if(valorEstoque <=0){
            System.out.println("Valor inválido");
            return;
        }

        this.quilos += valorEstoque;
    }

    @Override
    public boolean removerEstoque(double valorEstoque){

        if(valorEstoque > quilos){
            System.out.println("Falta no estoque");
            return false;
        }

        quilos -= valorEstoque;
        return true;
    }

    @Override
    public String mostrarEstoque(){
        return String.format("%.2f kg", quilos);
    }

    @Override
    public void mostrarDadosDoProduto(){

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("\nProduto cadastrado:");
        System.out.println("ID: " + getId());
        System.out.println("Semente de " + getNome());
        System.out.println("Lote: " + getLote());
        System.out.println("Safra: " + getSafra());
        System.out.println("Validade: " + getValidade().format(formato));
        System.out.println("Categoria: " + getCategoria());
        System.out.println("Quilos: " + getQuilos() + " kg");
        System.out.printf("Preço: " + "R$" + "%.2f", getPreco());
        System.out.printf("\nValor total: " + "R$" + "%.2f", calcularValorTotal());
        System.out.println("\nFornecedor: " + getFornecedor());
    }
}
