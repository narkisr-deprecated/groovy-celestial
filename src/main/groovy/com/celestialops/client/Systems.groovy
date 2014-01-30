package com.celestialops.client;
 
import wslite.http.HTTPClientException
import wslite.rest.ContentType

@Mixin(Serviceable) 
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

 def delete(id) {
  delete(path:"systems/${id}")
 }

 def get(id) {
   get(path:"systems/${id}") 
 }

}
