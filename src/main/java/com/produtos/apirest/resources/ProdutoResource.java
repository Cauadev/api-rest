package com.produtos.apirest.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.produtos.apirest.models.Produto;
import com.produtos.apirest.repository.ProdutoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST produtos")
@CrossOrigin(origins = "*")
public class ProdutoResource {
	
		@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    final CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true);
	    config.setAllowedOrigins(Collections.singletonList("*"));
	    config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
	    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
	    source.registerCorsConfiguration("/**", config);
	    FilterRegistrationBean<CorsFilter> registration = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
	    return registration;
	}
	
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@GetMapping("/produtos")
	@ApiOperation(value = "Lista os produtos")
	public List<Produto> listProdutos(){
		return produtoRepository.findAll();
	}
	
	@GetMapping("/produto/{id}")
	@ApiOperation(value = "Pega um produto específico")
	public Produto getProduto(@PathVariable int id) {
		return produtoRepository.findById(id).get();
	}
	
	@PostMapping("/produto")
	@ApiOperation(value = "Cria um produto")
	public Produto saveProduto(@RequestBody @Valid Produto produto) {
		return produtoRepository.save(produto);
	}
	
	@DeleteMapping("/produto")
	@ApiOperation(value = "Este método deleta um produto")
	public void deleteProduto(@RequestBody @Valid Produto produto) {
		produtoRepository.delete(produto);
	}
	
	@PutMapping("/produto")
	@ApiOperation(value = "Atualiza um produto")
	public Produto updateProduto(@RequestBody @Valid Produto produto) {
		return produtoRepository.save(produto);
	}

}
