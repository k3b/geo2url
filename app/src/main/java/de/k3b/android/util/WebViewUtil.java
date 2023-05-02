/*
 * Copyright (C) 2011-2023 by k3b
 *
 * This file is part of "🌍📷 Photos Nearby for Android" (https://github.com/k3b/geo2url) and de.k3b.android.toGoZip (https://github.com/k3b/ToGoZip/) .
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
package de.k3b.android.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewUtil {
    public static WebView setHtml(WebView wv, CharSequence html) {
        final WebSettings settings = wv.getSettings();

        // Fix for "Wrong charset in serbian translations" https://github.com/k3b/LocationMapViewer/issues/5
        // (for android 2.2) see http://stackoverflow.com/questions/4933069/android-webview-with-garbled-utf-8-characters
        settings.setDefaultTextEncodingName("utf-8");
        settings.setBuiltInZoomControls(true);

        wv.loadData(html.toString(), "text/html; charset=utf-8", "UTF-8");
        wv.setVerticalScrollBarEnabled(true);

        wv.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        wv.setScrollbarFadingEnabled(false);
        return wv;
    }

    public static AlertDialog.Builder setHtml(AlertDialog.Builder builder, Context context, CharSequence html) {
        final WebView wv = setHtml(new WebView(context), html);
        builder.setView(wv);
        return builder;
    }
}
