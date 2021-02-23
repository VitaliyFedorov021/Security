package ua.com.aimprosoft.shop.util.converters;

import java.util.ArrayList;
import java.util.List;

import ua.com.aimprosoft.shop.dto.CartEntryDto;
import ua.com.aimprosoft.shop.entities.CartEntry;


public class CartEntryConverter
{

	private static CartEntryDto entityToDto(final CartEntry cartEntry)
	{
		final CartEntryDto dto = new CartEntryDto();
		dto.setProduct(cartEntry.getProduct());
		dto.setQuantity(cartEntry.getQuantity());
		dto.setTotalPrice(cartEntry.getTotalPrice());
		return dto;
	}

	private static CartEntry dtoToEntity(final CartEntryDto cartEntryDto)
	{
		final CartEntry entry = new CartEntry();
		entry.setProduct(cartEntryDto.getProduct());
		entry.setQuantity(cartEntryDto.getQuantity());
		entry.setTotalPrice(cartEntryDto.getTotalPrice());
		return entry;
	}

	public static List<CartEntryDto> castToDto(final List<CartEntry> entries)
	{
		final List<CartEntryDto> entriesDto = new ArrayList<>();
		for (final CartEntry entry : entries)
		{
			entriesDto.add(CartEntryConverter.entityToDto(entry));
		}
		return entriesDto;
	}

	public static List<CartEntry> castToEntry(final List<CartEntryDto> dtos)
	{
		final List<CartEntry> entries = new ArrayList<>();
		for (final CartEntryDto dto : dtos)
		{
			entries.add(CartEntryConverter.dtoToEntity(dto));
		}
		return entries;
	}
}
