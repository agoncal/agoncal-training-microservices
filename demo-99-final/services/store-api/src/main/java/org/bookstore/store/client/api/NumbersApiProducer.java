package org.bookstore.store.client.api;

import org.bookstore.store.client.ApiClient;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class NumbersApiProducer {
    @Produces
    public NumbersApi createNumbersApi() {
        return new ApiClient().buildNumberApiClient();
    }
}
