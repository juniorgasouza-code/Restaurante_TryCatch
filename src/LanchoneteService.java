import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class LanchoneteService {
    private HashMap<Integer, Produto> produtos = new HashMap<>();
    private HashMap<Integer, Cliente> clientes = new HashMap<>();
    private HashMap<Integer, Pedidos> pedidos = new HashMap<>();

    private int proximoIdProduto = 1;
    private int proximoIdCliente = 1;
    private int proximoIdPedido = 1;


    public boolean nomeJaExisteCliente(String nome){
        for(Cliente cliente : clientes.values()){
            if(cliente.getNome().equals(nome)){
                return true;
            }
        }
        return false;
    }
    public boolean nomeJaExisteProduto(String nome){
        for(Produto produto : produtos.values()){
            if(produto.getNome().equals(nome)){
                return true;
            }
        }
        return false;
    }
    public void adicionarProduto(Produto produto){
        produtos.put(produto.getId(), produto);
    }
    public void adicionarCliente(Cliente cliente){
        clientes.put(cliente.getId(), cliente);
    }
    public void adicionarPedido(Pedidos pedido){
        pedidos.put(pedido.getId(), pedido);
    }
    public boolean validarCliente(int idCliente){
        return clientes.containsKey(idCliente);
    }
    public boolean validarPedido(int idPedido){
        return pedidos.containsKey(idPedido);
    }
    public boolean validarProduto(int idProduto){
        return produtos.containsKey(idProduto);
    }
    public void listarCardapio(){
        for(Integer id : produtos.keySet()){
            System.out.println("ID: " + id);
            System.out.println(produtos.get(id).toString());
            System.out.println("--------------------");
        }
    }
    public Pedidos getPedido(int idPedido){
        return pedidos.get(idPedido);
    }
    public Produto getProduto(int idProduto){
        return produtos.get(idProduto);
    }
    public void atualizaListaPedidos(int id, Pedidos pedido){
        pedidos.put(id, pedido);
    }
    public void transformaBooleanProduto(int idproduto){
        Produto produto = produtos.get(idproduto);
        produto.setDisponivel(false);
        produtos.put(idproduto, produto);
    }
    public void transformarStatusPedido(int idproduto){
        Pedidos pedido = pedidos.get(idproduto);
        pedido.setStatus("PAGO");
        pedidos.put(idproduto, pedido);
    }
    public void removerPedido(int idPedido){
        pedidos.remove(idPedido);
    }
    public void mostrarTodosPedidods(){
        for( int idPedido : pedidos.keySet()){
            System.out.println("ID: " + idPedido);
            Pedidos pedido = getPedido(idPedido);
            System.out.println("Nome do Cliente: " + pedido.getIdCliente());
            System.out.println("Status do Pedido: " + pedido.getStatus());
            ArrayList<Integer> idPedidos = pedido.getIdProdutos();
            for(int i = 0; i < idPedidos.size(); i++){
                Produto produto = getProduto(idPedidos.get(i));
                System.out.println(produto.toString());
                System.out.println("------------------");
            }
        }
        System.out.println("=====================");
    }
    public void mostrarTodosProdutos(){
        for(int id : produtos.keySet()){
            Produto produto = getProduto(id);
            if(produto.isDisponivel()){
                System.out.println(produto.toString());
                System.out.println("-----------");
            }
        }
    }
}
