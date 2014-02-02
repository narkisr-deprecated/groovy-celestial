package com.celestialops.client;
 
import wslite.http.HTTPClientException
import wslite.rest.ContentType

@Mixin(Serviceable) 
class Systems {

 def create(system) {
   try{
     def res = post(path:'systems'){
      type ContentType.JSON 
      json system
     }
     res.json
   } catch (HTTPClientException e) {
     throw new RuntimeException(new String(e.response.data))
   }
 }

 def delete(id) {
   delete(path:"systems/${id}")
 }

 def get(id) {
   get(path:"systems/${id}").json
 }

}
