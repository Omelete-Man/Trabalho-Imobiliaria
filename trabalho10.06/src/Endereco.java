public class Endereco {
    private String cidade;
    private String bairro;

    // Construtor para inicializar cidade e bairro
    public Endereco(String cidade, String bairro) {
        this.cidade = cidade;
        this.bairro = bairro;
    }

    // Getters e setters para os atributos
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    // Método toString para facilitar a impressão do endereço
    @Override
    public String toString() {
        return bairro + ", " + cidade;
    }
}
