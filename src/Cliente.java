import java.util.ArrayList;

public class Cliente {
    private int id = 1;
    private static int proximoID = 0;
    private String nome;
    private ArrayList<Integer> idPedidos;

    public Cliente(String nome) {
        this.id = proximoID++;
        proximoID++;
        this.nome = nome;
        this.idPedidos = new ArrayList<>();
    }
    public int getId() {
        return this.id;
    }
    public String getNome(){
        return this.nome;
    }


}
