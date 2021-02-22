package ua.com.aimprosoft.shop.entities;

import java.util.Objects;


public class Entity
{
	private int id;

	public int getId()
	{
		return id;
	}

	public void setId(final int id)
	{
		this.id = id;
	}

	@Override
	public boolean equals(final Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}
		final Entity entity = (Entity) o;
		return id == entity.id;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id);
	}
}
