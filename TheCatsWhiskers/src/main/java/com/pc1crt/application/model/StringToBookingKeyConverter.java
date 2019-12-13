package com.pc1crt.application.model;

import java.sql.Date;


import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class StringToBookingKeyConverter implements Converter<String, BookingKey> {
	@Override
	public BookingKey convert(String source) {
		if (source == null) {
			return null;
		}
		String[] data = source.split(",");
		return new BookingKey(Date.valueOf(data[0]), Integer.valueOf(data[1]));
	}
	public StringToBookingKeyConverter() {
		// TODO Auto-generated constructor stub
	}

}
