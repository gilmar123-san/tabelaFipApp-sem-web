package br.com.veiclo.tabelaFipApp.model.menu;

import br.com.veiclo.tabelaFipApp.model.entities.AnoVeiculo;
import br.com.veiclo.tabelaFipApp.model.entities.DadosModeloVeiculo;
import br.com.veiclo.tabelaFipApp.model.entities.DadosVeiculo;
import br.com.veiclo.tabelaFipApp.model.entities.Veiculo;
import br.com.veiclo.tabelaFipApp.model.services.ConsumoAPI;
import br.com.veiclo.tabelaFipApp.model.services.ConverteDados;

import java.util.*;

import static br.com.veiclo.tabelaFipApp.model.utils.TabelaFipAppConstants.*;

public class MenuPrincipal {

    private Scanner leitor = new Scanner(System.in);

    private ConsumoAPI consulta = new ConsumoAPI();

    private ConverteDados conversor = new ConverteDados();

    public void exibeMenu() {
        System.out.println("OPÇÕES: *** 1: carros - 2: motos - 3: caminhoes ***");

        System.out.print("Escolha uma das opções acima: ");
        var opcao = leitor.nextInt();

        var urlRequest = String.format("%s%s/marcas", DEFAULT_URL, getAutomovelConsulta(opcao));
        var marcas = conversor.obterLista(consulta.obterDados(urlRequest), DadosVeiculo.class);

        if(marcas.isEmpty()) {
            System.out.println("Nenhuma marca encontrada!");
           return;
        }

        marcas.stream()
                .sorted(Comparator.comparingInt(DadosVeiculo::codigo))
                .map(m -> String.format("Cód: %d - Descrição: %s", m.codigo(), m.nome()))
                .forEach(System.out::println);

        System.out.println();
        System.out.print("Informe o código da marca para consulta: ");
        var codMarca = leitor.nextInt();
        urlRequest = String.format("%s%s/marcas/%d/modelos", DEFAULT_URL, getAutomovelConsulta(opcao), codMarca);

        var modelo = conversor.converteDados(consulta.obterDados(urlRequest), DadosModeloVeiculo.class);
        if(Objects.isNull(modelo) || Objects.isNull(modelo.modelos())) {
            System.out.println("Nenhum modelo encontrado!");
            return;
        }

        System.out.println();
        modelo.modelos().stream()
                .sorted(Comparator.comparingInt(DadosVeiculo::codigo))
                .map(m -> String.format("Cód: %d - Descrição: %s", m.codigo(), m.nome()))
                .forEach(System.out::println);

        System.out.println();
        System.out.print("Digite o código do modelo para consultar valores: ");
        var codModelo = leitor.nextInt();

        urlRequest = String.format("%s%s/marcas/%d/modelos/%d/anos", DEFAULT_URL, getAutomovelConsulta(opcao), codMarca, codModelo);
        var listAnos = conversor.obterLista(consulta.obterDados(urlRequest), AnoVeiculo.class);

        List<Veiculo> veiculos = new ArrayList<>();
        listAnos.stream().forEach(a -> {
            var url = String.format("%s%s/marcas/%d/modelos/%d/anos/%s", DEFAULT_URL, getAutomovelConsulta(opcao), codMarca, codModelo, a.codigo());
            veiculos.add(conversor.converteDados(consulta.obterDados(url), Veiculo.class));
        });
        veiculos.forEach(System.out::println);

         leitor.close();
    }

    private Object getAutomovelConsulta(int opcao) {
        return switch (opcao) {
            case 1 -> CARROS;
            case 2 -> MOTOS;
            case 3 -> CAMINHOES;
            default ->
                    throw new IllegalArgumentException("Nenhuma das informações inseridas é válida. Tente novamente");
        };
    }
}
