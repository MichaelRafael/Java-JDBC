package br.com.fuctura.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import br.com.fuctura.model.Pessoa;

public class PessoaRepositorio {
	
	private ResultSet rs;

	public void inserirPessoa(String cpf, Connection con) {

		Scanner entrada = new Scanner(System.in);
		PreparedStatement pstm;

		String url = "insert into pessoa (cpf, nome, sexo, idade, email) values (?, ?, ?, ?, ?)";
		
		System.out.print("Digite o nome: ");
		String nome = entrada.nextLine();
		
		System.out.print("Digite o sexo: ");
		String sexo = entrada.nextLine();
		
		System.out.print("Digite a idade: ");
		int idade = entrada.nextInt();
		
		System.out.print("Digite o email: ");
		String email = entrada.next();

		try {
			pstm = con.prepareStatement(url);

			pstm.setString(1, cpf);
			pstm.setString(2, nome);
			pstm.setString(3, sexo);
			pstm.setInt(4, idade);
			pstm.setString(5, email);

			pstm.execute();
			pstm.close();
			System.out.println("\nDados inseridos com sucesso!\n");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("\nErro ao inserir dados!\n");
		}
	}

	public ArrayList<Pessoa> pesquisarBanco(Connection con) {

		PreparedStatement pstm;
		ResultSet rs;
		ArrayList<Pessoa> pessoa = new ArrayList<Pessoa>();
		String url = "select * from pessoa";

		try {
			pstm = con.prepareStatement(url);
			rs = pstm.executeQuery();

			while (rs.next()) {
				String cpf = rs.getString("cpf");
				String nome = rs.getString("nome");
				String sexo = rs.getString("sexo");
				int idade = rs.getInt("idade");
				String email = rs.getString("email");

				Pessoa p = new Pessoa(cpf, nome, sexo, idade, email);

				pessoa.add(p);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pessoa;
	}

	public void listarBanco(ArrayList<Pessoa> lista) {

		for (Pessoa listar : lista) {
			System.out.println(listar);
		}
	}

	public boolean verificarNoBanco(String cpf, Connection con) {

		PreparedStatement pstm;
		ResultSet rs;
		boolean existe = false;

		String verificar = "select cpf from pessoa where cpf = ?";

		try {
			pstm = con.prepareStatement(verificar);
			pstm.setString(1, cpf);			
			rs = pstm.executeQuery();
			if (rs.next()) {
				existe = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return existe;
	}
	
	public void listarPessoa(String id, Connection con) {
		
		PreparedStatement pstm;
		ResultSet rs;
		
		String listar = "select * from pessoa where cpf = ?";
		
		try {
			pstm = con.prepareStatement(listar);
			pstm.setString(1, id);
			rs = pstm.executeQuery();
			while (rs.next()) {
				String cpf = rs.getString("cpf");
				String nome = rs.getString("nome");
				String sexo = rs.getString("sexo");
				int idade = rs.getInt("idade");
				String email = rs.getString("email");
				
				System.out.println("=====================================\n"
								 + "CPF:    " + cpf +
					               "\nNOME:   " + nome +
						           "\nSEXO:   " + sexo +
						           "\nIDADE:  " + idade +
						           "\nEMAIL:  " + email +
						           "\n=====================================");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	public void atualizarPessoa(String cpf, Connection con) {
		
		PreparedStatement pstm;
		String atualizar = "update pessoa set nome = ?, sexo = ?, idade = ? where cpf = ?";
		Scanner entrada = new Scanner(System.in);
		
		System.out.print("Digite o novo nome: ");
		String nome = entrada.nextLine();
		
		System.out.print("Digite o novo sexo: ");
		String sexo = entrada.nextLine();
		
		System.out.print("Digite a nova idade: ");
		int idade = entrada.nextInt();
		
		try {
			pstm = con.prepareStatement(atualizar);
			
			pstm.setString(1, nome);
			pstm.setString(2, sexo);
			pstm.setInt(3, idade);
			pstm.setString(4, cpf);
			
			pstm.execute();
			pstm.close();
			
			System.out.println("\nDados alterados com sucesso!\n");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void excluirPessoa(String cpf, Connection con) {
		
		PreparedStatement pstm;
		String excluir = "delete from pessoa where cpf = ?";
		
		try {
			pstm = con.prepareStatement(excluir);
			pstm.setString(1, cpf);
			
			pstm.execute();
			pstm.close();
			
			System.out.println("\nCadastro excluido com sucesso!\n");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("\nErro ao excluir cadastro!\n");
		}
		
	}
}
































