package com.ttps2023.CuentasClaras.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ttps2023.CuentasClaras.model.Crew;
import com.ttps2023.CuentasClaras.model.CrewCategory;
import com.ttps2023.CuentasClaras.model.Expense;
import com.ttps2023.CuentasClaras.model.ExpenseCategory;
import com.ttps2023.CuentasClaras.model.SplitWay;
import com.ttps2023.CuentasClaras.model.User;
import com.ttps2023.CuentasClaras.repositories.CrewRepository;
import com.ttps2023.CuentasClaras.repositories.ExpenseCategoryRepository;
import com.ttps2023.CuentasClaras.repositories.ExpenseRepository;
import com.ttps2023.CuentasClaras.repositories.SplitWayRepository;

@Service
public class CrewService {

	private final CrewRepository crewRepository;
	private final SplitWayRepository splitwayRepository;
	private final ExpenseCategoryRepository expenseCategoryRepository;

	public CrewService(CrewRepository crewRepository, SplitWayRepository splitwayRepository) {
		this.crewRepository = crewRepository;
		this.splitwayRepository = splitwayRepository;
		
	}

//    public Boolean existCrewName(String crewname) {
//		 return crewRepository.findByCrewName(crewname).isPresent()) }

	public Boolean exists(Long id) {
		return crewRepository.existsById(id);
	}

	public void create(Crew crew) {
		crewRepository.save(crew);
	}

	public Optional<Crew> getById(Long id) {
		return crewRepository.findById(id);
	}

	public Crew updateCrew(Long crewId, String name, Boolean isActive) {
		Optional<Crew> crewQuery = crewRepository.findById(crewId);
		Crew crewBD = crewQuery.orElse(null); // se rompe si no existe ver si hace falta

		crewBD.setName(name);
		crewBD.setActive(isActive);

		return crewRepository.save(crewBD);

	}

	public void createExpenseInCrew(Long crewId, Expense expense, Long splitwayId, Long categoryId) {

		Date date = new Date();

		Optional<Crew> crewQuery = crewRepository.findById(crewId);
		Crew crewBD = crewQuery.orElse(null);
		
		
		Optional<ExpenseCategory> categoryQuery = expenseCategoryRepository.findById(categoryId);
		ExpenseCategory category = categoryQuery.orElse(null);

		Optional<SplitWay> splitwayQuery = splitwayRepository.findById(splitwayId);
		SplitWay splitway = splitwayQuery.orElse(null);
		/// aca hay q hacer la creacion de los gastos pero splitway no puedo hacerlo
		
		
		Expense expenseBd = new Expense(expense.getBelongsTo(), crewBD, expense.getAmount(), category, date, false, null, splitway);

		crewBD.addExpense(expenseBd);

		crewRepository.save(crewBD);
	}

}
//	public Optional<Crew> getById(Long id) {
//		Optional<Crew> aux= crewRepository.findById(id);
//		Crew aux2 = aux.orElse(null);
//		
//		
//		System.out.println(aux2.getMembersList().toString());
//		
//		return aux;

//		return crewRepository.findById(id);

//	
//	 public List<User> getAllMembersList(Long crewId){
//		 return 
//	 }
//}
