package com.ttps2023.CuentasClaras.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttps2023.CuentasClaras.model.Crew;
import com.ttps2023.CuentasClaras.model.Expense;
import com.ttps2023.CuentasClaras.model.Payment;
import com.ttps2023.CuentasClaras.model.SplitWay;
import com.ttps2023.CuentasClaras.model.User;
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

		updateUserBalances(expense, paymentList);

		return paymentList;
	}

	@Transactional
	private void updateUserBalances(Expense expense, List<Payment> paymentList) {
	    User expenseOwner = expense.getBelongsTo();

	    for (Payment payment : paymentList) {
	        if (payment.getBelongsTo().equals(expenseOwner)) {
	            payment.setDone(true);
	            expenseOwner.addToYouAreOwed(expense.getAmount() - payment.getAmount()); 
	        } else {
	            User debtor = payment.getBelongsTo();
	            debtor.addToYouOwe(payment.getAmount());
	        }
	    }
	}


}
