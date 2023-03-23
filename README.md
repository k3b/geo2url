# 🌍📷 Photos Nearby for Android

Show Photos from commons.wikimedia.org that are near a given geo-location in a webbrowser.

## Uscase: 

Suppose you are going on holiday. Your navigation app shows you a geo-map with your hotel
and you want to see photos that are near this place.

In your navigation app

* (1) select a place in the map (i.e. your hotel)
* (2) open menu "view in external app" (or "send location to" or "share location with" or ...)
* (3) choose "🌍📷 Photos Nearby" as destination 
* (4) => The webbrowser opens with a web page with photos near the selected place.  

---

<img src="https://raw.githubusercontent.com/k3b/geo2url/master/fastlane/metadata/android/en-US/images/phoneScreenshots/1-ShareInNaviApp.png" width="180" height="300" /><br/>
(1-3) Inside "navigation app" open selected geo-location with "🌍📷 Photos Nearby"

---

<img src="https://raw.githubusercontent.com/k3b/geo2url/master/fastlane/metadata/android/en-US/images/phoneScreenshots/2-PhotosFoundInWebBrowser.png" width="180" height="340" /><br/>
(4) As a result the webbrowser is opened with photos near the selected geo-location.

Note: You can use [the commons app](https://f-droid.org/packages/fr.free.nrw.commons/) to
upload photos to commons.wikimedia.org.

Note: There is a similar app [AndroidGeo2ArticlesMap](https://f-droid.org/packages/de.k3b.android.geo2articlesmap/)
that shows articles from wikipedia/wikivoyage near the selected location in a geographic map.

## Download

"🌍📷 Photos Nearby" will soon be available here:

[<img src="https://f-droid.org/badge/get-it-on.png" alt="available on F-Droid app store" width="200" >](https://f-droid.org/packages/de.k3b.android.geo2url/)<br/>
[<img src="https://github.com/k3b/geo2url/raw/master/app/src/debug/res/drawable/qr_code_url_geo2url_fdroid.png" alt="available on F-Droid app store" height="200" width="200">](https://f-droid.org/packages/de.k3b.android.geo2url/)

## Compatibility info

The App 

* requires android 4.4 (api 19) or newer
* requires no additional permissions
* has no userinterface of it-s own
* has a minimal memory footprint (apk size is less than 0.2 Megabyte)

You can get geo-infos from these opensource apps

* navigation: [OsmAnd](https://f-droid.org/packages/net.osmand.plus/),
* public transportation: [Transportr](https://f-droid.org/packages/de.grobox.liberario) or [oeffi](https://f-droid.org/packages/de.schildbach.oeffi)
* geo-caching [c:geo](https://apt.izzysoft.de/fdroid/index/apk/cgeo.geocaching)
* translate postal address to geo [Acastus Photon ](https://f-droid.org/packages/name.gdr.acastus_photon)
* Share your current position [LocationShare](https://f-droid.org/packages/ca.cmetcalfe.locationshare) or [My Position](https://f-droid.org/packages/net.mypapit.mobile.myposition)
* and probably many others.......

## Technical Description

Technically speaking the app

* hooks into the android system for view/send/share events for geographic coordinates (also known as [geo: - uri](https://en.wikipedia.org/wiki/Geo_URI_scheme)),
* Translates the geo data into [SPARQL database query](https://github.com/k3b/geo2url/blob/master/app/src/main/java/de/k3b/android/geo2url/photos-nearby.sparql).
* converts the query into a [https web adress](https://query.wikidata.org/).
* and asks android to show the web adress in a webbrowser

## Legal stuff

Wikipedia, Wikimedia, Wikidata and Wikivoyage are  [trademarks of the Wikimedia Foundation](https://foundation.wikimedia.org/wiki/Wikimedia_trademarks)

## Donations:

If you like this app please consider to donating to https://donate.wikimedia.org/ .

Since android-developping is a hobby (and an education tool) i donot want any
money for my apps so donation should go to projects i benefit from.

