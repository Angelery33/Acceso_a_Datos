package com.angelcantero.tema02.conexionbd;

import java.sql.*;

public class ConexionBD {

    private static final String DB_DRIVER = "postgresql";
    private static final String DB_HOST = "localhost";
    private static final int DB_PORT = 5433;
    private static final String DB_NAME = "Ciclismo";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "test";

    private final String url;
    private final String user;
    private final String password;

    public ConexionBD() {
        this.url = "jdbc:" + DB_DRIVER + "://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
        this.user = DB_USERNAME;
        this.password = DB_PASSWORD;
    }

    public ConexionBD(Driver driver, String host, int port, String database, String user, String password) {
        this.url = "jdbc:" + driver + "://" + host + ":" + port + "/" + database;
        this.user = user;
        this.password = password;
    }

    public Connection GetConnexion() throws SQLException {
        try (Connection conexion = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD)) {

            return conexion;

        } catch (SQLException e) {
            throw new SQLException(e);
        }

    }


    public ResultSet EjecutarConsultaTabla(String tabla) throws SQLException {
        String sql = "SELECT * FROM " + tabla;
        try (Connection conexion = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD)) {
            PreparedStatement pst = conexion.prepareStatement(sql);
            return pst.executeQuery();

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public ResultSet EjecutarConsultaEj2(int id) throws SQLException {
        String sql;
        if (id == 0) {
            sql = "SELECT *\n" + "FROM ciclistas\n" + "ORDER BY id_equipo;";
        } else {
            sql = "SELECT * FROM ciclistas WHERE id_equipo =" + id;
        }
        try (Connection conexion = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD)) {
            PreparedStatement pst = conexion.prepareStatement(sql);
            return pst.executeQuery();

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public ResultSet EjecutarConsultaEj3() throws SQLException {
        String sql = "SELECT tipo, COUNT(*) AS cantidad_etapas, SUM(distancia_km) AS total_kilometros FROM etapas GROUP BY " + "tipo";

        try (Connection conexion = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD)) {
            PreparedStatement pst = conexion.prepareStatement(sql);
            return pst.executeQuery();

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public ResultSet EjecutarConsultaEj4(int id) throws SQLException {
        String sql;

        sql = """
                       SELECT c.id_ciclista,
                       c.nombre,
                       ROUND(SUM(e.distancia_km) / NULLIF(SUM(EXTRACT(EPOCH FROM r.tiempo)) / 3600.0, 0), 2) AS velocidad_media
                FROM resultados_etapa r
                JOIN etapas e    ON e.id_etapa = r.id_etapa
                JOIN ciclistas c ON c.id_ciclista = r.id_ciclista
                WHERE r.id_ciclista = ?
                  AND r.estado = 'FINALIZADO'
                  AND r.tiempo IS NOT NULL
                GROUP BY c.id_ciclista, c.nombre
                """;


        try (Connection conexion = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD)) {
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, id);
            return pst.executeQuery();

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    public ResultSet EjecutarConsultaEj5(int id) throws SQLException {
        String sql;

        sql = """
                       SELECT
                         ROW_NUMBER() OVER (ORDER BY r.tiempo ASC) AS posicion,
                         c.nombre       AS ciclista,
                         e.nombre       AS equipo,
                         r.tiempo
                       FROM resultados_etapa r
                       JOIN ciclistas c ON c.id_ciclista = r.id_ciclista
                       JOIN equipos   e ON e.id_equipo   = c.id_equipo
                       WHERE r.id_etapa = ?
                         AND r.estado = 'FINALIZADO'
                       ORDER BY r.tiempo ASC;
                """;


        try (Connection conexion = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD)) {
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, id); // 'id' es tu parámetro
            return pst.executeQuery();

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public ResultSet EjecutarConsultaEj6()throws SQLException{
        String sql;
        sql = """
SELECT DENSE_RANK() OVER (ORDER BY SUM(rp.puntos) DESC) AS posicion,
       c.nombre  AS ciclista,
       e.nombre  AS equipo,
       SUM(rp.puntos) AS puntos_totales
FROM resultados_puerto rp
JOIN ciclistas c ON c.id_ciclista = rp.id_ciclista
JOIN equipos   e ON e.id_equipo   = c.id_equipo
GROUP BY c.id_ciclista, c.nombre, e.nombre
ORDER BY puntos_totales DESC, ciclista ASC
""";
        try (Connection conexion = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD)) {
            PreparedStatement pst = conexion.prepareStatement(sql);
            return pst.executeQuery();

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    public ResultSet EjecutarConsultaEj7( )throws SQLException{
        String sql=
        """ 
  WITH puntos AS (
  SELECT id_ciclista, puntos FROM puntos_meta
  UNION ALL
  SELECT id_ciclista, puntos FROM resultados_sprint
)
SELECT
  DENSE_RANK() OVER (ORDER BY SUM(p.puntos) DESC) AS posicion,
  c.nombre  AS ciclista,
  e.nombre  AS equipo,
  SUM(p.puntos) AS puntos_totales
FROM puntos p
JOIN ciclistas c ON c.id_ciclista = p.id_ciclista
JOIN equipos   e ON e.id_equipo   = c.id_equipo
GROUP BY c.id_ciclista, c.nombre, e.nombre
ORDER BY puntos_totales DESC, ciclista ASC;
                """ ;
        try (Connection conexion = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD)) {
            PreparedStatement pst = conexion.prepareStatement(sql);

            return pst.executeQuery();

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    public ResultSet EjecutarConsultaEj8( )throws SQLException{
        String sql= """
                SELECT
                  DENSE_RANK() OVER (ORDER BY SUM(r.tiempo)) AS posicion,
                  c.nombre                                   AS ciclista,
                  e.nombre                                   AS equipo,
                  SUM(r.tiempo)                              AS tiempo_total
                FROM resultados_etapa r
                JOIN ciclistas c ON c.id_ciclista = r.id_ciclista
                JOIN equipos   e ON e.id_equipo   = c.id_equipo
                WHERE r.estado = 'FINALIZADO'
                  AND r.tiempo IS NOT NULL
                GROUP BY c.id_ciclista, c.nombre, e.nombre
                ORDER BY tiempo_total ASC, ciclista ASC;
                """;
        try (Connection conexion = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD)) {
            PreparedStatement pst = conexion.prepareStatement(sql);
            return pst.executeQuery();

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    public ResultSet EjecutarConsultaEj9( )throws SQLException{
        String sql= """
                WITH tiempos AS (
                  SELECT
                    r.id_etapa,
                    c.id_equipo,
                    e.nombre AS equipo,
                    r.id_ciclista,
                    r.tiempo,
                    ROW_NUMBER() OVER (
                      PARTITION BY r.id_etapa, c.id_equipo
                      ORDER BY r.tiempo ASC
                    ) AS rn
                  FROM resultados_etapa r
                  JOIN ciclistas c ON c.id_ciclista = r.id_ciclista
                  JOIN equipos   e ON e.id_equipo   = c.id_equipo
                  WHERE r.estado = 'FINALIZADO'
                    AND r.tiempo IS NOT NULL
                ),
                top3 AS (
                  SELECT id_etapa, id_equipo, equipo, tiempo
                  FROM tiempos
                  WHERE rn <= 3
                )
                SELECT
                  DENSE_RANK() OVER (ORDER BY SUM(tiempo)) AS posicion,
                  equipo,
                  SUM(tiempo)                              AS tiempo_total
                FROM top3
                GROUP BY id_equipo, equipo
                ORDER BY tiempo_total ASC, equipo ASC;
                """;
        try (Connection conexion = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD)) {
            PreparedStatement pst = conexion.prepareStatement(sql);

            return pst.executeQuery();

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public enum Driver {
        MYSQL, POSTGRESQL;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

}
