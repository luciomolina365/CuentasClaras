package com.ttps2023.CuentasClaras.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ttps2023.CuentasClaras.model.SplitWay;
import com.ttps2023.CuentasClaras.repositories.ExpenseRepository;
import com.ttps2023.CuentasClaras.repositories.SplitWayRepository;

@Service
public class SplitWayService {
	private final SplitWayRepository splitWayRepository;

	public SplitWayService(SplitWayRepository splitWayRepository) {
		this.splitWayRepository = splitWayRepository;
	}

	public Optional<SplitWay> getById(Long id) {
		return splitWayRepository.findById(id);
	}

//	private final ExpenseRepository expenseRepository;
//	private
//	
//	
//	   public SplitWayService(ExpenseRepository expenseRepository) {
//	        this.expenseRepository = expenseRepository;
//	    }
//	
//	public SplitWayService() {
//		
//		
//		public

	// recibo la lista de usuarios q van a estar dentro de este gasto tambien pasar
	// splitway y expense
	// entonces segun q ocurra haria una funcion u otra y devuelve la paymentList

	// la duda es si tengo q poner un valor especificco para cada user, como hacer
	// si lo tengo q enviar a una nueva pestaña

}
