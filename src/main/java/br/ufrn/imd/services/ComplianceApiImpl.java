package br.ufrn.imd.services;

import br.ufrn.imd.models.BankAccount;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ComplianceApiImpl implements ComplianceApi {

    private final HttpClient client;

    public ComplianceApiImpl(){
        client = HttpClient.newHttpClient();
    }

    @Override
    public boolean CanItReceiveNewDeposit(BankAccount account, double value) {
        try {
            var route = String.format( "api/compliance/account/%s/deposit", account.getId().toString());
            var request = createNewRequest(route);
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 500)
                throw new RuntimeException("COMPLIANCE API NOT AVAILABLE");

            return response.statusCode() >= 200 && response.statusCode() <= 400;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean CanItReceiveNewWithdraw(BankAccount account, double value) {
        try {
            var route = String.format( "api/compliance/account/%s/deposit", account.getId().toString());
            var request = createNewRequest(route);
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 500)
                throw new RuntimeException("COMPLIANCE API NOT AVAILABLE");

            return response.statusCode() >= 200 && response.statusCode() <= 400;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static HttpRequest createNewRequest(String route){
        var url = String.format("http://localhost:8081/%s", route);
        var uri = URI.create(url);

        return HttpRequest.newBuilder().uri(uri).build();
    }
}
