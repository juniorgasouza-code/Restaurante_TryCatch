import java.util.ArrayList;

public class Pedidos {
    private int id = 1;
    private static int proximoID = 0;
    private int idCLiente;
    private ArrayList<Integer> idProdutos;
    private String status;

    public Pedidos(int idCLiente) {
        this.id = proximoID++;
        proximoID++;
        this.idCLiente = idCLiente;
        this.idProdutos = new ArrayList<>();
        this.status = "PENDENTE";
    }
    public int getId() {
        return this.id;
    }
    public int getIdCliente() {
        return this.idCLiente;
    }
    public ArrayList<Integer> getIdProdutos(){
        return this.idProdutos;
    }
    public String getStatus(){
        return this.status;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public void setIdProduto(ArrayList<Integer> idProdutos){
        this.idProdutos = idProdutos;
    }

}
