//package com.github.stocky37.yougo.http.v1;
//
//import com.github.stocky37.yougo.api.v1.json.GoInputDTO;
//import com.github.stocky37.yougo.api.v1.GoAPI;
//import com.github.stocky37.yougo.core.GosService;
//
//import javax.enterprise.context.ApplicationScoped;
//import javax.inject.Inject;
//import javax.ws.rs.NotFoundException;
//
//@ApplicationScoped
//public class GoResource implements GoAPI {
//
//	private final GosService service;
//
//	@Inject
//	public GoResource(GosService service) {
//		this.service = service;
//	}
//
//	@Override
//	public GoInputDTO go(String alias) {
//		return service.getGoByName(alias).orElseThrow(NotFoundException::new);
//	}
//}
