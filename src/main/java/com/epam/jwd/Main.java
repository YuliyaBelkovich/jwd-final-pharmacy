package com.epam.jwd;

import com.epam.jwd.context.PharmacyContext;
import com.epam.jwd.domain.*;
import com.epam.jwd.service.impl.*;
import com.epam.jwd.service.mail.MailService;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) throws Exception {
        PharmacyContext.getInstance().init();
//        Criteria<Medicine> criteria = MedicineCriteria.builder().setPrice(2.5).build();
//        List<Medicine> medicineList = null;
//        try {
//            medicineList = MedicineService.getInstance().findByCriteria(criteria);
//        } catch (EntityNotFoundException e) {
//            e.printStackTrace();
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(medicineList.toString());
//
//        Map<Integer,Integer> orderedMedicines = new HashMap<>();
//        orderedMedicines.put(1,20);
//        Order order = OrderService.getInstance().createEntity(0, 5.40, 0, "NOT PAID", orderedMedicines);
//        order.toString();

        MailService.getInstance().sendPDFEmail("lily.belkovich@gmail.com","test","test","vlkerhaifulkjdncljkvelkrjvbdslkjfrekjekjvmcx,,xx,v,fke;akandmdgkfjhfdkvjnekjrnkejvnkjvnfkevjnekjvnkevnkjf");
    }
}
