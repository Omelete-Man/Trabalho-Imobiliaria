public class Endereco {
    private String cidade;
    private String bairro;

    // Construtor
    public Endereco(String cidade, String bairro) {
        this.cidade = cidade;
        this.bairro = bairro;
    }

    // Getters e Setters
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

    // Método toString para exibir as informações do endereço
    @Override
    public String toString() {
        return "Cidade: " + cidade + ", Bairro: " + bairro;
    }
}

