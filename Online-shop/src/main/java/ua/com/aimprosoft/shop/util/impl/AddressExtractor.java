package ua.com.aimprosoft.shop.util.impl;

import javax.servlet.http.HttpServletRequest;

import ua.com.aimprosoft.shop.models.Address;
import ua.com.aimprosoft.shop.util.Extractor;
import ua.com.aimprosoft.shop.util.constant.ApplicationConstant;


public class AddressExtractor implements Extractor<Address>
{
	@Override
	public Address map(final HttpServletRequest request)
	{
		final Address address = new Address();
		address.setCountry(request.getParameter(ApplicationConstant.COUNTRY));
		address.setStreet(request.getParameter(ApplicationConstant.STREET));
		address.setTown(request.getParameter(ApplicationConstant.TOWN));
		address.setRegion(request.getParameter(ApplicationConstant.REGION));
		address.setPostalCode(request.getParameter(ApplicationConstant.POSTAL_CODE));
		return address;
	}
}
