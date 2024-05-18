import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Imobiliaria imobiliaria = new Imobiliaria();
        testarBuscas(imobiliaria); // Adicione esta linha para testar as buscas
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

    private static void testarBuscas(Imobiliaria imobiliaria) {
        Endereco endereco1 = new Endereco("São Paulo", "Centro");
        Endereco endereco2 = new Endereco("Rio de Janeiro", "Copacabana");
        Endereco endereco3 = new Endereco("São Paulo", "Jardins");

        Imovel imovel1 = new Imovel(1, 100.0f, 200.0f, 3, 0, 500000.0f, endereco1);
        Imovel imovel2 = new Imovel(2, 80.0f, 150.0f, 2, 1, 300000.0f, endereco2);
        Imovel imovel3 = new Imovel(3, 120.0f, 250.0f, 4, 0, 700000.0f, endereco3);

        imobiliaria.cadastrarImovel(imovel1);
        imobiliaria.cadastrarImovel(imovel2);
        imobiliaria.cadastrarImovel(imovel3);

        System.out.println("Busca por código (1):");
        System.out.println(imobiliaria.buscarImovelPorCodigo(1));

        System.out.println("Busca por tipo (0 - Casa):");
        List<Imovel> casas = imobiliaria.buscarImoveisPorTipo(0);
        for (Imovel imovel : casas) {
            System.out.println(imovel);
        }

        System.out.println("Busca por cidade (São Paulo):");
        List<Imovel> imoveisSP = imobiliaria.buscarImoveisPorCidade("São Paulo");
        for (Imovel imovel : imoveisSP) {
            System.out.println(imovel);
        }

        System.out.println("Busca por bairro e cidade (Jardins, São Paulo):");
        List<Imovel> imoveisJardinsSP = imobiliaria.buscarImoveisPorBairroECidade("Jardins", "São Paulo");
        for (Imovel imovel : imoveisJardinsSP) {
            System.out.println(imovel);
        }

        System.out.println("Busca por faixa de preço (300000 a 600000):");
        List<Imovel> imoveisPreco = imobiliaria.buscarImoveisPorFaixaDePreco(300000.0f, 600000.0f);
        for (Imovel imovel : imoveisPreco) {
            System.out.println(imovel);
        }

        System.out.println("Busca por número mínimo de quartos (3):");
        List<Imovel> imoveisQuartos = imobiliaria.buscarImoveisPorNumeroDeQuartos(3);
        for (Imovel imovel : imoveisQuartos) {
            System.out.println(imovel);
        }
    }
}
