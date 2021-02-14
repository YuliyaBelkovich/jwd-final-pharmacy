package com.epam.jwd.command.impl.entity.basket;

import com.epam.jwd.domain.Basket;
import com.epam.jwd.command.Command;
import com.epam.jwd.context.RequestContext;
import com.epam.jwd.context.ResponseContext;
import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.criteria.RecipeCriteria;
import com.epam.jwd.domain.Medicine;
import com.epam.jwd.domain.Recipe;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.service.entity.impl.MedicineService;
import com.epam.jwd.service.entity.impl.RecipeService;

import java.time.LocalDate;
import java.util.List;

/**
 * Command responsible for adding {@link Medicine} objects to {@link Basket} and updating the total price
 * Checks if medicine requires recipe and automatically sets amount of the ordered medicine as described in the recipe
 * Sends error message if user is trying to add to the basket medicine, that requires recipe, and doesn't has it
 */
public class AddToBasketCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        int id = 0;
        int amount = 0;
        int userId = 0;
        String url = (String) requestContext.getSession().getAttribute("previousPage");

        if (requestContext.hasParameter("medicine_id") && requestContext.hasParameter("amount")) {
            id = Integer.parseInt(requestContext.getParameter("medicine_id"));
            amount = Integer.parseInt(requestContext.getParameter("amount"));
            if (requestContext.getSession().getAttribute("user_id") != null) {
                userId = (Integer) requestContext.getSession().getAttribute("user_id");
            }
        }

        Medicine medicine;
        try {
            medicine = MedicineService.getInstance().findById(id);
            if (medicine.isRecipeRequirement()) {
                if (requestContext.getSession().getAttribute("user_role").equals("GUEST")) {
                    return () -> url + "&error=This+medicine+requires+recipe.+Please+contact+your+doctor+to+get+an+appropriate+recipe";
                }
                Criteria<Recipe> criteria = RecipeCriteria.builder().setPatientId(userId).setMedicineId(id).build();
                List<Recipe> recipeList = RecipeService.getInstance().findByCriteria(criteria);
                if (!recipeList.isEmpty()) {
                    if(recipeList.get(0).getDate().plusDays(recipeList.get(0).getDuration()).isAfter(LocalDate.now())) {
                        amount = (int) recipeList.get(0).getDose();
                    } else {
                        return ()-> url+"&error=Your+recipe+expired";
                    }
                } else {
                    return () -> url + "&error=This+medicine+requires+recipe.+Please+contact+your+doctor+to+get+an+appropriate+recipe";

                }
            }
            Basket basket = Basket.getInstance();
            basket.addToBasket(medicine, amount);
            requestContext.getSession().setAttribute("basket", basket);
            requestContext.getSession().setAttribute("basketPrice", basket.getTotalPrice());
        } catch (EntityNotFoundException | DAOException e) {
            return () -> url + "&error=Error+accused+while+adding+to+basket";
        }
        return () -> url + "&message=Medicine+added!&error=";
    }
}
