import java.time.LocalDate;
import java.util.Comparator;
import java.util.Scanner;
import java.util.List;

public class SistemaEstoque {

    private Scanner scanner;
    private int proximoId;
    private EstoqueService service;

    public SistemaEstoque() {

        scanner = new Scanner(System.in);

        service = new EstoqueService();
        proximoId = service.buscarMaiorId();
    }

    public void iniciar() {
        menu();
    }

    private void menu() {
        boolean status = true;

        while (status) {

            System.out.println("\n--Menu--");
            System.out.println("1 - Cadastrar produto.");
            System.out.println("2 - Listar produtos.");
            System.out.println("3 - Buscar produto.");
            System.out.println("4 - Entrada estoque.");
            System.out.println("5 - Saída estoque.");
            System.out.println("6 - Remover produto.");
            System.out.println("7 - Relatório.");
            System.out.println("8 - Sair.");

            int opcao = Utilitarios.lerInt(scanner);
            scanner.nextLine();

            switch (opcao) {

                case 1:
                    cadastrarProduto();
                    break;

                case 2:
                    listarProduto();
                    break;

                case 3:
                    buscarProduto();
                    break;

                case 4:
                    entradaDeEstoque();
                    break;

                case 5:
                    saidaEstoque();
                    break;

                case 6:
                    removerProduto();
                    break;

                case 7:
                    relatorio();
                    break;

                case 8:
                    System.out.println("Volte sempre.");
                    status = false;
                    break;

                default:
                    System.out.println("Valor inválido.");
            }
        }
    }

    private void cadastrarProduto() {
        Produto novoProduto;

        String nomeDaCategoria;
        String nome;
        int quantidade;
        double peso;
        double preco;
        String fornecedor;

        System.out.println("Digite a categoria:");
        System.out.println("1 - Sementes");
        System.out.println("2 - Maquinario"); //Produto produto = service.buscarPorId(id);

        int categoria = Utilitarios.lerInt(scanner);
        scanner.nextLine();

        switch (categoria) {
            case 1:
                System.out.println("Digite o nome do cultivo:");
                nome = Utilitarios.lerProduto(scanner);

                System.out.println("Digite o lote:");
                int lote = Utilitarios.lerInt(scanner);
                scanner.nextLine();

                System.out.println("Digite a safra:");
                String safra = Utilitarios.lerString(scanner);

                System.out.println("Digite a validade:");
                LocalDate validade = Utilitarios.lerData(scanner);

                System.out.println("Digite o kg:");
                peso = Utilitarios.lerDouble(scanner);
                scanner.nextLine();

                System.out.println("Digite o preço:");
                preco = Utilitarios.lerDouble(scanner);
                scanner.nextLine();

                System.out.println("Digite o fornecedor:");
                fornecedor = Utilitarios.lerFornecedor(scanner);

                nomeDaCategoria = "Sementes";
                novoProduto = new ProdutoSementes(proximoId, nome, nomeDaCategoria, preco,
                        fornecedor, lote, validade, safra, peso);

                adicionarProduto(novoProduto);
                break;

            case 2:
                System.out.println("Digite qual o tipo do Maquinario:");
                nome = Utilitarios.lerProduto(scanner);

                System.out.println("Digite o Fabricante:");
                String fabricante = Utilitarios.lerString(scanner);

                System.out.println("Digite o Modelo:");
                String modelo = Utilitarios.lerString(scanner);

                System.out.println("Digite sobre a ultima manutenção:");
                String manutencao = Utilitarios.lerString(scanner);

                System.out.println("Digite as horas de uso:");
                int horasDeUso = Utilitarios.lerInt(scanner);
                scanner.nextLine();

                System.out.println("Digite quantas unidades:");
                quantidade = Utilitarios.lerInt(scanner);
                scanner.nextLine();

                System.out.println("Digite o preço:");
                preco = Utilitarios.lerDouble(scanner);
                scanner.nextLine();

                System.out.println("Digite o fornecedor:");
                fornecedor = Utilitarios.lerFornecedor(scanner);

                nomeDaCategoria = "Maquinario";
                novoProduto = new ProdutoMaquinario(
                        proximoId, nome, nomeDaCategoria, quantidade, preco,
                        fornecedor, horasDeUso, modelo, fabricante, manutencao);

                adicionarProduto(novoProduto);
                break;

            default:
                System.out.println("Categoria inexistente");
                return;

        }

        novoProduto.mostrarDadosDoProduto();
        proximoId++;

    }

    private void adicionarProduto(Produto produtos) {
        service.cadastrarProduto(produtos);
    }

    private void listarProduto() {

        if (service.listarProdutos().isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        }

        for (Produto produto : service.listarProdutos()) {
            System.out.println(
                    "\nProduto:" +
                            "\nNome: " + produto.getNome() +
                            "\nID: " + produto.getId() +
                            "\nCategoria: " + produto.getCategoria() +
                            "\nEstoque: " + produto.mostrarEstoque());
        }
    }

    private void buscarProduto() {

        if (service.listarProdutos().isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.println("1 - Buscar por ID");
        System.out.println("2 - Buscar por nome");
        System.out.println("3 - Buscar por categoria");

        int opcao = Utilitarios.lerInt(scanner);
        scanner.nextLine();

        switch (opcao) {

            case 1:
                try {

                    System.out.println("Digite o ID:");
                    int id = Utilitarios.lerInt(scanner);

                    Produto produto = service.buscarPorId(id);

                    mostrarProduto(produto);

                } catch (ProdutoNaoEncontradoException e) {
                    System.out.println(e.getMessage());
                }

                break;

            case 2:

                System.out.println("Digite o nome:");
                String nome = Utilitarios.lerString(scanner);

                Produto produtoEncontrado = service.listarProdutos()
                        .stream()
                        .filter(p -> p.getNome().equalsIgnoreCase(nome))
                        .findFirst()
                        .orElse(null);


                if(produtoEncontrado != null){
                    mostrarProduto(produtoEncontrado);
                }else{
                    System.out.println("Produto não encontrado.");
                }

                break;

            case 3:

                System.out.println("Digite categoria:");
                String categoria = Utilitarios.lerString(scanner);

                List<Produto> encontrados = service.listarProdutos()
                        .stream()
                        .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
                        .toList();

                if(encontrados.isEmpty()){
                    System.out.println("Nenhum produto dessa categoria.");
                }else{
                    encontrados.forEach(this::mostrarProduto);
                }

                break;

            default:
                System.out.println("Opção inválida");
        }
    }

    private void mostrarProduto(Produto produto) {

        System.out.println("\nProduto encontrado:");
        System.out.println("Nome: " + produto.getNome());
        System.out.println("ID: " + produto.getId());
        System.out.println("Categoria: " + produto.getCategoria());
        System.out.println("Estoque: " + produto.mostrarEstoque());
        System.out.printf("Valor total: R$ %.2f%n", produto.calcularValorTotal());

    }

    private void entradaDeEstoque() {

        if (service.listarProdutos().isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        try {

            System.out.println("Digite ID:");
            int id = Utilitarios.lerInt(scanner);

            Produto produto = service.buscarPorId(id);

            System.out.println("Valor: ");
            double valor = Utilitarios.lerDouble(scanner);

            produto.adicionarEstoque(valor);

            System.out.println("Novo estoque: " + produto.mostrarEstoque());

        } catch (ProdutoNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }

    }

    private void saidaEstoque() {

        if (service.listarProdutos().isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        try {
            System.out.println("Digite ID:");
            int id = Utilitarios.lerInt(scanner);

            Produto produto = service.buscarPorId(id);

            System.out.println("Valor de saida: ");
            double valor = Utilitarios.lerDouble(scanner);

            if (produto.removerEstoque(valor)){
                System.out.println("Saida realizad com sucesso!");
                System.out.println(produto.mostrarEstoque());
            }

        } catch (ProdutoNaoEncontradoException e){
            System.out.println(e.getMessage());
        }

    }

    private void removerProduto() {

        if (service.listarProdutos().isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        try {
            System.out.println("Digite ID:");
            int id = Utilitarios.lerInt(scanner);

            Produto produto = service.buscarPorId(id);

            System.out.println("Nome: " + produto.getNome());
            System.out.println("1 - Remover.");
            System.out.println("2 - Cancelar.");

            int opcao = Utilitarios.lerInt(scanner);

            if (opcao == 1){
                boolean removido = service.removerProduto(id);

                if (removido){
                    System.out.println("produto Removido com sucesso!");
                }

            }

        } catch (ProdutoNaoEncontradoException e){
            System.out.println(e.getMessage());
        }

    }

    private void relatorio() {

        if (service.listarProdutos().isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        var produtos = service.listarProdutos();

        double patrimonio = produtos.stream()
                .mapToDouble(Produto::calcularValorTotal)
                .sum();

        Produto maisCaro = produtos.stream()
                .max(Comparator.comparing(Produto::calcularValorTotal))
                .orElseThrow();

        Produto maisBarato = produtos.stream()
                .min(Comparator.comparing(Produto::calcularValorTotal)).orElseThrow();

        System.out.printf("\nPatrimônio total: R$ %.2f%n", patrimonio);
        System.out.println("\n Produto mais barato: " + maisBarato);
        System.out.println("\n produto mais caro: " + maisCaro);

    }

}