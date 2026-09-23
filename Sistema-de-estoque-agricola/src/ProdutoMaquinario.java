public class ProdutoMaquinario extends Produto{
    private String modelo;
    private String fabricante;
    private String manutencao;
    private int horasDeUso;
    private int quantidade;

    public ProdutoMaquinario( int id, String nome, String categoria,int quantidade ,double preco,
                              String fornecedor, int horasDeUso, String modelo,
                              String fabricante, String manutencao ) {

        super(id, nome, categoria, preco, fornecedor);
        this.quantidade = quantidade;
        this.horasDeUso = horasDeUso;
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.manutencao = manutencao;
    }

    public String getModelo(){
        return modelo;
    }

    public String getFabricante(){
        return fabricante;
    }

    public String getManutencao(){
        return manutencao;
    }

    public int getHorasDeUso(){
        return horasDeUso;
    }

    public int getQuantidade() {
        return quantidade;
    }

    protected int getQuantidadeInterna() {
        return quantidade;
    }

    protected void adicionarQuantidade(int quantidade) {
        this.quantidade += quantidade;
    }

    protected void removerQuantidade(int valor) {
        quantidade -= valor;
    }

    @Override
    public void adicionarEstoque(double valorEstoque){

        if(valorEstoque <= 0){
            System.out.println("Valor inválido");
            return;
        }

        int unidades = (int) valorEstoque;

        adicionarQuantidade(unidades);
    }

    @Override
    public String mostrarEstoque(){
        return getQuantidade() + " unidades";
    }

    @Override
    public boolean removerEstoque(double valorEstoque){

        if(valorEstoque <= 0){
            System.out.println("Valor inválido");
            return false;
        }

        int unidades = (int) valorEstoque;


        if(unidades > getQuantidade()){
            System.out.println("Quantidade insuficiente no estoque.");
            return false;
        }

        removerQuantidade(unidades);

        return true;
    }

    @Override
    public void mostrarDadosDoProduto(){
        System.out.println("\nProduto cadastrado:" + getCategoria());
        System.out.println("ID: " + getId());
        System.out.println("Maquinario: " + getNome());
        System.out.println("Fabricante: " + getFabricante());
        System.out.println("Modelo: " + getModelo());
        System.out.println("Ultima manutenção realizada: " + getManutencao());
        System.out.printf("Horas de uso: " + "%,d", getHorasDeUso());
        System.out.println("\nUnidades: " + getQuantidade());
        System.out.printf("Preço: " + "R$" + "%.2f", getPreco());
        System.out.printf("\nValor total: " + "R$" + "%.2f", calcularValorTotal());
        System.out.println("\nFornecedor: " + getFornecedor());
    }

    @Override
    public double calcularValorTotal(){
        return getQuantidade() * getPreco();
    }

}
