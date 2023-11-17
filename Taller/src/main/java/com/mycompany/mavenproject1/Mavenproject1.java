package com.mycompany.mavenproject1;

import static spark.Spark.*;
import com.google.gson.Gson;
import java.util.LinkedList;
import spark.Request;
import spark.Response;

/**
 *
 * @author 104623010375 Henry Fernando Mulato Llanten
 */

/* Enlaces
    http://localhost:4567/automoviles
    http://localhost:4567/agregarAutomovil/nissan/frontier/uyt2334/5/1200
    http://localhost:4567/motos
    http://localhost:4567/agregarMoto/Honda/XR/uyt2334/250/1400
    http://localhost:4567/motos
    http://localhost:4567/motos
    http://localhost:4567/registrarHoraSalidaMoto/2100/XYZ789
    http://localhost:4567/registrarHoraDeSalidaAutomovil/2000/ZYX987
    http://localhost:4567/registrarHoraDeSalidaAutomovil/1700/uyt2334
    http://localhost:4567/motosActuales
    http://localhost:4567/AutomovilesReporte
    http://localhost:4567/motosReporte
*/
public class Mavenproject1 {

    public static void main(String[] args) {
        /*
        * Esto nos sirve para generar un formato json para retornar la data del array
        * sin tener que acomodarla bonita de manera manual
         */
        Gson gson = new Gson();

        LinkedList<Automovil> automoviles = new LinkedList<>();
        LinkedList<Motocicleta> motocicletas = new LinkedList<>();
        final float valorHora = 1000;

        // Automovil creado por defecto
        Automovil auto = new Automovil(4, "Mazda", "3", "ZYX987");
        automoviles.add(auto);
        auto.setHoraDeIngreso(1300);

        Motocicleta moto = new Motocicleta(600, "Honda", "CBR600", "XYZ789");
        motocicletas.add(moto);
        moto.setHoraDeIngreso(1000);

        // Guardar motocicleta
        // endpoint GET para agregar un motocicleta
        get("/agregarMoto/:marca/:modelo/:placa/:cilindrada/:horaDeIngreso", (req, res) -> {

            // Añadimos esto para retornar en formato Json
            res.type("application/json");

            // Obtener parámetros de la URL
            String marca = req.params(":marca");
            String modelo = req.params(":modelo");
            String placa = req.params(":placa");

            // No olvidar convertir en integer los string numericos que llegan por url
            int cilindrada = Integer.parseInt(req.params(":cilindrada"));
            int horaDeIngreso = Integer.parseInt(req.params(":horaDeIngreso"));

            // Crear un nuevo automóvil y agregarlo al parqueadero
            Motocicleta nuevaMotocicleta = new Motocicleta(cilindrada, marca, modelo, placa);
            nuevaMotocicleta.setHoraDeIngreso(horaDeIngreso);
            motocicletas.add(nuevaMotocicleta);

            return gson.toJson(nuevaMotocicleta);
        });

        // Listado de motocicletas
        get("/motos", (req, res) -> {
            res.type("application/json");
            return gson.toJson(motocicletas);
        });

        // Listado de automoviles
        get("/automoviles", (req, res) -> {
            res.type("application/json");
            return gson.toJson(automoviles);
        });

        // Guardar automovil
        // endpoint GET para agregar un automóvil
        get("/agregarAutomovil/:marca/:modelo/:placa/:numeroPuertas/:horaDeIngreso", (req, res) -> {

            // Añadimos esto para retornar en formato Json
            res.type("application/json");

            // Obtener parámetros de la URL
            String marca = req.params(":marca");
            String modelo = req.params(":modelo");
            String placa = req.params(":placa");

            // No olvidar convertir en integer los string numericos que llegan por url
            int numeroPuertas = Integer.parseInt(req.params(":numeroPuertas"));
            int horaDeIngreso = Integer.parseInt(req.params(":horaDeIngreso"));

            // Crear un nuevo automóvil y agregarlo al parqueadero
            Automovil nuevoAuto = new Automovil(numeroPuertas, marca, modelo, placa);
            nuevoAuto.setHoraDeIngreso(horaDeIngreso);
            automoviles.add(nuevoAuto);

            return gson.toJson(nuevoAuto);
        });

        // Registrar hora de salida de motocicletas 
        // endpoint GET para registrar hora de salida de motocicletas
        get("/registrarHoraSalidaMoto/:horaDeSalida/:placa", (Request req, Response res) -> {

            // Añadimos esto para retornar en formato Json
            res.type("application/json");

            // Obtener parámetros de la URL
            String placa = req.params(":placa");

            // No olvidar convertir en integer los string numericos que llegan por url
            int horaDeSalida = Integer.parseInt(req.params(":horaDeSalida"));

            for (Motocicleta moto1 : motocicletas) {
                if (moto1.getPlaca().equals(placa)) {
                    moto1.setHoraDeSalida(horaDeSalida);
                }
            }

            return gson.toJson(horaDeSalida);
        });

        // Registrar hora de salida de automoviles 
        // endpoint GET para registrar hora de salida de automoviles
        get("/registrarHoraDeSalidaAutomovil/:horaDeSalida/:placa", (Request req, Response res) -> {

            // Añadimos esto para retornar en formato Json
            res.type("application/json");

            // Obtener parámetros de la URL
            String placa = req.params(":placa");

            // No olvidar convertir en integer los string numericos que llegan por url
            int horaDeSalida = Integer.parseInt(req.params(":horaDeSalida"));

            for (Automovil auto1 : automoviles) {
                if (auto1.getPlaca().equals(placa)) {
                    auto1.setHoraDeSalida(horaDeSalida);
                }
            }

            return gson.toJson(horaDeSalida);
        });

        // Registrar hora de ingreso de motocicletas 
        // endpoint GET para registrar hora de ingreso de motocicletas
        get("/registrarHoraDeIngresoMoto/:horaDeIngreso/:placa", (Request req, Response res) -> {

            // Añadimos esto para retornar en formato Json
            res.type("application/json");

            // Obtener parámetros de la URL
            String placa = req.params(":placa");

            // No olvidar convertir en integer los string numericos que llegan por url
            int horaDeIngreso = Integer.parseInt(req.params(":horaDeIngreso"));

            for (Motocicleta moto1 : motocicletas) {
                if (moto1.getPlaca().equals(placa)) {
                    moto1.setHoraDeIngreso(horaDeIngreso);
                }
            }

            return gson.toJson(horaDeIngreso);
        });

        // Registrar hora de ingreso de automoviles
        // endpoint GET para registrar hora de ingreso de automoviles
        get("/registrarHoraDeIngresoAutomovil/:horaDeIngreso/:placa", (Request req, Response res) -> {

            // Añadimos esto para retornar en formato Json
            res.type("application/json");

            // Obtener parámetros de la URL
            String placa = req.params(":placa");

            // No olvidar convertir en integer los string numericos que llegan por url
            int horaDeIngreso = Integer.parseInt(req.params(":horaDeIngreso"));

            for (Automovil auto1 : automoviles) {
                if (auto1.getPlaca().equals(placa)) {
                    auto1.setHoraDeIngreso(horaDeIngreso);
                }
            }

            return gson.toJson(horaDeIngreso);
        });

        // Verificar motos dentro del parqueadero
        // endpoint GET para verificar motos dentro del parqueadero
        get("/motosActuales", (req, res) -> {
            LinkedList<Motocicleta> motocicletasEnParqueadero = new LinkedList<>();
            // Añadimos esto para retornar en formato Json
            res.type("application/json");

            for (Motocicleta moto1 : motocicletas) {
                if (moto1.getHoraDeIngreso() != 0 && moto1.getHoraDeSalida() == 0) {
                    motocicletasEnParqueadero.add(moto1);
                }
            }

            return gson.toJson(motocicletasEnParqueadero);
        });

        // Verificar autos dentro del parqueadero
        // endpoint GET para verificar motos dentro del parqueadero
        get("/automovilesActuales", (req, res) -> {
            LinkedList<Automovil> automovilesEnParqueadero = new LinkedList<>();
            // Añadimos esto para retornar en formato Json
            res.type("application/json");

            for (Automovil auto1 : automoviles) {
                if (auto1.getHoraDeIngreso() != 0 && auto1.getHoraDeSalida() == 0) {
                    automovilesEnParqueadero.add(auto1);
                }
            }

            return gson.toJson(automovilesEnParqueadero);
        });

        // Obtener un informe de las ganancias por cada moto que ingresó y salió del parqueadero
        // endpoint GET para informe de las ganancias por cada moto que ingresó y salió del parqueadero
        get("/motosReporte", (req, res) -> {

            LinkedList<GananciaPorMoto> gananciasPorMoto = new LinkedList<>();
            
            // Añadimos esto para retornar en formato Json
            res.type("application/json");


            for (Motocicleta moto1 : motocicletas) {
                if (moto1.getHoraDeSalida() != 0) {
                    int horasTotales = moto1.getHoraDeSalida() - moto1.getHoraDeIngreso();
                    float gananciaMoto = (horasTotales / 100) * valorHora;

                    GananciaPorMoto gananciaPorMotoActual = new GananciaPorMoto(moto1.getPlaca(), gananciaMoto);
                    gananciasPorMoto.add(gananciaPorMotoActual);
                }
            }

            return gson.toJson(gananciasPorMoto);
        });
        
        // Obtener un informe de las ganancias por cada auto que ingresó y salió del parqueadero
        // endpoint GET para informe de las ganancias por cada moto que ingresó y salió del parqueadero
        get("/AutomovilesReporte", (req, res) -> {

            LinkedList<GananciaPorAuto> gananciasPorAuto = new LinkedList<>();
            
            // Añadimos esto para retornar en formato Json
            res.type("application/json");


            for (Automovil auto1 : automoviles) {
                if (auto1.getHoraDeSalida() != 0) {
                    int horasTotales = auto1.getHoraDeSalida() - auto1.getHoraDeIngreso();
                    float gananciaAutomovil = (horasTotales / 100) * valorHora;

                    GananciaPorAuto gananciaPorAutoActual = new GananciaPorAuto(auto1.getPlaca(), gananciaAutomovil);
                    gananciasPorAuto.add(gananciaPorAutoActual);
                }
            }

            return gson.toJson(gananciasPorAuto);
        });
    }
}
