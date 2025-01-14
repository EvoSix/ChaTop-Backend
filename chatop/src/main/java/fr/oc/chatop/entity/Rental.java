package fr.oc.chatop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="rentals")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long surface;
    private Double price;
    private String picture;
    @Column(length = 2000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    private String created_at;
    private String updated_at;




}