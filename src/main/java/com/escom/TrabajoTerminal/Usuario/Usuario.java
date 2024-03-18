package com.escom.TrabajoTerminal.Usuario;

import com.escom.TrabajoTerminal.Envio.Envio;
import com.escom.TrabajoTerminal.Equipo.Equipo;
import com.escom.TrabajoTerminal.Grupo.Grupo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "correoElectronico")
    private String correoElectronico;

    @Column(name = "tipoUsuario")
    private Integer tipoUsuario;

    @Column(name = "contraseña")
    private String contraseña;

    @ManyToMany
    @JoinTable(name = "inscripcion",
            joinColumns = @JoinColumn(name = "usuario_id_alumno"),
            inverseJoinColumns = @JoinColumn(name = "grupos_id_grupo"))
    private Set<Grupo> grupos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "profesor", orphanRemoval = true)
    private Set<Grupo> gruposImparte = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "miembro_equipo",
            joinColumns = @JoinColumn(name = "usuario_id_alumno"),
            inverseJoinColumns = @JoinColumn(name = "equipos_id_equipo"))
    private Set<Equipo> equipos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "alumno", orphanRemoval = true)
    private Set<Envio> envios = new LinkedHashSet<>();

}
