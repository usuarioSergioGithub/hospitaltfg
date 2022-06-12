package com.eep.hospital;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
@EnableEncryptableProperties //--> Establecer que se tienen que descifrar algunos elementos
// CommandLineRunner -> ejecutar antes de que se cargue el contexto de spring
public class HospitalApplication /*implements CommandLineRunner*/ {

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}

	/*@Autowired
	private StringEncryptor stringEncryptor; --> clase de Jasypt para el cifrado y descifrado


	=============================================
	Obtener los datos sin cifrar del application.properties para mostrarlos cifrados
	y ponerlos cifrados con el prefijo ENC (prefijo por defecto que utiliza jasypt
	para diferenciar del contenido cifrado del que no lo está
	=============================================

	@Value("${spring.datasource.url}")
	String url;

	@Value("${spring.datasource.username}")
	String usuario;

	@Value("${spring.datasource.password}")
	String contra;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Url:" + stringEncryptor.encrypt(url) );
		System.out.println("usuario:" + stringEncryptor.encrypt(usuario) );
		System.out.println("Contra:" + stringEncryptor.encrypt(contra) );
	}*/
}
