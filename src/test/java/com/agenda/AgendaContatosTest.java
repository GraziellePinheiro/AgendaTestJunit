package com.agenda;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class AgendaContatosTest {
    private AgendaContatos agenda;

    @BeforeEach
    public void setUp(){
        agenda = new AgendaContatos();
    }


    @Test
    @DisplayName("Deve adicionar um contato")
    public void testAdicionarContato(){
        Contato contato = new Contato("Joaquina", "9 0000-0000", "joaquina.joaquina.com");
        agenda.adicionarContato(contato);

        Assertions.assertEquals(1, agenda.getContatos().size());
        Assertions.assertTrue(agenda.getContatos().contains(contato));
    }
    @Test
    @DisplayName("Deve falhar ao adicionar um contato vazio")
    public void testDeveFalharAoAdicionarContatoVazio(){
        Contato contatoVazio = new Contato("", "", "");

        Assertions.assertThrows(IllegalArgumentException.class, () ->{
            agenda.adicionarContato(contatoVazio);
        });
    }

    

    @Test
    @DisplayName("Deve buscar um contato existente")
    public void testBuscarcontatoExistente(){
        Contato contato1 = new Contato("Joaquina", "9 0000-0000", "joaquina.joaquina.com");
        Contato contato2 = new Contato("Franscisco", "9 0000-0000", "franscisco@franscisco.com");
        Contato contato3 = new Contato("Catherine", "9 0000-0000", "candel@candel.com");
        agenda.adicionarContato(contato1);
        agenda.adicionarContato(contato2);
        agenda.adicionarContato(contato3);

        Contato res = agenda.buscarContatoPorNome("Joaquina");

        Assertions.assertEquals(contato1, res);
        Assertions.assertEquals("Joaquina", res.getNome());
        Assertions.assertEquals(3, agenda.getContatos().size());

    }

    @Test
    @DisplayName("Deve retornar null ao buscar um contato inexistente")
    public void testBuscarContatoInexistente(){
        Contato contato1 = new Contato("Joaquina", "9 0000-0000", "joaquina.joaquina.com");
        Contato contato2 = new Contato("Franscisco", "9 0000-0000", "franscisco@franscisco.com");
        Contato contato3 = new Contato("Catherine", "9 0000-0000", "candel@candel.com");
        agenda.adicionarContato(contato1);
        agenda.adicionarContato(contato2);
        agenda.adicionarContato(contato3);

        Contato res = agenda.buscarContatoPorNome("Matheus");

        Assertions.assertNull(res);

    }

    @Test
    @DisplayName("Deve remover um contato")
    public void testRemoverUmContato(){
        Contato contato1 = new Contato("Joaquina", "9 0000-0000", "joaquina.joaquina.com");
        Contato contato2 = new Contato("Franscisco", "9 0000-0000", "franscisco@franscisco.com");
        Contato contato3 = new Contato("Catherine", "9 0000-0000", "candel@candel.com");
        agenda.adicionarContato(contato1);
        agenda.adicionarContato(contato2);
        agenda.adicionarContato(contato3);

        agenda.removerContato(contato3);

        Assertions.assertTrue(agenda.getContatos().contains(contato1));
        Assertions.assertEquals(2, agenda.getContatos().size());
    }

    /**
     * 
     */
    @Test
    @DisplayName("Deve falhar ao remover um contato inexistente")
    public void testDeveFalharAoRemoverContatoInexistente(){
        Contato contatoInexistente = new Contato("Maria", "9 0000-0000", "marizinhaOFF@taon.com");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            agenda.removerContato(contatoInexistente);
            System.out.println("Foi lançada a exception!");
        });
    }

    @Test
    @DisplayName("Deve buscar por todos os contatos")
    public void testDeveBuscarTodosOscontatos(){
        Contato contato1 = new Contato("Joaquina", "9 0000-0000", "joaquina.joaquina.com");
        Contato contato2 = new Contato("Franscisco", "9 0000-0000", "franscisco@franscisco.com");
        Contato contato3 = new Contato("Catherine", "9 0000-0000", "candel@candel.com");
        agenda.adicionarContato(contato1);
        agenda.adicionarContato(contato2);
        agenda.adicionarContato(contato3);

        List<Contato> contatos = agenda.getContatos();

        Assertions.assertEquals(3, contatos.size());
        Assertions.assertTrue(contatos.contains(contato1));
        Assertions.assertTrue(contatos.contains(contato2));
        Assertions.assertTrue(contatos.contains(contato3));
        Assertions.assertEquals("Joaquina", contatos.get(0).getNome());
        Assertions.assertEquals("9 0000-0000", contatos.get(1).getTelefone());
        Assertions.assertEquals("candel@candel.com", contatos.get(2).getEmail());
    }

  
    @Test
    @DisplayName("Deve falhar ao tentar adicionar contato duplicado")
    public void testDeveFalharAoTentarAdicionarContatoDuplicado() {
        AgendaContatos agenda = new AgendaContatos();
        Contato contato1 = new Contato("Joaquina", "9 0000-0000", "joaquina.joaquina.com");
        Contato contato2 = new Contato("Joaquina", "9 1234-5678", "joaquina2.joaquina.com");

        agenda.adicionarContato(contato1);

        IllegalArgumentException excecao = Assertions.assertThrows(IllegalArgumentException.class, () -> {
        agenda.adicionarContato(contato2);

        });
        
        String mensagemErroEsperada = "Já existe um contato com o mesmo nome da agenda";

        Assertions.assertEquals(mensagemErroEsperada, excecao.getMessage());

        // Verificação do resultado
        List<Contato> contatos = agenda.getContatos();
        Assertions.assertEquals(1, contatos.size());
        Assertions.assertTrue(contatos.contains(contato1));
    }
}
    

