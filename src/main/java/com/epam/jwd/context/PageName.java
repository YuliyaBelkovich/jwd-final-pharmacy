package com.epam.jwd.context;

/**
 * Class contains constants with the names of the JSP pages
 */
public enum PageName {
    MAIN_PAGE("/WEB-INF/jsp/MainPage.jsp"),
    LOG_IN_PAGE("/WEB-INF/jsp/LogInPage.jsp"),
    REGISTER_PAGE("/WEB-INF/jsp/RegisterPage.jsp"),
    SEARCH_MEDICINE_PAGE("/WEB-INF/jsp/SearchMedicinePage.jsp"),
    MEDICINE_PAGE("/WEB-INF/jsp/MedicinePage.jsp"),
    SEARCH_PATIENT_PAGE("/WEB-INF/jsp/SearchPatientPage.jsp"),
    PATIENT_PAGE("/WEB-INF/jsp/PatientPage.jsp"),
    SEARCH_DOCTOR_PAGE("/WEB-INF/jsp/SearchDoctorPage.jsp"),
    DOCTOR_PAGE("/WEB-INF/jsp/DoctorPage.jsp"),
    SEARCH_APPOINTMENT_PAGE("/WEB-INF/jsp/SearchAppointmentPage.jsp"),
    SEARCH_RECIPE_PAGE("/WEB-INF/jsp/SearchRecipePage.jsp"),
    RECIPE_PAGE("/WEB-INF/jsp/RecipePage.jsp"),
    SEARCH_ORDER_PAGE("/WEB-INF/jsp/SearchOrderPage.jsp"),
    ORDER_PAGE("/WEB-INF/jsp/OrderPage.jsp"),
    SEARCH_PAYMENT_PAGE("/WEB-INF/jsp/SearchPaymentPage.jsp"),
    PAYMENT_PAGE("/WEB-INF/jsp/PaymentPage.jsp"),
    ACCESS_DENIED_PAGE("/WEB-INF/jsp/AccessDeniedPage.jsp"),
    BASKET_PAGE("/WEB-INF/jsp/BasketPage.jsp"),
    ADD_PAYMENT("/WEB-INF/jsp/AddPaymentPage.jsp"),
    PAYMENT_SUCCESS("/WEB-INF/jsp/PaymentSuccess.jsp"),
    USER_PAGE("/WEB-INF/jsp/UserMainPage.jsp"),
    COMMAND_NOT_FOUND("/WEB-INF/jsp/CommandNotFound.jsp");


    private final String jspFileName;

    PageName(String jspFileName) {
        this.jspFileName = jspFileName;
    }

    public String getJspFileName() {
        return jspFileName;
    }
}
