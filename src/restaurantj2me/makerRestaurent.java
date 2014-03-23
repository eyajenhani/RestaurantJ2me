package restaurantj2me;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;
import com.jappit.midmaps.googlemaps.GoogleMaps;
import com.jappit.midmaps.googlemaps.GoogleMapsCoordinates;
import com.jappit.midmaps.googlemaps.GoogleMapsMarker;
import com.jappit.midmaps.googlemaps.GoogleStaticMap;
import com.jappit.midmaps.googlemaps.GoogleStaticMapHandler;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Display;

public class makerRestaurent extends Canvas implements GoogleStaticMapHandler
{
	GoogleMaps gMaps = null;
	GoogleStaticMap map = null;
        Displayable testListScreen;
	MIDlet midlet;
	double x = 0.010299549591064 ;
        double y = 0.012292096652901 ;
       public double x1=36.814263;
       public double y1=10.165250;
       
      	public makerRestaurent(MIDlet m, Displayable testListScreen)
	{	gMaps = new GoogleMaps();	
		map = gMaps.createMap(getWidth(), getHeight(), GoogleStaticMap.FORMAT_PNG);
		map.setHandler(this);       
		map.setCenter(new GoogleMapsCoordinates(x1, y1));
		 map.setZoom(15);
		map.update();               
	}
	
	protected void paint(Graphics g)
	{
		map.draw(g, 0, 0, Graphics.TOP | Graphics.LEFT);
	}
        void showError(String message)
	{
		Alert error = new Alert("Error", message, null, AlertType.ERROR);
		
		Display.getDisplay(midlet).setCurrent(error, this);
	}
	public void GoogleStaticMapUpdateError(GoogleStaticMap map, int errorCode, String errorMessage)
	{
		showError("map error: " + errorCode + ", " + errorMessage);
	}
	public void GoogleStaticMapUpdated(GoogleStaticMap map)
	{
		repaint();
	}
	protected void keyPressed(int key)
	{
		int gameAction = getGameAction(key);
		
		if(gameAction == Canvas.UP )
		{
			map.move(gameAction);
                      x1=x1+x;  
		}
                else if(gameAction == Canvas.RIGHT) 
                {
                      map.move(gameAction);
                 y1=y1+y;
                    
                }
                 else if(gameAction == Canvas.DOWN) 
                {
                      map.move(gameAction);
                      x1=x1-x;
                }
                 else if( gameAction == Canvas.LEFT) 
                {
                    map.move(gameAction);
                     y1=y1-y;
                }  
                 else if( gameAction == Canvas.FIRE) 
                {
                   gMaps = new GoogleMaps();
		
		map = gMaps.createMap(getWidth(), getHeight(), GoogleStaticMap.FORMAT_PNG);
		
		map.setHandler(this);
		
		map.setCenter(new GoogleMapsCoordinates(x1, y1));
		
		GoogleMapsMarker redMarker = new GoogleMapsMarker(new GoogleMapsCoordinates(x1, y1));
		redMarker.setColor(GoogleStaticMap.COLOR_BLUE);
		redMarker.setSize(GoogleMapsMarker.SIZE_MID);
		redMarker.setLabel('R');
		
		map.addMarker(redMarker);
		
		map.setZoom(15);
		
		map.update();
                
        }
 
        }

   
      
}