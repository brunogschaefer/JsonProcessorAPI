package com.mesbro.standardbroker.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class GetProcessor implements Processor{

    public void process(Exchange exchange) throws Exception {
        System.out.println(exchange.getIn().getBody(String.class));
    }
    
}
