package ua.com.aimprosoft.shop.util.constant;




public class ApplicationConstant
{
	public static final String JDBC_URL = "jdbcUrl";
	public static final String USERNAME = "username";

	public static final String ENTRY_ID = "entryId";

	public static final String EMAIL = "email";
	public static final String PASSWORD = "password";
	public static final String FIRST_NAME = "first_name";
	public static final String LAST_NAME = "last_name";
	public static final String GENDER = "gender";
	public static final String BIRTHDAY = "birthday_date";
	public static final String NUMBER = "phone_number";
	public static final String CUSTOMER_ID = "customer_id";

	public static final String ADDRESS_ID = "address_id";
	public static final String COUNTRY = "country";
	public static final String POSTAL_CODE_DB = "postal_code";
	public static final String POSTAL_CODE = "postalCode";
	public static final String REGION = "region";
	public static final String TOWN = "town";
	public static final String STREET = "street";

	public static final String PLACED_DATE = "placed_date";
	public static final String TOTAL_PRICE = "total_price";

	public static final String CATEGORY_ID = "category_id";
	public static final String CATEGORY_NAME = "category_name";
	public static final String CATEGORY_CODE = "category_code";
	public static final String CART_ID = "cart_id";
	public static final String CART_CODE = "cart_code";
	public static final String CART_TOTAL_PRICE = "cart_total_price";

	public static final String ENTRY_NUMBER = "entry_number";
	public static final String CURRENT_ENTRY_NUMBER = "current_entry";
	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String PRODUCT_QUANTITY = "product_quantity";
	public static final String PRODUCT_ID = "product_id";
	public static final String PRODUCT_CODE = "productCode";
	public static final String PRODUCT_NAME = "product_name";
	public static final String PRODUCT_CODE_DB = "product_code";
	public static final String QUANTITY = "quantity";
	public static final String CART = "cart";

	public static final String PRICE = "price";
	public static final String DESCRIPTION = "description";

	public static final String HOME_PAGE = "/homePage.jsp";
	public static final String LOGIN_PAGE = "/loginPage.jsp";
	public static final String SIGN_UP_PAGE = "/signUpPage.jsp";
	public static final String HOME = "/";
	public static final String DB_PROPERTIES = "hikariDB.properties";
	public static final String LOGIN_PATH = "/login?command=Login";
	public static final String CHECKOUT_COMMAND = "/placeorder?command=CheckoutPage";
	public static final String CONFIRMATION_PAGE_PATH = "/confirmorder?command=OrderConfirmationPage&code=%s";
	public static final String CONFIRMATION_PATH = "/confirmorder?command=SaveDeliveryAddress";
	public static final String LOGIN_PAGE_PATH = "/login/page?command=LoginPage";
	public static final String CATEGORIES_PAGE = "/categoriesPage.jsp";
	public static final String PLP_PATH = "/plp.jsp";
	public static final String PDP_PATH = "/pdp.jsp";
	public static final String ERROR_PATH = "/error.jsp";
	public static final String EMPTY_CART = "/emptyCart.jsp";
	public static final String SHOW_CART = "/showCart.jsp";
	public static final String SHOW_CART_COMMAND = "/cart?command=CartPage";
	public static final String CHECKOUT_PAGE = "/checkout.jsp";
	public static final String CONFIRMATION_PAGE = "/orderConfirmation.jsp";

	public static final String MESSAGE = "message";
	public static final String CUSTOMER = "customer";
	public static final String COMMAND = "command";
	public static final String EMPTY = "empty";
	public static final String CATEGORIES = "categories";
	public static final String PRODUCTS = "products";
	public static final String PRODUCT = "product";
	public static final String REFERER = "referer";

	public static final String SLASH = "/";
	public static final String DELIMITER = "=====================";
	public static final String QUESTION_MARK = "?";
	public static final String COLON = ": ";

	public static final String ALGORITHM = "MD5";
	public static final String COMMAND_PATH = "ua.com.aimprosoft.shop.controller.command.impl.%sCommand";

	public static final String EMAIL_PATTERN = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
	public static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[\\d])[a-zA-Z\\d]{6,}";
	public static final String NAME_PATTERN = "^[A-Z][a-z]*$";
	public static final String NUMBER_PATTERN = "^[0-9]{10}$";
	public static final String DATE_PATTERN = "yyyy-MM-dd";

	public static final String TYPE_TEXT = "text/plain";
	public static final String SUCCESS_TEXT = "Added to cart";
	public static final String PATH = "path";

	public static final String REQUEST_HEADERS = "REQUEST_HEADERS";
	public static final String REQUEST_PARAMS = "REQUEST_PARAMS";
	public static final String RESPONSE_HEADERS = "RESPONSE_HEADERS";
	public static final String END = "END";
}
