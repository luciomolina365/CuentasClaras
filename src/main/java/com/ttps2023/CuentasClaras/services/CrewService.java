package com.ttps2023.CuentasClaras.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttps2023.CuentasClaras.model.Crew;
import com.ttps2023.CuentasClaras.model.CrewCategory;
import com.ttps2023.CuentasClaras.model.Expense;
import com.ttps2023.CuentasClaras.model.Payment;
import com.ttps2023.CuentasClaras.model.User;
import com.ttps2023.CuentasClaras.repositories.CrewRepository;

@Service
public class CrewService {

	private final CrewRepository crewRepository;

	@Autowired
	private SplitWayService splitwayService;

	public CrewService(CrewRepository crewRepository) {
		this.crewRepository = crewRepository;
	}

	public Boolean exists(Long id) {
		return crewRepository.existsById(id);
	}

	@Transactional
	public void create(Crew crew) {
		
		List<User> membersList = crew.getMembersList();
		for (User user : membersList) {
			user.getCrewList().add(crew);
		}
		
		crewRepository.save(crew);
	}

	public Optional<Crew> getById(Long id) {
		return crewRepository.findById(id);
	}

	public Crew updateCrew(Long crewId, String name, Boolean isActive, CrewCategory category) {
		Optional<Crew> crewQuery = crewRepository.findById(crewId);
		Crew crewBD = crewQuery.orElse(null); // se rompe si no existe ver si hace falta

		crewBD.setCategory(category);
		crewBD.setName(name);
		crewBD.setActive(isActive);

		return crewRepository.save(crewBD);

	}
	
	@Transactional
	public void createExpenseInCrew(Crew crew, Expense expense) {


		List<Payment> paymentList = splitwayService.expenseSplit(expense, crew);

		//ACTUALIZAR MONTOS USUARIOS
		
		expense.setPaymentList(paymentList);
		
		
		crew.addExpense(expense);

		crewRepository.save(crew);
	}

}

