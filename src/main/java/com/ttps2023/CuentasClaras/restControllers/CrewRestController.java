package com.ttps2023.CuentasClaras.restControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.ttps2023.CuentasClaras.model.Crew;
import com.ttps2023.CuentasClaras.model.Expense;
import com.ttps2023.CuentasClaras.model.ExpenseCategory;
import com.ttps2023.CuentasClaras.model.User;
import com.ttps2023.CuentasClaras.repositories.ExpenseRepository;
import com.ttps2023.CuentasClaras.repositories.UserRepository;
import com.ttps2023.CuentasClaras.services.CrewService;
import com.ttps2023.CuentasClaras.services.ExpenseCategoryService;
import com.ttps2023.CuentasClaras.services.ExpenseService;
import com.ttps2023.CuentasClaras.services.UserService;

import jakarta.persistence.Column;

@RestController
@RequestMapping(value = "/crew", produces = MediaType.APPLICATION_JSON_VALUE)
public class CrewRestController {

	private final CrewService crewService;
	private final ExpenseService expenseService;
	private final UserService userService;
	private final ExpenseCategoryService expenseCategoryService;

	public CrewRestController(CrewService crewService, ExpenseService expenseService, UserService userService, ExpenseCategoryService expenseCategoryService) {
	    this.crewService = crewService;
	    this.expenseService = expenseService;
	    this.userService = userService;
	    this.expenseCategoryService = expenseCategoryService;
	}


	@PostMapping("/create")
	public ResponseEntity<String> createCrew(@RequestBody Crew crew) {
		crewService.create(crew);
		return new ResponseEntity<String>("Grupo creado", HttpStatus.CREATED);
	}

	@PutMapping("/{crewId}/update")
	public Crew updateCrew(@RequestBody Map<String, Object> crewRequest, @PathVariable("crewId") Long crewId) {
		String name = (String) crewRequest.get("name");
		Boolean isActive = (Boolean) crewRequest.get("active");
		return crewService.updateCrew(crewId, name, isActive);
	}

	@PostMapping("/{crewId}/createExpense")
			public ResponseEntity<String> createExpenseInCrew(@RequestBody Map<String, Object> request, @PathVariable("crewId") Long crewId) {
			
			 	Optional<User> userQuery = userService.getById((Long)request.get("belongsToId"));
			 	User user = userQuery.orElse(null);
			 	
			 	Optional<ExpenseCategory> categoryQuery = expenseCategoryService.getById((Long)request.get("categoryId"));
			 	ExpenseCategory category = categoryQuery.orElse(null);
			 
			 	//Expense expense = new Expense(belongsTo, crew, amount, category, date, isPaid, paymentList, splitway)
			 
			 
			 	Optional<Crew> crewQuery = crewService.getById(crewId);
				Crew crewBd = crewQuery.orElse(null);
				if (crewBd == null) {
					return new ResponseEntity<String>("Datos del grupo incorrectos.", HttpStatus.FORBIDDEN);
				}
				
//				crewService.createExpenseInCrew(crewBd.getId(), expense, splitwayId);
				
				return new ResponseEntity<String>("Gasto creado en el grupo", HttpStatus.CREATED);
			}

	@GetMapping("/{crewId}/expenseList")
	public List<Expense> showExpenseList(@PathVariable("crewId") Long crewId) {
		crewService.getById(crewId);
		Optional<Crew> crewQuery = crewService.getById(crewId);
		Crew crewBD = crewQuery.orElse(null); // controlar el null
		return crewBD.getExpenseList();
	}

	@PutMapping("/updateExpense/{expenseId}") // no funciona
	public Expense updateExpense(@RequestBody Map<String, Object> expenseRequest,
			@PathVariable("expenseId") Long expenseId) {

//			 
//			 	System.out.println(expenseRequest.get("amount").getClass());
//				Float amount = Float.par //expenseRequest.get("amount"); // pongo amount solo pero se deberia todo
//				
		Date date = new Date((Long) expenseRequest.get("date"));
		// (Date) expenseRequest.get("date");

		return expenseService.updateExpense(expenseId, date);

	}
}

//		@PostMapping("/update/{id}")
//		public ResponseEntity<String> getById(@PathVariable Long id, @RequestHeader("Token") String token) {
//			
//			Optional<Crew> crewQuery= crewService.getById(id);
//			
//			Crew crew=crewQuery.orElse(null);
//			if (crew==null) {
//				return new ResponseEntity<String>("Grupo no encontrado.", HttpStatus.NOT_FOUND);
//			}
//			
//			if (!token.equals(crew.getId()+"/123456")) {
//				return new ResponseEntity<String>("Grupo no encontrado.", HttpStatus.UNAUTHORIZED);
//			}
//			
//			
//			HttpHeaders responseHeaders = new HttpHeaders();					  //
//			responseHeaders.set("Token", crew.getId() + "/123456");
//		
//			return ResponseEntity.accepted().headers(responseHeaders).body(null);
//		}

//		public ResponseEntity<String> createUser(@RequestBody Crew crew) {
//			if (crewService.existCrewName(crew.getCrewName())) {
//				return new ResponseEntity<String>("Grupo con ese nombre ya existe", HttpStatus.CONFLICT);
//			}
