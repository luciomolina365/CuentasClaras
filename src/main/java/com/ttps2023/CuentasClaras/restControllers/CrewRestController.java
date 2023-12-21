package com.ttps2023.CuentasClaras.restControllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttps2023.CuentasClaras.model.Crew;
import com.ttps2023.CuentasClaras.model.CrewCategory;
import com.ttps2023.CuentasClaras.model.Expense;
import com.ttps2023.CuentasClaras.model.ExpenseCategory;
import com.ttps2023.CuentasClaras.model.SplitWay;
import com.ttps2023.CuentasClaras.model.User;
import com.ttps2023.CuentasClaras.services.CrewCategoryService;
import com.ttps2023.CuentasClaras.services.CrewService;
import com.ttps2023.CuentasClaras.services.ExpenseCategoryService;
import com.ttps2023.CuentasClaras.services.ExpenseService;
import com.ttps2023.CuentasClaras.services.SplitWayService;
import com.ttps2023.CuentasClaras.services.UserService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
@RequestMapping(value = "/crew", produces = MediaType.APPLICATION_JSON_VALUE)
public class CrewRestController {

	private final CrewService crewService;
	private final ExpenseService expenseService;
	private final UserService userService;
	private final ExpenseCategoryService expenseCategoryService;
	private final SplitWayService splitwayService;
	private final CrewCategoryService crewCategoryService;

	public CrewRestController(CrewService crewService, ExpenseService expenseService, UserService userService,
			ExpenseCategoryService expenseCategoryService, SplitWayService splitwayService,
			CrewCategoryService crewCategoryService) {
		this.crewService = crewService;
		this.expenseService = expenseService;
		this.userService = userService;
		this.expenseCategoryService = expenseCategoryService;
		this.splitwayService = splitwayService;
		this.crewCategoryService = crewCategoryService;
	}

	@PostMapping("/create")
	public ResponseEntity<Object> createCrew(@RequestBody Map<String, Object> request) {

		if (!request.containsKey("membersList") || !request.containsKey("category") || !request.containsKey("name")
				|| !request.containsKey("isPrivate")) {
			return new ResponseEntity<>("Campos obligatorios faltantes en la solicitud.", HttpStatus.BAD_REQUEST);
		}

		List<User> membersList = new ArrayList<>();

		for (Number id : (List<Number>) request.get("membersList")) {
			Long userId = id.longValue();

			if (!userService.exists(userId)) {
				return new ResponseEntity<>("Usuario no encontrado con el ID proporcionado: " + id,
						HttpStatus.NOT_FOUND);
			}

			User user = userService.getById(userId).get();
			membersList.add(user);
		}

		Long categoryId = ((Number) request.get("category")).longValue();
		Optional<CrewCategory> categoryQuery = crewCategoryService.getById(categoryId);
		CrewCategory category = categoryQuery.orElse(null);

		if (category == null) {
			return new ResponseEntity<>("Categoria de grupo no encontrada con el ID proporcionado.",
					HttpStatus.NOT_FOUND);
		}

		String name = (String) request.get("name");
		Boolean isPrivate = (Boolean) request.get("isPrivate");

		Crew crew = new Crew(name, isPrivate, category, membersList);

		crewService.create(crew);

		return new ResponseEntity<Object>(crew, HttpStatus.CREATED);

	}

	@PutMapping("/{crewId}/update")
	public ResponseEntity<Object> updateCrew(@RequestBody Map<String, Object> request,
			@PathVariable("crewId") Long crewId) {

		if (!request.containsKey("category")) {
			return new ResponseEntity<>("Campo 'category' no encontrado en la solicitud.", HttpStatus.BAD_REQUEST);
		}

		Optional<Crew> crewQuery = crewService.getById(crewId);
		Crew crew = crewQuery.orElse(null);
		if (crew == null) {
			return new ResponseEntity<>("Grupo no encontrado con el ID proporcionado.", HttpStatus.NOT_FOUND);
		}

		Long categoryId = ((Number) request.get("category")).longValue();
		Optional<CrewCategory> categoryQuery = crewCategoryService.getById(categoryId);
		CrewCategory category = categoryQuery.orElse(null);
		if (category == null) {
			return new ResponseEntity<>("Categoría de grupo no encontrada con el ID proporcionado.",
					HttpStatus.NOT_FOUND);
		}

		if (request.containsKey("name")) {
			String name = (String) request.get("name");
			crew.setName(name);
		}

		if (request.containsKey("active")) {
			Boolean isActive = (Boolean) request.get("active");
			crew.setActive(isActive);
		}

		crew.setCategory(category);

		Crew updatedCrew = crewService.updateCrew(crew);

		return new ResponseEntity<>(updatedCrew, HttpStatus.ACCEPTED);
	}

	@PostMapping("/{crewId}/expense")
	public ResponseEntity<String> createExpenseInCrew(@RequestBody Map<String, Object> request,
			@PathVariable("crewId") Long crewId) {

		if (!request.containsKey("belongsToId") || !request.containsKey("categoryId")
				|| !request.containsKey("splitwayId") || !request.containsKey("amount")) {
			return new ResponseEntity<>("Campos requeridos no encontrados en la solicitud.", HttpStatus.BAD_REQUEST);
		}

		Long belongsToId = ((Number) request.get("belongsToId")).longValue();
		Optional<User> userQuery = userService.getById(belongsToId);
		User user = userQuery.orElse(null);
		if (user == null) {
			return new ResponseEntity<>("Usuario no encontrado con el ID proporcionado.", HttpStatus.NOT_FOUND);
		}

		Optional<Crew> crewQuery = crewService.getById(crewId);
		Crew crew = crewQuery.orElse(null);
		if (crew == null) {
			return new ResponseEntity<>("Grupo no encontrado con el ID proporcionado.", HttpStatus.NOT_FOUND);
		}

		Long categoryId = ((Number) request.get("categoryId")).longValue();
		Optional<ExpenseCategory> categoryQuery = expenseCategoryService.getById(categoryId);
		ExpenseCategory category = categoryQuery.orElse(null);
		if (category == null) {
			return new ResponseEntity<>("Categoría de gasto no encontrada con el ID proporcionado.",
					HttpStatus.NOT_FOUND);
		}

		Long splitwayId = ((Number) request.get("splitwayId")).longValue();
		Optional<SplitWay> splitwayQuery = splitwayService.getById(splitwayId);
		SplitWay splitway = splitwayQuery.orElse(null);
		if (splitway == null) {
			return new ResponseEntity<>("Método de reparto no encontrado con el ID proporcionado.",
					HttpStatus.NOT_FOUND);
		}

		Date date = new Date();
		Float amount = ((Number) request.get("amount")).floatValue();

		Expense expense = new Expense(user, crew, amount, category, date, false, null, splitway);

		crewService.createExpenseInCrew(crew, expense);

		return new ResponseEntity<>("Gasto creado en el grupo", HttpStatus.CREATED);
	}

	@PutMapping("/expense/{expenseId}")
	public ResponseEntity<Object> updateExpense(@RequestBody Map<String, Object> request,
			@PathVariable("expenseId") Long expenseId) {

		if (!request.containsKey("date")) {
			return new ResponseEntity<Object>("Campo 'date' no encontrado en la solicitud.", HttpStatus.BAD_REQUEST);
		}

		try {

			Date date = new Date((Long) request.get("date"));

			Expense expense = expenseService.updateExpense(expenseId, date);

			return new ResponseEntity<Object>(expense, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Error al procesar la solicitud.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}/expenses")
	public ResponseEntity<List<Expense>> getCrewExpenses(@PathVariable Long id) {
		List<Expense> crewExpenses = crewService.crewExpenseList(id);
		return new ResponseEntity<>(crewExpenses, HttpStatus.OK);
	}

}
