package in.walkover.chawghadia;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class UpdateTimeService extends Service implements LocationListener {
    StringBuilder strReturnedAddress = null;

    @Override
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        super.onStart(intent, startId);
        Log.i("This is service", "this is service");
    }

    //getting address from geocordinates
    private String getCityName() {
        try {
            Geocoder geocoder = new Geocoder(this);
            strReturnedAddress = new StringBuilder();
            Log.i("Tessting Address", "Inside GetAddress");
            double[] geocordinates = getGeocordinates();
            List<Address> addresses = geocoder.getFromLocation(geocordinates[0], geocordinates[1], 1);
            Log.i("Tessting Addressloop", "" + addresses);
            if (addresses != null) {
                for (int j = 0; j < 1; j++) {

                    Address returnedAddress = addresses.get(j);
                    Log.i("Tessting Address", "" + returnedAddress);
                    strReturnedAddress.append(returnedAddress.getAddressLine(1));
                    Log.i("Tessting Addressloop2", "" + strReturnedAddress);

                    if (strReturnedAddress.toString().contains(",")) {
                        String[] cityname = null;
                        cityname = strReturnedAddress.toString().split(",");
                        strReturnedAddress = new StringBuilder(cityname[0]);

                    }
                }
                Log.d("Current Address", strReturnedAddress.toString());
            }
        } catch (Exception e) {
            Log.i("error", "Error has come");

            strReturnedAddress = new StringBuilder("indore");

            e.printStackTrace();
        }

        return strReturnedAddress.toString();

    }

    public double[] getGeocordinates() {
        double[] geocordinate = {0, 0};
        Log.i("Testing LOcation", "1here");
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
                0, this);

        Location location = locationManager
                .getLastKnownLocation(LocationManager.GPS_PROVIDER);


        if (location != null) {
            Log.i("Testing LOcation", "ifhere");
            geocordinate[0] = location.getLatitude();
            geocordinate[1] = location.getLongitude();
            Toast.makeText(getApplicationContext(), "" + geocordinate[0], Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "" + geocordinate[1], Toast.LENGTH_LONG).show();
//           String lat = Double.toString(latitude);
//                String  lon = Double.toString(longitude);
        } else {
            Log.d("Current Location", "No Location Found");
        }
        return geocordinate;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onLocationChanged(Location location) {
    }

    public void onProviderDisabled(String provider) {
    }

    public void onProviderEnabled(String provider) {
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

//    class getSunRiseTime extends AsyncTask<Integer, Integer, Integer>
//
//    {
//        final int[] sunrise_time = {0};
//
//        @Override
//        protected Integer doInBackground(Integer... params) {
//
//            try {
//                DefaultHttpClient httpClient = new DefaultHttpClient();
//                Log.e("CityNAme", "" + getCityName());
//                String cityName = getCityName();
//                cityName = cityName.replace(" ", "%20");
//                String url = "http://api.dataweave.in/v1/indian_weather/findByCity/?api_key=309932c2e303da3ef6671930dfbece5157694b37&city=" + cityName + "&date=20121113";
//                HttpPost httpPost = new HttpPost(url);
//                HttpResponse httpResponse = httpClient.execute(httpPost);
//                HttpEntity httpEntity = httpResponse.getEntity();
//                is = httpEntity.getContent();
//                if (is != null) {
//                    Log.i("Json", "" + is);
//                } else {
//                    Log.i("EmptyJson", "" + is);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
//                StringBuilder sb = new StringBuilder();
//                String line = null;
//                while ((line = reader.readLine()) != null) {
//                    sb.append(line + "\n");
//                }
//                is.close();
//                json = sb.toString();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Integer integer) {
//
//
//            try {
//                jObj = new JSONObject(json);
//                JSONObject data = jObj.getJSONObject("data");
//                JSONArray a = data.getJSONArray("2012-11-13");
//                JSONObject job = a.getJSONObject(6);
//                String timestring = job.getString("Tommorows Sunrise ");
//                String[] time1 = new String[2];
//                if (timestring.contains(":")) {
//                    time1 = timestring.split(":");
//                } else {
//                    time1 = timestring.split(";");
//                }
//                int n1 = Integer.parseInt(time1[0]);
//                int n2 = Integer.parseInt(time1[1]);
//                sunrise_time[0] = n1 * 60 + n2;
//                Log.i("Time", "" + sunrise_time[0]);
//
//            } catch (JSONException e) {
//                Log.i("JSON Parsor", "Error parsing data" + e.toString());
//            }
//            return sunrise_time[0];
//            super.onPostExecute(integer);
//        }
//    }
}



