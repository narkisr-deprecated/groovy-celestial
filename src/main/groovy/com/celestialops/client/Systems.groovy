package com.celestialops.client;

class Systems {

 def create(system) {
   // assert twitter.head( path : 'public_timeline.json' ).status == 200
 }

 def get(id) {
   Celestial.getInstance().get(path:"systems/${id}", accept: 'application/json')
 }

}
