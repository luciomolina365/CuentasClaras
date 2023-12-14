package com.ttps2023.CuentasClaras.restControllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttps2023.CuentasClaras.model.Crew;
import com.ttps2023.CuentasClaras.model.Expense;
import com.ttps2023.CuentasClaras.model.ExpenseCategory;
import com.ttps2023.CuentasClaras.model.SplitWay;
import com.ttps2023.CuentasClaras.model.User;
import com.ttps2023.CuentasClaras.services.CrewService;
import com.ttps2023.CuentasClaras.services.ExpenseCategoryService;
import com.ttps2023.CuentasClaras.services.ExpenseService;
import com.ttps2023.CuentasClaras.services.SplitWayService;
import com.ttps2023.CuentasClaras.services.UserService;

@RestController
@RequestMapping(value = "/crew", produces = MediaType.APPLICATION_JSON_VALUE)
public class CrewRestController {

	private final CrewService crewService;
	private final ExpenseService expenseService;
	private final UserService userService;
	private final ExpenseCategoryService expenseCategoryService;
	private final SplitWayService splitwayService;

	public CrewRestController(CrewService crewService, ExpenseService expenseService, UserService userService,
			ExpenseCategoryService expenseCategoryService, SplitWayService splitwayService) {
		this.crewService = crewService;
		this.expenseService = expenseService;
		this.userService = userService;
		this.expenseCategoryService = expenseCategoryService;
		this.splitwayService = splitwayService;
	}

	@PostMapping("/create")
	public ResponseEntity<String> createCrew(@RequestBody Map<String, Object> request) {


		
		System.out.println(request.get("membersList"));
		
		
		
		List<User> membersList = null;
		Optional<User> userQuery;
		for (Number id : (List<Number>) request.get("membersList")) {
			userQuery = userService.getById((Long)id);
			User user = userQuery.orElse(null);
			if (user == null) {
				return new ResponseEntity<String>("Usuario no encontrado con el ID proporcionado: " + id , HttpStatus.NOT_FOUND);
			}		
			membersList.add(user);
		}
		
		String name = (String) request.get("name");
		Boolean isPrivate = (Boolean) request.get("isPrivate");
		
		Crew crew = new Crew(name, isPrivate, null, membersList);

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
	public ResponseEntity<String> createExpenseInCrew(@RequestBody Map<String, Object> request,
			@PathVariable("crewId") Long crewId) {

		Optional<User> userQuery = userService.getById(Long.valueOf((Integer) request.get("belongsToId")));
		User user = userQuery.orElse(null);

		if (user == null) {
			return new ResponseEntity<String>("Usuario no encontrado con el ID proporcionado.", HttpStatus.NOT_FOUND);
		}

		Optional<Crew> crewQuery = crewService.getById(crewId);
		Crew crew = crewQuery.orElse(null);

		if (crew == null) {
			return new ResponseEntity<String>("Grupo no encontrado con el ID proporcionado.", HttpStatus.NOT_FOUND);
		}

		Optional<ExpenseCategory> categoryQuery = expenseCategoryService
				.getById(Long.valueOf((Integer) request.get("belongsToId")));
		ExpenseCategory category = categoryQuery.orElse(null);

		if (category == null) {
			return new ResponseEntity<String>("Categoría de gasto no encontrada con el ID proporcionado.",
					HttpStatus.NOT_FOUND);
		}

		Optional<SplitWay> splitwayQuery = splitwayService.getById(Long.valueOf((Integer) request.get("splitwayId")));
		SplitWay splitway = splitwayQuery.orElse(null);

		if (splitway == null) {
			return new ResponseEntity<String>("Método de reparto no encontrado con el ID proporcionado.",
					HttpStatus.NOT_FOUND);
		}

		Date date = new Date();

		Float amount = ((Double) request.get("amount")).floatValue();

		Expense expense = new Expense(user, crew, amount, category, date, false, null, splitway);

		crewService.createExpenseInCrew(crew, expense);

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
