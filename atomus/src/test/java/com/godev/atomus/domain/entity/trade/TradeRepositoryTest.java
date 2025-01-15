package com.godev.atomus.domain.entity.trade;

import com.godev.atomus.entity.broker.Broker;
import com.godev.atomus.entity.broker.BrokerRegisterData;
import com.godev.atomus.entity.product.Product;
import com.godev.atomus.entity.product.ProductResgisterData;
import com.godev.atomus.entity.trade.EnumTradeType;
import com.godev.atomus.entity.trade.Trade;
import com.godev.atomus.entity.trade.TradeRegisterData;
import com.godev.atomus.entity.trade.TradeRepository;
import com.godev.atomus.entity.user.User;
import com.godev.atomus.entity.user.UserRegisterData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class TradeRepositoryTest {

    @Autowired
    private TradeRepository repository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Should return an empty List if there aren't trades saved by user")
    void findAllByUsuarioIdTest1() {
        var usuario = cadastrarUsuario("Daniel", "000.000.000-00", "daniel@godev.com", "123456", null, false);
        var corretora = cadastrarCorretora("XP Investimentos", "77.777.777/7777-77");
        var produto = cadastrarProduto("Ações", false);
        List<Trade> negociacaosBuscadas = repository.findAllByUsuarioId(usuario.getId(), null);
        assertThat(negociacaosBuscadas).isEmpty();
    }

    @Test
    @DisplayName("Should return a List with one or more items if there are trades saved by the user")
    void findAllByUsuarioIdTest2() {
        var usuario = cadastrarUsuario("Daniel", "000.000.000-00", "daniel@godev.com", "123456", null, false);
        var corretora = cadastrarCorretora("XP Investimentos", "77.777.777/7777-77");
        var produto = cadastrarProduto("Ações", false);
        List<Trade> negociacaosSalvas = Collections.singletonList(cadastrarNegociacao(usuario.getId(), EnumTradeType.COMPRA, LocalDate.of(2024, 07, 12), corretora.getId(), produto.getId(), "B3SA3", 2, 12.03, 0.0));
        List<Trade> negociacaosBuscadas = repository.findAllByUsuarioId(usuario.getId(), null);
        assertThat(negociacaosBuscadas).isEqualTo(negociacaosSalvas);
    }

    @Test
    @DisplayName("Should return an empty List if there aren't trades saved by user")
    void findAllByUsuarioIdAndProductTest1() {
        var usuario = cadastrarUsuario("Daniel", "000.000.000-00", "daniel@godev.com", "123456", null, false);
        var corretora = cadastrarCorretora("XP Investimentos", "77.777.777/7777-77");
        var produto = cadastrarProduto("Ações", false);
        List<Trade> negociacaosBuscadas = repository.findAllByUsuarioIdAndProduct(usuario.getId(), "Ações", null);
        assertThat(negociacaosBuscadas).isEmpty();
    }

    @Test
    @DisplayName("Should return a List with one or more items if there are trades saved by the user")
    void findAllByUsuarioIdAndProductTest2() {
        var usuario = cadastrarUsuario("Daniel", "000.000.000-00", "daniel@godev.com", "123456", null, false);
        var corretora = cadastrarCorretora("XP Investimentos", "77.777.777/7777-77");
        var produto = cadastrarProduto("Ações", false);
        List<Trade> negociacaosSalvas = new ArrayList<>();
        negociacaosSalvas.add(cadastrarNegociacao(usuario.getId(), EnumTradeType.COMPRA, LocalDate.of(2024, 7, 12), corretora.getId(), produto.getId(), "B3SA3", 2, 12.03, 0.0));
        List<Trade> negociacaosBuscadas = repository.findAllByUsuarioIdAndProduct(usuario.getId(), "Ações",null);
        assertThat(negociacaosBuscadas).isEqualTo(negociacaosSalvas);
    }

    @Test
    @DisplayName("Should return an empty list if there are trades saved by the user, but there aren't trades saved with the requested product")
    void findAllByUsuarioIdAndProductTest3() {
        var usuario = cadastrarUsuario("Daniel", "000.000.000-00", "daniel@godev.com", "123456", null, false);
        var corretora = cadastrarCorretora("XP Investimentos", "77.777.777/7777-77");
        var produto = cadastrarProduto("Ações", false);
        List<Trade> negociacaosSalvas = new ArrayList<>();
        negociacaosSalvas.add(cadastrarNegociacao(usuario.getId(), EnumTradeType.COMPRA, LocalDate.of(2024, 7, 12), corretora.getId(), produto.getId(), "B3SA3", 2, 12.03, 0.0));
        List<Trade> negociacaosBuscadas = repository.findAllByUsuarioIdAndProduct(usuario.getId(), "FII's",null);
        assertThat(negociacaosBuscadas).isEmpty();
    }

    @Test
    @DisplayName("Should return an empty List if there aren't trades saved by user")
    void findAllByUsuarioIdAndTitleTest1() {
        var usuario = cadastrarUsuario("Daniel", "000.000.000-00", "daniel@godev.com", "123456", null, false);
        var corretora = cadastrarCorretora("XP Investimentos", "77.777.777/7777-77");
        var produto = cadastrarProduto("Ações", false);
        List<Trade> negociacaosBuscadas = repository.findAllByUsuarioIdAndTitle(usuario.getId(), "Ações", "B3SA3", null);
        assertThat(negociacaosBuscadas).isEmpty();
    }

    @Test
    @DisplayName("Should return a List with one or more items if there are trades saved by the user")
    void findAllByUsuarioIdAndTitleTest2() {
        var usuario = cadastrarUsuario("Daniel", "000.000.000-00", "daniel@godev.com", "123456", null, false);
        var corretora = cadastrarCorretora("XP Investimentos", "77.777.777/7777-77");
        var produto = cadastrarProduto("Ações", false);
        List<Trade> negociacaosSalvas = new ArrayList<>();
        negociacaosSalvas.add(cadastrarNegociacao(usuario.getId(), EnumTradeType.COMPRA, LocalDate.of(2024, 7, 12), corretora.getId(), produto.getId(), "B3SA3", 2, 12.03, 0.0));
        List<Trade> negociacaosBuscadas = repository.findAllByUsuarioIdAndTitle(usuario.getId(), "Ações", "B3SA3", null);
        assertThat(negociacaosBuscadas).isEqualTo(negociacaosSalvas);
    }

    @Test
    @DisplayName("Should return an empty list if there are trades saved by the user, but there aren't trades saved with the requested title")
    void findAllByUsuarioIdAndTitleTest3() {
        var usuario = cadastrarUsuario("Daniel", "000.000.000-00", "daniel@godev.com", "123456", null, false);
        var corretora = cadastrarCorretora("XP Investimentos", "77.777.777/7777-77");
        var produto = cadastrarProduto("Ações", false);
        List<Trade> negociacaosSalvas = new ArrayList<>();
        negociacaosSalvas.add(cadastrarNegociacao(usuario.getId(), EnumTradeType.COMPRA, LocalDate.of(2024, 7, 12), corretora.getId(), produto.getId(), "B3SA3", 2, 12.03, 0.0));
        List<Trade> negociacaosBuscadas = repository.findAllByUsuarioIdAndTitle(usuario.getId(), "Ações", "VALE3", null);
        assertThat(negociacaosBuscadas).isEmpty();
    }

    private Trade cadastrarNegociacao(Long usuarioId, EnumTradeType enumTradeType, LocalDate data, Long corretoraId, Long produtoId, String titulo, Integer quantidade, Double precoUnitario, Double taxas) {
        var negociacao = new Trade(negociacaoDadosCadastro(usuarioId, enumTradeType, data, corretoraId, produtoId, titulo, quantidade, precoUnitario, taxas));
        testEntityManager.persist(negociacao);
        return negociacao;
    }

    private User cadastrarUsuario(String nome, String cpf, String email, String senha, Long perfilId, Boolean ativo) {
        var usuario = new User(usuarioDadosCadastro(nome, cpf, email, senha, perfilId, ativo));
        testEntityManager.persist(usuario);
        return usuario;
    }

    private Broker cadastrarCorretora(String nome, String cnpj) {
        var corretora = new Broker(corretoraDadosCadastro(nome, cnpj));
        testEntityManager.persist(corretora);
        return corretora;
    }

    private Product cadastrarProduto(String nome, Boolean coberturaFgc) {
        var produto = new Product(produtoDadosCadastro(nome, coberturaFgc));
        testEntityManager.persist(produto);
        return produto;
    }

    private TradeRegisterData negociacaoDadosCadastro(Long usuarioId, EnumTradeType enumTradeType, LocalDate data, Long corretoraId, Long produtoId, String titulo, Integer quantidade, Double precoUnitario, Double taxas) {
        return new TradeRegisterData(
                usuarioId,
                enumTradeType,
                data,
                corretoraId,
                produtoId,
                titulo,
                quantidade,
                precoUnitario,
                taxas
        );
    }

    private UserRegisterData usuarioDadosCadastro(String nome, String cpf, String email, String senha, Long perfilId, Boolean ativo) {
        return new UserRegisterData(
                nome,
                cpf,
                email,
                senha,
                perfilId,
                ativo
        );
    }

    private BrokerRegisterData corretoraDadosCadastro(String nome, String cnpj) {
        return new BrokerRegisterData(
                nome,
                cnpj
        );
    }

    private ProductResgisterData produtoDadosCadastro(String nome, Boolean coberturaFgc) {
        return new ProductResgisterData(
                nome,
                coberturaFgc
        );
    }
}