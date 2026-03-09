package com.example.SpringAlura.dto;

import java.util.List;

public class DadosResultado {
    private List<DadosLivro> results;

    public List<DadosLivro> getResults() { return results; }
    public void setResults(List<DadosLivro> results) { this.results = results; }
}