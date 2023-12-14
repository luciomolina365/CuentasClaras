package com.ttps2023.CuentasClaras.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ttps2023.CuentasClaras.model.AmountPerMember;
import com.ttps2023.CuentasClaras.model.Crew;
import com.ttps2023.CuentasClaras.model.EqualPerMember;
import com.ttps2023.CuentasClaras.model.Expense;
import com.ttps2023.CuentasClaras.model.Payment;
import com.ttps2023.CuentasClaras.model.PercentagePerMember;
import com.ttps2023.CuentasClaras.model.SplitWay;
import com.ttps2023.CuentasClaras.repositories.SplitWayRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SplitWayService {
	private final SplitWayRepository splitWayRepository;

	public SplitWayService(SplitWayRepository splitWayRepository) {
		this.splitWayRepository = splitWayRepository;
	}

	public Optional<SplitWay> getById(Long id) {
		return splitWayRepository.findById(id);
	}
	
	public List<Payment> expenseSplit(Expense expense, Crew crew) {
		
		SplitWay splitway = expense.getSplitway();
		
		List<Payment> paymentList = splitway.split(expense, crew);
		
		return paymentList;
	}

	// recibo la lista de usuarios q van a estar dentro de este gasto tambien pasar
	// splitway y expense
	// entonces segun q ocurra haria una funcion u otra y devuelve la paymentList

	// la duda es si tengo q poner un valor especificco para cada user, como hacer
	// si lo tengo q enviar a una nueva pestaña

}
