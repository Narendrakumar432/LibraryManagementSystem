package com.library.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    @Column(name = "membership_type")
    private String membershipType; // STUDENT, FACULTY, PUBLIC

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BorrowRecord> borrowRecords;

    // ── Constructors ──────────────────────────────────────────
    public Member() {}

    public Member(String name, String email, String phone, String membershipType) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.membershipType = membershipType;
    }

    // ── Getters & Setters ─────────────────────────────────────
    public Long getId()                              { return id; }
    public void setId(Long id)                       { this.id = id; }

    public String getName()                          { return name; }
    public void setName(String name)                 { this.name = name; }

    public String getEmail()                         { return email; }
    public void setEmail(String email)               { this.email = email; }

    public String getPhone()                         { return phone; }
    public void setPhone(String phone)               { this.phone = phone; }

    public String getMembershipType()                { return membershipType; }
    public void setMembershipType(String type)       { this.membershipType = type; }

    public List<BorrowRecord> getBorrowRecords()             { return borrowRecords; }
    public void setBorrowRecords(List<BorrowRecord> records) { this.borrowRecords = records; }
}
