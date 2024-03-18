package com.escom.TrabajoTerminal.Archivo;

import com.escom.TrabajoTerminal.Carpeta.Carpeta;
import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "archivo")
public class Archivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_archivo", nullable = false)
    private Long idArchivo;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "carpeta_id_carpeta")
    private Carpeta carpeta;

}
