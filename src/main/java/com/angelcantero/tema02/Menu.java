package com.angelcantero.tema02;

import com.angelcantero.lib.TextTable;
import com.angelcantero.tema02.conexionbd.ConexionBD;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Menu {
    private static final java.util.Scanner sc = new java.util.Scanner(System.in);
    private static ConexionBD con;

    public static void mostrarMenu() {
        ConexionBD con = new ConexionBD();
        System.out.println();
        System.out.println("--- MENÚ PRINCIPAL ---");
        System.out.println("1. Listar equipos");
        System.out.println("2. Ciclistas por equipos");
        System.out.println("3. Listar Etapas");
        System.out.println("4. Velocidad media de un ciclista");
        System.out.println("5. “Clasificación de etapa");
        System.out.println("6. Clasificación de la montaña");
        System.out.println("7. Clasificación de la regularidad");
        System.out.println("8. Clasificación general");
        System.out.println("9. Clasificación por equipos");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
        int opcion = sc.nextInt();
        sc.nextLine();
        System.out.println();
        switch (opcion) {
            case 1:
                ejercicio1(con);
                break;
            case 2:
                ejercicio1(con);
                ejercicio2(con);

                break;
            case 3:
                ejercicio3(con);
                ejercicio31(con);
                break;
            case 4:
                ejercicio4(con);
                break;
            case 5:
                ejercicio5(con);
                break;
            case 6:
                ejercicio6(con);
                break;
            case 7:
                ejercicio7(con);
                break;
            case 8:
                ejercicio8(con);
                break;
            case 9:
                ejercicio9(con);
                break;


            case 0:
                System.out.println("¡Hasta luego!");
                System.exit(0);
                break;
        }
    }




    private static void ejercicio1(ConexionBD con) {
        String sql1 = "equipos";
        try {
            ResultSet rs = con.EjecutarConsultaTabla(sql1);
            TextTable tt = new TextTable("ID del equipo", "Nombre del equipo", "Pais");
            while (rs.next()) {
                tt.addRow(rs.getString("id_equipo"), rs.getString("nombre"), rs.getString("pais"));
            }
            System.out.println(tt);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void ejercicio2(ConexionBD con) {
        int idequipo;
        while (true) {
            System.out.println("Introduzca el id del equipo para consultar sus ciclistas(0 para listar todos los " +
                    "equipos:");
            idequipo = sc.nextInt();
            sc.nextLine();
            try {
                ResultSet rs = con.EjecutarConsultaEj2(idequipo);
                TextTable tt = new TextTable("ID del equipo","Id del ciclista", "Nombre del ciclista", "Pais");
                while (rs.next()) {
                    tt.addRow(rs.getString("id_equipo"),rs.getString("id_ciclista"),  rs.getString("nombre"),
                            rs.getString("pais"));
                }
                System.out.println(tt);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            finally {
                break;
            }
        }
    }
    private static void ejercicio3(ConexionBD con) {
        String sql1 = "etapas";
        try {
            ResultSet rs = con.EjecutarConsultaTabla(sql1);
            TextTable tt = new TextTable("ID de la etapa", "Tipo", "Fecha", "Salida", "Llegada", "Km");
            while (rs.next()) {
                tt.addRow(rs.getString("id_etapa"), rs.getString("tipo"), rs.getString("fecha"), rs.getString("salida"),
                        rs.getString("llegada"), rs.getString("distancia_km"));
            }
            System.out.println(tt);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void ejercicio31(ConexionBD con) {
        try {
            ResultSet rs = con.EjecutarConsultaEj3();
            TextTable tt = new TextTable("Tipo de etapa", "Cantidad de etapas", "Kilometros totales");
            while (rs.next()) {
                tt.addRow(rs.getString("tipo"), rs.getString("cantidad_etapas"), rs.getString("total_kilometros"));
            }
            System.out.println(tt);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void ejercicio4(ConexionBD con) {
        ejercicio1(con);
        ejercicio2(con);

        int idciclista;
        while (true) {
            System.out.println("Introduzca el id del ciclista para consultar su velocidad media");
            idciclista = sc.nextInt();
            sc.nextLine();
            try {
                ResultSet rs = con.EjecutarConsultaEj4(idciclista);
                TextTable tt = new TextTable("Ciclista", "Velocidad media");
                while (rs.next()) {
                    tt.addRow(rs.getString("nombre"), rs.getString("velocidad_media"));
                }
                System.out.println(tt);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            finally {
                break;
            }
        }
    }
    private static void ejercicio5(ConexionBD con) {
        ejercicio3(con);
        int idetapa;
        while (true) {
            System.out.println("Introduzca el id de la etapa para consultar clasificación de la etapa :");
            idetapa = sc.nextInt();
            sc.nextLine();
            try {
                ResultSet rs = con.EjecutarConsultaEj5(idetapa);
                TextTable tt = new TextTable("Posición", "Nombre del ciclista", "Equipo del ciclista", "Tiempo");
                while (rs.next()) {
                    tt.addRow(rs.getString("posicion"), rs.getString("ciclista"), rs.getString("equipo"),
                            rs.getString("tiempo"));
                }
                System.out.println(tt);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                break;
            }
        }

    }
    private static void ejercicio6(ConexionBD con){
         try {
                ResultSet rs = con.EjecutarConsultaEj6();
                TextTable tt = new TextTable("Posición", "Nombre del ciclista", "Equipo del ciclista", "Puntos totales");
                while (rs.next()) {
                    tt.addRow(String.valueOf(rs.getInt("posicion")),
                            rs.getString("ciclista"),
                            rs.getString("equipo"),
                            String.valueOf(rs.getInt("puntos_totales")));
                }
                System.out.println(tt);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            finally {

            }
    }
private static void ejercicio7(ConexionBD con){
         try {
                ResultSet rs = con.EjecutarConsultaEj7();
                TextTable tt = new TextTable("Posición", "Nombre del ciclista", "Equipo del ciclista", "Puntos totales");
                while (rs.next()) {
                    tt.addRow(
                            String.valueOf(rs.getInt("posicion")),
                            rs.getString("ciclista"),
                            rs.getString("equipo"),
                            String.valueOf(rs.getInt("puntos_totales"))
                    );
                }
                System.out.println(tt);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

}
private static void ejercicio8(ConexionBD con){
         try {
                ResultSet rs = con.EjecutarConsultaEj8();
                TextTable tt = new TextTable("Posición", "Nombre del ciclista", "Equipo del ciclista", "Tiempo total");
                while (rs.next()) {
                    tt.addRow(
                            String.valueOf(rs.getInt("posicion")),
                            rs.getString("ciclista"),
                            rs.getString("equipo"),
                            rs.getString("tiempo_total")
                    );
                }
                System.out.println(tt);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

}
private static void ejercicio9(ConexionBD con){
         try {
                ResultSet rs = con.EjecutarConsultaEj9();
                TextTable tt = new TextTable("Posición", "Nombre del equipo", "Tiempo total");
                while (rs.next()) {
                    tt.addRow(
                            String.valueOf(rs.getInt("posicion")),
                            rs.getString("equipo"),
                            rs.getString("tiempo_total")
                    );
                }
                System.out.println(tt);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

}
}
