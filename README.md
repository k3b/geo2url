# 🌍📷 Photos Nearby for Android

Show Photos from commons.wikimedia.org that that are near a given geo-location in a webbrowser.

## Uscase: 

Suppose you are going on holiday. Your navigation app shows you a geo-map with your hotel
and you want to see photos that are near this place.

In your navigation app

* select a place in the map (i.e. your hotel)
* open menu "view in external app" (or "send location to" or "share location with" or ...)
* choose "🌍📷 Photos Nearby" as destination 
* => The webbrowser opens with a web page with photos near the selected place.  


![](https://raw.githubusercontent.com/k3b/geo2url/main/fastlane/metadata/android/en-US/images/phoneScreenshots/1-ShareInNaviApp.png)
![](https://raw.githubusercontent.com/k3b/geo2url/main/fastlane/metadata/android/en-US/images/phoneScreenshots/2-PhotosFoundInWebBrowser.png)


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
* Translates the geo data into SPARQL database query
* converts the query into a https web adress
* and asks android to show the web adress in a webbrowser

## Legal stuff

Wikipedia, Wikimedia, Wikidata and Wikivoyage are  [trademarks of the Wikimedia Foundation](https://foundation.wikimedia.org/wiki/Wikimedia_trademarks)

## Donations:

If you like this app please consider to donating to https://donate.wikimedia.org/ .

Since android-developping is a hobby (and an education tool) i donot want any
money for my apps so donation should go to projects i benefit from.

