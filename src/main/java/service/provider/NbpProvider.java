package service.provider;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;

import exceptions.ConnectionException;
import exceptions.CurrencyException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import model.CurrencyCode;

@NoArgsConstructor
@AllArgsConstructor
public class NbpProvider implements Providing<String> {
	
	private String url = "http://api.nbp.pl/api/exchangerates/rates/a/%s/%s/?format=json";

	private static final HttpClient httpClient = HttpClient.newBuilder()
			.version(HttpClient.Version.HTTP_1_1)
			.connectTimeout(Duration.ofSeconds(10))
			.build();

	@Override
	public String find(CurrencyCode code, LocalDate date) {
		HttpRequest request = HttpRequest.newBuilder()
				.GET()
				.uri(URI.create(String.format(url, code, date)))
				.build();
		HttpResponse<String> response;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			return response.statusCode() == 200 ? response.body() : null;
		} catch (IOException e) {
			throw new ConnectionException("Unable to connect to the API: " + url, e);
		} catch (InterruptedException e) {
			throw new CurrencyException(e);
		}
	}
}