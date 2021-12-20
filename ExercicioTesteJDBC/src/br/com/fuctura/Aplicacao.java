package br.com.fuctura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import br.com.fuctura.model.Pessoa;
import br.com.fuctura.repositorio.PessoaRepositorio;

public class Aplicacao {

	@SuppressWarnings("finally")
	public static int leiaInt(String num) {

		int n = 0;

		while (true) {
			try {
				n = Integer.valueOf(num);
			} catch (Exception e) {
				System.out.println("ERRO! Digite apenas números inteiros.");
			} finally {
				return n;
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection con = null;

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String usuario = "fuctura1";
		String senha = "123";

		try {

			con = DriverManager.getConnection(url, usuario, senha);

			JOptionPane.showMessageDialog(null, "Conexão estabelecida");
		} catch (Exception e) {
			// TODO: handle exception
		}

		System.out.println("===== CADASTRO DE USUÁRIO =====\n");
		Scanner entrada = new Scanner(System.in);
		int opc;

		while (true) {

			System.out.println("Escolha a opção: ");

			System.out.println("|-----------------------|");
			System.out.println("| 1 - Inserir cliente   |");
			System.out.println("| 2 - Pesquisar banco   |");
			System.out.println("| 3 - Pesquisar cliente |");
			System.out.println("| 4 - Atualizar cliente |");
			System.out.println("| 5 - Apagar cliente    |");
			System.out.println("| 6 - Sair...           |");
			System.out.println("|-----------------------|");
			System.out.print("Opção: ");
			opc = leiaInt(entrada.next());

			// ### INSERIR PESSOA###
			if (opc == 1) {

				System.out.println("\n### INSERIR PESSOA ###\n");

				System.out.print("Digite o cpf: ");
				String cpf = entrada.next();

				PessoaRepositorio pr = new PessoaRepositorio();
				boolean existe;
				
				existe = pr.verificarNoBanco(cpf, con);
				
				if (! existe) {
					
					pr.inserirPessoa(cpf, con);
					
				} else {
					
					System.out.println("\nCPF já cadastrada no banco de dados!\n");
					
				}
				

			// ### PESQUISAR O BANCO INTEIRO ###
			} else if (opc == 2) {

				System.out.println("\n### PESQUISAR BANCO ###\n");
				
				PessoaRepositorio pr = new PessoaRepositorio();
				ArrayList<Pessoa> pessoa = new ArrayList();
				
				pessoa = pr.pesquisarBanco(con);
				pr.listarBanco(pessoa);
				
			// ### PESQUISAR PESSOA POR CPF ###
			} else if (opc == 3) {
				
				System.out.println("\n### PESQUISAR PESSOA ###\n");
				
				System.out.print("Digite o cpf: ");
				String cpf = entrada.next();
				
				boolean verificador;
				
				PessoaRepositorio pr = new PessoaRepositorio();
				verificador = pr.verificarNoBanco(cpf, con);
				
				if (verificador) {

					pr.listarPessoa(cpf, con);
					
				} else {
					System.out.println("\nCPF inexistente na base de dados!\n");
				}
			
			
			// ### ATUALIZAR PESSOA ###
			} else if (opc == 4) {
				
				System.out.println("\n### ATUALIZAR PESSOA ###\n");
				
				Scanner teclado = new Scanner(System.in);
				System.out.print("Digite o cpf: ");
				String cpf = teclado.nextLine();
				
				boolean verificador;
				PessoaRepositorio pr = new PessoaRepositorio();
				verificador = pr.verificarNoBanco(cpf, con);
				
				if (verificador) {
					
					pr.atualizarPessoa(cpf, con);
					
				} else {
					
					System.out.println("\nCPF inexistente no banco de dados!\n");
				}
			
				// ### EXCLUIR PESSOA ###
			} else if (opc == 5) {
				
				System.out.println("\n### EXLUIR PESSOA ###\n");

				Scanner teclado = new Scanner(System.in);
				System.out.print("Digite o cpf: ");
				String cpf = teclado.nextLine();
				
				boolean verificador;
				PessoaRepositorio pr = new PessoaRepositorio();
				verificador = pr.verificarNoBanco(cpf, con);
				
				if (verificador) {
					
					pr.excluirPessoa(cpf, con);
					
				} else {
					
					System.out.println("\nCPF inexistente no banco de dados!\n");
					
				}
			
			// ### OPÇÃO SAIR ###
			} else if (opc == 6) {
				
				break;
				
			// ### OPÇÃO INVÁLIDA ###	
			} else {
				
				System.out.println("\nDigite uma opção válida!\n");
				
			}
		}
		
		System.out.println("\n### FIM DA EXECUÇÃO ###");
	}
}
