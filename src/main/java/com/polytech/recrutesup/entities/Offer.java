package com.polytech.recrutesup.entities;

import com.polytech.recrutesup.entities.reference.EWorkflowState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Offer")
public class Offer implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "label", length = 40, nullable = false)
    private String label;

    @Column(name = "description", length = 500, nullable = true)
    private String description;

    @Column(name = "address", length = 40, nullable = false)
    private String address;

    @Column(name = "city", length = 40, nullable = false)
    private String city;

    @Column(name = "mail_address", length = 40, nullable = false)
    private String mailAddress;

    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date", length = 40, nullable = false, updatable = false)
    private Date creationDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "created_by_user", referencedColumnName = "id", nullable = false)
    private User createdByUser;

    @Column(name = "state", length = 40, nullable = false)
    @Enumerated(EnumType.STRING)
    private EWorkflowState state;

    @JoinTable(name = "company_offer",
            joinColumns = @JoinColumn(name = "id_offer", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_company", referencedColumnName = "id"))
    @ManyToOne(cascade = {CascadeType.ALL})
    private Company company;

    @JoinTable(name = "offer_attachment",
            joinColumns = @JoinColumn(name = "id_offer", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_attachment", referencedColumnName = "id"))
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Attachment> attachmentList;

    @JoinTable(name = "offer_student_wish",
            joinColumns = @JoinColumn(name = "id_offer", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_student_wish", referencedColumnName = "id"))
    @OneToMany(cascade = {CascadeType.ALL})
    private List<StudentWish> wishReceivedList;
}
