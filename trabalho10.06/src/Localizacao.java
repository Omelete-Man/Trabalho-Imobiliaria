public class Localizacao {
    private String cidade;
    private String bairro;

    public Localizacao(String cidade, String bairro) {
        this.cidade = cidade;
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }
}



    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }
}
