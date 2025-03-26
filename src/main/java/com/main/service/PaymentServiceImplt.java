package com.main.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.main.model.Order;
import com.main.response.PaymentResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

@Service
public class PaymentServiceImplt implements PaymentService{
	
	@Value("${strip.api.key}")
	private String stripeSecretKey;

	@Override
	public PaymentResponse createPaymentLink(Order order) throws StripeException {
		// TODO Auto-generated method stub
		Stripe.apiKey = stripeSecretKey;
		
//		 String frontendBaseUrl = "http://localhost:3000";
		String frontendBaseUrl = "https://food-land-git-main-krushna-yadavs-projects.vercel.app";
		
		SessionCreateParams params= SessionCreateParams.builder().addPaymentMethodType(
				SessionCreateParams.
				PaymentMethodType.CARD)
				.setMode(SessionCreateParams.Mode.PAYMENT)
				.setSuccessUrl(frontendBaseUrl+"/payment/success/" + order.getId())
				.setCancelUrl(frontendBaseUrl+"/payment/fail")
				.addLineItem(SessionCreateParams.LineItem.builder()
						.setQuantity(1L).setPriceData(SessionCreateParams.LineItem.PriceData.builder()
								.setCurrency("usd")
								.setUnitAmount((long) order.getTotalPrice() * 100)
								.setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder().setName("Food Land")
										.build())
								 .build()
								
								)
						 .build()
						)
				.build();
		
		
		Session session = Session.create(params);
		
		PaymentResponse res = new PaymentResponse();
		res.setPayment_url(session.getUrl());
				
		
		return res;
	}

	
}
