/*
 * Headwind MDM: Open Source Android MDM Software
 * https://h-mdm.com
 *
 * Copyright (C) 2024 Headwind Solutions LLC (http://h-sms.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hmdm.launcher.privilege;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Defines the API endpoint for the application privilege check.
 * This service communicates with a mocked authorization server.
 */
public interface PrivilegeCheckService {

    /**
     * Sends a request to check if a user has the privilege to launch a specific application.
     * The endpoint is mocked for demonstration purposes.
     *
     * @param request The request body containing the package name and user token.
     * @return A Call object which can be used to asynchronously or synchronously execute the request.
     */
    @POST("/api/v1/check-privilege")
    Call<PrivilegeCheckResponse> checkPrivilege(@Body PrivilegeCheckRequest request);
}
