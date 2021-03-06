package com.lab.gc1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

/*import java.util.ArrayList;
import java.util.List;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.OverlayItem;*/

public class MainActivity extends MapActivity {
	//MyLocationOverlay me;
	//MapView map;
	//MyLocationOverlay myLocationOverlay;
	
	 private MapView mapView;
     private MyLocationOverlay myLocationOverlay;

	@Override
    public void onCreate(Bundle savedInstanceData) {
        super.onCreate(savedInstanceData);
        setContentView(R.layout.activity_main);
        

        
        //MapView mapView = (MapView) findViewById(R.id.mapview);
        
        /*final MapView mMapView = (MapView) findViewById(R.id.mapview);
        mMapView.setBuiltInZoomControls(true);
        
     // �������� MapController
     MapController mMapController = mMapView.getController();
      
     // ���������� ����� �� �������� ����������
     mMapController.animateTo(new GeoPoint((int)60.113337, (int)55.151317));
      
     mMapController.setZoom(15);*/
        
        
//----------------------------------------------------------------------------------------------------------------------
        //final MapView 
        mapView = (MapView) findViewById(R.id.mapview);
     // �������� �������� ����������, �������������� ���
        //��������������� �����.
        mapView.setBuiltInZoomControls(true);
        //MapController mapController = mapView.getController();
     // �������� MapController
     // create an overlay that shows our current location
        ArrayList<GeoPoint> pnts = new ArrayList<GeoPoint>();
        //pnts.add(new GeoPoint((int) (55.75219  * 1E6), (int) (37.60836  * 1E6)));// ��������������� ���
        //pnts.add(new GeoPoint((int) (55.80737  * 1E6), (int) (37.63844  * 1E6)));// ������������
        //pnts.add(new GeoPoint((int) (55.89504  * 1E6), (int) (37.58605  * 1E6)));// ���������
        //pnts.add(new GeoPoint((int) (55.581818 * 1E6), (int) (37.594978 * 1E6)));// ������ (���.)
        //pnts.add(new GeoPoint((int) (55.75228  * 1E6), (int) (37.60357  * 1E6)));// ���������
        //pnts.add(new GeoPoint((int) (55.79981  * 1E6), (int) (37.53412  * 1E6)));// ��������
        //pnts.add(new GeoPoint((int) (55.86814  * 1E6), (int) (37.66292  * 1E6)));// ������������
        //pnts.add(new GeoPoint((int) (55.74326  * 1E6), (int) (37.49753  * 1E6)));// ���������������
        //pnts.add(new GeoPoint((int) (55.76027  * 1E6), (int) (37.58111  * 1E6)));// �����������
        //pnts.add(new GeoPoint((int) (55.77228  * 1E6), (int) (37.67857  * 1E6)));// ����������
        //pnts.add(new GeoPoint((int) (55.77378  * 1E6), (int) (37.54412  * 1E6)));// �������
        //pnts.add(new GeoPoint((int) (55.77492  * 1E6), (int) (37.58207  * 1E6)));// �����������
        //pnts.add(new GeoPoint((int) (55.64371  * 1E6), (int) (37.52762  * 1E6)));// �������
        //pnts.add(new GeoPoint((int) (55.88294  * 1E6), (int) (37.60523  * 1E6)));// ��������
        //pnts.add(new GeoPoint((int) (55.75211  * 1E6), (int) (37.60988  * 1E6)));// ���������� ��. ������
        //pnts.add(new GeoPoint((int) (55.60029  * 1E6), (int) (37.55735  * 1E6)));// ���������� ����
        //pnts.add(new GeoPoint((int) (55.75034  * 1E6), (int) (37.60857  * 1E6)));// ����������
        //pnts.add(new GeoPoint((int) (55.84649  * 1E6), (int) (37.63914  * 1E6)));// ������������ ���
        //pnts.add(new GeoPoint((int) (55.631363 * 1E6), (int) (37.75174  * 1E6)));// �������� (���.)
        //pnts.add(new GeoPoint((int) (55.66126  * 1E6), (int) (37.7509   * 1E6)));// �������������
        //pnts.add(new GeoPoint((int) (55.567759 * 1E6), (int) (37.575724 * 1E6)));// ������� ������� �������� (���.)
        pnts.add(new GeoPoint((int) (54.847473 * 1E6), (int) (83.05344 * 1E6)));
        pnts.add(new GeoPoint((int) (47.368491 * 1E6), (int) (8.540389 * 1E6)));
        pnts.add(new GeoPoint((int) (40.730789 * 1E6), (int) (-73.997477 * 1E6)));
        Iterator<GeoPoint> it=pnts.iterator();
        GeoPoint pnt;// = it.next();
        //me=new MyLocationOverlay(this, mapView);
     // create an overlay that shows our current location
        //����������� �������������� ������������ �� ����� Google Map.
        myLocationOverlay = new FixedMyLocationOverlay(this, mapView);
        
        // add this overlay to the MapView and refresh it
        mapView.getOverlays().add(myLocationOverlay);
        mapView.postInvalidate();
        
        // call convenience method that zooms map on our location
        //zoomToMyLocation();
        
        GeoPoint myLocationGeoPoint = myLocationOverlay.getMyLocation();
        if(myLocationGeoPoint != null) {
                mapView.getController().animateTo(myLocationGeoPoint);
                mapView.getController().setZoom(16);
        }
        else {
                Toast.makeText(this, "�� ������� ���������� ��������������", Toast.LENGTH_SHORT).show();
        }
        

        
        List<Overlay> mapOverlays = mapView.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.androidmarker);
        HelloItemizedOverlay itemizedoverlay = new HelloItemizedOverlay(drawable, this);
        OverlayItem overlayitem;
        
        //GeoPoint point = new GeoPoint(19240000,-99120000);
        
        //pnt = me.getMyLocation();
        //overlayitem = new OverlayItem(pnt, null, null);
        //itemizedoverlay.addOverlay(overlayitem);
        
        //LocationManager lm;
        //GeoPoint gp;        

        //lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        //Location lastKnownLoc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        //if (lastKnownLoc != null){
        //  int longTemp = (int)(lastKnownLoc.getLongitude()* 1000000);
        //  int latTemp = (int)(lastKnownLoc.getLatitude() * 1000000);
        //  gp =  new GeoPoint(latTemp, longTemp);
        //}

        //gp = myLocationOverlay.getMyLocation();
        
        //mapController.animateTo(gp);
        //mapController.setZoom(15);
        
        
        while(it.hasNext()) {
        	pnt = it.next();
            overlayitem = new OverlayItem(pnt, null, null);
            itemizedoverlay.addOverlay(overlayitem);
        }
        
       
        mapOverlays.add(itemizedoverlay);
        
        //������������� ����������� ��� ��������� � �������� �� �������� �������.
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        double lat = 54.846324;
        double lng = 83.051402;
        float rad = 100f;
        long expiration = -1;

        Intent intent = new Intent("");
        PendingIntent pIntent = PendingIntent.getBroadcast(this, -1, intent, 0);
        lm.addProximityAlert(lat, lng, rad, expiration, pIntent);
        
       //-----------------
        //����������� �������� � ����������� �������� ������������.
        String provider = LocationManager.GPS_PROVIDER; 

        int t = 5000; // milliseconds
        int distance = 5; // meters

        LocationListener myLocationListener = new LocationListener() {

          public void onLocationChanged(Location location) {
            // Update application based on new location.
          }
         
          public void onProviderDisabled(String provider){
            // Update application if provider disabled.
          }

          public void onProviderEnabled(String provider){
            // Update application if provider enabled.
          }

          public void onStatusChanged(String provider, int status, 
                                      Bundle extras){
            // Update application if provider hardware status changed.
          }
        };

        lm.requestLocationUpdates(provider, t, distance,
                                               myLocationListener);

//--------------------------------------------------------------------------------------------------------------------------------
        
        
        //pnt = it.next();
        //overlayitem = new OverlayItem(pnt, null, null);
        
        //itemizedoverlay.addOverlay(overlayitem);
        //mapOverlays.add(itemizedoverlay);


        
        //GeoPoint point = new GeoPoint((int) (55.75219 * 1E6),
		//		(int) (37.60836 * 1E6));
		
        //mapController.animateTo(pnt);
		//mapController.setZoom(16);
        
		
        //String locService = Context.LOCATION_SERVICE;
        //LocationManager lm = (LocationManager) getSystemService(locService);
        //double lat = 55.75219;
        //double lng = 37.60836;
        //float radius = 100f;
        //long expiration = -1;

        //Intent intent = new Intent();
        //PendingIntent pIntent = PendingIntent.getBroadcast(this, -1, intent, 0);

		//lm.addProximityAlert(lat, lng, radius, expiration, pIntent);

        /*
         * final LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        final LocationListener locationListener = new LocationListener(){
            
            //@Override
            public void onStatusChanged(String provider, int status, Bundle extras) {}
                    
            //@Override
            public void onProviderEnabled(String provider) {}
                    
            //@Override
            public void onProviderDisabled(String provider) {}
                    
            //@Override
            public void onLocationChanged(Location location) {}


        };
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        
     // ������ overlay ��� ����������� ����� �������
        MyLocationOverlay cMyLocationOverlay = new MyLocationOverlay(this, mapView);
        cMyLocationOverlay.disableCompass();
        cMyLocationOverlay.enableMyLocation();
        mapView.getOverlays().add(cMyLocationOverlay);
        
     // �������� ���� ���������� (����� ������������ LocationManager.NETWORK_PROVIDER, ��� ����� ������� ����������� �������)
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        // ���������� ����� �� �������� ����������
        cMapController.animateTo(new GeoPoint((int) location.getLatitude() * 1000000, (int) location.getLongitude() * 1000000));
     
*/
     /*
      * // ������ �������� �������������� � "���������"
     final LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
     final LocationListener locationListener = new LocationListener() {
             
         @Override
         public void onStatusChanged(String provider, int status, Bundle extras) {}
                 
         @Override
         public void onProviderEnabled(String provider) {}
                 
         @Override
         public void onProviderDisabled(String provider) {}
                 
         @Override
         public void onLocationChanged(Location location) {}
     };
     locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

     // ������ overlay ��� ����������� ����� �������
     cMyLocationOverlay = new MyLocationOverlay(this, cMapView);
     cMyLocationOverlay.disableCompass();
     cMyLocationOverlay.enableMyLocation();
     cMapView.getOverlays().add(cMyLocationOverlay);
      
     // ���
     cMapController.setZoomCurrent(15);

     // �������� ���� ���������� (����� ������������ LocationManager.NETWORK_PROVIDER, ��� ����� ������� ����������� �������)
     Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

     // ���������� ����� �� �������� ����������
     cMapController.animateTo(new GeoPoint((int) location.getLatitude() * 1000000, (int) location.getLongitude() * 1000000));*/
//----------------------------------------------------------------------------------------------------------------------        
        
        //map=(MapView)findViewById(R.id.mapview);
        
        //map.getController().setCenter(getPoint(40.76793169992044,
        //                                        -73.98180484771729));
        //map.getController().setZoom(17);
        //map.setBuiltInZoomControls(true);
        
        //Drawable marker=getResources().getDrawable(R.drawable.androidmarker);
        
        //marker.setBounds(0, 0, marker.getIntrinsicWidth(),
        //                        marker.getIntrinsicHeight());
        //http://s.pikabu.ru/images/big_size_comm/2012-11_1/1352008226513.jpg
        //map.getOverlays().add(new SitesOverlay(marker));
        
        //me=new MyLocationOverlay(this, map);
        //map.getOverlays().add(me);    
        
        
        
        
        
        
    }
	
	@Override
    protected void onResume() {
            super.onResume();
            // when our activity resumes, we want to register for location updates
            myLocationOverlay.enableMyLocation();
    }

    @Override
    protected void onPause() {
            super.onPause();
            // when our activity pauses, we want to remove listening for location updates
            myLocationOverlay.disableMyLocation();
    }
    
//    private void zoomToMyLocation() {
//        GeoPoint myLocationGeoPoint = myLocationOverlay.getMyLocation();
//        if(myLocationGeoPoint != null) {
//                mapView.getController().animateTo(myLocationGeoPoint);
//                mapView.getController().setZoom(10);
//        }
//        else {
//                Toast.makeText(this, "Cannot determine location", Toast.LENGTH_SHORT).show();
//        }
//}
	//---------------------
	/*@Override
	  public void onResume() {
	    super.onResume();
	    
	    me.enableCompass();
	  }   
	  
	  @Override
	  public void onPause() {
	    super.onPause();
	    
	    me.disableCompass();
	  }  
	  
	  @Override
	  protected boolean isRouteDisplayed() {
	    return(false);
	  }
	  
	 //@Override
	 // public boolean onKeyDown(int keyCode, KeyEvent event) {
	 //   if (keyCode == KeyEvent.KEYCODE_S) {
	 //     map.setSatellite(!map.isSatellite());
	 //     return(true);
	 //   }
	 //   else if (keyCode == KeyEvent.KEYCODE_Z) {
	 //     map.displayZoomControls(true);
	 //     return(true);
	 //   }
	 //   
	 //   return(super.onKeyDown(keyCode, event));
	 // }

	  private GeoPoint getPoint(double lat, double lon) {
	    return(new GeoPoint((int)(lat * 1E6), (int)(lon * 1E6)));
	  }
	    
	  private class SitesOverlay extends ItemizedOverlay<OverlayItem> {
	    private List<OverlayItem> items=new ArrayList<OverlayItem>();
	    
	    public SitesOverlay(Drawable marker) {
	      super(marker);
	      
	      //boundCenterBottom(marker);
	      
	      items.add(new OverlayItem(getPoint(40.748963847316034,
	                                          -73.96807193756104),
	                                "UN", "United Nations"));
	      items.add(new OverlayItem(getPoint(40.76866299974387,
	                                          -73.98268461227417),
	                                "Lincoln Center",
	                                "Home of Jazz at Lincoln Center"));
	      items.add(new OverlayItem(getPoint(40.765136435316755,
	                                          -73.97989511489868),
	                                "Carnegie Hall",
	              "Where you go with practice, practice, practice"));
	      items.add(new OverlayItem(getPoint(40.70686417491799,
	                                          -74.01572942733765),
	                                "The Downtown Club",
	                        "Original home of the Heisman Trophy"));

	      populate();
	    }
	    
	    @Override
	    protected OverlayItem createItem(int i) {
	      return(items.get(i));
	    }
	    
	    @Override
	    protected boolean onTap(int i) {
	      Toast.makeText(MainActivity.this,
	                      items.get(i).getSnippet(),
	                      Toast.LENGTH_SHORT).show();
	      
	      return(true);
	    }
	    
	    @Override
	    public int size() {
	      return(items.size());
	    }
	  }*/
    //-------------------------
    
	
	@Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}