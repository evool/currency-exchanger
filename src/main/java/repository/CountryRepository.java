package repository;

import entity.CountryEntity;

public interface CountryRepository {
	CountryEntity getCountryById(Long id);
	CountryEntity getCountryByName(String name);
	void saveCountry(CountryEntity country);
}
