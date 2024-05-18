import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ImobiliariaGUI {
    private Imobiliaria imobiliaria;
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;

    public ImobiliariaGUI() {
        imobiliaria = new Imobiliaria();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Sistema de Controle de Imóveis");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Tabela para exibir os imóveis
        String[] columnNames = {"Código", "Área Construída", "Área Total", "Nº Quartos", "Tipo", "Preço", "Cidade", "Bairro"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Painel de botões
        JPanel panel = new JPanel();
        frame.add(panel, BorderLayout.SOUTH);

        JButton btnAdd = new JButton("Adicionar Imóvel");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarImovel();
            }
        });
        panel.add(btnAdd);

        JButton btnListAll = new JButton("Listar Todos Imóveis");
        btnListAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarTodosImoveis();
            }
        });
        panel.add(btnListAll);

        JButton btnSearch = new JButton("Buscar Imóvel");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarImovel();
            }
        });
        panel.add(btnSearch);

        JButton btnDelete = new JButton("Excluir Imóvel");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                excluirImovel();
            }
        });
        panel.add(btnDelete);

        JButton btnUpdate = new JButton("Alterar Imóvel");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                alterarImovel();
            }
        });
        panel.add(btnUpdate);

        frame.setVisible(true);
    }

    private void adicionarImovel() {
        JTextField codigoField = new JTextField(5);
        JTextField areaConstruidaField = new JTextField(5);
        JTextField areaTotalField = new JTextField(5);
        JTextField numeroQuartosField = new JTextField(5);
        JTextField tipoField = new JTextField(5);
        JTextField precoField = new JTextField(5);
        JTextField cidadeField = new JTextField(5);
        JTextField bairroField = new JTextField(5);

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Código:"));
        panel.add(codigoField);
        panel.add(new JLabel("Área Construída:"));
        panel.add(areaConstruidaField);
        panel.add(new JLabel("Área Total:"));
        panel.add(areaTotalField);
        panel.add(new JLabel("Número de Quartos:"));
        panel.add(numeroQuartosField);
        panel.add(new JLabel("Tipo (0 - Casa, 1 - Apartamento):"));
        panel.add(tipoField);
        panel.add(new JLabel("Preço:"));
        panel.add(precoField);
        panel.add(new JLabel("Cidade:"));
        panel.add(cidadeField);
        panel.add(new JLabel("Bairro:"));
        panel.add(bairroField);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Adicionar Imóvel", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int codigo = Integer.parseInt(codigoField.getText());
            float areaConstruida = Float.parseFloat(areaConstruidaField.getText());
            float areaTotal = Float.parseFloat(areaTotalField.getText());
            int numeroQuartos = Integer.parseInt(numeroQuartosField.getText());
            int tipo = Integer.parseInt(tipoField.getText());
            float preco = Float.parseFloat(precoField.getText());
            String cidade = cidadeField.getText();
            String bairro = bairroField.getText();

            Endereco endereco = new Endereco(cidade, bairro);
            Imovel imovel = new Imovel(codigo, areaConstruida, areaTotal, numeroQuartos, tipo, preco, endereco);
            imobiliaria.cadastrarImovel(imovel);
            listarTodosImoveis();
        }
    }

    private void listarTodosImoveis() {
        List<Imovel> imoveis = imobiliaria.getListaDeImoveis();
        tableModel.setRowCount(0);
        for (Imovel imovel : imoveis) {
            tableModel.addRow(new Object[]{
                    imovel.getCodigo(),
                    imovel.getAreaConstruida(),
                    imovel.getAreaTotal(),
                    imovel.getNumeroQuartos(),
                    imovel.getTipo() == 0 ? "Casa" : "Apartamento",
                    imovel.getPreco(),
                    imovel.getLocalizacao().getCidade(),
                    imovel.getLocalizacao().getBairro()
            });
        }
    }

    private void buscarImovel() {
        String[] options = {"Tipo", "Cidade", "Bairro e Cidade", "Faixa de Preço", "Número de Quartos"};
        String busca = (String) JOptionPane.showInputDialog(frame, "Escolha o critério de busca:", "Buscar Imóvel",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (busca != null) {
            switch (busca) {
                case "Tipo":
                    buscarPorTipo();
                    break;
                case "Cidade":
                    buscarPorCidade();
                    break;
                case "Bairro e Cidade":
                    buscarPorBairroECidade();
                    break;
                case "Faixa de Preço":
                    buscarPorFaixaDePreco();
                    break;
                case "Número de Quartos":
                    buscarPorNumeroDeQuartos();
                    break;
            }
        }
    }

    private void buscarPorTipo() {
        String tipoStr = JOptionPane.showInputDialog(frame, "Digite o tipo (0 - Casa, 1 - Apartamento):");
        int tipo = Integer.parseInt(tipoStr);
        List<Imovel> imoveis = imobiliaria.buscarImoveisPorTipo(tipo);
        atualizarTabela(imoveis);
    }

    private void buscarPorCidade() {
        String cidade = JOptionPane.showInputDialog(frame, "Digite a cidade:");
        List<Imovel> imoveis = imobiliaria.buscarImoveisPorCidade(cidade);
        atualizarTabela(imoveis);
    }

    private void buscarPorBairroECidade() {
        JTextField cidadeField = new JTextField(5);
        JTextField bairroField = new JTextField(5);
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Cidade:"));
        panel.add(cidadeField);
        panel.add(new JLabel("Bairro:"));
        panel.add(bairroField);
        int result = JOptionPane.showConfirmDialog(frame, panel, "Buscar por Bairro e Cidade", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String cidade = cidadeField.getText();
            String bairro = bairroField.getText();
            List<Imovel> imoveis = imobiliaria.buscarImoveisPorBairroECidade(bairro, cidade);
            atualizarTabela(imoveis);
        }
    }

    private void buscarPorFaixaDePreco() {
        JTextField precoMinField = new JTextField(5);
        JTextField precoMaxField = new JTextField(5);
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Preço Mínimo:"));
        panel.add(precoMinField);
        panel.add(new JLabel("Preço Máximo:"));
        panel.add(precoMaxField);
        int result = JOptionPane.showConfirmDialog(frame, panel, "Buscar por Faixa de Preço", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            float precoMin = Float.parseFloat(precoMinField.getText());
            float precoMax = Float.parseFloat(precoMaxField.getText());
            List<Imovel> imoveis = imobiliaria.buscarImoveisPorFaixaDePreco(precoMin, precoMax);
            atualizarTabela(imoveis);
        }
    }

    private void buscarPorNumeroDeQuartos() {
        String numeroQuartosStr = JOptionPane.showInputDialog(frame, "Digite o número mínimo de quartos:");
        int numeroMinQuartos = Integer.parseInt(numeroQuartosStr);
        List<Imovel> imoveis = imobiliaria.buscarImoveisPorNumeroDeQuartos(numeroMinQuartos);
        atualizarTabela(imoveis);
    }

    private void excluirImovel() {
        String codigoStr = JOptionPane.showInputDialog(frame, "Digite o código do imóvel a ser excluído:");
        int codigo = Integer.parseInt(codigoStr);
        boolean sucesso = imobiliaria.excluirImovel(codigo);
        if (sucesso) {
            JOptionPane.showMessageDialog(frame, "Imóvel excluído com sucesso.");
            listarTodosImoveis();
        } else {
            JOptionPane.showMessageDialog(frame, "Imóvel não encontrado.");
        }
    }

    private void alterarImovel() {
        String codigoStr = JOptionPane.showInputDialog(frame, "Digite o código do imóvel a ser alterado:");
        int codigo = Integer.parseInt(codigoStr);
        Imovel imovelExistente = imobiliaria.buscarImovelPorCodigo(codigo);
        if (imovelExistente == null) {
            JOptionPane.showMessageDialog(frame, "Imóvel não encontrado.");
            return;
        }

        JTextField areaConstruidaField = new JTextField(String.valueOf(imovelExistente.getAreaConstruida()), 5);
        JTextField areaTotalField = new JTextField(String.valueOf(imovelExistente.getAreaTotal()), 5);
        JTextField numeroQuartosField = new JTextField(String.valueOf(imovelExistente.getNumeroQuartos()), 5);
        JTextField tipoField = new JTextField(String.valueOf(imovelExistente.getTipo()), 5);
        JTextField precoField = new JTextField(String.valueOf(imovelExistente.getPreco()), 5);
        JTextField cidadeField = new JTextField(imovelExistente.getLocalizacao().getCidade(), 5);
        JTextField bairroField = new JTextField(imovelExistente.getLocalizacao().getBairro(), 5);

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Área Construída:"));
        panel.add(areaConstruidaField);
        panel.add(new JLabel("Área Total:"));
        panel.add(areaTotalField);
        panel.add(new JLabel("Número de Quartos:"));
        panel.add(numeroQuartosField);
        panel.add(new JLabel("Tipo (0 - Casa, 1 - Apartamento):"));
        panel.add(tipoField);
        panel.add(new JLabel("Preço:"));
        panel.add(precoField);
        panel.add(new JLabel("Cidade:"));
        panel.add(cidadeField);
        panel.add(new JLabel("Bairro:"));
        panel.add(bairroField);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Alterar Imóvel", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            float areaConstruida = Float.parseFloat(areaConstruidaField.getText());
            float areaTotal = Float.parseFloat(areaTotalField.getText());
            int numeroQuartos = Integer.parseInt(numeroQuartosField.getText());
            int tipo = Integer.parseInt(tipoField.getText());
            float preco = Float.parseFloat(precoField.getText());
            String cidade = cidadeField.getText();
            String bairro = bairroField.getText();

            Endereco endereco = new Endereco(cidade, bairro);
            Imovel novoImovel = new Imovel(codigo, areaConstruida, areaTotal, numeroQuartos, tipo, preco, endereco);
            imobiliaria.alterarImovel(codigo, novoImovel);
            listarTodosImoveis();
        }
    }

    private void atualizarTabela(List<Imovel> imoveis) {
        tableModel.setRowCount(0);
        for (Imovel imovel : imoveis) {
            tableModel.addRow(new Object[]{
                    imovel.getCodigo(),
                    imovel.getAreaConstruida(),
                    imovel.getAreaTotal(),
                    imovel.getNumeroQuartos(),
                    imovel.getTipo() == 0 ? "Casa" : "Apartamento",
                    imovel.getPreco(),
                    imovel.getLocalizacao().getCidade(),
                    imovel.getLocalizacao().getBairro()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ImobiliariaGUI();
            }
        });
    }
}
