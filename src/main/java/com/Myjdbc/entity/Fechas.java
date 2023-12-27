package com.Myjdbc.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Fechas {

    private int idFechas;
    private int idCustomer;
    private LocalDate dia;
    private String hora;
    private String status;

    public Fechas(String registro) {

    }
}
