package br.com.igordev.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.igordev.dao.JpaPagamentoDao;
import br.com.igordev.dominio.Pagamento;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Transactional
@RestController
public class RestfulController {
	
	@Autowired
	@Qualifier("jpaPagamentoDao")
	private JpaPagamentoDao dao;
	
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "popula", method = RequestMethod.POST)
	@ResponseBody
	public void populaPagamentos(@RequestBody Pagamento Pagamento) {
		dao.popula();
	}

	@RequestMapping(value = "get-pagamentos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Pagamento> getPagamentos() {
		return dao.buscaTodos();
	}

	@RequestMapping(value = "get-pagamento/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Pagamento getPagamento(@PathVariable("id") int id) {
		return dao.buscaPorId(id);
	}

	@RequestMapping(value = "remove-pagamento/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void remove(@PathVariable("id") int id) {
		Pagamento p = dao.buscaPorId(id);
		dao.exclui(p);
	}

}
