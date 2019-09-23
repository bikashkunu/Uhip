package com.nj.gov.uhip.uhipproperty;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix="uhip")
@Data
public class UhipProperty {
	private Map<String,String> uhipProperty=new HashMap<String,String>();

}
