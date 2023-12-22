package com.ttps2023.CuentasClaras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ttps2023.CuentasClaras.model.AmountPerMember;
import com.ttps2023.CuentasClaras.model.CrewCategory;
import com.ttps2023.CuentasClaras.model.EqualPerMember;
import com.ttps2023.CuentasClaras.model.ExpenseCategory;
import com.ttps2023.CuentasClaras.model.PercentagePerMember;
import com.ttps2023.CuentasClaras.model.SplitWay;
import com.ttps2023.CuentasClaras.repositories.CrewCategoryRepository;
import com.ttps2023.CuentasClaras.repositories.ExpenseCategoryRepository;
import com.ttps2023.CuentasClaras.repositories.SplitWayRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private SplitWayRepository splitWayRepository;

    @Autowired
    private CrewCategoryRepository crewCategoryRepository;

    @Autowired
    private ExpenseCategoryRepository expenseCategoryRepository;

    @Override
    public void run(String... args) throws Exception {
        // Verificar si los datos ya existen antes de crearlos
        if (splitWayRepository.count() == 0) {
            // Solo crear si no hay registros existentes
            SplitWay sw1 = new EqualPerMember("EqualPerMember");
            SplitWay sw2 = new AmountPerMember("AmountPerMember");
            SplitWay sw3 = new PercentagePerMember("PercentagePerMember");

            splitWayRepository.save(sw1);
            splitWayRepository.save(sw2);
            splitWayRepository.save(sw3);
        }

        // Repite el mismo patrón para CrewCategory y ExpenseCategory

        if (crewCategoryRepository.count() == 0) {
            CrewCategory cc1 = new CrewCategory("Familia", null);
            CrewCategory cc2 = new CrewCategory("Viaje", null);
            CrewCategory cc3 = new CrewCategory("Salida", null);
            CrewCategory cc4 = new CrewCategory("Otro", null);

            crewCategoryRepository.save(cc1);
            crewCategoryRepository.save(cc2);
            crewCategoryRepository.save(cc3);
            crewCategoryRepository.save(cc4);
        }

        if (expenseCategoryRepository.count() == 0) {
            ExpenseCategory ec1 = new ExpenseCategory("Familia", null);
            ExpenseCategory ec2 = new ExpenseCategory("Viaje", null);
            ExpenseCategory ec3 = new ExpenseCategory("Salida", null);
            ExpenseCategory ec4 = new ExpenseCategory("Otro", null);

            expenseCategoryRepository.save(ec1);
            expenseCategoryRepository.save(ec2);
            expenseCategoryRepository.save(ec3);
            expenseCategoryRepository.save(ec4);
        }
    }
}


//splitway equalPerMember

//categorias de grupo como de gasto

//