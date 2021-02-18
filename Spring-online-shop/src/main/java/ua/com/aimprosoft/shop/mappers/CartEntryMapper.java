package ua.com.aimprosoft.shop.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ua.com.aimprosoft.shop.models.CartEntry;
import ua.com.aimprosoft.shop.models.Product;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public class CartEntryMapper implements RowMapper<CartEntry>
{

	@Override
	public CartEntry mapRow(final ResultSet resultSet, final int i) throws SQLException
	{
		final CartEntry cartEntry = new CartEntry();
		cartEntry.setEntryNumber(resultSet.getInt(ApplicationConstant.ENTRY_NUMBER_DB));
		cartEntry.setQuantity(resultSet.getInt(ApplicationConstant.PRODUCT_QUANTITY_DB));
		cartEntry.setId(resultSet.getInt(ApplicationConstant.ID));
		cartEntry.setTotalPrice(resultSet.getDouble(ApplicationConstant.TOTAL_PRICE_DB));
		final Product product = new Product();
		product.setId(resultSet.getInt(ApplicationConstant.PRODUCT_ID_DB));
		product.setPrice(resultSet.getInt(ApplicationConstant.PRICE));
		product.setName(resultSet.getString(ApplicationConstant.PRODUCT_NAME_DB));
		product.setDescription(resultSet.getString(ApplicationConstant.DESCRIPTION));
		product.setCode(resultSet.getString(ApplicationConstant.PRODUCT_CODE_DB));
		cartEntry.setProduct(product);
		return cartEntry;
	}
}
