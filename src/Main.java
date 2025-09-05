import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner SC = new Scanner(System.in);
    static LanchoneteService LANCHONETE = new LanchoneteService();
    public static void main(String[] args) {
        mostrarMenu();
    }
    public static void mostrarMenu() {
        while(true) {
            System.out.println("1. Cadastrar produto\n" +
                    "2. Cadastrar cliente\n" +
                    "3. Criar pedido\n" +
                    "4. Adicionar produto a pedido\n" +
                    "5. Finalizar pedido\n" +
                    "6. Cancelar pedido\n" +
                    "7. Listar pedidos\n" +
                    "8. Listar cardápio\n" +
                    "9. Sair\n");
        int escolhaMenu = SC.nextInt();
        switch (escolhaMenu){
            case 1: 
                cadastrarProduto();
                break;
            case 2: 
                cadastrarCliente();    
                break;
            case 3:
                criarPedido();
                break;
            case 4:
                adicionarProdutoAPedido();
                break;
            case 5:
                finalizarPedido();
                break;
            case 6:
                cancelarPedido();
                break;
            case 7:
                listarPedidos();
                break;
            case 8:
                listarCardapio();
                break;
            case 9:
                System.out.println("Saindo...");
                return;
            default:
                throw new InputMismatchException("Dado invalido no menu!");
        }
        }
    }

    private static void cadastrarProduto() {
        System.out.println("Insira o nome do produto: ");
        String nome = SC.next();
        if(nome == null){
            throw new IllegalArgumentException("Nome inválido");
        }
        if(LANCHONETE.nomeJaExisteProduto(nome)){
            throw new IllegalArgumentException("Nome já existe");
        }
        System.out.println("Insira o preço do produto: ");
        double preco = SC.nextDouble();
        if(preco <= 0){
            throw new IllegalArgumentException("Preço inválido");
        }
        Produto produto = new Produto(nome, preco);
        LANCHONETE.adicionarProduto(produto);
        System.out.println("Produto cadastrado com sucesso! ID: " + produto.getId());
    }

    private static void cadastrarCliente() {
        System.out.println("Insira o nome do cliente: ");
        String nome = SC.next();
        if(nome == null){
            throw  new IllegalArgumentException("Nome inválido");
        }
        if(LANCHONETE.nomeJaExisteCliente(nome)){
            throw new IllegalArgumentException("Nome já existe");
        }
        Cliente cliente = new Cliente(nome);
        LANCHONETE.adicionarCliente(cliente);
        System.out.println("Cliente cadastrado com sucesso! ID: " + cliente.getId());
    }

    private static void criarPedido() {
        System.out.println("Insira o ID do cliente:");
        int idCliente = SC.nextInt();
        if(!LANCHONETE.validarCliente(idCliente)){
            throw new IllegalArgumentException("Cliente não existe!");
        }
        Pedidos pedido = new Pedidos(idCliente);
        LANCHONETE.adicionarPedido(pedido);
        System.out.println("Pedido criado com sucesso! ID: " + pedido.getId());
    }

    private static void adicionarProdutoAPedido() {
        System.out.println("Insira o ID do pedido:");
        int idPedido = SC.nextInt();
        if(LANCHONETE.validarPedido(idPedido)){
            System.out.println("Produtos: ");
            LANCHONETE.listarCardapio();
            System.out.println("Insira o ID do produto que você que adicionar\n>");
            int idProduto = SC.nextInt();
            Produto produto = LANCHONETE.getProduto(idProduto);
            if(!produto.isDisponivel()){
                throw new IllegalStateException("Tentou acessar um produto não disponivel");
            }
            if(LANCHONETE.validarProduto(idProduto)){
                Pedidos pedido = LANCHONETE.getPedido(idPedido);
                if(pedido.getStatus().equals("PENDENTE")){
                    ArrayList<Integer> idProdutos = pedido.getIdProdutos();
                    LANCHONETE.transformaBooleanProduto(idProduto);
                    idProdutos.add(idProduto);
                    pedido.setIdProduto(idProdutos);
                    LANCHONETE.atualizaListaPedidos(idPedido, pedido);
                    System.out.println("Produto Adicionado ao pedido foi efetuado com sucesso!");
                } else {
                    throw new IllegalStateException("Adicionou produto que não esta com o status de pedido pendente!");
                }
            } else {
                throw new IllegalArgumentException("Id do Pedido não existe!");
            }
        } else {
            throw new IllegalArgumentException("Id do Pedido não existe!");
        }
    }

    private static void finalizarPedido() {
        System.out.println("Insira o ID do pedido: ");
        int idPedido = SC.nextInt();
        if(LANCHONETE.validarPedido(idPedido)){
            System.out.println("Lista de produtos do pedido: ");
            Pedidos pedido = LANCHONETE.getPedido(idPedido);
            ArrayList<Integer> idProdutos = pedido.getIdProdutos();
            double valorTotal = 0;
            for (int idProduto : idProdutos) {
                Produto produto = LANCHONETE.getProduto(idProduto);
                System.out.println(produto.toString());
                valorTotal+= produto.getPreco();
                produto.setDisponivel(true);
            }
            System.out.println("O preço total do pedido é " + valorTotal);
            LANCHONETE.transformarStatusPedido(idPedido);
        } else{
            throw  new IllegalArgumentException("Pedido não existe!");
        }
    }

    private static void cancelarPedido() {
        System.out.println("Insira o ID do pedido: ");
        int idPedido = SC.nextInt();
        if(LANCHONETE.validarPedido(idPedido)){
            Pedidos pedido = LANCHONETE.getPedido(idPedido);
            if(pedido.getStatus().equals("PENDENTE")){
                if(!pedido.getStatus().equals("PAGO")){
                    ArrayList<Integer> idProdutos = pedido.getIdProdutos();
                    for(int id : idProdutos){
                        Produto produto = LANCHONETE.getProduto(id);
                        produto.setDisponivel(true);
                    }
                    LANCHONETE.removerPedido(idPedido);
                    System.out.println("Pedido cancelado com sucesso!");
                } else{
                    throw  new IllegalStateException("Pedido esta pago");
                }
            } else{
                throw  new IllegalStateException("Pedido esta pendente!");
            }
        } else{
            throw new IllegalArgumentException("Pedido não existe");
        }
    }

    private static void listarPedidos() {
        LANCHONETE.mostrarTodosPedidods();
    }

    private static void listarCardapio() {
        LANCHONETE.mostrarTodosProdutos();
    }
}