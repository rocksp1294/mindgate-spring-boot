package com.mindgate.main.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.Address;
@Repository
public class AddressRepository implements AddressDetailsRepositoryInterface {

	private static final String INSERT_NEW_ADDRESS = "INSERT INTO address_details values(address_details_sequence.NEXTVAL,?,?,?,?)";
	private static final String SELECT_ALL_ADDRESS = "SELECT * FROM address_details";

	@Autowired

	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean addNewAddress(Address address) {
		Object[] params = { address.getBuildingName(), address.getStreet(), address.getCity(), address.getPin() };

		int result = jdbcTemplate.update(INSERT_NEW_ADDRESS, params);
		if (result > 0) {
			return true;

		}
		return false;

	}

	@Override
	public List<Address> getAllAddresses() {
		List<Address> allAddresses = jdbcTemplate.query(SELECT_ALL_ADDRESS, new AddressRowMapper());

		return allAddresses;
	}

}
