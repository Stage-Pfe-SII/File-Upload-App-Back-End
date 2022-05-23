package com.sii.fileupload.services;

import com.sii.fileupload.configuration.TemplateEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.StringWriter;

@Service
public class TemplateRenderingImpl implements TemplateRendering {
    @Autowired
    TemplateEngineConfiguration configuration;

    @Override
    public String render(String template, Context context) {
        StringWriter writer = new StringWriter();
        TemplateEngine templateEngine = configuration.templateEngine();

        if(context == null) context = new Context();

        if(template==null) return "null";

        templateEngine.process(template, context, writer);
        return writer.toString();
    }
}
