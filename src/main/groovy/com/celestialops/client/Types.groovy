package com.celestialops.client;
 
import wslite.http.HTTPClientException
import wslite.rest.ContentType

@Mixin(Serviceable) 
class Types {

 def create(t) {
   try{
     post(path:'types'){
      type ContentType.JSON 
      json t
     }
   } catch (HTTPClientException e) {
     throw new RuntimeException(new String(e.response.data))
   }
 }

 def delete(id) {
   delete(path:"types/${id}")
 }

 def get(id) {
   get(path:"types/${id}") 
 }

}
