import java.util.Scanner;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        Imobiliaria imobiliaria = new Imobiliaria();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1 - Cadastrar Imóvel");
            System.out.println("2 - Listar Imóveis");
            System.out.println("3 - Buscar Imóvel por Condição");
            System.out.println("4 - Excluir Imóvel");
            System.out.println("5 - Alterar Imóvel");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consome a nova linha

            switch (opcao) {
                case 1:
                    cadastrarImovel(imobiliaria, scanner);
                    break;
                case 2:
                    imobiliaria.listarImoveis();
                    break;
                case 3:
                    listarImoveisCondicao(imobiliaria, scanner);
                    break;
                case 4:
                    excluirImovel(imobiliaria, scanner);
                    break;
                case 5:
                    alterarImovel(imobiliaria, scanner);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void cadastrarImovel(Imobiliaria imobiliaria, Scanner scanner) {
        System.out.print("Código: ");
        int codigo = scanner.nextInt();
        System.out.print("Área construída: ");
        float areaConstruida = scanner.nextFloat();
        System.out.print("Área total: ");
        float areaTotal = scanner.nextFloat();
        System.out.print("Número de quartos: ");
        int numeroQuartos = scanner.nextInt();
        System.out.print("Tipo (0 - Casa, 1 - Apartamento): ");
        int tipo = scanner.nextInt();
        System.out.print("Preço: ");
        float preco = scanner.nextFloat();
        scanner.nextLine(); // Consome a nova linha
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Bairro: ");
        String bairro = scanner.nextLine();

        Endereco endereco = new Endereco(cidade, bairro);
        Imovel imovel = new Imovel(codigo, areaConstruida, areaTotal, numeroQuartos, tipo, preco, endereco);
        imobiliaria.cadastrarImovel(imovel);
        System.out.println("Imóvel cadastrado com sucesso!");
    }

    private static void listarImoveisCondicao(Imobiliaria imobiliaria, Scanner scanner) {
        System.out.println("Escolha a condição:");
        System.out.println("1 - Tipo do imóvel");
        System.out.println("2 - Cidade");
        System.out.println("3 - Bairro e Cidade");
        System.out.println("4 - Faixa de preço");
        System.out.println("5 - Número mínimo de quartos");
        int condicao = scanner.nextInt();
        scanner.nextLine(); // Consome a nova linha

        List<Imovel> imoveis;
        switch (condicao) {
            case 1:
                System.out.print("Tipo (0 - Casa, 1 - Apartamento): ");
                int tipo = scanner.nextInt();
                imoveis = imobiliaria.buscarImoveisPorTipo(tipo);
                break;
            case 2:
                System.out.print("Cidade: ");
                String cidade = scanner.nextLine();
                imoveis = imobiliaria.buscarImoveisPorCidade(cidade);
                break;
            case 3:
                System.out.print("Cidade: ");
                String cidadeCond = scanner.nextLine();
                System.out.print("Bairro: ");
                String bairroCond = scanner.nextLine();
                imoveis = imobiliaria.buscarImoveisPorBairroECidade(bairroCond, cidadeCond);
                break;
            case 4:
                System.out.print("Preço mínimo: ");
                float precoMin = scanner.nextFloat();
                System.out.print("Preço máximo: ");
                float precoMax = scanner.nextFloat();
                imoveis = imobiliaria.buscarImoveisPorFaixaDePreco(precoMin, precoMax);
                break;
            case 5:
                System.out.print("Número mínimo de quartos: ");
                int numeroMinQuartos = scanner.nextInt();
                imoveis = imobiliaria.buscarImoveisPorNumeroDeQuartos(numeroMinQuartos);
                break;
            default:
                System.out.println("Condição inválida!");
                return;
        }

        if (imoveis.isEmpty()) {
            System.out.println("Nenhum imóvel encontrado.");
        } else {
            for (Imovel imovel : imoveis) {
                System.out.println(imovel);
            }
        }
    }

    private static void excluirImovel(Imobiliaria imobiliaria, Scanner scanner) {
        System.out.print("Código do imóvel a excluir: ");
        int codigo = scanner.nextInt();
        if (imobiliaria.excluirImovel(codigo)) {
            System.out.println("Imóvel excluído com sucesso!");
        } else {
            System.out.println("Imóvel não encontrado!");
        }
    }

    private static void alterarImovel(Imobiliaria imobiliaria, Scanner scanner) {
        System.out.print("Código do imóvel a alterar: ");
        int codigo = scanner.nextInt();

        Imovel imovel = imobiliaria.buscarImovelPorCodigo(codigo);
        if (imovel == null) {
            System.out.println("Imóvel não encontrado!");
            return;
        }

        System.out.print("Nova área construída: ");
        float areaConstruida = scanner.nextFloat();
        System.out.print("Nova área total: ");
        float areaTotal = scanner.nextFloat();
        System.out.print("Novo número de quartos: ");
        int numeroQuartos = scanner.nextInt();
        System.out.print("Novo tipo (0 - Casa, 1 - Apartamento): ");
        int tipo = scanner.nextInt();
        System.out.print("Novo preço: ");
        float preco = scanner.nextFloat();
        scanner.nextLine(); // Consome a nova linha
        System.out.print("Nova cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Novo bairro: ");
        String bairro = scanner.nextLine();

        Endereco endereco = new Endereco(cidade, bairro);
        Imovel novoImovel = new Imovel(codigo, areaConstruida, areaTotal, numeroQuartos, tipo, preco, endereco);

        if (imobiliaria.alterarImovel(codigo, novoImovel)) {
            System.out.println("Imóvel alterado com sucesso!");
        } else {
            System.out.println("Erro ao alterar imóvel!");
        }
    }
}
