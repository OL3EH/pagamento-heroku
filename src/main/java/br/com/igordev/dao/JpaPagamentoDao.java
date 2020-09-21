package br.com.igordev.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.igordev.dominio.Pagamento;

@Repository
public class JpaPagamentoDao {
	
	private static List<Pagamento> pagamentos = new ArrayList<>();
	
    static {
        pagamentos.add(new Pagamento("Mouse", LocalDate.of(2020,8,20), 45.00, true));
        pagamentos.add(new Pagamento("Teclado", LocalDate.now(), 145.00, false));
        pagamentos.add(new Pagamento("Material Escritório", LocalDate.of(2020,7,12), 200.00, false));
        pagamentos.add(new Pagamento("Cursos", LocalDate.of(2020,8,19), 2300.00, true));
        pagamentos.add(new Pagamento("Cadeira", LocalDate.of(2020,9,5), 750.00, false));
        pagamentos.add(new Pagamento("Energia", LocalDate.of(2020,10,10), 280.00, false));
        pagamentos.add(new Pagamento("Fatura Celular", LocalDate.now(), 190.00, false));
        pagamentos.add(new Pagamento("Fatura Netflix", LocalDate.of(2020,11,15), 60.00, false));
    }
	
	

	@PersistenceContext
	EntityManager manager;

	
	public void popula() {
		pagamentos
			.forEach(manager::persist);
	}// fim salvar

	public void exclui(Pagamento Pagamento) {
		manager.remove(Pagamento);
	}// fim excluir

	public Pagamento buscaPorId(int id) {
		return manager.find(Pagamento.class, id);
	}// fim buscarId

	public List<Pagamento> buscaTodos() {
		return  manager.createQuery("select f from Pagamento f", Pagamento.class).getResultList();
	}// fim buscarTodos

}
