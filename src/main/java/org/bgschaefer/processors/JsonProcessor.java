package org.bgschaefer.processors;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class JsonProcessor implements Processor {

    @ConfigProperty(name = "api.resources.spec")
    private String specFile;

    @Override
    public void process(Exchange exchange) throws Exception {
        String body = exchange.getMessage().getBody().toString();
        List<Object> spec = JsonUtils.jsonToList(this.getClass().getClassLoader().getResourceAsStream("spec.json")); // tratar exceção
        Chainr chainr = Chainr.fromSpec(spec);
        Object input = JsonUtils.jsonToObject(body);
        Object output = chainr.transform(input);
        exchange.getMessage().setBody(JsonUtils.toPrettyJsonString(output));
    }
}
