package com.alexdevmicro.companies_crud.configs;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import io.micrometer.observation.Observation;
import io.micrometer.observation.Observation.Context;
import io.micrometer.observation.ObservationHandler;
import lombok.extern.slf4j.Slf4j;

@Component
@Order(999)
@Slf4j
public class LogObservationHandler implements ObservationHandler<Observation.Context>{

	@Override
	public boolean supportsContext(Context context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onStart(Context context) {
		log.info("LogObservationHandler::onStart: {}",context.getName());
		
	}

	@Override
	public void onError(Context context) {
		// TODO Auto-generated method stub

		log.info("LogObservationHandler::onError: {}",context.getName());
	}

	@Override
	public void onStop(Context context) {
	

		log.info("LogObservationHandler::onStop: {}",context.getName());
	}
	

	
}
