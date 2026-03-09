package com.example.SpringAlura.dto;

import java.util.List;

public class DadosLivro {
    private String title;
    private List<DadosAutor> authors;
    private List<String> languages;
    private int download_count;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<DadosAutor> getAuthors() { return authors; }
    public void setAuthors(List<DadosAutor> authors) { this.authors = authors; }

    public List<String> getLanguages() { return languages; }
    public void setLanguages(List<String> languages) { this.languages = languages; }

    public int getDownload_count() { return download_count; }
    public void setDownload_count(int download_count) { this.download_count = download_count; }
}