package org.example;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.*;
        import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Pessoa> data = importJson();
        if (data == null) return;

        System.out.println(searchJson(data, 2));
        System.out.println(listJsonCrescentWithCPF(data));
        System.out.println(jsonListGreaterThanFifty(data));
        System.out.println(jsonListDocuments(data));
        System.out.println(jsonListWithoutCPF(data));
    }

    // Importa o JSON
    private static List<Pessoa> importJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File("src/main/resources/data.json"), new TypeReference<List<Pessoa>>() {});
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
            return null;
        }
    }

    // Busca por ID
    public static Pessoa searchJson(List<Pessoa> pessoas, int id) {
        return pessoas.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Lista ordenada por idade (ascendente) com CPF
    public static List<Pessoa> listJsonCrescentWithCPF(List<Pessoa> pessoas) {
        return pessoas.stream()
                .filter(p -> p.getDocumentos().stream().anyMatch(d -> "CPF".equals(d.getTipo())))
                .sorted(Comparator.comparingInt(Pessoa::getIdade))
                .collect(Collectors.toList());
    }

    // Lista de pessoas com idade >= 50
    public static List<Pessoa> jsonListGreaterThanFifty(List<Pessoa> pessoas) {
        return pessoas.stream()
                .filter(p -> p.getIdade() >= 50)
                .collect(Collectors.toList());
    }

    // Lista de documentos únicos
    public static List<String> jsonListDocuments(List<Pessoa> pessoas) {
        return pessoas.stream()
                .flatMap(p -> p.getDocumentos().stream())
                .map(Documentos::getTipo)
                .distinct()
                .collect(Collectors.toList());
    }

    // Lista de pessoas sem CPF
    public static List<Pessoa> jsonListWithoutCPF(List<Pessoa> pessoas) {
        return pessoas.stream()
                .filter(p -> p.getDocumentos().stream().noneMatch(d -> "CPF".equals(d.getTipo())))
                .collect(Collectors.toList());
    }
}