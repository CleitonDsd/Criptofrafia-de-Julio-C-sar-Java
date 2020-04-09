package dev.codenation.challenge.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;

import dev.codenation.criptografia.CriptografiaCesar;

public class RequestJsonChallenge {

	private static HttpURLConnection connection;

	public static void main(String[] args) {
		// Metodo 1 : java.net.HttpURLConnection

		// Para lermos a leitura de uma InputStreamReader
		BufferedReader reader;
		String line;
		StringBuffer responseContent = new StringBuffer();

		try {
			// Definido URL que será aberta
			URL url = new URL(
					"https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=e44e639cf8c3f00f3ab0edf957d3351d29d8bda6&format=json");
			// conexão para o API endpoint == URL que iremos abrir
			connection = (HttpURLConnection) url.openConnection();

			// Solicita a configuração

			// Seta o tipo da requisição
			connection.setRequestMethod("GET");
			// tempo limite de conexão
			connection.setConnectTimeout(5000);
			// tempo limite de leitura da conexão
			connection.setReadTimeout(5000);

			// Pegar a resposta do server
			int status = connection.getResponseCode();
			// System.out.println(status);

			if (status > 299) {
				// Se a conexão falhar ler a mensagem de erro
				reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				while ((line = reader.readLine()) != null) {
					// se ainda houver mensagens para serem lidas, acrescentar linhas
					responseContent.append(line);
				}
				// depois de ler fechar a conexão
				reader.close();
			} else {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while ((line = reader.readLine()) != null) {

					responseContent.append(line);
				}
				reader.close();
			}
			System.out.println(responseContent.toString());

			// Descriptografar Cifra
			CriptografiaCesar cifra = new CriptografiaCesar();
			cifra.lerDados();
			char[] mensagemDescriptograda = cifra.decifrar();
			System.out.println("====================");
			System.out.println("Mensagem Decifrada: ");
			System.out.println(mensagemDescriptograda);
			// DID YOU KNOW? THE COLLECTIVE NOUN FOR A GROUP OF PROGRAMMERS IS A
			// MERGE-CONFLICT. UNKNOWN
			System.out.println("====================");
			cifra.criptografarJSON(
					"DID YOU KNOW? THE COLLECTIVE NOUN FOR A GROUP OF PROGRAMMERS IS A MERGE-CONFLICT. UNKNOWN");
			System.out.println("====================");

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// depois de ler fechar as conexões de leitura
			connection.disconnect();
		}

	}

}
