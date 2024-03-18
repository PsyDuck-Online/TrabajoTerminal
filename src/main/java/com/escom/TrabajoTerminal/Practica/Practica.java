package com.escom.TrabajoTerminal.Practica;

import com.escom.TrabajoTerminal.Envio.Envio;
import com.escom.TrabajoTerminal.Equipo.Equipo;
import com.escom.TrabajoTerminal.Grupo.Grupo;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "practica")
public class Practica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPractica;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_entrega")
    private Date fechaEntrega;

    @Column(name = "url_demo")
    private String urlDemo;

    @OneToMany(mappedBy = "practica", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Equipo> equipos = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "grupo_id_grupo")
    private Grupo grupo;

    @ToString.Exclude
    @OneToMany(mappedBy = "practica", orphanRemoval = true)
    private Set<Envio> envios = new LinkedHashSet<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Practica practica = (Practica) o;
        return getIdPractica() != null && Objects.equals(getIdPractica(), practica.getIdPractica());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
