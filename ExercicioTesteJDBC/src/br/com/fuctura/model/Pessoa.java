package br.com.fuctura.model;

public class Pessoa {
	
	private String cpf;
	private String nome;
	private String sexo;
	private int idade;
	private String email;	
	
	public Pessoa() {
		
		this.cpf = cpf;
		this.nome = nome;
		this.sexo = sexo;
		this.idade = idade;
		this.email = email;
		
	}
	
	public Pessoa(String cpf, String nome, String sexo, int idade, String email) {
		
		this.cpf = cpf;
		this.nome = nome;
		this.sexo = sexo;
		this.idade = idade;
		this.email = email;
	}
	
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "=====================================\n"
				+ "CPF:    " + this.cpf +
				"\nNOME:   " + this.nome +
				"\nSEXO:   " + this.sexo +
				"\nIDADE:  " + this.idade +
				"\nEMAIL:  " + this.email +
				"\n=====================================";
	}	
	
}
