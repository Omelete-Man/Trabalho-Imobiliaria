public class Imovel {
    private int codigo;
    private int tipo;
    private Localizacao localizacao;
    private float preco;
    private int numeroQuartos;
    private float areaConstruida;
    private float areaTotal;

    public Imovel(int codigo, int tipo, Localizacao localizacao, float preco, int numeroQuartos, float areaConstruida, float areaTotal) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.localizacao = localizacao;
        this.preco = preco;
        this.numeroQuartos = numeroQuartos;
        this.areaConstruida = areaConstruida;
        this.areaTotal = areaTotal;
    }

    public Imovel(int codigo2, float areaConstruida2, float areaTotal2, int numeroQuartos2, int tipo2, float preco2,
            Endereco endereco) {
        
    }

    public int getCodigo() {
        return codigo;
    }

    public int getTipo() {
        return tipo;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public float getPreco() {
        return preco;
    }

    public int getNumeroQuartos() {
        return numeroQuartos;
    }

    public float getAreaConstruida() {
        return areaConstruida;
    }

    public float getAreaTotal() {
        return areaTotal;
    }

    public void setAreaConstruida(float areaConstruida) {
        this.areaConstruida = areaConstruida;
    }

    public void setAreaTotal(float areaTotal) {
        this.areaTotal = areaTotal;
    }

    public void setNumeroQuartos(int numeroQuartos) {
        this.numeroQuartos = numeroQuartos;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }
}
