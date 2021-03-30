package service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;

import exceptions.ConnectionException;
import exceptions.CurrencyException;
import exceptions.InvalidDateException;
import model.CurrencyCode;

public class NbpLoader implements Loading {

	String url = "http://api.nbp.pl/api/exchangerates/rates/a/%s/%s/?format=json";
	
	public NbpLoader() {
	}

	public NbpLoader(String dataPath) {
		this.url = dataPath;
	}

	private static final HttpClient httpClient = HttpClient.newBuilder()
			.version(HttpClient.Version.HTTP_1_1)
			.connectTimeout(Duration.ofSeconds(10))
			.build();

	@Override
	public String load(CurrencyCode code, LocalDate sourceDate) {
		LoadingUtils.checkIsDateAfterToday(sourceDate);
		LocalDate date = sourceDate;
		for(int attempts = 10; attempts > 0; --attempts) {
			date = LoadingUtils.verifyAndSkipIfItIsWeekend(date);
			HttpRequest request = HttpRequest.newBuilder()
					.GET()
					.uri(URI.create(String.format(url, code, date)))
					.build();
			HttpResponse<String> response;
			try {
				response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
				if (response.statusCode() == 200) {
					return response.body();
				}
			} catch (IOException e) {
				throw new ConnectionException("Unable to connect to the API: " + url, e);
			} catch (InterruptedException e) {
				throw new CurrencyException(e);
			}	
			date = date.minusDays(1);
		}
		throw new InvalidDateException(String.format("There is no data for currency %s from %s.", code, sourceDate.toString()));
	}
}