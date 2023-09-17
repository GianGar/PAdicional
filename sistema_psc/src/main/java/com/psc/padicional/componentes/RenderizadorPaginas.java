package com.psc.padicional.componentes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class RenderizadorPaginas <T>{
	
	private String url;
	private int totalPag;
	private int pagActual;
	private List<ElementosPagina> paginas;
	
	public RenderizadorPaginas(String url, Page<T> pag) {
		this.url=url;
		this.paginas = new ArrayList<ElementosPagina>();
		totalPag = pag.getTotalPages();
		pagActual = pag.getNumber()+1;
		int desde,hasta;
		desde = 1;
		hasta = totalPag;
		for (int i=0; i<hasta; i++) {
			paginas.add(new ElementosPagina(desde+i,pagActual == desde+i));
			}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getTotalPag() {
		return totalPag;
	}

	public void setTotalPag(int totalPag) {
		this.totalPag = totalPag;
	}

	public int getPagActual() {
		return pagActual;
	}

	public void setPagActual(int pagActual) {
		this.pagActual = pagActual;
	}

	public List<ElementosPagina> getPaginas() {
		return paginas;
	}

	public void setPaginas(List<ElementosPagina> paginas) {
		this.paginas = paginas;
	}
	
	
}
