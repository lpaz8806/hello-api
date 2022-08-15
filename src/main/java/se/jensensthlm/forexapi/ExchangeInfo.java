package se.jensensthlm.forexapi;

public record ExchangeInfo(String sourceCurrency, String targetCurrency, double exchangeRate) { }
