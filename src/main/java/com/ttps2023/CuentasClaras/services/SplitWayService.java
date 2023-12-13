package com.ttps2023.CuentasClaras.services;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ttps2023.CuentasClaras.model.AmountPerMember;
import com.ttps2023.CuentasClaras.model.EqualPerMember;
import com.ttps2023.CuentasClaras.model.PercentagePerMember;
import com.ttps2023.CuentasClaras.model.SplitWay;
import com.ttps2023.CuentasClaras.repositories.SplitWayRepository;

@Service
public class SplitWayService {
	private final SplitWayRepository splitWayRepository;

	public SplitWayService(SplitWayRepository splitWayRepository) {
		this.splitWayRepository = splitWayRepository;
	}

	public SplitWay getById(Long id) {
	    Map<String, Object> auxSplitway = splitWayRepository.findSplitWayById(id);

	    String splitWayType = (String) auxSplitway.get("split_way_type");
	    String splitWayName = (String) auxSplitway.get("name");

	    return createSplitWay(splitWayType, splitWayName);
	}

	private SplitWay createSplitWay(String splitWayType, String splitWayName) {
	    switch (splitWayType) {
	        case "AmountPerMember":
	            return new AmountPerMember(splitWayName);

	        case "EqualPerMember":
	            return new EqualPerMember(splitWayName);

	        case "PercentagePerMember":
	            return new PercentagePerMember(splitWayName);

	        default:
	            
	            throw new IllegalArgumentException("Tipo de SplitWay no válido: " + splitWayType);
	    }
	}


	public void create(String splitwayType) {

		SplitWay splitway = null;
		if (splitwayType.equals("AmountPerMember")) {
			splitway = new AmountPerMember(splitwayType);
		}
		if (splitwayType.equals("EqualPerMember")) {
			splitway = new EqualPerMember(splitwayType);
		}
		if (splitwayType.equals("PercetagePerMember")) {
			splitway = new PercentagePerMember(splitwayType);
		}

		splitWayRepository.save(splitway);
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
