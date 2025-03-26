package com.main.service;

import com.main.model.Order;
import com.main.response.PaymentResponse;
import com.stripe.exception.StripeException;

public interface PaymentService {
	
	public PaymentResponse createPaymentLink(Order order) throws StripeException;

}
