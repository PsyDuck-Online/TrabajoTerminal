package com.escom.TrabajoTerminal.Carpeta;

import com.escom.TrabajoTerminal.Archivo.Archivo;
import com.escom.TrabajoTerminal.Envio.Envio;
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
@Table(name = "carpeta")
public class Carpeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carpeta", nullable = false)
    private Long idCarpeta;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "envio_id_envio")
    private Envio envio;

    @OneToMany(mappedBy = "carpeta", orphanRemoval = true)
    private Set<Archivo> archivos = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "carpeta_padre_id_carpeta")
    private Carpeta carpetaPadre;

    @OneToMany(mappedBy = "carpetaPadre", orphanRemoval = true)
    private Set<Carpeta> subCarpetas = new LinkedHashSet<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Carpeta carpeta = (Carpeta) o;
        return getIdCarpeta() != null && Objects.equals(getIdCarpeta(), carpeta.getIdCarpeta());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
