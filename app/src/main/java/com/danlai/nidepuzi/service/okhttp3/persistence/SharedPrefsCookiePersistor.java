/*
 * Copyright (C) 2016 Francisco José Montiel Navarro.
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

package com.danlai.nidepuzi.service.okhttp3.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import okhttp3.Cookie;

public class SharedPrefsCookiePersistor implements CookiePersistor {

    private final SharedPreferences cookiePrefs1;
    private SharedPreferences sharedPreferences;

    public SharedPrefsCookiePersistor(Context context) {
        final String SHARED_PREFERENCES_NAME = "CookiePersistence";
        cookiePrefs1 = context.getSharedPreferences("CookiesAxiba", Context.MODE_PRIVATE);
        sharedPreferences =
                context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    private static String createCookieKey(Cookie cookie) {
        return (cookie.secure() ? "https" : "http")
                + "://"
                + cookie.domain()
                + cookie.path()
                + "|"
                + cookie.name();
    }

    @Override public List<Cookie> loadAll() {
        List<Cookie> cookies = new ArrayList<>();

        for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
            String serializedCookie = (String) entry.getValue();
            Cookie cookie = new SerializableCookie().decode(serializedCookie);
            cookies.add(cookie);
        }
        return cookies;
    }

    @Override public void saveAll(Collection<Cookie> cookies) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        SharedPreferences.Editor editor1 = cookiePrefs1.edit();
        for (Cookie cookie : cookies) {
            if (cookie.persistent()) {
                editor.putString(createCookieKey(cookie),
                        new SerializableCookie().encode(cookie));

                editor1.putString("cookiesString", cookie.name()
                        + "="
                        + cookie.value()
                        + "; domain="
                        + cookie.domain()
                        + "; path="
                        + cookie.path());
                editor1.putString("cookiesDomain", cookie.domain());
                editor1.putString("Cookie", cookie.name() + "=" + cookie.value());
            }
        }
        editor.apply();
        editor1.apply();
    }

    @Override public void removeAll(Collection<Cookie> cookies) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (Cookie cookie : cookies) {
            editor.remove(createCookieKey(cookie));
        }
        editor.apply();
    }

    @Override public void clear() {
        sharedPreferences.edit().clear().apply();
        cookiePrefs1.edit().clear().apply();
    }
}
