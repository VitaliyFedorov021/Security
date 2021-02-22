package ua.com.aimprosoft.shop.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ua.com.aimprosoft.shop.entities.Cart;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public class CartMapper implements RowMapper<Cart>
{
	@Override
	public Cart mapRow(final ResultSet resultSet, final int i) throws SQLException
	{
		final Cart cart = new Cart();
		cart.setId(resultSet.getInt(ApplicationConstant.ID));
		cart.setCode(resultSet.getString(ApplicationConstant.CODE));
		cart.setPlacedDate(resultSet.getDate(ApplicationConstant.PLACED_DATE_DB));
		cart.setTotalPrice(resultSet.getDouble(ApplicationConstant.TOTAL_PRICE_DB));
		return cart;
	}
}
