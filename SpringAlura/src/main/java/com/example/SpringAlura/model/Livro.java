package com.example.SpringAlura.model;

import jakarta.persistence.*;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String idioma;
    private Double downloads;

    @ManyToOne
    private Autor autor;

    @Override
    public String toString() {
        return titulo + " - " + idioma + " - downloads: " + downloads + " - Autor: " + autor.getNome();
    }

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }
    public Double getDownloads() { return downloads; }
    public void setDownloads(Double downloads) { this.downloads = downloads; }
    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }
}