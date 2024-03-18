package com.escom.TrabajoTerminal.Equipo;

import com.escom.TrabajoTerminal.Envio.Envio;
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
@Table(name = "equipo")
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long idEquipo;

    @Column(name = "nombre")
    private String nombre;

    @ManyToMany(mappedBy = "equipos")
    private Set<Usuario> alumnos = new LinkedHashSet<>();

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "practica_id_practica")
    private Practica practica;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "envio_id_envio")
    private Envio envio;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Equipo equipo = (Equipo) o;
        return getIdEquipo() != null && Objects.equals(getIdEquipo(), equipo.getIdEquipo());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
