package com.gds.dsmatch.service;

import com.gds.dsmatch.service.request.ApiExcelDataSourceRequest;
import com.gds.dsmatch.service.response.ApiDataSourceRowDefinition;
import com.gds.dsmatch.service.response.ApiResponse;
import com.gds.dsmatch.service.response.ApiResponseProcessingResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.ArrayList;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 08/05/2017
 */
public class ExcelDataSourceDefinitionService<T extends ApiExcelDataSourceRequest>
        implements DataSourceTypeDefinitionService<T> {

    private static final Logger LOG = LoggerFactory.getLogger(ExcelDataSourceDefinitionService.class);
    private final ExcelRequestToResponseTransformer transformer;

    public ExcelDataSourceDefinitionService(final ExcelRequestToResponseTransformer transformer) {
        Assert.notNull(transformer, "Mandatory argument 'transformer' is missing.");
        this.transformer = transformer;
    }

    @Override
    public ApiResponse<T> handleRequest(final T request) throws ApiException {

        Assert.notNull(request, "Mandatory argument 'request' is missing.");
        return new ApiResponse<T>() {
            private ApiResponseProcessingResult result;
            @Override
            public ApiDataSourceRowDefinition getApiDataSourceRowDefinition() {
                try {
                    final ApiDataSourceRowDefinition rowDefinition = transformer.transform(request);
                    result = ApiResponseProcessingResult.SUCCESS;
                    return rowDefinition;
                } catch (ApiException e) {
                    LOG.error("API Processing exception:", e);
                    result = ApiResponseProcessingResult.PROCESSING_EXCEPTION;
                    return () -> new ArrayList<>();
                }
            }

            @Override
            public T getApiRequest() {
                return request;
            }

            @Override
            public ApiResponseProcessingResult getProcessingResult() {
                return result;
            }
        };
    }
}