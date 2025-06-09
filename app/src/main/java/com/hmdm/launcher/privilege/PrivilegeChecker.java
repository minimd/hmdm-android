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

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.hmdm.launcher.Const;
import com.hmdm.launcher.R;
import com.hmdm.launcher.server.ServerServiceKeeper;

import retrofit2.Response;

/**
 * A utility class to check for user privileges before launching an application.
 * It makes a network request to a mocked API endpoint.
 */
public class PrivilegeChecker {

    /**
     * Callback interface to handle the result of the privilege check.
     */
    public interface Callback {
        void onPrivilegeCheckResult(boolean isAllowed);
    }

    /**
     * Performs the privilege check.
     *
     * @param context     The application context.
     * @param packageName The package name of the app to be launched.
     * @param callback    The callback to be invoked with the result.
     */
    public static void checkPrivilege(Context context, String packageName, Callback callback) {
        new CheckPrivilegeTask(context, packageName, callback).execute();
    }

    private static class CheckPrivilegeTask extends AsyncTask<Void, Void, Boolean> {
        private final Context context;
        private final String packageName;
        private final Callback callback;

        public CheckPrivilegeTask(Context context, String packageName, Callback callback) {
            this.context = context.getApplicationContext();
            this.packageName = packageName;
            this.callback = callback;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            // In a real application, you would retrieve this from a secure source.
            String mockJwtToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

            PrivilegeCheckService service = ServerServiceKeeper.getPrivilegeCheckServiceInstance(context);
            PrivilegeCheckRequest request = new PrivilegeCheckRequest(packageName, mockJwtToken);

            try {
                // Since this is a mocked API, we simulate the network call and response.
                // In a real scenario, you would make a genuine network call.
                // For this example, we'll just return 'true' to simulate a successful check.
                // To test the "denied" flow, simply change this to 'false'.
                Log.d(Const.LOG_TAG, "Simulating privilege check for package: " + packageName);
                // Response<PrivilegeCheckResponse> response = service.checkPrivilege(request).execute();
                // if (response.isSuccessful() && response.body() != null) {
                //     return response.body().isAllowed();
                // }
                return false; // Mocked response
            } catch (Exception e) {
                Log.e(Const.LOG_TAG, "Privilege check failed", e);
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean isAllowed) {
            if (callback != null) {
                callback.onPrivilegeCheckResult(isAllowed);
            }
        }
    }
}
