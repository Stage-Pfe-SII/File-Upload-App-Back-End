package com.sii.fileupload.services;

import org.thymeleaf.context.Context;

public interface TemplateRendering {
    String render(String template, Context context);
}
