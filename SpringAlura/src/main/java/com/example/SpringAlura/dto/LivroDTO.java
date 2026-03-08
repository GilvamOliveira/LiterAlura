package com.example.SpringAlura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LivroDTO {

    private String titulo;

    @JsonAlias("authors")
    private List<AutorDTO> autores;

    @JsonAlias("languages")
    private List<String> idiomas;

    private Double downloads;

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public List<AutorDTO> getAutores() { return autores; }
    public void setAutores(List<AutorDTO> autores) { this.autores = autores; }
    public List<String> getIdiomas() { return idiomas; }
    public void setIdiomas(List<String> idiomas) { this.idiomas = idiomas; }
    public Double getDownloads() { return downloads; }
    public void setDownloads(Double downloads) { this.downloads = downloads; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AutorDTO {
        private String nome;
        private Integer anoNascimento;
        private Integer anoFalecimento;

        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }
        public Integer getAnoNascimento() { return anoNascimento; }
        public void setAnoNascimento(Integer anoNascimento) { this.anoNascimento = anoNascimento; }
        public Integer getAnoFalecimento() { return anoFalecimento; }
        public void setAnoFalecimento(Integer anoFalecimento) { this.anoFalecimento = anoFalecimento; }
    }
}