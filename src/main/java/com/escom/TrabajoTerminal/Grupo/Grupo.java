package com.escom.TrabajoTerminal.Grupo;

import com.escom.TrabajoTerminal.Practica.Practica;
import com.escom.TrabajoTerminal.Usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "grupo")
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGrupo;

    @Column(name = "nombre_grupo", nullable = false)
    private String nombreGrupo;


    @ManyToMany(mappedBy = "grupos")
    private Set<Usuario> alumnos = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "profesor_id_usuario")
    private Usuario profesor;

    @OneToMany(mappedBy = "grupo", orphanRemoval = true)
    private Set<Practica> practicas = new LinkedHashSet<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Grupo grupo = (Grupo) o;
        return getIdGrupo() != null && Objects.equals(getIdGrupo(), grupo.getIdGrupo());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
