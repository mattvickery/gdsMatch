package com.gds.dsmatch.service;

import com.gds.dsmatch.service.request.ApiRequest;
import com.gds.dsmatch.service.response.ApiResponse;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 08/05/2017
 */
public interface DataSourceTypeDefinitionService<T extends ApiRequest> {
    ApiResponse handleRequest(final T request) throws ApiException;
}
