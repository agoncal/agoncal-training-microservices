package org.cdstore.book.client.api;

import org.cdstore.book.client.ApiClient;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class NumbersApiProducer {
    @Produces
    public NumbersApi createNumbersApi() {
        return new ApiClient().buildNumberApiClient();
    }
}
