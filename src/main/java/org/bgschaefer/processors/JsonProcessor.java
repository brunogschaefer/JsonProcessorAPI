package org.bgschaefer.processors;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@ApplicationScoped
public class JsonProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String body = exchange.getMessage().getBody().toString();
        System.out.println("teste em java" + body);
        Map<String, Object> map = new HashMap<String, Object>();
        map = new ObjectMapper().readValue(body, new TypeReference<Map<String, Object>>(){});
        Iterator<String> itr = map.keySet().iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
        // processar o json aqui
        exchange.getMessage().setBody(body);
        
    }
    
}
