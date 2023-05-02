/*
 * Copyright (c) 2023 by k3b.
 *
 *  This file is part of "🌍📷 Photos Nearby for Android" (https://github.com/k3b/geo2url) .
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>
 */

package de.k3b.android.geo2url;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import de.k3b.android.util.WebViewUtil;
import de.k3b.geo.api.GeoPointDto;
import de.k3b.geo.io.GeoUri;

public class ShowPhotosActivity extends Activity {
    private static final String TAG = ShowPhotosActivity.class.getSimpleName();

    /** translated from photos-nearby.sparql where the hardcoded lat/lon will be replaced by the values of the geo uri */
    private static final String URL_TEMPLATE = "https://query.wikidata.org/embed.html#%23%20Photos%20nearby%20hotel%20see%20https%3A%2F%2Fgithub.com%2Fk3b%2Fgeo2url%0A%23defaultView%3AImageGrid%0ASELECT%20%3Fplace%20%3FplaceLabel%20%3FplaceDescription%20%3Flocation%20%3Fimage%20%3Fdist%0AWHERE%20%7B%0A%20%20BIND(%22Point(28.22016%2036.45284%20)%22%5E%5Egeo%3AwktLiteral%20as%20%3Fhotel)%0A%0A%20%20SERVICE%20wikibase%3Aaround%20%7B%0A%20%20%20%20%20%20%3Fplace%20wdt%3AP625%20%3Flocation.%0A%20%20%20%20%20%20bd%3AserviceParam%20wikibase%3Acenter%20%3Fhotel.%0A%20%20%20%20%20%20bd%3AserviceParam%20wikibase%3Aradius%20%221.5%22.%0A%20%20%7D%0A%20%20BIND(geof%3Adistance(%3Fhotel%2C%20%3Flocation)%20as%20%3Fdist)%0A%0A%20%20OPTIONAL%20%7B%0A%20%20%20%20%3Fplace%20wdt%3AP18%20%3Fimage.%0A%20%20%7D%0A%20%20SERVICE%20wikibase%3Alabel%20%7B%20bd%3AserviceParam%20wikibase%3Alanguage%20%22%5BAUTO_LANGUAGE%5D%2Cen%2Cde%22.%20%7D%0A%7D%0AORDER%20BY%20%3Fdist%0ALIMIT%2050%0A";

    /** parameters to be replaced in URL_TEMPLATE */
    private static final String PARAM_LONG = "28.22016";
    private static final String PARAM_LAT = "36.45284";

    private static final String LAST_URI_KEY = "LAST_URI_KEY";
    private static final String LAST_URI_DEFAULT = "geo:52.51,13.35";
    private EditText editUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GeoPointDto geoPoint = getGeoPointDtoFromIntent(getIntent());

        if (geoPoint != null) {
            showResult(geoPoint);
            // keep app invisible
            finish();
            return;
        }

        // if called with no or invalid geo uri show demo gui
        setContentView(R.layout.activity_demo);
        editUri = findViewById(R.id.edit_Uri);
        editUri.setText(loadLastUsedUri());

        this.<Button>findViewById(R.id.run).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onShowResultClick();
            }
        });

        WebView webView = findViewById(R.id.webview);
        webView.setWebViewClient(new CustomWebViewClient());
        WebViewUtil.setHtml(webView, getString(R.string.html));
    }

    private void onShowResultClick() {
        GeoPointDto geoPoint = getGeoPointDtoFromUri(editUri.getText().toString());

        if (geoPoint != null) {
            showResult(geoPoint);
            finishIfEnabled();
        }
    }

    private void finishIfEnabled() {
        if (this.<CheckBox>findViewById(R.id.chk_finish).isChecked()) {
            finish();
        }
    }

    private void showResult(@NonNull GeoPointDto geoPoint) {
        showResult(geoPoint.getLatitude(), geoPoint.getLongitude());
    }

    private void showResult(double lat, double lon) {
        String url = URL_TEMPLATE.replace(PARAM_LONG, "" + lon).replace(PARAM_LAT, "" + lat);
        showResult(url);
    }

    private void showResult(String url) {
        Intent httpsIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
        startActivity(httpsIntent);
    }

    @Nullable private GeoPointDto getGeoPointDtoFromIntent(@Nullable Intent intent) {
        final Uri uri = (intent != null) ? intent.getData() : null;
        return getGeoPointDtoFromUri((uri != null) ? uri.toString() : null);
    }

    @Nullable
    private GeoPointDto getGeoPointDtoFromUri(@Nullable String uriAsString) {
        if (uriAsString != null) {
            showMessage("Using geo-uri:  " + uriAsString);

            GeoUri parser = new GeoUri(GeoUri.OPT_PARSE_INFER_MISSING);
            GeoPointDto pointFromIntent = parser.fromUri(uriAsString, new GeoPointDto());
            if (pointFromIntent != null && !GeoPointDto.isEmpty(pointFromIntent)) {
                saveLastUsedUri(uriAsString);
                return pointFromIntent;
            }
        }
        showMessage("no geo-info found in:  " + uriAsString);
        return null;
    }

    private void saveLastUsedUri(String uriAsString) {
        final SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        prefs.edit().putString(LAST_URI_KEY, uriAsString).apply();
    }

    private String loadLastUsedUri() {
        final SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        return prefs.getString(LAST_URI_KEY, LAST_URI_DEFAULT);
    }

    private void showMessage(String msg) {
        Log.i(TAG, getString(R.string.app_name) + ": " + msg);
        Toast.makeText(this, msg,
                Toast.LENGTH_LONG).show();
    }

    //web view client implementation
    private class CustomWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            showMessage("show url:  " + url);
            saveLastUsedUri(url);
            showResult(url);
            finishIfEnabled();
            //return true if this method handled the link event
            //or false otherwise
            return true;
        }
    }
}