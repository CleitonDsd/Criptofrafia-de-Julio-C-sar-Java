package dev.codenation.criptografia;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class CriptografiaCesar {

	private String mensagem;
	private int chave;
	private static char[] alfabeto = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
			'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	public CriptografiaCesar() {
		super();
	}

	public CriptografiaCesar(String mensagem, int chave) {
		super();
		this.mensagem = mensagem;
		this.chave = chave;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public int getChave() {
		return chave;
	}

	public void setChave(int chave) {
		this.chave = chave;
	}

	public static char[] getAlfabeto() {
		return alfabeto;
	}

	public static void setAlfabeto(char[] alfabeto) {
		CriptografiaCesar.alfabeto = alfabeto;
	}

	// Ler dados
	public void lerDados() {
		Scanner scan = new Scanner(System.in);

		System.out.println("Insira a mensagem: ");
		setMensagem(scan.nextLine().toUpperCase());

		System.out.println("Insira a casa: ");
		setChave(scan.nextInt());

		scan.close();
	}

	// Encriptografar
	public char[] encriptar() {
		char[] novaMensagem = new char[getMensagem().length()];

		// iterar dentro de cada letra da nossa mensagem original exemplo do H -> L...

		for (int i = 0; i < getMensagem().length(); i++) {

			// Verificar se a letra digita corresponde a um espa�o
			if (getMensagem().charAt(i) == ' ') {
				novaMensagem[i] = getMensagem().charAt(i);
			} else {

				for (int j = 0; j < getAlfabeto().length; j++) {

					// verificar se a posi��o "j" � igual a uma letra
					if (getMensagem().charAt(i) == getAlfabeto()[j]) {

						// se a chave for negativa
						if (getChave() < 0) {
							/*
							 * Nova mensagem na posi��o i � igual (=) alfabeto na posi��o J, que s�o
							 * correspondentes maws sem ter feito alguma altera��o. Por�m ainda mais + a
							 * Chave que � negativa (teria que fazer uma exce��o) ent�o... Temos que
							 * adicionar o n�mero de letras no comprimento do alfabeto MOD do alfabeto..
							 * 
							 * Resumindo faz uma tratativa contra problemas negativos
							 */

							novaMensagem[i] = getAlfabeto()[(j + getChave() + getAlfabeto().length)
									% getAlfabeto().length];
							j = getAlfabeto().length;

						} else {
							novaMensagem[i] = getAlfabeto()[(j + getChave()) % getAlfabeto().length];
							j = getAlfabeto().length;
						}
					} else {
						// Trata o que n�o � nenhuma letra ou espa�o por exemplo, s�mbolos e n�meros.
						novaMensagem[i] = getMensagem().charAt(i);

					}

				}
			}
		}

		return novaMensagem;

	}

	// Decifrar
	public char[] decifrar() {

		char[] novaMensagem = new char[getMensagem().length()];

		// iterar dentro de cada letra da nossa mensagem original exemplo do H -> L...

		for (int i = 0; i < getMensagem().length(); i++) {

			// Verificar se a letra digita corresponde a um espa�o
			if (getMensagem().charAt(i) == ' ') {
				novaMensagem[i] = getMensagem().charAt(i);
			} else {

				for (int j = 0; j < getAlfabeto().length; j++) {

					// verificar se a posi��o "j" � igual a uma letra
					if (getMensagem().charAt(i) == getAlfabeto()[j]) {

						/*
						 * se a letra for menor que a chave agora pode, pois por exemplo poderia ocorrer
						 * de, se a tecla � 7 ea posi��o que eu encontrei as letras � 3, eu ficaria com
						 * -4.
						 */
						if (j < getChave()) {

							novaMensagem[i] = getAlfabeto()[(j - getChave() + getAlfabeto().length)
									% getAlfabeto().length];
							j = getAlfabeto().length;

						} else {
							novaMensagem[i] = getAlfabeto()[(j - getChave()) % getAlfabeto().length];
							j = getAlfabeto().length;
						}
					} else {
						// Trata o que n�o � nenhuma letra ou espa�o por exemplo, s�mbolos e n�meros.
						novaMensagem[i] = getMensagem().charAt(i);

					}

				}
			}
		}

		return novaMensagem;

	}

	public static void criptografarJSON(String mensagemDescriptograda) {
		String retorno = "";
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-1");
			BigInteger hash = new BigInteger(1, md.digest(mensagemDescriptograda.getBytes()));
			// Retornar hexadecimal
			retorno = hash.toString(16);
		} catch (Exception e) {
		}
		System.out.println("Mensagem Criptografada SHA1: \n" + retorno);
	}

}
