package strategies.load;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;

import currency.CurrencyCode;
import exceptions.ConnectionException;
import exceptions.CurrencyException;

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
	public String load(CurrencyCode code, LocalDate date) {
		LoadingUtils.checkIsDateAfterToday(date);
		for(int attempts = 10; attempts > 0; --attempts) {
			LoadingUtils.verifyItIsNotWeekend(date);
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
		return null;
	}
}