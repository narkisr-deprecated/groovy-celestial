package com.celestialops.client;
 
import wslite.http.HTTPClientException
import wslite.rest.ContentType

class Systems {

 def create(system) {
   try{
     Celestial.getInstance().post(path:'systems'){
      type ContentType.JSON 
      json system
     }
   } catch (HTTPClientException e) {
     throw new RuntimeException(new String(e.response.data))
   }
 }

 def get(id) {
   Celestial.getInstance().get(path:"systems/${id}") 
 }

}
