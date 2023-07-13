package com.agenda;

import java.util.ArrayList;
import java.util.List;

public class AgendaContatos {
    private List<Contato> contatos;

    public AgendaContatos(){
        contatos = new ArrayList<>();
    }

    public void adicionarContato(Contato contato){
        if(contato.getNome() == null || contato.getNome().isEmpty()){
            throw new IllegalArgumentException("O campo nome não pode estar vazio!");
        }
        if(contato.getTelefone() == null || contato.getTelefone().isEmpty()){
            throw new IllegalArgumentException("O campo telefone não pode estar vazio!");
        }
        if(existeContatoComNome(contato.getNome())){
            throw new IllegalArgumentException("Já existe um contato com esse nome!");
        }
        if(existeContatoComTelefone(contato.getTelefone())){
            throw new IllegalArgumentException("Já existe contato com o telefone informado!");
        }
        contatos.add(contato);
    }

    public boolean existeContatoComNome(String nome){
        for(Contato contato : contatos){
            if(contato.getNome().equalsIgnoreCase(nome)){
                return true;
            }
        }
        return false;
    }
    public boolean existeContatoComTelefone(String telefone){
        for(Contato contato : contatos){
            if(contato.getNome().equalsIgnoreCase(telefone)){
                return true;
            }
        }
        return false;
    }

    public void removerContato(Contato contato){
        contatos.remove(contato);
    }

    public Contato buscarContatoPorNome(String nome){
       
        if(nome == null || nome.isEmpty()){
            throw new IllegalArgumentException("O nome para busca não pode estar vazio!");
        }

        for(Contato contato : contatos){
            if(contato.getNome().equalsIgnoreCase(nome)){
            return contato;
            } 
        }
        return null;
    }
    
    public List<Contato> getContatos(){
        return contatos;
    }
}
