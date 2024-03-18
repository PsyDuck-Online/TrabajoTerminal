package com.escom.TrabajoTerminal.Envio;

import com.escom.TrabajoTerminal.Carpeta.Carpeta;
import com.escom.TrabajoTerminal.Equipo.Equipo;
import com.escom.TrabajoTerminal.Practica.Practica;
import com.escom.TrabajoTerminal.Usuario.Usuario;
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
@Entity
@Table(name = "Envio")
public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_envio", nullable = false)
    private Long idEnvio;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_hora_envio")
    private Date fechaHoraEnvio;

    @Column(name = "calificacion")
    private Integer calificacion;

    @Column(name = "comentarios")
    private String comentarios;

    @ManyToOne
    @JoinColumn(name = "alumno_id_usuario")
    private Usuario alumno;

    @OneToOne(mappedBy = "envio", orphanRemoval = true)
    private Equipo equipo;

    @ManyToOne
    @JoinColumn(name = "practica_id_practica")
    private Practica practica;

    @OneToMany(mappedBy = "envio", orphanRemoval = true)
    private Set<Carpeta> carpetas = new LinkedHashSet<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Envio envio = (Envio) o;
        return getIdEnvio() != null && Objects.equals(getIdEnvio(), envio.getIdEnvio());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
