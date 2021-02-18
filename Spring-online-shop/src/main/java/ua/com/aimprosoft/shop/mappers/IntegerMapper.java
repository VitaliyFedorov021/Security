package ua.com.aimprosoft.shop.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public class IntegerMapper implements RowMapper<Integer>
{
	@Override
	public Integer mapRow(final ResultSet resultSet, final int i) throws SQLException
	{
		return resultSet.getInt(ApplicationConstant.CURRENT_ENTRY_DB);
	}
}
