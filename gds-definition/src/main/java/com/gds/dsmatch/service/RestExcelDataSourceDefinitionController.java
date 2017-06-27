package com.gds.dsmatch.service;

import com.gds.dsmatch.service.request.ApiExcelDataSourceRequest;
import com.gds.dsmatch.service.request.ApiExcelDataSourceRequestBuilder;
import com.gds.dsmatch.service.response.ApiResponse;

import java.io.File;

import static org.springframework.util.Assert.notNull;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 09/05/2017
 */
public class RestExcelDataSourceDefinitionController {

    private final ExcelDataSourceDefinitionService<ApiExcelDataSourceRequest> service;
    private final ApiExcelDataSourceRequestBuilder builder;

    public RestExcelDataSourceDefinitionController(final ExcelDataSourceDefinitionService<ApiExcelDataSourceRequest> service,
                                                   final ApiExcelDataSourceRequestBuilder builder) {
        notNull(service, "Mandatory argument 'service' is missing.");
        notNull(builder, "Mandatory argument 'builder' is missing.");
        this.service = service;
        this.builder = builder;
    }


    public ApiResponse<ApiExcelDataSourceRequest> handleRequest(final String dataSourceDefinitionName,
                                                                final File dataSourceFile,
                                                                final int sheetIndex,
                                                                final int headerRowIndex,
                                                                final int dataRowIndex) throws ApiException {

        return service.handleRequest(builder.addName(dataSourceDefinitionName).add(dataSourceFile).addSheetIndex(sheetIndex)
                .addHeaderRowIndex(headerRowIndex).addDataRowIndex(dataRowIndex).build());
    }
}