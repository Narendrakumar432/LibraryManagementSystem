package com.library.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(unique = true, nullable = false)
    private String isbn;

    private String genre;

    @Column(name = "total_copies")
    private int totalCopies;

    @Column(name = "available_copies")
    private int availableCopies;

    // ── Constructors ──────────────────────────────────────────
    public Book() {}

    public Book(String title, String author, String isbn, String genre, int totalCopies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    // ── Getters & Setters ─────────────────────────────────────
    public Long getId()             
    { 
    	return id; 
    }
    public void setId(Long id)       
    {
    	this.id = id; 
    }

    public String getTitle()                   { return title; }
    public void setTitle(String title)         { this.title = title; }

    public String getAuthor()                  { return author; }
    public void setAuthor(String author)       { this.author = author; }

    public String getIsbn()                    { return isbn; }
    public void setIsbn(String isbn)           { this.isbn = isbn; }

    public String getGenre()                   { return genre; }
    public void setGenre(String genre)         { this.genre = genre; }

    public int getTotalCopies()                { return totalCopies; }
    public void setTotalCopies(int totalCopies){ this.totalCopies = totalCopies; }

    public int getAvailableCopies()                        { return availableCopies; }
    public void setAvailableCopies(int availableCopies)    { this.availableCopies = availableCopies; }
}
