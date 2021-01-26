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

	public static final String CATEGORY_ID = "c_id";
	public static final String CATEGORY_NAME = "c_name";
	public static final String CATEGORY_CODE = "c_code";

	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String PRODUCT_QUANTITY = "p_q";

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
	public static final String ERROR_PATH = "/error.jsp";

	public static final String MESSAGE = "message";
	public static final String CUSTOMER = "customer";
	public static final String COMMAND = "command";
	public static final String EMPTY = "empty";
	public static final String CATEGORIES = "categories";
	public static final String PRODUCTS = "products";

	public static final String SLASH = "/";
	public static final String ALGORITHM = "MD5";
	public static final String COMMAND_PATH = "ua.com.aimprosoft.shop.controller.command.impl.%sCommand";

	public static final String EMAIL_PATTERN = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
	public static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[\\d])[a-zA-Z\\d]{6,}";
	public static final String NAME_PATTERN = "^[A-Z][a-z]*$";
	public static final String NUMBER_PATTERN = "^[0-9]{10}$";
	public static final String DATE_PATTERN = "yyyy-MM-dd";
}
