import java.util.ArrayList;
import java.util.List;

public class Imobiliaria {
    private List<Imovel> listaDeImoveis;

    // Construtor para inicializar a lista de imóveis
    public Imobiliaria() {
        this.listaDeImoveis = new ArrayList<>();
    }

    // Método para cadastrar um novo imóvel na lista
    public void cadastrarImovel(Imovel imovel) {
        listaDeImoveis.add(imovel);
    }

    // Método para obter a lista de todos os imóveis
    public List<Imovel> getListaDeImoveis() {
        return listaDeImoveis;
    }

    // Método para buscar um imóvel pelo código
    public Imovel buscarImovelPorCodigo(int codigo) {
        for (Imovel imovel : listaDeImoveis) {
            if (imovel.getCodigo() == codigo) {
                return imovel;
            }
        }
        return null;
    }

    // Método para excluir um imóvel pelo código
    public boolean excluirImovel(int codigo) {
        Imovel imovel = buscarImovelPorCodigo(codigo);
        if (imovel != null) {
            listaDeImoveis.remove(imovel);
            return true;
        }
        return false;
    }

    // Método para listar todos os imóveis cadastrados
    public void listarImoveis() {
        if (listaDeImoveis.isEmpty()) {
            System.out.println("Nenhum imóvel cadastrado.");
        } else {
            for (Imovel imovel : listaDeImoveis) {
                System.out.println(imovel);
            }
        }
    }

    // Métodos para buscar imóveis por diferentes condições
    public List<Imovel> buscarImoveisPorTipo(int tipo) {
        List<Imovel> resultado = new ArrayList<>();
        for (Imovel imovel : listaDeImoveis) {
            if (imovel.getTipo() == tipo) {
                resultado.add(imovel);
            }
        }
        return resultado;
    }

    public List<Imovel> buscarImoveisPorCidade(String cidade) {
        List<Imovel> resultado = new ArrayList<>();
        for (Imovel imovel : listaDeImoveis) {
            if (imovel.getLocalizacao().getCidade().equalsIgnoreCase(cidade)) {
                resultado.add(imovel);
            }
        }
        return resultado;
    }

    public List<Imovel> buscarImoveisPorBairroECidade(String bairro, String cidade) {
        List<Imovel> resultado = new ArrayList<>();
        for (Imovel imovel : listaDeImoveis) {
            if (imovel.getLocalizacao().getBairro().equalsIgnoreCase(bairro) &&
                imovel.getLocalizacao().getCidade().equalsIgnoreCase(cidade)) {
                resultado.add(imovel);
            }
        }
        return resultado;
    }

    public List<Imovel> buscarImoveisPorFaixaDePreco(float precoMin, float precoMax) {
        List<Imovel> resultado = new ArrayList<>();
        for (Imovel imovel : listaDeImoveis) {
            if (imovel.getPreco() >= precoMin && imovel.getPreco() <= precoMax) {
                resultado.add(imovel);
            }
        }
        return resultado;
    }

    public List<Imovel> buscarImoveisPorNumeroDeQuartos(int numeroMinQuartos) {
        List<Imovel> resultado = new ArrayList<>();
        for (Imovel imovel : listaDeImoveis) {
            if (imovel.getNumeroQuartos() >= numeroMinQuartos) {
                resultado.add(imovel);
            }
        }
        return resultado;
    }

    // Método para alterar as informações de um imóvel existente pelo código
    public boolean alterarImovel(int codigo, Imovel novoImovel) {
        Imovel imovel = buscarImovelPorCodigo(codigo);
        if (imovel != null) {
            imovel.setAreaConstruida(novoImovel.getAreaConstruida());
            imovel.setAreaTotal(novoImovel.getAreaTotal());
            imovel.setNumeroQuartos(novoImovel.getNumeroQuartos());
            imovel.setTipo(novoImovel.getTipo());
            imovel.setPreco(novoImovel.getPreco());
            imovel.setLocalizacao(novoImovel.getLocalizacao());
            return true;
        }
        return false;
    }
}
