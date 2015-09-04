var map;
var info;

document.addEventListener("deviceready", function() {
    var div = document.getElementById("map");
    
    // Initialize the map view
    info = JSON.parse( window.localStorage.getItem("info") );
    console.log( info.length );
    console.log( info );
    map = plugin.google.maps.Map.getMap(div);
    // Wait until the map is ready status.
    map.addEventListener(plugin.google.maps.event.MAP_READY, onMapReady);
}, false);

function onMapReady() {
    myPosition.mapPosition();
    readMarkers.initMarker();
    map.setMapTypeId(plugin.google.maps.MapTypeId.ROADMAP);
}

var dat = {
    origin: null,
    destination: null
};

var myPosition = {
    
    mapPosition: function() {
		"use strict";
		navigator.geolocation.getCurrentPosition( 
			this.onPositionSuccess,
			this.onPositionError, 
			{ maximumAge: 5000, timeout: 5000, enableHighAccuracy: true });
	},
    
	onPositionSuccess: function(position) {
		"use strict";
		var longitude = position.coords.longitude;
		var latitude = position.coords.latitude;
		dat.origin = new plugin.google.maps.LatLng( latitude , longitude );
        map.setCenter( new plugin.google.maps.LatLng( latitude , longitude ) );
		
		map.addMarker(
            {
                icon: 'blue',
                position: dat.origin,
                title: "myPosition"
            }
        );
	},
    
	onPositionError: function(error) {
		"use strict";
		var messaggio = "";
		
		switch (error.code) {
            
			case error.PositionError.PERMISSION_DENIED:
				messaggio = "L'applicazione non Ã¨ autorizzata all'acquisizione della posizione corrente";
				break;
				   
			case error.PositionError.POSITION_UNAVAILABLE:
				messaggio = "Non Ã¨ disponibile la rilevazione della posizione corrente";
				break;
				   
			case error.PositionError.TIMEOUT:
				messaggio = "Non Ã¨ stato possibile rilevare la posizione corrente";
				break;
		} 
		alert(messaggio);
		
	}
};

var readMarkers = {
    
    LatLngBounds: null,
    
    initMarker: function() {
        this.LatLngBounds = new plugin.google.maps.LatLngBounds();
        for( var i = 0 ; i < info.length ; i++ ) {
            this.createMarker( info[i] , i );
        }
        this.fitBounds();
    },
    
    icon: "",
    
    createMarker: function( myMarker , i ) {
        console.log("lat: " + myMarker.lat + "\nlng: " + myMarker.lng );
        if( myMarker.type === "ArtWork" ) {
            this.icon = base64image.artIcon;
        } else {
            this.icon = base64image.museumIcon;
        }
        map.addMarker(
            {
                icon: this.icon,
                position: new plugin.google.maps.LatLng( myMarker.lat , myMarker.lng ),
                disableAutoPan: false,
                placeTitle: myMarker.title,
                placeType: myMarker.type,
                placeImg: myMarker.image,
                placeDesc: myMarker.description,
                placeLat: myMarker.lat,
                placeLng: myMarker.lng,
                'markerClick': function(marker) {
                    readMarkers.markerListener(marker);
                }
            }
        );
        this.LatLngBounds.extend( new plugin.google.maps.LatLng( myMarker.lat , myMarker.lng ) );
    },
    
    markerListener: function( marker ) {
        console.log(marker);
        console.log( marker.get( "placeTitle" ) );
        console.log( marker.get( "placeType" ) );
        console.log( marker.get( "placeDesc" ) );
        dat.destination = new plugin.google.maps.LatLng( marker.get("placeLat") , marker.get("placeLng") );
        document.getElementById("markerInfo").innerHTML = 
            '<div class="closeImg" onclick="markerInfo.close()">'+
                '<img src='+ base64image.closeImg +'>'+
            '</div>'+
            '<div class="divInfo">'+
                '<p id="title">'+ marker.get( "placeTitle" ) +'</p>'+
                '<p>'+ marker.get( "placeType" ) +'<p/>'+
                '<div class="miniImg">'+
                    '<img src="'+ marker.get( "placeImage" ) +'"/>'+
                '</div>'+
                '<p>'+ marker.get( "placeDesc" ) +'</p>'+
                '<p id="path" onClick="drawPath.initMap()">Percorso</p>'+
            '</div>';
        markerInfo.open();
    },
    
    fitBounds: function() {
        map.moveCamera({
            'target': this.LatLngBounds
        });
        console.log( this.LatLngBounds );
    }
};

var drawPath = {
    initMap: function() {
        if (confirm("Do you want to go?")) {
            plugin.google.maps.external.launchNavigation({
                "from": dat.origin,
                "to": dat.destination
            });
        }
    }
};

var markerInfo = {
    isOpen: false,
    
    open: function() {
        /*
        if(menuInfo.isOpen === true) {
            $("#menuInfo").index = 2;
        } else {
            $("#menuInfo").index = 1;
        }
        */
        console.log("markerInfo open");
        document.getElementById("markerInfo").style.visibility = "visible";
        this.isOpen = true;
    },
    
    close: function() {
        console.log("markerInfo close");
        document.getElementById("markerInfo").style.visibility = "hidden";
        this.isOpen = false;
    }
};

var base64image = {
    closeImg: "data:image/png;base64,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",
    
    museumIcon: "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAF9wAABfcB7fylMwAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAAAPkSURBVHic7ZrPixRHFMc/36qeTUSWzYJIIOA14EHwL5AEcww5elATAokBhfwD+S8EBc1CSMwlJw05Cgoi+wfkIHiVBEIuSZCoZHf65bCjzNhVNV0z/WPWni8UzNR78+rVt9979Xq6ZWYMGa5vB/rGmoC+Hegbgyeg6GthSdvgPj/4Vn5nZn/14oiZdTqA0w63I9wz4WwynjncDnC6c3862vQG+PPC705tOjL8LvjzwEYXvqnNPkDSCYf7yuAL4Hjmz/8U7JSUN8zsSRv+Ac1HACAozgrdFm5//hWfO/aFbkNxFg4u2EpGgKQtcJ8JLgPvN2K0iscG16H83sz+acLg0gRIOuVwlw0uAEebcKoG/hX8WFJeN7Nfl7K0YJiPwJ8T/kEDIb7k8A/AnwNGraeApPcc7pLBJeDdpZhvHn8IbpaUN83s97o/qkWANPpAjK+APqHH5qkm9sF+Nvw1s73785SjBEjaBPfppKidbNrLjvBoUjR/MLOnIYUKAZJOOtwVg4vAZhdedoCnglsl5TUzezQtqBDg5J8Db3fpXYd4Udr4yPREIJ/tLrDRkUNd47/XJ1pthQ8DalV0L//tpCYcGghujW385Ty9ukfaCHhrOZc6x6iOUi0CSvTQcbhSpUQP6+gNvgZk/Sfo5H9z8n9PD0ln4vruTlXffxPT9/I3Xtd3cndi+lJxsapf7ObsKbetPU4lt3yiWXLHwLZmZmA7YX8b2Jqdcsfi6rZZ1besP15qR4AkES4sqaYpJIvqGxYqtCn7ufoV5KRA7BRILGhZBITXCNpI+dQ5AUci8zFZSj+0RpP6FTRAQPIKVWSW1HdZV9StSAQ0VgNopgY4SbWaIHgzU2Deb2awNAEuOwJck0Vtkaic9aauYu5ikjYAVSXZVV0TW0v7FEKbKZA7v8gaXaZAkct27jxkXtFI4zRvjRm0eQq0TsAC+hVkEBBm27AVTIGiu1OAhiJgcnYHimbKVrBxSq1dtVBXkSgB0WMtGraTG6ua9tO2wtPJk2YGLZ4CyTAMOZggIGYrWgTbSIHYYjG28+4RSBIQtbUKKbBQsQvJUimQWQSjhbmCPopgTNZgDVjtPiAmW20CIvfeEA3PZBhmpkDUVuwGbZ0CCVszaIKAQpLPc6IIyKL3GkFbkgri/tcmYPAPRqLPBaTRh2L8dZfOtAXDXzXbuxeSJR6M2InJO0FvACz6dGnwr8u/qgHS6CNR/jIl86z+G2F1sQ+MX34x3Mdme3dhdoOew/cOQF0UVPcKrFOAQtI7Bx99V+/5rgDs6Mt9S7hBNwLrFBD81LcTfWLwrfDgU2BNQN8O9I3BE/A/3LUaHOu8+DcAAAAASUVORK5CYII=",
    
    artIcon: "data:image/png;base64,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"
};