package com.library.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "borrow_records")
public class BorrowRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "borrow_date", nullable = false)
    private LocalDate borrowDate;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    private String status; // BORROWED, RETURNED, OVERDUE

    // ── Constructors ──────────────────────────────────────────
    public BorrowRecord() {}

    public BorrowRecord(Book book, Member member) {
        this.book = book;
        this.member = member;
        this.borrowDate = LocalDate.now();
        this.dueDate = LocalDate.now().plusDays(14);
        this.status = "BORROWED";
    }

    // ── Getters & Setters ─────────────────────────────────────
    public Long getId()                          { return id; }
    public void setId(Long id)                   { this.id = id; }

    public Book getBook()                        { return book; }
    public void setBook(Book book)               { this.book = book; }

    public Member getMember()                    { return member; }
    public void setMember(Member member)         { this.member = member; }

    public LocalDate getBorrowDate()             { return borrowDate; }
    public void setBorrowDate(LocalDate d)       { this.borrowDate = d; }

    public LocalDate getDueDate()                { return dueDate; }
    public void setDueDate(LocalDate d)          { this.dueDate = d; }

    public LocalDate getReturnDate()             { return returnDate; }
    public void setReturnDate(LocalDate d)       { this.returnDate = d; }

    public String getStatus()                    { return status; }
    public void setStatus(String status)         { this.status = status; }

    public boolean isOverdue() {
        return "BORROWED".equals(status) && LocalDate.now().isAfter(dueDate);
    }
}
