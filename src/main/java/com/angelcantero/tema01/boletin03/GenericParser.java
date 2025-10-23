package com.angelcantero.tema01.boletin03;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de utilidad para analizar (parsear) archivos XML y JSON y convertirlos en objetos Java.
 * Proporciona métodos estáticos para manejar diferentes tipos de archivos de datos.
 */
public class GenericParser {

    /**
     * Analiza el archivo `empleados.xml` y lo convierte en un array de objetos {@link Empleado}.
     *
     * @return Un array de {@link Empleado} que contiene los datos de los empleados parseados.
     * @throws IOException Si ocurre un error de entrada/salida durante la lectura del archivo.
     * @throws ParserConfigurationException Si se detecta un error de configuración grave en el parser XML.
     * @throws SAXException Si se produce un error durante el análisis del documento XML.
     */
    public static Empleado[] parseEmpleados() throws IOException, ParserConfigurationException, SAXException {
        Empleado[] empleados = null;

        try (InputStream fis = GenericParser.class.getResourceAsStream("/empleados.xml")) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(fis);
            dom.getDocumentElement().normalize();

            NodeList nodeList = dom.getElementsByTagName("empleado");
            empleados = new Empleado[nodeList.getLength()];

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element item = (Element) nodeList.item(i);

                String id = item.getAttribute("id");
                String nombre = item.getElementsByTagName("nombre").item(0).getTextContent();
                String departamento = item.getElementsByTagName("departamento").item(0).getTextContent();

                Element salarioElem = (Element) item.getElementsByTagName("salario").item(0);
                double salario = Double.parseDouble(salarioElem.getTextContent());
                String moneda = salarioElem.getAttribute("moneda");

                String fechaAlta = item.getElementsByTagName("fechaAlta").item(0).getTextContent();

                empleados[i] = new Empleado(id, nombre, departamento, salario, moneda, fechaAlta);
            }
        }

        return empleados;
    }

    /**
     * Analiza el archivo `biblioteca.xml` y lo convierte en un array de objetos {@link Libro}.
     *
     * @return Un array de {@link Libro} que contiene los datos de los libros parseados.
     * @throws IOException Si ocurre un error de entrada/salida durante la lectura del archivo.
     * @throws ParserConfigurationException Si se detecta un error de configuración grave en el parser XML.
     * @throws SAXException Si se produce un error durante el análisis del documento XML.
     */
    public static Libro[] parseLibro() throws IOException, ParserConfigurationException, SAXException {
        Libro[] libros = null;

        try (InputStream fis = GenericParser.class.getResourceAsStream("/biblioteca.xml")) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(fis);
            dom.getDocumentElement().normalize();

            NodeList nodeList = dom.getElementsByTagName("libro");
            libros = new Libro[nodeList.getLength()];

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element item = (Element) nodeList.item(i);
                Autor[] autores = null;
                Genero[] generos = null;
                Prestamo[] prestamos = null;
                boolean disp = false;

                String isbn = item.getAttribute("isbn");

                String titulo = item.getElementsByTagName("titulo").item(0).getTextContent();

                NodeList aut = item.getElementsByTagName("autor");
                autores = new Autor[aut.getLength()];
                for (int j = 0; j < aut.getLength(); j++) {
                    Element autor = (Element) aut.item(j);
                    String nombre = autor.getElementsByTagName("nombre").item(0).getTextContent();
                    String fechaDeNacimiento = autor.getElementsByTagName("nacimiento").item(0).getTextContent();
                    autores[j] = new Autor(nombre, fechaDeNacimiento);

                }

                String anio = item.getElementsByTagName("anio").item(0).getTextContent();

                NodeList gen = item.getElementsByTagName("generos");
                generos = new Genero[gen.getLength()];

                if (gen.getLength() > 0) {
                    Element elementoGeneros = (Element) gen.item(0);


                    NodeList listaHijosGenero = elementoGeneros.getElementsByTagName("genero");


                    generos = new Genero[listaHijosGenero.getLength()];


                    for (int k = 0; k < listaHijosGenero.getLength(); k++) {
                        Element generoIndividual = (Element) listaHijosGenero.item(k);


                        String nombre = generoIndividual.getTextContent();
                        generos[k] = new Genero(nombre);
                    }
                } else {
                    generos = new Genero[0];
                }
                if (item.getElementsByTagName("disponible").item(0).getTextContent().equals("true")) {
                    disp = true;
                }


                NodeList prest = item.getElementsByTagName("prestamos");
                prestamos = new Prestamo[prest.getLength()];

                for (int l = 0; l < prest.getLength(); l++) {
                    Element prestamo = (Element) prest.item(l);
                    String idUsuario = prestamo.getElementsByTagName("usuario").item(0).getTextContent();
                    String fecha = prestamo.getElementsByTagName("fecha").item(0).getTextContent();
                    prestamos[l] = new Prestamo(idUsuario, fecha);
                }
                libros[i] = new Libro(prestamos, isbn, titulo, autores[0], anio, generos, disp);
            }
        }

        return libros;
    }

    /**
     * Analiza el archivo `pedidos.xml` y lo convierte en un array de objetos {@link Pedido}.
     *
     * @return Un array de {@link Pedido} que contiene los datos de los pedidos parseados.
     * @throws IOException Si ocurre un error de entrada/salida durante la lectura del archivo.
     * @throws ParserConfigurationException Si se detecta un error de configuración grave en el parser XML.
     * @throws SAXException Si se produce un error durante el análisis del documento XML.
     */
    public static Pedido[] parsePedidos() throws IOException, ParserConfigurationException, SAXException {
        Pedido[] pedidos = null;
        try (InputStream fis = GenericParser.class.getResourceAsStream("/pedidos.xml")) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(fis);
            dom.getDocumentElement().normalize();

            NodeList nodeList = dom.getElementsByTagName("pedido");
            pedidos = new Pedido[nodeList.getLength()];
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element item = (Element) nodeList.item(i);
                Cliente cliente = null;
                ItemPedido[] items = null;

                String id = item.getAttribute("id");

                String fecha = item.getElementsByTagName("fecha").item(0).getTextContent();

                NodeList cli = item.getElementsByTagName("cliente");

                if (cli.getLength() > 0) {
                    Element clie = (Element) cli.item(0);
                    String nombre = clie.getElementsByTagName("nombre").item(0).getTextContent();
                    String fechaDeNacimiento = clie.getElementsByTagName("email").item(0).getTextContent();
                    cliente = new Cliente(nombre, fechaDeNacimiento);
                }

                NodeList itemsContainerList = item.getElementsByTagName("items");

                if (itemsContainerList.getLength() > 0) {

                    Element itemsContainer = (Element) itemsContainerList.item(0);


                    NodeList itemsList = itemsContainer.getElementsByTagName("item");


                    items = new ItemPedido[itemsList.getLength()];

                    for (int j = 0; j < itemsList.getLength(); j++) {

                        Element itemIndividual = (Element) itemsList.item(j);

                        String codigo = itemIndividual.getAttribute("sku");
                        String descripcion = itemIndividual.getElementsByTagName("descripcion").item(0).getTextContent();
                        int cantidad = Integer.parseInt(itemIndividual.getElementsByTagName("cantidad").item(0).getTextContent());

                        Element pUnitario = (Element) itemIndividual.getElementsByTagName("precioUnitario").item(0);

                        double precioUnitario = Double.parseDouble(pUnitario.getTextContent());
                        String moneda = pUnitario.getAttribute("moneda");

                        items[j] = new ItemPedido(codigo, descripcion, cantidad, precioUnitario, moneda);
                    }
                } else {
                    items = new ItemPedido[0]; 
                }

                Element totalElem = (Element) item.getElementsByTagName("total").item(0);
                double total = Double.parseDouble(totalElem.getTextContent());
                String monedaTotal = totalElem.getAttribute("moneda");
                pedidos[i] = new Pedido(id, cliente, fecha, items, total, monedaTotal);



            }
        }
        return pedidos;
    }

    /**
     * Analiza el archivo `alumnos.json` y lo convierte en un array de objetos {@link Alumno}.
     *
     * @return Un array de {@link Alumno} que contiene los datos de los alumnos parseados.
     * @throws IOException Si ocurre un error de entrada/salida durante la lectura del archivo.
     */
    public Alumno[] parseAlumnos() throws IOException {
        Alumno[] alumnos = null;
        Nota[] notas = null;
        StringBuilder sb = new StringBuilder();

        try (InputStream is = getClass().getResourceAsStream("/alumnos.json");
             BufferedReader br = new BufferedReader(new InputStreamReader(is,
                     StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            JSONTokener tokener = new JSONTokener(sb.toString());
            JSONArray jsonArray = new JSONArray(tokener);
            alumnos = new Alumno[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String nombre = jsonObject.getString("nombre");
                boolean matriculado = jsonObject.getBoolean("matriculado");
                LocalDate fechaNacimiento = LocalDate.parse(jsonObject.getString("fechaNacimiento"));
                JSONArray notasArray = jsonObject.getJSONArray("notas");
                notas = new Nota[notasArray.length()];
                for (int j = 0; j < notasArray.length(); j++) {
                    JSONObject notaObject = notasArray.getJSONObject(j);
                    String asignatura = notaObject.getString("asignatura");
                    double nota = notaObject.getDouble("nota");
                    notas[j] = new Nota(asignatura, nota);
                }
                alumnos[i] = new Alumno(id, nombre, matriculado, fechaNacimiento, notas);
            }
        }
        return alumnos;
    }

    /**
     * Analiza el archivo `inventario.json` y lo convierte en un objeto {@link Inventario}.
     *
     * @return Un objeto {@link Inventario} que contiene los datos del inventario parseado.
     * @throws IOException Si ocurre un error de entrada/salida durante la lectura del archivo.
     */
    public Inventario parseInventario() throws IOException{
        Inventario inventario = null;
        StringBuilder sb = new StringBuilder();
        try (InputStream is = getClass().getResourceAsStream("/inventario.json");
             BufferedReader br = new BufferedReader(new InputStreamReader(is,
                     StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            JSONTokener tokener = new JSONTokener(sb.toString());
            JSONObject jsonObject = new JSONObject(tokener);

            String almacen = jsonObject.getString("almacen");
            String actualizado = jsonObject.getString("actualizado");
            JSONArray listaProductos=jsonObject.getJSONArray("productos");
            Producto[] productos=new Producto[listaProductos.length()];
            for (int i = 0; i < listaProductos.length(); i++) {
                JSONObject jsonObject1 = listaProductos.getJSONObject(i);
                String id = jsonObject1.getString("id");
                String nombre= jsonObject1.getString("nombre");
                int stock=jsonObject1.getInt("stock");
                double precio=jsonObject1.getDouble("precio");
                JSONArray listaTags=jsonObject1.getJSONArray("tags");
                List<String> tags = new ArrayList<>();
                for (int j = 0; j < listaTags.length(); j++) {
                    tags.add(listaTags.getString(j));

                }
                JSONObject ubi = jsonObject1.getJSONObject("ubicacion");
                int pasillo = ubi.getInt("pasillo");
                String estante = ubi.getString("estante");
                Ubicacion ubicacion=new Ubicacion(pasillo,estante);
                productos[i]=new Producto(id,nombre,stock,precio,tags,ubicacion);
                inventario=new Inventario(almacen,actualizado,productos);

            }



        }
        return inventario;
    }

    /**
     * Analiza el archivo `peliculas.json` y lo convierte en un objeto {@link Videoteca}.
     *
     * @return Un objeto {@link Videoteca} que contiene la colección de películas parseadas.
     * @throws IOException Si ocurre un error de entrada/salida durante la lectura del archivo.
     */
    public Videoteca parseVideoteca() throws IOException {
        Videoteca videoteca = null;
        StringBuilder sb = new StringBuilder();
        try (InputStream is = getClass().getResourceAsStream("/peliculas.json");
             BufferedReader br = new BufferedReader(new InputStreamReader(is,
                     StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            JSONTokener tokener = new JSONTokener(sb.toString());
            JSONObject jsonObject = new JSONObject(tokener);

            JSONArray peliculasArrayJson = jsonObject.getJSONArray("peliculas");
            Pelicula[] peliculas = new Pelicula[peliculasArrayJson.length()];

            for (int i = 0; i < peliculasArrayJson.length(); i++) {
                JSONObject peliculaJson = peliculasArrayJson.getJSONObject(i);

                String id = peliculaJson.getString("id");
                String titulo = peliculaJson.getString("titulo");
                String director = peliculaJson.getString("director");
                int estreno = peliculaJson.getInt("estreno");
                int duracionMin = peliculaJson.getInt("duracionMin");

                JSONArray generosArrayJson = peliculaJson.getJSONArray("generos");
                Genero[] generos = new Genero[generosArrayJson.length()];
                for (int j = 0; j < generosArrayJson.length(); j++) {
                    String ngenero = generosArrayJson.getString(j);
                    generos[j] = new Genero(ngenero);
                }

                JSONObject puntuacionesJson = peliculaJson.getJSONObject("puntuaciones");
                double imdb = puntuacionesJson.getDouble("imdb");
                int rt = puntuacionesJson.getInt("rt");
                Puntuaciones puntuaciones = new Puntuaciones(imdb, rt);

                peliculas[i]=new Pelicula(id, titulo, director, estreno, duracionMin, generos, puntuaciones);
            }
            videoteca = new Videoteca(peliculas);
        }
        return videoteca;
    }
}
