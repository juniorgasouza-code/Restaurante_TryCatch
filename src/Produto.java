public class Produto {
    private int id = 1;
    private static int proximoID = 0;
    private String nome;
    private double preco;
    private boolean disponivel = true;

    public Produto(String nome, double preco) {
        this.id = proximoID++;
        proximoID++;
        this.nome = nome;
        this.preco = preco;
    }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }
    public boolean isDisponivel(){
        return this.disponivel;
    }
    public double getPreco(){
        return this.preco;
    }
    public void setDisponivel(boolean disponivel){
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + "\nPreço: " + this.preco + "\nDisponível: " + (this.disponivel ? "Sim" : "Não");
    }
}
