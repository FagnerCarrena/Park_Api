package com.FCarrena.demoparkapi.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name="usuarios")
@EntityListeners(AuditingEntityListener.class)
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "username", nullable = false, unique = true, length = 100 )
    private String username;
    @Column(name = "password", nullable = false, length = 200)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 25)
    private Role role = Role.ROLE_CLIENT;
    @Column(name = "data_criacao") @CreatedDate
    private LocalDateTime dataCriacao;
    @Column(name = "data_modificacao") @LastModifiedDate
    private LocalDateTime dataModificacao;
    @Column(name = "criado_por") @CreatedBy
    private String criadoPor;
    @Column(name = "modificado_por") @LastModifiedBy
    private String modificadoPor;

    public enum Role{
ROLE_ADMIN, ROLE_CLIENT

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                '}';
    }
}