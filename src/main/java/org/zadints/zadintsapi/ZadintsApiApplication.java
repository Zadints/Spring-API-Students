package org.zadints.zadintsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class ZadintsApiApplication {

	public static void main(String[] args) {

		Path path = Path.of(
				"C:/Users/AUGUSTO RODRIGUEZ/OneDrive/Documentos/Servinet.db"
		);

		System.out.println("Existe: " + Files.exists(path));
		System.out.println("Es archivo: " + Files.isRegularFile(path));
		System.out.println("Lectura: " + Files.isReadable(path));
		System.out.println("Escritura: " + Files.isWritable(path));
		System.out.println("Carpeta escribible: " + Files.isWritable(path.getParent()));

		String url = "jdbc:sqlite:C:/Users/AUGUSTO RODRIGUEZ/OneDrive/Documentos/Servinet.db";

		try (Connection conn = DriverManager.getConnection(url)) {

			System.out.println("Conexión exitosa");
			System.out.println("Conexión válida: " + conn.isValid(5));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		SpringApplication.run(ZadintsApiApplication.class, args);
	}

}
