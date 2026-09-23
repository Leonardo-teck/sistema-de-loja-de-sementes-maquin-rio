    import java.io.Serializable;

    public abstract class Produto implements Estocavel, Serializable {

        private static final long serialVersionUID = 1L;
        private int id;
        private String nome;
        private String categoria;
        private double preco;
        private String fornecedor;

        public Produto(int id, String nome, String categoria, double preco, String fornecedor) {

            this.id = id;
            this.nome = nome;
            this.categoria = categoria;
            this.preco = preco;
            this.fornecedor = fornecedor;
        }

        public int getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }

        public String getCategoria() {
            return categoria;
        }

        public double getPreco() {
            return preco;
        }

        public String getFornecedor() {
            return fornecedor;
        }

        public abstract void adicionarEstoque(double valorEstoque);

        public abstract boolean removerEstoque(double valorEstoque);

        public abstract String mostrarEstoque();

        public abstract double calcularValorTotal();

        public abstract void mostrarDadosDoProduto();

    }
