package com.ttps2023.CuentasClaras.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ttps2023.CuentasClaras.model.Crew;
import com.ttps2023.CuentasClaras.model.Expense;
import com.ttps2023.CuentasClaras.model.Payment;
import com.ttps2023.CuentasClaras.model.SplitWay;
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
	
	public List<Payment> expenseSplit(Expense expense, Crew crew) {
		
		SplitWay splitway = expense.getSplitway();
		
		List<Payment> paymentList = splitway.split(expense, crew);
		
		//actualizar datos de usuarios.
		
		return paymentList;
	}
}
