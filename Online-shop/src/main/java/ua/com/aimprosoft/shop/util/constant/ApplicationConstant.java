package ua.com.aimprosoft.shop.util.constant;




public class ApplicationConstant
{
	public static final String JDBC_URL = "jdbcUrl";
	public static final String USERNAME = "username";

	public static final String EMAIL = "email";
	public static final String PASSWORD = "password";
	public static final String FIRST_NAME = "first_name";
	public static final String LAST_NAME = "last_name";
	public static final String GENDER = "gender";
	public static final String BIRTHDAY = "birthday_date";
	public static final String NUMBER = "phone_number";
	public static final String CUS_ID = "cus_id";

	public static final String A_ID = "a_id";
	public static final String COUNTRY = "country";
	public static final String POSTAL_CODE = "postal_code";
	public static final String REGION = "region";
	public static final String TOWN = "town";
	public static final String STREET = "street";

	public static final String PLACED_DATE = "placed_date";
	public static final String TOTAL_PRICE = "total_price";

	public static final String C_ID = "c_id";
	public static final String C_NAME = "c_name";
	public static final String C_CODE = "c_code";
	public static final String C2_ID = "c2_id";
	public static final String C2_CODE = "c2_code";
	public static final String C2_TOTAL_PRICE = "c2_total_price";

	public static final String ENTRY_NUMBER = "entry_number";
	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String PRODUCT_QUANTITY = "p_q";
	public static final String PRODUCT_ID = "product_id";
	public static final String PRODUCT_CODE = "productCode";
	public static final String P_ID = "p_id";
	public static final String P_NAME = "p_name";
	public static final String P_CODE = "p_code";
	public static final String QUANTITY = "quantity";

	public static final String PRICE = "price";
	public static final String DESCRIPTION = "description";

	public static final String HOME_PAGE = "/homePage.jsp";
	public static final String LOGIN_PAGE = "/loginPage.jsp";
	public static final String SIGN_UP_PAGE = "/signUpPage.jsp";
	public static final String HOME = "/";
	public static final String DB_PROPERTIES = "hikariDB.properties";
	public static final String LOGIN_PATH = "/login?command=Login";
	public static final String CATEGORIES_PAGE = "/categoriesPage.jsp";
	public static final String PLP_PATH = "/plp.jsp";
	public static final String PDP_PATH = "/pdp.jsp";
	public static final String ERROR_PATH = "/error.jsp";

	public static final String MESSAGE = "message";
	public static final String CUSTOMER = "customer";
	public static final String COMMAND = "command";
	public static final String EMPTY = "empty";
	public static final String CATEGORIES = "categories";
	public static final String PRODUCTS = "products";
	public static final String PRODUCT = "product";

	public static final String SLASH = "/";
	public static final String ALGORITHM = "MD5";
	public static final String COMMAND_PATH = "ua.com.aimprosoft.shop.controller.command.impl.%sCommand";

	public static final String EMAIL_PATTERN = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
	public static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[\\d])[a-zA-Z\\d]{6,}";
	public static final String NAME_PATTERN = "^[A-Z][a-z]*$";
	public static final String NUMBER_PATTERN = "^[0-9]{10}$";
	public static final String DATE_PATTERN = "yyyy-MM-dd";
}
