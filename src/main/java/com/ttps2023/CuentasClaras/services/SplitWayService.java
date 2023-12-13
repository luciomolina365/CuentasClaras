package com.ttps2023.CuentasClaras.services;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ttps2023.CuentasClaras.model.AmountPerMember;
import com.ttps2023.CuentasClaras.model.EqualPerMember;
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

	public SplitWay getById(Long id) {
		
		Optional<SplitWay> splitWayOptional = splitWayRepository.findById(id);

	    if (splitWayOptional.isPresent()) {
	        return splitWayOptional.get();
	    } else {
	        throw new EntityNotFoundException("SplitWay no encontrado con ID: " + id);
	    }
	}


	
//    public SplitWay getById(Long id) {
//        String splitWayType = splitWayRepository.findSplitWayTypeById(id);
//
//        if (splitWayType != null) {
//            switch (splitWayType) {
//                
//            	case "AmountPerMember":
//                    return new AmountPerMember("AmountPerMember");
//                
//                case "EqualPerMember":
//                    return new EqualPerMember("EqualPerMember");
//                
//                case "PercentagePerMember":
//                    return new PercentagePerMember("PercentagePerMember");
//                    
//                default:
//                    throw new IllegalArgumentException("Tipo de SplitWay no reconocido: " + splitWayType);
//            }
//        } else {
//            throw new EntityNotFoundException("SplitWay no encontrado con ID: " + id);
//        }
//    }


	// recibo la lista de usuarios q van a estar dentro de este gasto tambien pasar
	// splitway y expense
	// entonces segun q ocurra haria una funcion u otra y devuelve la paymentList

	// la duda es si tengo q poner un valor especificco para cada user, como hacer
	// si lo tengo q enviar a una nueva pestaña

}
